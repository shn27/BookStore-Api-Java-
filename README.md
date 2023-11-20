# BookStore-Api-Java-
A book store api server.

**Installation**
-----------------------------------------------------------------
Download Java and Maven, and set up their path variables.

**Running the server**
-----------------------------------------------------------------
```git clone git@github.com:shn27/BookStore-Api-Java-.git``` </br>
Use postman for testing.


**API Endpoints**
-----------------------------------------------------------------
|method|url|body|action
|-----|----|---|---|
|GET| `http://localhost:8000/bookStore/login` | --header 'Authorization: Basic c2FtaToxMjM0' | returns a JWT token $TOKEN|
|GET| `http://localhost:8000/bookStore` |   | returns all the books.|
|GET| `http://localhost:8000/bookStore?bookId` |   | return a single book where Id = bookId.|
|POST| `http://localhost:8000/bookStore/addBook` |   | Add the book. Return the addded book.|
|PUT| `http://localhost:8000/bookStore/updateBook?bookId` |   | Update the book the book if bookId is present. Return the updated book.|
|DELETE| `http://localhost:8000/bookStore/deleteBook?bookId` |   | Delete the book the book if bookId is present. Return void.|

**cURL commands**
-----------------------------------------------------------------
**Login and receive a JWT $TOKEN (username: admin, password: 1234)**
```
curl --location 'http://localhost:8000/bookStore/login' \
--header 'Authorization: Basic YWRtaW46MTIzNA==' \
--header 'Cookie: Token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXNzd29yZCI6IjEyMzQiLCJleHBpcnlfdGltZSI6MTcwMDA1MTcyMywidXNlcm5hbWUiOiJhZG1pbiJ9.GMfloFmkCpvi7zYoyzZkD0ycMQNrNEj7j6UQsBGL5O4"; myCookie="cookieValue"' \
--data ''
```
**Add book**
```
curl --location 'http://localhost:8000/bookStore/addBook' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic YWRtaW46MTIzNA==' \
--header 'Cookie: Token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXNzd29yZCI6IjEyMzQiLCJleHBpcnlfdGltZSI6MTcwMDA1MjcyNiwidXNlcm5hbWUiOiJhZG1pbiJ9.SQMrL0VArHh6OBrK0ufeXKSG8WwrJmSqvV_7s4-Ixnw"; myCookie="cookieValue"' \
--data '{
id: 1,
name :"Tom Jones" ,
author : "Henry Fielding" ,
genre :"Novel"
}

'
```


**Show all books**

```
curl --location 'http://localhost:8000/bookStore' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXNzd29yZCI6IjEyMzQiLCJleHBpcnlfdGltZSI6MTY5OTk3MzQxNywidXNlcm5hbWUiOiJhZG1pbiJ9.gaS2JWdnzKa3N-t37TT1a-hL_02JfNGmqBMjTbJIBHU' \
--header 'Cookie: Token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXNzd29yZCI6IjEyMzQiLCJleHBpcnlfdGltZSI6MTcwMDA1MjcyNiwidXNlcm5hbWUiOiJhZG1pbiJ9.SQMrL0VArHh6OBrK0ufeXKSG8WwrJmSqvV_7s4-Ixnw"; myCookie="cookieValue"' \
--data ''
```

**Show book with given {id}**

```
curl --location 'http://localhost:8000/bookStore?1=null' \
--header 'Cookie: Token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXNzd29yZCI6IjEyMzQiLCJleHBpcnlfdGltZSI6MTcwMDA1MjcyNiwidXNlcm5hbWUiOiJhZG1pbiJ9.SQMrL0VArHh6OBrK0ufeXKSG8WwrJmSqvV_7s4-Ixnw"; myCookie="cookieValue"'
```

**Update book with given {id}**
```
curl --location --request PUT 'http://localhost:8000/bookStore/updateBook?1=null' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic YWRtaW46MTIzNA==' \
--header 'Cookie: Token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXNzd29yZCI6IjEyMzQiLCJleHBpcnlfdGltZSI6MTcwMDA1MjcyNiwidXNlcm5hbWUiOiJhZG1pbiJ9.SQMrL0VArHh6OBrK0ufeXKSG8WwrJmSqvV_7s4-Ixnw"; myCookie="cookieValue"' \
--data '{
id: 1,
name :"Tom Jones 1" ,
author : "Henry Fielding" ,
genre :"Novel"
}

'
```
**Delete book with given {id}**
```
curl --location --request DELETE 'http://localhost:8000/bookStore/deleteBook?2=null' \
--header 'Cookie: Token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXNzd29yZCI6IjEyMzQiLCJleHBpcnlfdGltZSI6MTcwMDA1MjcyNiwidXNlcm5hbWUiOiJhZG1pbiJ9.SQMrL0VArHh6OBrK0ufeXKSG8WwrJmSqvV_7s4-Ixnw"; myCookie="cookieValue"'
```
**References**
-----------------------------------------------------------------
https://github.com/abrarrhine/AbrarBookstoreTransact </br>
https://github.com/justinmtech/Bookstore-API </br>
https://github.com/samiulsami/apitest </br>
https://howtodoinjava.com/jersey/jersey-rest-security/ </br>

**Download and run Docker Image**
-----------------------------------------------------------------
docker pull shn27/bookstore-api:1.2
docker run -p 8000:8000 shn27/bookstore-api:1.2



