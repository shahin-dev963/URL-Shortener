package com.url.shortner.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.url.shortner.entity.ShortenUrl;

@RestController
@RequestMapping("/api/v1")
public class UrlShorterRestController {

	private Map<String, ShortenUrl> shortenUrlList = new HashMap<>();
	
	@PostMapping("/shorturl")
	public ResponseEntity<Object> getShortenUrl(@RequestBody ShortenUrl shortenUrl) {
		String randomChar = getRandomChars();
		setShortUrl(randomChar, shortenUrl);
		return new ResponseEntity<Object>(shortenUrl, HttpStatus.OK);
	}
	
	@GetMapping("/shorturl/{randomstring}")
	public void getFullUrl(HttpServletResponse response, @PathVariable("randomstring") String randomString) throws IOException {
		response.sendRedirect(shortenUrlList.get(randomString).getFull_url());
	}
	

	private void setShortUrl(String randomChar, ShortenUrl shortenUrl)  {
		 shortenUrl.setShort_url("http://localhost:8080/api/v1/shorturl/"+randomChar);
		 shortenUrlList.put(randomChar, shortenUrl);
	}

	private String getRandomChars() {
		String randomStr = "";
		String possibleChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		for (int i = 0; i < 5; i++)
			randomStr += possibleChars.charAt((int) Math.floor(Math.random() * possibleChars.length()));
		return randomStr;
	}
		
}