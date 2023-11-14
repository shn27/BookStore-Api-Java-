# BookStore-Api-Java-
A book store api server.

**Installation**
-----------------------------------------------------------------
Download Java and Maven, and set up their path variables

**Running the server**
-----------------------------------------------------------------
```git clone git@github.com:shn27/BookStore-Api-Java-.git```


**API Endpoints**
-----------------------------------------------------------------
|method|url|body|action
|-----|----|---|---|
|GET| `http://localhost:8000/BookStore/login` | --header 'Authorization: Basic c2FtaToxMjM0' | returns a JWT token $TOKEN|
|GET| `http://localhost:8000/BookStore` | --header 'Authorization: Bearer $TOKEN' | returns all the books.|
|GET| `http://localhost:8000/BookStore?bookId` | --header 'Authorization: Bearer $TOKEN' | return a single book where Id = bookId.|
|POST| `http://localhost:8000/BookStore/addBook` | --header 'Authorization: Bearer $TOKEN' | Add the book. Return the addded book.|
|PUT| `http://localhost:8000/BookStore/updateBook?bookId` | --header 'Authorization: Bearer $TOKEN' | Update the book the book if bookId is present. Return the updated book.|
|DELETE| `http://localhost:8000/BookStore/deleteBook?bookId` | --header 'Authorization: Bearer $TOKEN' | Delete the book the book if bookId is present. Return void.|

**cURL commands**
-----------------------------------------------------------------
**Login and receive a JWT $TOKEN (username: admin, password: 1234)**
```
curl --location 'http://localhost:8000/BookStore/login' \
--header 'Authorization: Basic YWRtaW46MTIzNA=='
```
**Add book**
```
curl --location 'http://localhost:8000/BookStore/addBook' \
--header 'id: 1' \
--header 'name: Tom Jones' \
--header 'author: Henry Fielding' \
--header 'genre: novel' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXNzd29yZCI6IjEyMzQiLCJleHBpcnlfdGltZSI6MTY5OTk1NDM1MywidXNlcm5hbWUiOiJhZG1pbiJ9.WK4GrfQIge-yRUJCBLkgHiwI-51dHVk8WA15r5CmldE' \
--data '
```


**Show all books**
```
curl --location 'http://localhost:8000/BookStore/login' \
--header 'Authorization: Basic YWRtaW46MTIzNA=='
```

**Show book with given {id}**
```
curl --location 'http://localhost:8000/BookStore?1=null' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXNzd29yZCI6IjEyMzQiLCJleHBpcnlfdGltZSI6MTY5OTk1NDM1MywidXNlcm5hbWUiOiJhZG1pbiJ9.WK4GrfQIge-yRUJCBLkgHiwI-51dHVk8WA15r5CmldE'
```

**Update book with given {id}**
```
curl --location --request PUT 'http://localhost:8000/BookStore/updateBook?2=null' \
--header 'id: 1' \
--header 'name: Tom Jones' \
--header 'author: Henry Fielding' \
--header 'genre: novel' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXNzd29yZCI6IjEyMzQiLCJleHBpcnlfdGltZSI6MTY5OTk1NDM1MywidXNlcm5hbWUiOiJhZG1pbiJ9.WK4GrfQIge-yRUJCBLkgHiwI-51dHVk8WA15r5CmldE' \
--data '
```
**Delete book with given {id}**
```
curl --location --request DELETE 'http://localhost:8000/BookStore/deleteBook?2=null' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXNzd29yZCI6IjEyMzQiLCJleHBpcnlfdGltZSI6MTY5OTk1NDM1MywidXNlcm5hbWUiOiJhZG1pbiJ9.WK4GrfQIge-yRUJCBLkgHiwI-51dHVk8WA15r5CmldE'
```
**References**
-----------------------------------------------------------------
https://github.com/abrarrhine/AbrarBookstoreTransact </br>
https://github.com/justinmtech/Bookstore-API </br>
https://github.com/samiulsami/apitest </br>
https://howtodoinjava.com/jersey/jersey-rest-security/ </br>

