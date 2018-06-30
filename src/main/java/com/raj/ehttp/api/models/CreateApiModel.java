package com.raj.ehttp.api.models;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateApiModel {
	private String uri;
	private Map<String, String> headers;
	private String responseBody;
	private int responseCode;
	private List<HttpVerb> methodAllowed;
	private int latency = 0;

}
