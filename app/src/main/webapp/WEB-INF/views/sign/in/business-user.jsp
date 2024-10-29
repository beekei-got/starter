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
          <h2 class="section-title">사업자 회원가입</h2>
          <p>앱을 사용할 사업자분들의 회원가입</p>
          <div class="row mb-5">
            <form>
              <div class="row">
                <div class="form-group">
                  <label class="text-black" for="login-id">아이디</label>
                  <input type="text" class="form-control" id="login-id">
                </div>
                <div class="col-md-6 col-xl-12">
                  <div class="form-group">
                    <label class="text-black" for="password">비밀번호</label>
                    <input type="password" class="form-control" id="password">
                  </div>
                </div>
                <div class="col-md-6 col-xl-12">
                  <div class="form-group">
                    <label class="text-black" for="password-check">비밀번호 확인</label>
                    <input type="password" class="form-control" id="password-check">
                  </div>
                </div>
                <div class="col-md-6 col-xl-12">
                  <div class="form-group">
                    <label class="text-black" for="name">성명</label>
                    <input type="text" class="form-control" id="name">
                  </div>
                </div>
                <div class="col-md-6 col-xl-12">
                  <div class="form-group">
                    <label class="text-black" for="phone-number">휴대폰번호</label>
                    <input type="tel" class="form-control" id="phone-number">
                  </div>
                </div>
                <div class="form-group">
                  <label class="text-black" for="email">이메일</label>
                  <input type="email" class="form-control" id="email">
                </div>
              </div>
              <div class="form-group mb-5">
                <label class="text-black" for="business-info">사업체 정보</label>
                <textarea name="business-info" class="form-control" id="business-info" cols="30" rows="7" placeholder="도입하려는 사업체의 정보를 자세히 입력해주세요.">
상호(법인명):
사업자 등록번호:
사업장 소재지:
업태:
종목:
                </textarea>
              </div>
              <button type="submit" class="btn btn-primary-hover-outline">회원가입</button>
            </form>
          </div>
      </div>
      </div>
    </div>
  </div>
</div>

<jsp:include page="../../component/footer.jsp"/>
</body>

</html>
