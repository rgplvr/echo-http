# echo-http

How to start the echo-http application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/echo-http-1.0-SNAPSHOT.jar`

#Posting an Api.
`curl -X POST \
  http://localhost:8080/echo/admin/create \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: a0d2f715-f1ff-7617-911f-c20838440edf' \
  -d '{
	"headers":{
		"auth":"authVal",
		"raj":"rajval",
		"content-type":"application/json"
	},
	"responseBody":"{\"raj\":\"raj\",\"test\":\"Test\"}",
	"responseCode":"200",
	"methodAllowed":["GET","POST"],
	"latency":0,
	"uri":"/user/getprofile1"
}'`

Please note queryparams are ignored. while creating the config

to get the api respose `http://localhost:8080/<uri>

