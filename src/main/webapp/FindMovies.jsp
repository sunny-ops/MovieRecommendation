<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Movie Database</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link rel="stylesheet" href="css/movie.css">
    
</head>
<body>
	<h1 action = "findmovies" class="jumbotron">Search Movies</h1>
	<input type="text" id="imdb" class="form-control form-control-lg">
	<input type="submit" id="on" class="btn btn-primary" value="Search">
	<input type="submit" id="rel" class="btn btn-success" value="Reload">
    <div class="container">
            <!-- <div class="card" style="width: 18rem;">
                    <img src="..." class="card-img-top img" alt="..." id="img">
                    <div class="card-body">
                      <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                    </div>
                  </div> -->
        <img src="" class="img" id="img">
            <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id="tit">Title</span>
                    </div>
                    <textarea class="form-control tit" aria-label="With textarea"></textarea>
                  </div>
            <div class="input-group">
                   
                    <div class="input-group-prepend">
                      <span class="input-group-text" id="rating1">Imdb Ratings</span>
                    </div>
                    <textarea class="form-control ok1" aria-label="With textarea"></textarea>
                  </div>
                  <div class="input-group">
                        <div class="input-group-prepend">
                          <span class="input-group-text" id="rating2">Rotten Tomatoes</span>
                        </div>
                        <textarea class="form-control ok2" aria-label="With textarea"></textarea>
                      </div>
                      <div class="input-group">
                            <div class="input-group-prepend">
                              <span class="input-group-text" id="tit">Plot</span>
                            </div>
                            <textarea class="form-control tit2" aria-label="With textarea"></textarea>
                          </div>
                          <div class="input-group">
                                <div class="input-group-prepend">
                                  <span class="input-group-text" id="tit">Year</span>
                                </div>
                                <textarea class="form-control tit3" aria-label="With textarea"></textarea>
                              </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.3.1.js" integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60=" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
    <script src="js/movie.js"></script>
    
</body>
</html>
