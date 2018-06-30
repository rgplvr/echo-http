package com.raj.ehttp.api.handler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.Map;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raj.ehttp.EHttpConfiguration;
import com.raj.ehttp.api.models.CreateApiModel;

@Component
public class CreateResponseHandler {
	@Autowired
	EHttpConfiguration config;
	@Autowired
	ObjectMapper mapper;

	public void buildResponse(String url, ServletResponse response)
			throws JsonParseException, JsonMappingException, IOException {
		System.err.println("############################"+url);
		String everything;
		try (BufferedReader br = new BufferedReader(new FileReader(
				config.getPathForJsons().concat("/").replace("//", "/") + Base64.getUrlEncoder().encodeToString(url.getBytes()) + ".json"))) {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			everything = sb.toString();
		}

		CreateApiModel model = mapper.readValue(everything, CreateApiModel.class);

		HttpServletResponse httpResposnse = (HttpServletResponse) response;

		for (Map.Entry<String, String> entry : model.getHeaders().entrySet()) {
			httpResposnse.addHeader(entry.getKey().toLowerCase(), entry.getValue());
		}
		httpResposnse.setStatus(model.getResponseCode());
		PrintWriter out = httpResposnse.getWriter();
		out.print(model.getResponseBody());
		out.flush();

	}
}
