package com.kiwoom.demo.home.controller;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Controller
@RequestMapping("/api")
public class UrlController {
	
	@Autowired
	private RequestMappingHandlerMapping handlerMapping;
	
	/**
	 * @param request
	 * @return
	 */
	@RequestMapping("/getAllRequestUrl")
	@ResponseBody
	public List<HashMap<String, Object>> getAllRequestUrl(HttpServletRequest request){
		List<HashMap<String, Object>> mappingInfoList = new ArrayList<HashMap<String, Object>>();
		
		RequestMappingInfo requestMappingInfo = null;
		
		Set<String> patterns;
		Set<RequestMethod> methods;
		
		Object[] urlArr = null;
		Object[] methodArr = null;
		
		String controllerName, url, method;
		
		Map<RequestMappingInfo, HandlerMethod> handlerMap = handlerMapping.getHandlerMethods();
		Iterator<RequestMappingInfo> it = handlerMap.keySet().iterator();
		
		while(it.hasNext()) { 
			requestMappingInfo = it.next();
			
			patterns = requestMappingInfo.getPatternsCondition().getPatterns();
			methods = requestMappingInfo.getMethodsCondition().getMethods();
			
			if(!patterns.isEmpty()) { 
				urlArr = patterns.toArray();
				
				if(!methods.isEmpty()) {
					methodArr = methods.toArray();
				}else {
					methodArr[0] = "";
				}
				
				if(urlArr.length == 1) { 
					HashMap<String,Object> map = new HashMap<String,Object>(); 
					
					// Controller Name
					controllerName = (String) handlerMap.get(requestMappingInfo).getBean();
					
					// URL
					url = (String) urlArr[0];
					
					// HTTP Request Method
					method = methodArr[0].toString();
					
					map.put("controller", controllerName);
					map.put("url", url);
					map.put("httpMethod", method);
					
					mappingInfoList.add(map);
				} 
			}
		}	
			
		return mappingInfoList;
	}
}
