package com.restapi.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.restapi.model.ApiVO;
import com.restapi.service.ApiProviderService;

import go.kr.dsp.api.ChecksumModule;
import go.kr.dsp.api.CryptoModule;



@RestController
public class ApiConsumerController {
	@Resource
	private ApiProviderService apiService;

	ApiVO user;
	
	private static HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK " + "695c206ec549d13455fd2e0b582ea69a");
		//headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_FORM_URLENCODED));

		return headers;
	}

	@GetMapping(value = "/encryptdecrypt")
	public void encryptdecrypt(){
		System.out.println("encryptdecrypt***");
			
		String message = "Temporary Message for Checksum";
		System.out.println("message "+message);
		ChecksumModule checksumModule = new ChecksumModule();
		String checksumResult = checksumModule.checksum(message);
		System.out.println("checksumResult "+checksumResult);

		// Encrypt, Decrypt
		String strKey = "b7Rz1cawP9ws1RJQARK5wbiqHOQnTLxErPQVQMKeBnY=";
		String data = "Temporary Message for Encrypt";
	
		CryptoModule cryptoModule = new CryptoModule();
		String encryptResult = cryptoModule.encrypt(strKey,data);
		System.out.println("encryptResult "+encryptResult);
		String decryptResult = cryptoModule.decrypt(strKey, encryptResult);
		System.out.println("decryptResult "+decryptResult);
	}
	
	// 회원 정보및 예약정보 조회
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping(value = "/consumerSearch")
	public ResponseEntity<?> cgetUserinfoById(@RequestParam String id){
		System.out.println("consumer | cgetUserinfoById | request: get | id " + id);
		
		
		
		RestTemplate template = new RestTemplate();

		//RestAPI 준비및 요청 https://devtalk.kakao.com/t/400-bad-request/47373/8
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("bodykey", "bodyvalue");
            
        HttpEntity<Object> request = new HttpEntity<Object>(body, getHeaders());
        ResponseEntity<Map> response = template.exchange("http://localhost:8081/responderSearch", HttpMethod.POST, request, Map.class);
        
        //Map readyResp = response.getBody();
        System.out.println("readyResp | " + response);
        
        //오류방지
        user = apiService.getUserinfoById(id);
		System.out.println("user " + user);
		if (user == null) {
			user = apiService.getOnlyUserinfoById(id);
		}

		return new ResponseEntity(user, HttpStatus.OK);
	}

	// 예약하기
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping(value = "/cinsert")
	public ResponseEntity<?> reservation(@RequestBody ApiVO messageBody)
			throws ParseException, JsonProcessingException {
		System.out.println("reservation | request: post");
		System.out.println("messageBody |" + messageBody);
		apiService.reservation(messageBody);

		user = apiService.getUserinfoById(messageBody.getId());

		// 1.set header
		/*HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
		headers.set("헤더이름", "값");

		// 2. set body
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("키", "값");

		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(user);

		body.add("user", jsonString);

		HashMap<String, Object> result = new HashMap<>();

		result.put("header", headers);
		result.put("body", body);*/

		return new ResponseEntity(user, HttpStatus.OK);
	}

	// 예약정보수정하기
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping(value = "/cmodify")
	public ResponseEntity modify(@RequestHeader Map<String, String> header, @RequestBody ApiVO messageBody)
			throws JsonProcessingException {
		System.out.println("modify | request: post");
		System.out.println("messageBody |" + messageBody);
		System.out.println("getuserid |" + messageBody.getId());
		apiService.modify(messageBody);
		/*ApiVO resinfo = apiService.getReservationInfo(messageBody.getInsp_rsvt_no());

		// 1.set header
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
		headers.set("헤더이름", "값");

		// 2. set body
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("키", "값");

		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(resinfo);

		body.add("user", jsonString);

		HashMap<String, Object> result = new HashMap<>();

		result.put("header", headers);
		result.put("body", body);*/

		return new ResponseEntity(user, HttpStatus.OK);
	}

	// 예약정보삭제하기
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping(value = "/cdelete")
	public ResponseEntity delete(@RequestHeader Map<String, String> header, @RequestBody ApiVO messageBody) throws JsonProcessingException {
		System.out.println("delete | request: post");
		System.out.println("messageBody |" + messageBody);
		apiService.delete(messageBody);
		/*user = apiService.getUserinfoById(messageBody.getId());

		// 1.set header
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
		headers.set("헤더이름", "값");

		// 2. set body
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("키", "값");

		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(user);

		body.add("user", jsonString);

		HashMap<String, Object> result = new HashMap<>();

		result.put("header", headers);
		result.put("body", body);*/

		return new ResponseEntity(user, HttpStatus.OK);
	}

}
