package com.restapi.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.model.ApiVO;
import com.restapi.service.ApiProviderService;

//github token | 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
//@Controller // @Controller + @ResponseBody
@RestController
public class ApiResponderController {
	
	@Resource
	private ApiProviderService apiService;
	ApiVO user;
	
	// 회원 정보및 예약정보 조회
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping(value = "/responderSearch", consumes = {"application/x-www-form-urlencoded"})
	public ResponseEntity<?> rgetUserinfoById(@RequestHeader Map<String, String> header, MultiValueMap<String, String> map) {
		System.out.println("responder | rgetUserinfoById | request: get | result " +map);
		
		String decryptedid = apiService.decrypt("sldkslkfj");
		
		user = apiService.getUserinfoById(decryptedid);
		
		System.out.println("user | " + user);
		if (user == null) {
			user = apiService.getOnlyUserinfoById(decryptedid);
		}

		return new ResponseEntity(user, HttpStatus.OK);

	}

	

}
