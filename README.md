SPRING-JWT


this project implements a mechanism for authorizing users and accessing resources based on a JWT token.

the project used Spring Boot, Spring Data JPA, MySQL, Spring Security, JWT


BEFORE RUNNING


before getting a jwt token, you need to add a user to the database, you can use the following data:

username : testtest
password : $2a$10$1Lt3481whvkkrI5SL6DqGuUG2egRfkBqWoEySpzs5nTP.xfmvWyT2

after that, we can make a POST request to our End-Point with the following JSON:

{
"username" : "testtest",
"password" : "testtest"
}

the server, after processing the request, will give us a token, of the form:
"token": "eyJ...............w4"

Once we receive the token, we can use it to make a GET and POST request to our server/message address,
where by GET request with JSON body:
{
"username":"testtest",
"message" : "any message"
} 
we will be adding messages to the database.

where by GET request with JSON body:

{
"username":"testtest",
"message" : "history 10"
}
we can get the history of the last 10 posts of a given user
