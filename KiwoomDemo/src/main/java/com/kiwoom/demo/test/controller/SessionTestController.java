package com.kiwoom.demo.test.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.kiwoom.demo.test.vo.Event;

@Controller
@SessionAttributes("event")
public class SessionTestController {
	
	@GetMapping("/events/form/name")
	public String eventsFormName(Model model) {
		model.addAttribute("event", new Event());
		return "/events/form-name";
	}
	
	@PostMapping("/events/form/name")
	public String eventsFormNameSubmit(@Validated @ModelAttribute Event event, 
			BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "/events/form/name";
		}
		
		return "redirect:/events/form/limit";
	}
	
	@GetMapping("/events/form/limit")
	public String eventsFormLimit(@ModelAttribute Event event, Model model) {
		model.addAttribute("event", event);
		return "/events/form-limit";
	}
	
	@PostMapping("/events/form/limit")
	public String eventsFormLimitSubmit(@Validated @ModelAttribute Event event, 
			BindingResult bindingResult, SessionStatus sessionStatus, HttpSession httpSession) {
		if(bindingResult.hasErrors()) {
			return "/events/form/limit";
		}
		
		Event sessionEvent = (Event)httpSession.getAttribute("event");
		System.out.println("sessionEvent : " + sessionEvent);
		
		sessionStatus.setComplete();
		
		System.out.println("session is completed : " + sessionStatus.isComplete());
		
		return "redirect:/events/list";
	}
	
	@GetMapping("/events/list")
	public String eventsList(@ModelAttribute Event event, Model model, SessionStatus sessionStatus) {
		
		System.out.println("session is completed2 : " + sessionStatus.isComplete());
		
		model.addAttribute("event", new Event());
		return "/events/form-list";
	}
}
