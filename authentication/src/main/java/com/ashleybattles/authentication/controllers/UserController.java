package com.ashleybattles.authentication.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ashleybattles.authentication.models.LoginUser;
import com.ashleybattles.authentication.models.User;
import com.ashleybattles.authentication.repos.UserRepo;
import com.ashleybattles.authentication.services.UserService;

@Controller
public class UserController {
	
	//bring in service
	@Autowired
	private UserService userServ;
	
	@Autowired 
	private UserRepo userRepo;
	
	//display 
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		
		return "index.jsp";
	}
	
	@GetMapping("/home")
	public String home(Model model, HttpSession session ) {
		if(session.getAttribute("uuid")==null) {
			return "redirect:/";
		}
		
		model.addAttribute("user",userServ.getOne((Long)session.getAttribute("uuid")));
		return "home.jsp";
	}
	
	
	
	//action
	
	@PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, 
            BindingResult result, Model model, HttpSession session) {
		User user = userServ.register(newUser, result);
        
        // TO-DO Later -- call a register method in the service 
        // to do some extra validations and create a new user!
        
        if(result.hasErrors()) {
            // Be sure to send in the empty LoginUser before 
            // re-rendering the page.
            model.addAttribute("newLogin", new LoginUser());
            return "index.jsp";
        }
        
        // No errors! 
        // TO-DO Later: Store their ID from the DB in session, 
        // in other words, log them in.
        
        session.setAttribute("uuid", user.getId());
    
        return "redirect:/home";
    }
	
	
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {
    	User user = userServ.login(newLogin, result);
        // Add once service is implemented:
        // User user = userServ.login(newLogin, result);
    
        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index.jsp";
        }
    
        // No errors! 
        // TO-DO Later: Store their ID from the DB in session, 
        // in other words, log them in.
        session.setAttribute("uuid", user.getId());
    
        return "redirect:/home";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.removeAttribute("uuid");
    	return "redirect:/";
    }
 

}
