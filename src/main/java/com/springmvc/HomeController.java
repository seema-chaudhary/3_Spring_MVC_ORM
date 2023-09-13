package com.springmvc;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import com.springmvc.entity.User;
import com.springmvc.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/home")
	public String home(Model m)
	{
		m.addAttribute("name", "Seema");
		m.addAttribute("id", 101);
		
		return "home";
	}

		
	@RequestMapping("/register")
	public String signup()
	{		
		return "register";
	}
	
	@RequestMapping(path="/createUser", method= RequestMethod.POST)
	public String registerUser(@ModelAttribute User user, @RequestParam("img") String img,Model m )
	{
		user.setImage(img);
		userService.saveUser(user);
		System.out.println(user);
		System.out.println("Thanku...");
		return "success";
	}
	
//	Redirection Way-1
	@RequestMapping("/google1")
	public String redirectPage()
	{		
		return "redirect:https://www.google.com";
	}
	
	
//	Redirection Way-2
	@RequestMapping("/google2")
	public RedirectView redirectPage1()
	{		
		RedirectView redview = new RedirectView();
		redview.setUrl("https://www.google.com");
		return redview;
	}

	
//	Search Engine
	@RequestMapping(path="/search", method=RequestMethod.POST)
	public String search(@RequestParam("keyword") String keyword)
	{		
		String url="https://www.google.com/search?q="+keyword;
		return "redirect:"+url;
	}
	
	
////	@PathVariable Example for single Value
//	@RequestMapping("/user/{id}")
//	public String search(@PathVariable("id")int id)
//	{		
//		System.out.println("Id = "+ id);
//		return "home";
//	}
	
//	@PathVariable Example for double Value
	@RequestMapping("/user/{id}/{name}")
	public String search(@PathVariable("id")int id, @PathVariable String name)
	{		
		System.out.println("Id = "+ id);
		System.out.println("Name = "+ name);
		return "home";
	}
	
	
}