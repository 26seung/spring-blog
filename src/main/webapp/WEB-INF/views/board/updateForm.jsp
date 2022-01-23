
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
   
   <form>
     <input type="hidden" id="id" value="${board.id}" />
      <div class="form-group">
        <input type="text" class="form-control" value="${board.title}" id="title">
      </div>

      <div class="form-group">
         <textarea class="form-control summernote" rows="5" id="content">${board.content}</textarea>
       </div>
       
      </form>
      <button id="btn-update" class="btn btn-primary">글수정 완료</button>

</div>

<script>
  $('.summernote').summernote({
    tabsize: 2,
    height: 100
  });
</script>

<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>