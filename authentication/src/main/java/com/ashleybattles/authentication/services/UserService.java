package com.ashleybattles.authentication.services;



import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.ashleybattles.authentication.models.LoginUser;
import com.ashleybattles.authentication.models.User;
import com.ashleybattles.authentication.repos.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;
	
	//register and login
	
    // TO-DO: Write register and login methods!
    public User register(User newUser, BindingResult result) {
        // TO-DO: Additional validations!
    	if(userRepo.findByEmail(newUser.getEmail()).isPresent()) {
    		result.rejectValue("email", "Unique", "Email already taken!");
    	}
    	//if they dont match
    	if(!newUser.getPassword().equals(newUser.getConfirm())) {
    		result.rejectValue("confirm", "Matches", "Must match password and confirm password!");
    	}
    	if(result.hasErrors()) {
    		return null;
    	}
    	//hash password
        String hashBrowns = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
        newUser.setPassword(hashBrowns);
        
        return userRepo.save(newUser);
    }
    public User login(LoginUser newLogin, BindingResult result) {
        // TO-DO: Additional validations!
    	if(result.hasErrors()) { //checks for credentials
    		return null;
    	}
    	Optional<User>optUser=userRepo.findByEmail(newLogin.getEmail());
    	if(!optUser.isPresent()) {
    		result.rejectValue("email", "Unique", "Invalid Credentials");
    		return null;
    	}
    	User user=optUser.get();
    	if(!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
    		result.rejectValue("password", "Matches", "Invalid Credentials");
    		return null;
    	
    	}
    	
        return user;
    }
    
    //read
    public User getOne(Long id) {
    	return userRepo.findById(id).orElse(null);
    }
    
    
}
