<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">

    <h3>로그인 화면</h3>

<form>
  <div class="form-group">
    <label for="username">Username</label>
    <input type="username" class="form-control" placeholder="Enter username" id="username">
  </div>
  <div class="form-group">
    <label for="password">Password</label>
    <input type="password" class="form-control" placeholder="Enter password" id="password">
  </div>
  <button type="btn-login" class="btn btn-primary">로그인</button>
</form>

</div>

<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>

