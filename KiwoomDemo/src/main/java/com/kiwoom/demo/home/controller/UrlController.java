package com.kiwoom.demo.home.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Controller
@RequestMapping("/api")
public class UrlController {
	
	@Autowired
	private RequestMappingHandlerMapping handlerMapping;
	
	@RequestMapping("/getAllRequestUrl")
	@ResponseBody
	public List<HashMap<String, Object>> getAllRequestUrl(HttpServletRequest request){
		List<HashMap<String, Object>> mappingInfoList = new ArrayList<HashMap<String, Object>>();
		
		RequestMappingInfo requestMappingInfo = null; 
		Set<String> patterns; 
		Object[] sArr; 
		String url, beanName;
		
		Map<RequestMappingInfo, HandlerMethod> handlerMap = handlerMapping.getHandlerMethods();
		Iterator<RequestMappingInfo> it = handlerMap.keySet().iterator();
		
		while(it.hasNext()) { 
			requestMappingInfo = it.next(); 
			patterns = requestMappingInfo.getPatternsCondition().getPatterns();
			
			if(!patterns.isEmpty()) { 
				sArr = patterns.toArray(); 
				if(sArr.length == 1) { 
					HashMap<String,Object> map = new HashMap<String,Object>(); 
					
					// annotaion에 지정된 URL 값
					url = (String) sArr[0];
					
					// URL이 지정되어있는 컨트롤러 이름
					beanName = (String) handlerMap.get(requestMappingInfo).getBean();
					
					map.put("controller", beanName);
					map.put("url", url);
					
					mappingInfoList.add(map);
				} 
			}
		}	
			
		return mappingInfoList;
	}
}
