package mk.ukim.finki.nsi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import mk.ukim.finki.nsi.model.User;
import mk.ukim.finki.nsi.service.SecurityService;
import mk.ukim.finki.nsi.service.UserService;
import mk.ukim.finki.nsi.validator.UserValidator;

@Controller
public class DMSController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;
    
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
    	model.addAttribute("userForm", new User());
    	
    	return "registration";
    }
    
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
    	
    	userValidator.validate(userForm, bindingResult);
    	if(bindingResult.hasErrors())
    		return "registration";
    	
    	userService.save(userForm);
    	securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());
    	
    	return "redirect:/welcome";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
    	if(error != null) 
    		model.addAttribute("error", "Your username and password is invalid.");
    	
    	if(logout != null)
    		model.addAttribute("message", "You have been logged out successfully.");
    	
    	return "login";
    }
    
    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
}
	
	@RequestMapping("/welcome")
	public ModelAndView helloworld() {
		String message = "<br><div style='text-align:center;'>"
				+ "<h3>********** Hello World, Spring MVC Tutorial</h3>This message is coming from CrunchifyHelloWorld.java **********</div><br><br>";
		return new ModelAndView("welcome", "message", message);
	}

}
