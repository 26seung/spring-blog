<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">

    <h3>로그인 화면</h3>

<form action="/auth/loginProc" method="post">
  <div class="form-group">
    <label for="username">Username</label>
    <input type="username" name="username" class="form-control" placeholder="Enter username" id="username">
  </div>
  <div class="form-group">
    <label for="password">Password</label>
    <input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
  </div>

  <div class="form-group form-check">
      <label class="form-check-label">
        <input class="form-check-input" name="remember" type="checkbox"> Remember me
      </label>
    </div>

  <button id="btn-login" class="btn btn-primary">로그인</button>
  <a href="https://kauth.kakao.com/oauth/authorize?client_id=759b749f2e8e045f970194c82dc89fce&redirect_uri=http://localhost:8080/auth/kakao/callback&response_type=code"><img src="/image/kakao_login_button.png" /></a>
</form>

</div>

<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>

