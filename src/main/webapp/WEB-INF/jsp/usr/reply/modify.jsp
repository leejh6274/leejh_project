<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="댓글 수정"/>
<%@include file="../common/head.jspf" %>

<script>
	//댓글 작성 관련
	let ReplyModify__submitForm = false;
	function ReplyModify__submitDone(form){
		if (ReplyModify__submitDone) {
			return;
	}
	
	//좌우공백 제거
	form.body.value = form.body.value.trim();
	
	if(form.body.value.length == 0){
		alert('내용을 입력해주세요.');
		form.body.focus();
		return;
	}
	
	ReplyModify__submitDone = true;
	form.submit();
}
</script>



<section class="mt-5">
	<div class="container mx-auto px-3">
	<form class="table-box-type-1" method="POST" action="../reply/doModify" onsubmit="ReplyModify__submit">
	  <input type="hidden" name="id" value="${reply.id }"/>
	  <input type="hidden" name="replaceUri" value="${param.replaceUri }"/>
      <table>
      <colgroup>
        <col width="200"/>
      </colgroup>
        <tbody>
          <tr>
            <th>게시물 번호</th>
            <td>${reply.relId}</td>
          </tr>
          <tr>
            <th>댓글 작성날짜</th>
            <td>${reply.getRegDateForPrint()}</td>
          </tr>
          <tr>
            <th>댓글 수정날짜</th>
            <td>${reply.getUpdateDateForPrint()}</td>
          </tr>
          <tr>
            <th>댓글 작성자</th>
            <td>${reply.extra__writerName}</td>
          </tr>
          <tr>
            <th>댓글 추천</th>
            <td>
            	<span class="text-red-700">${reply.goodReactionPoint }</span>
            </td>
          </tr>
          <tr>
            <th>내용</th>
            <td>
            	<textarea rows="5" class="w-full textarea textarea-bordered" name="body" placeholder="내용" >${reply.body }</textarea>
            </td>
          </tr>
          <tr>
            <th>수정</th>
            <td>
            	<input type="submit" class="btn btn-active btn-primary" value="댓글 수정"/>
            	<a class="btn btn-active btn-primary" href="${param.replaceUri}">뒤로가기</a>
            </td>
          </tr>
        </tbody>
      </table>
	
	</form>
	</div>
</section>
<%@include file="../common/foot.jspf" %>