<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!doctype html>
<html lang="ko">
<jsp:include page="../../component/head.jsp"/>
<body>
<jsp:include page="../../component/nav.jsp"/>

<div class="untree_co-section">
  <div class="container">
    <div class="block">
      <div class="row justify-content-center">
        <div class="col-md-8 col-lg-8 pb-4">
          <h2 class="section-title">사용자 회원가입</h2>
          <p>앱을 사용할 사용자 회원가입</p>
          <div class="row">
            <div class="col-12">
              <a href="/oauth2/authorization/google?appRedirectUri=${appRedirectUri}" class="btn btn-primary-hover-outline wd100 mg-bt20">구글 로그인</a>
              <a href="/oauth2/authorization/kakao?appRedirectUri=${appRedirectUri}" class="btn btn-primary-hover-outline wd100 mg-bt20">카카오 로그인</a>
              <a href="/oauth2/authorization/naver?appRedirectUri=${appRedirectUri}" class="btn btn-primary-hover-outline wd100 mg-bt20">네이버 로그인</a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<jsp:include page="../../component/footer.jsp"/>
</body>

</html>
