package com.sandiso.banking.system.controller;

import com.sandiso.banking.system.model.User;
import com.sandiso.banking.system.service.UserService;
import com.sandiso.banking.system.validation.UserRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class UserController {

    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public UserRegistration userRegistration(){
        return new UserRegistration();
    }

    @GetMapping
    public String showRegistrationForm(Model model){
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user")@Valid UserRegistration userRegistration,
                                      BindingResult result){

        User existingUser = userService.findByEmail(userRegistration.getEmail());
        if (existingUser != null){
            result.rejectValue("email", null,"There is already an account registered with that email");
        }

        if (result.hasErrors()){
            return "registration";
        }

        userService.save(userRegistration);
        return "redirect:/registration?success";
    }
}
