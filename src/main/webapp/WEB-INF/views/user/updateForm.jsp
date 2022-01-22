<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">

    <h3>회원정보 수정 화면</h3>

<form>
  <input type="hidden" id="id" value="${principal.user.id}" />
  <div class="form-group">
    <label for="username">Username</label>
    <input type="username" class="form-control" value="${principal.user.username}" id="username" readonly>
  </div>
    <div class="form-group">
      <label for="email">Email</label>
      <input type="email" class="form-control" value="${principal.user.email}" id="email">
    </div>
  <div class="form-group">
    <label for="password">Password</label>
    <input type="password" class="form-control" placeholder="Enter password" id="password">
  </div>
</form>

  <button id="btn-update" class="btn btn-primary">회원정보수정 완료</button>

</div>

<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>

