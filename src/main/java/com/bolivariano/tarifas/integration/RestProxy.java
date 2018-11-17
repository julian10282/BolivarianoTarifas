package com.bolivariano.tarifas.integration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

@Component("restIntegrationProxy")
public class RestProxy {
	
	private static final Logger logger = LoggerFactory.getLogger(RestProxy.class);
	
	public ResponseEntity<Object> sendRequest(RequestMethod requestMethod, String urlBase, Map<String, String> uriParams, Map<String,String> queryParams, Map<String, String> headers, String body) {
		logger.info("Sending request: [requestMethod={}][urlBase={}][uriParams={}][queryParams={}][headers={}]", requestMethod,urlBase,uriParams,queryParams,headers);
		
		try {
			
			String urlString = addParamsByMethod(urlBase, uriParams, queryParams);
			
		    URL url = new URL(urlString);
		    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		    
		    setHeaders(headers, connection);
		    
		    connection.setRequestMethod(requestMethod.name());
		    
		    if (body != null && !body.trim().isEmpty()) {
		    	connection.setDoOutput(true);
				OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
				osw.write(body);
				osw.flush();
				osw.close();
			}
		    
		    logger.info("Sending '{}' request to URL : {}", requestMethod, urlString);
		    int responseCode = connection.getResponseCode();
		    
		    BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		    String line;
		    
		    StringBuilder result = new StringBuilder();
		    while ((line = rd.readLine()) != null) {
		       result.append(line);
		    }
		    rd.close();
		    
		    logger.info("Received WS response: [responseCode={}][result={}]",responseCode ,result);
		    
		    return new ResponseEntity<>(result.toString(), HttpStatus.OK);
		    
		} catch (MalformedURLException e) {
			logger.error("MalformedURLException: " ,e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (IOException ex) {
			logger.error("IOException: ",ex);
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	private void setHeaders(Map<String, String> headers, HttpURLConnection connection) {
		if (headers != null && !headers.isEmpty()) {
			for (Map.Entry<String, String> header : headers.entrySet()) {
				connection.setRequestProperty(header.getKey(), header.getValue());
			}
		}
	}


	private String addParamsByMethod(String urlBase, Map<String, String> uriParams, Map<String, String> queryParams) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(urlBase);
		if (queryParams != null && !queryParams.isEmpty()) {
			for (Map.Entry<String, String> param : queryParams.entrySet()) {
				builder = builder.queryParam(param.getKey(), param.getValue());
			}
		}
		
		String urlString = builder.toUriString().replaceAll(" ", "%20");
		if (uriParams != null && !uriParams.isEmpty()) {
			urlString = builder.buildAndExpand(uriParams).toUriString().replaceAll(" ", "%20");
		}
		return urlString;
	}
	
//	public static void main(String[] args) {
//		Communications communications = new Communications();
//		Map<String, String> uriparams = new HashMap<>();
//		uriparams.put("test", "test1");
//		
//		Map<String, String> queyparams = new HashMap<>();
//		queyparams.put("test", "test1");	
//		
//		String teString = communications.addParamsByMethod("http://localhost:5008/transaction/{test}", uriparams, null);
//		System.out.println(teString);
//		String teString1 = communications.addParamsByMethod("http://localhost:5008/transaction", null, queyparams);
//		System.out.println(teString1);
//		
//		String urlBase = "http://181.49.114.18:8080/bonita/loginservice";
//		
//		Map<String, String> parametros = new HashMap<>();
//		parametros.put("redirect", "false");
//		parametros.put("username", "install");
//		parametros.put("password", "install");
//		
//		communications.sendRequest(RequestMethod.GET, urlBase, null, parametros, null, null);
//	}

}
