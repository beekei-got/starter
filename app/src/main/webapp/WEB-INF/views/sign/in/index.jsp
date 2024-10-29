<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!doctype html>
<html lang="ko">
<jsp:include page="../../component/head.jsp"/>
<body>
<jsp:include page="../../component/nav.jsp"/>

<div class="untree_co-section">
  <div class="container">
    <div class="row">

      <div class="col-md-6 col-xl-12">
        <h2 class="mb-4 section-title">사용자 회원가입</h2>
        <p class="mb-4">앱을 사용할 회원</p>
        <p><a href="/sign/in/client-user?appRedirectUri=test" class="btn">회원가입 하기</a></p>
      </div>

      <div class="col-md-6 col-xl-12">
        <h2 class="mb-4 section-title">사업자 회원가입</h2>
        <p class="mb-4">앱을 도입할 회원</p>
        <p><a href="/sign/in/business-user" class="btn">회원가입 하기</a></p>
      </div>

    </div>
  </div>
</div>

<jsp:include page="../../component/footer.jsp"/>
</body>

</html>
