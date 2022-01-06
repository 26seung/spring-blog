<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ include file="layout/header.jsp"%>

<div class="container">
    <h3>블로그 만들기 연습장</h3>

    <c:forEach var="board" items="${boards.content}">
    <div class="card">
      <div class="card-body">
        <h4 class="card-title">${board.title}</h4>
        <a href="#" class="btn btn-primary">상세보기</a>
      </div>
    </div>
  </c:forEach>

  <ul class="pagination justify-content-center">
    <li class="page-item disabled"><a class="page-link" href="#">Previous</a></li>
    <li class="page-item"><a class="page-link" href="#">Next</a></li>
  </ul>

</div>

<%@ include file="layout/footer.jsp"%>

