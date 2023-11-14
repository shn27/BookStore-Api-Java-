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

