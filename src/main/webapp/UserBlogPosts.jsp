<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<title>BlogPosts</title>
</head>
<body>
    <div class="container theme-showcase" role="main">
    
    <div class="jumbotron">
	<h1>${messages.title}</h1>
	</div>
        <table class="table table-striped">
            <thead><tr>
                <th>PostId</th>
                <th>Title</th>
                <th>Content</th>
                <th>Published</th>
                <th>Created</th>
                <th>Comments</th>
                <th>Delete BlogPost</th>
            </tr></thead>
            <c:forEach items="${blogPosts}" var="blogPost" >
                <tbody><tr>
                    <td><c:out value="${blogPost.getPostId()}" /></td>
                    <td><c:out value="${blogPost.getTitle()}" /></td>
                    <td><c:out value="${blogPost.getContent()}" /></td>
                    <td><c:out value="${blogPost.isPublished()}" /></td>
                    <td><fmt:formatDate value="${blogPost.getCreated()}" pattern="MM-dd-yyyy hh:mm:sa"/></td>
                    <td><a href="blogcomments?postid=<c:out value="${blogPost.getPostId()}"/>">BlogComments</a></td>
                    <td><a href="deleteblogpost?postid=<c:out value="${blogPost.getPostId()}"/>">Delete</a></td>
                </tr></tbody>
            </c:forEach>
       </table>
    </div>
       
    <!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>
