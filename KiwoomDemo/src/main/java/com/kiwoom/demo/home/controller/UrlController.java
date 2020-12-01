package com.kiwoom.demo.home.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import com.kiwoom.demo.common.StringUtil;

@Controller
@RequestMapping("/api")
public class UrlController {
	
	@Autowired
	private RequestMappingHandlerMapping requestMappingHandlerMapping;
	
	@RequestMapping("/getAllRequestUrl")
	public String getAllRequestUrl(Model model){
		
		List<Map<String, String>> lists = new ArrayList<Map<String, String>>();
		
		Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
		for (Entry<RequestMappingInfo, HandlerMethod> elem : map.entrySet()) {
			Map<String, String> item = new HashMap<String, String>();
			StringBuffer sb = new StringBuffer();
			
			RequestMappingInfo key = elem.getKey();
			HandlerMethod method = elem.getValue();
			
			item.put("url", StringUtil.nvl(key.getPatternsCondition().getPatterns().toArray()[0]));
			item.put("class", method.getMethod().getDeclaringClass().getSimpleName());
			item.put("method", method.getMethod().getName());
			
			Set<RequestMethod> methods = key.getMethodsCondition().getMethods();
			if (!methods.isEmpty()) {
				item.put("request", StringUtil.nvl(methods.toArray()[0]));
			} else {
				item.put("request", "");
			}
			
			Description desc = method.getMethodAnnotation(Description.class);
			if (desc != null) {
				item.put("desc", desc.value());
			} else {
				item.put("desc", "");
			}
			
			for (MethodParameter param : method.getMethodParameters()) {
				sb.append(param.getParameterType().getSimpleName()).append(", ");
			}
			
			if (sb.toString().length() > 0) {
				item.put("param", sb.toString().substring(0, sb.toString().length() - 2));
			} else {
				item.put("param", "");
			}
			
			item.put("rtn", StringUtil.nvl(method.getMethod().getReturnType().getSimpleName()));
			
			/*
			AuditMenu auditMenu = method.getMethod().getDeclaringClass().getAnnotation(AuditMenu.class);
			if (auditMenu != null) {
				item.put("menuId", auditMenu.value().getMenuId());
			}
			*/
			
			/*
			AuditOperation operation = method.getMethodAnnotation(AuditOperation.class);
			if (operation != null) {
				item.put("operation", operation.value().getOperation());
			}
			*/
			
			lists.add(item); 
		}
		
		model.addAttribute("lists", lists);
		
		return "/api/urllist";
	}
}
