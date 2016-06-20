package br.com.radio.service;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class EmailService {

	
	
	public void enviar(){
		
		RestTemplate template = new RestTemplate();
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		
		HttpHeaders headers = new HttpHeaders();
//		headers.add( headerName, headerValue );


//		http://stackoverflow.com/questions/35673298/mailgun-api-sending-inline-image-with-springs-resttemplate
	}
}
