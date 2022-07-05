package com.tp.surveysystem.surveysystemdemo.gateway;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Base64;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class HeaderFactory {

	private HttpHeaders headers;
	
	public HeaderFactory createBasicAuthenticationHeader(String userId, String password) {
		String authDetails = userId + ":" + password;
		byte[] authDetailsEncoded = Base64.getEncoder()
									.encode(authDetails.getBytes(Charset.forName("US-ASCII")));
		String authHeaderValue = "Basic " + new String(authDetailsEncoded);
		
		headers = new HttpHeaders();
		headers.add("Authorization", authHeaderValue);
		return this;
	}
	
	public HeaderFactory setJSONAccept() {
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		return this;
	}
	
	public HeaderFactory setJSONContentType() {
		headers.setContentType(MediaType.APPLICATION_JSON);
		return this;
	}
	
	public HttpHeaders build() {
		return headers;
	}
}
