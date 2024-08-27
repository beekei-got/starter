<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Starter | 인증 진행중..</title>
    <script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
</head>
<body>
    <h1>인증 진행중..</h1>
</body>
<script>
    $(document).ready(function() {
        const searchParams = new URLSearchParams(location.search);
        $.ajax({
            type: "POST",
            url: '/api/v1/auth/token',
            data: JSON.stringify ({
                authTokenId: searchParams.get("authTokenId")
            }),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            timeout: 5000,
            cache: false,
            success: function(response) {
                window.localStorage.setItem("accessToken", response.responseData.authToken.accessToken);
                window.localStorage.setItem("refreshToken", response.responseData.authToken.refreshToken);
                window.location.href = "/";
            },
            error: function(result) {
                alert(result.responseJSON.responseMessage + "[" + result.responseJSON.responseCode + "]");
            },
            complete: function() {

            }
        });
    });
</script>
</html>
