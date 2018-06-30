package com.raj.ehttp.api.handler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Base64;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raj.ehttp.EHttpConfiguration;
import com.raj.ehttp.api.models.CreateApiModel;

@Component
public class CreateEchoHandler {
	@Autowired
	ObjectMapper mapper;
	@Autowired
	EHttpConfiguration eHttpConfiguration;
	public Response handle(CreateApiModel model) throws IOException {
		System.err.println(">>>>>"+model.getUri().split("\\?")[0]);
		String fileName = 
				eHttpConfiguration.getPathForJsons().concat("/").replace("//", "/") + Base64.getUrlEncoder().encodeToString(model.getUri().split("\\?")[0].getBytes()) + ".json";
		
		File yourFile = new File(fileName);
		yourFile.createNewFile();
		try (PrintStream out = new PrintStream(new FileOutputStream(fileName))) {

			out.print(mapper.writeValueAsString(model));
		}

		return Response.accepted(mapper.writeValueAsString(model)).build();
	}
}
