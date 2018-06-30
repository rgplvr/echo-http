package com.raj.ehttp.api;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.raj.ehttp.api.handler.CreateEchoHandler;
import com.raj.ehttp.api.models.CreateApiModel;
@Component
@Path("/echo/admin/")
public class CreateEcho  {
	@Autowired
    CreateEchoHandler createEchoHandler;
    @POST
    @Path("create")
    public Response create(CreateApiModel model) throws IOException{
        return createEchoHandler.handle(model);
    }
}
