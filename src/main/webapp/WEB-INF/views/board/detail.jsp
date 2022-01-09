<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
   
  <button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
  <button id="btn-update" class="btn btn-warning">수정</button>
  <button id="btn-delete" class="btn btn-danger">삭제</button>
    <br/><br/>
  
      <div class="form-group">
        <label for="title">제목</label>
        <h3>${board.title}</h3>
      </div>
      <div class="form-group">
         <label for="contnet">본문:</label>
         <div>
           ${board.content}
         </div>
       </div>

</div>

<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>
