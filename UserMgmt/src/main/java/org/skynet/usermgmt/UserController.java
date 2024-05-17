package org.skynet.usermgmt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @GetMapping({"/", "/Home", "/home"})
    public String home() {
        return "Home";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
//        if (!model.containsAttribute("users")) {
//            model.addAttribute("users", new Users());
//        }
        return "register";  // Name of the HTML file
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String firstName, @RequestParam String lastName,
                               @RequestParam String email, @RequestParam String nationality,
                               @RequestParam Long mobileNumber, @RequestParam String password,
                               @RequestParam Long ssn, @RequestParam Date dateOfBirth, Model model, RedirectAttributes redirectAttributes) {
        try {
            Users newUser = userService.createUser(firstName, lastName, email, nationality, mobileNumber, password, dateOfBirth, ssn);

            if(newUser == null) {
//                model.addAttribute("error", "Error creating user: User details might be already in use or invalid.");
                redirectAttributes.addFlashAttribute("error", "Error creating user: User details might be already in use or invalid.");
                return "redirect:/register";  // Stay on the registration page if there's an error
            }

            redirectAttributes.addFlashAttribute("user", newUser);  // Correct use of flash attributes
            return "redirect:/registrationSuccess";
        }
        catch (DuplicateKeyException e) {
            String errorMessage = parseDuplicateKeyError(e.getMessage());
            redirectAttributes.addFlashAttribute("error", errorMessage);
//            model.addAttribute("error", errorMessage);
            return "redirect:/register";
        }
        catch (Exception e) {
//            model.addAttribute("error", "Error creating user: " + e.getMessage());
            redirectAttributes.addFlashAttribute("error", "Error creating user: " + e.getMessage());
            return "redirect:/register";  // Stay on the registration page if there's an error
        }
    }

    @GetMapping("/registrationSuccess")
    public String registrationSuccess(Model model) {
        return "registrationSuccess";
    }

    private String parseDuplicateKeyError(String errorMessage) {
        if (errorMessage.contains("email")) {
            return "The email is already in use.";
        } else if (errorMessage.contains("SSN")) {
            return "The SSN is already in use.";
        } else if (errorMessage.contains("mobile_number")) {
            return "The mobile number is already in use.";
        }
        return "Duplicate entry found";
    }

}

