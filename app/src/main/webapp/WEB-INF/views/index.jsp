<%--
  Created by IntelliJ IDEA.
  User: stockeeper
  Date: 7/31/24
  Time: 5:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
</head>
<body>
    <h1>테스트</h1>
</body>
<script>
    $(document).ready(function() {
        console.log("accessToken : " + window.localStorage.getItem("accessToken"));
        console.log("refreshToken : " + window.localStorage.getItem("refreshToken"));
    });
</script>
</html>
