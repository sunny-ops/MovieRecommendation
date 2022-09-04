<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MovieRecommendations</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/css/bootstrap.min.css"
        integrity="sha384-r4NyP46KrjDleawBgD5tp8Y7UzmLA05oM1iAEQ17CSuDqnUK2+k9luXQOfXJCJ4I" crossorigin="anonymous">
    <link rel="stylesheet" href="css/home1.css">
</head>

<body class="d-flex text-center text-white bg-dark">
    <div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
        <header class="mb-auto">
            <div>
                <h3 class="float-md-left mb-0">Movie+</h3>
                <nav class="nav nav-masthead justify-content-center float-md-right">
                    <a class="nav-link active" aria-current="page" href="#">Home</a>
                    <a class="nav-link" href="findusers">FindUsers</a>
                    <a class="nav-link" href="usercreate">CreateUsers</a>
                    <%-- <% if(!true) { %>
                        <a class="nav-link" href="/login">Login</a>
                        <a class="nav-link" href="/register">Register</a>
                        <% } else { %>
                            <a class="nav-link" href="/logout">Logout</a>
                            <% } %> --%>
                </nav>
            </div>
        </header>
        <main class="px-3">
            <h1>Movie+</h1>
            <p class="lead"> Welcome! <br> Jump right in and explore our movies. <br>
                Feel free to share your own reviews!</p>
            <a href="findmovies" class="nav-link">Search Movies</a>
        </main>

        <footer class="mt-auto text-white-50">
            <p>&copy; 2022 </p>
        </footer>


    </div>


    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/js/bootstrap.min.js"
        integrity="sha384-oesi62hOLfzrys4LxRF63OJCXdXDipiYWBnvTl9Y9/TRlw5xlKIEHpNyvvDShgf/"
        crossorigin="anonymous"></script>
</body>

</html>