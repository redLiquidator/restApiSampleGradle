package com.restapi.controller;

import java.text.ParseException;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.restapi.model.ApiVO;
import com.restapi.service.ApiProviderService;

//github token | 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
//@Controller // @Controller + @ResponseBody
@RestController
public class ApiProviderController {
	
	@Resource
	private ApiProviderService apiService;
	ApiVO user;

	@GetMapping("/front")
	public ModelAndView front() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("front");

		return mav;
	}
	
	// 회원 정보및 예약정보 조회
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping(value = "/search")
	public ResponseEntity<?> getUserinfoById(@RequestParam String id) {
		System.out.println("getUserinfoById | request: get | id " + id);
		
		user = apiService.getUserinfoById(id);
		System.out.println("user " + user);
		if (user == null) {
			user = apiService.getOnlyUserinfoById(id);
		}

		return new ResponseEntity(user, HttpStatus.OK);

	}

	// 예약하기
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping(value = "/insert")
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
	@PostMapping(value = "/modify")
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
	@PostMapping(value = "/delete")
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
