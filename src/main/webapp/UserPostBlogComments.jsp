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
<title>BlogComments</title>
</head>
<body>
	<div class="container theme-showcase" role="main">

	<div class="jumbotron">
	<h1>${messages.title}</h1>
	</div>
        <table class="table table-striped">
            <thead><tr>
                <th>CommentId</th>
                <th>PostId</th>
                <th>UserName</th>
                <th>Content</th>
                <th>Created</th>
            </tr></thead>
            <c:forEach items="${blogComments}" var="blogComment" >
                <tbody><tr>
                    <td><c:out value="${blogComment.getCommentId()}" /></td>
                    <td><c:out value="${blogComment.getBlogPost().getPostId()}" /></td>
                    <td><c:out value="${blogComment.getBlogUser().getUserName()}" /></td>
                    <td><c:out value="${blogComment.getContent()}" /></td>
                    <td><fmt:formatDate value="${blogComment.getCreated()}" pattern="MM-dd-yyyy hh:mm:sa"/></td>
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
