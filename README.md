# webgateway-issues
troubleshooting webgateway CSRF issues
api-gateway project implements spring cloud gateway project and acts like a gate keeper to other underlying microservices.
it runs on port 9000.
microservices is a sample rest microservice build on spring web mvc. It runs on port 8080.

sample end points:

GET:
http://localhost:9000/cms-service/webapi/service/hello

POST:
http://localhost:9000/cms-service/webapi/service/hello

body:
rowData ={message: "hello"}

when tried with 9000 port, below error is seen:
{
    "timestamp": "2022-07-30T13:19:40.218+00:00",
    "status": 400,
    "error": "Bad Request",
    "path": "/cms-service/webapi/service/post/greeting"
}
it works fine on 8080 post.

Just clone the project and the run the application. Configure postman to add CSRF cookie to post request. 
https://www.baeldung.com/postman-send-csrf-token
