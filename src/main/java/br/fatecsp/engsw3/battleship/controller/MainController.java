package br.fatecsp.engsw3.battleship.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping( value={"/","/index"} )
	public String index(){
		return "index";
	}
    
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/error")
	public String error() {
		return "error";
	}
    
    @GetMapping("/welcome")
    public String welcome() {
    	return "welcome";
    }
    
    @GetMapping("/admin")
    public String admin() {
    	return "admin";
    }

}
