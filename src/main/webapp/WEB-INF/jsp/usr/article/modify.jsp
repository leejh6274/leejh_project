<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="게시물 수정"/>
<%@include file="../common/head.jspf" %>

<script>
	let ArticleModify__submitForm = false;
	function ArticleModify__submitDone(form){
		if (ArticleModify__submitDone) {
			return;
	}
	
	//좌우공백 제거
	form.body.value = form.body.value.trim();
	
	if(form.body.value.length == 0){
		alert('내용을 입력해주세요.');
		form.body.focus();
		return;
	}
	
	ArticleModify__submitDone = true;
	form.submit();
}
</script>


<section class="mt-5">
	<div class="container mx-auto px-3">
	<form class="table-box-type-1" method="POST" action="../article/doModify" onsubmit="ArticleModify__submit">
	  <input type="hidden" name="memberModifyAuthKey" value="${param.memberModifyAuthKey }"/>
      <table>
      <colgroup>
        <col width="200"/>
      </colgroup>
        <tbody>
          <tr>
            <th>번호</th>
            <td>${article.id}</td>
          </tr>
          <tr>
            <th>작성날짜</th>
            <td>${article.getRegDateForPrint()}</td>
          </tr>
          <tr>
            <th>수정날짜</th>
            <td>${article.getUpdateDateForPrint()}</td>
          </tr>
          <tr>
            <th>작성자</th>
            <td>${article.extra__writerName}</td>
          </tr>
          <tr>
            <th>조회수</th>
            <td>
            	<span class="text-red-700 article-detail__hit-count">${article.hitCount }</span>
            </td>
          </tr>
          <tr>
            <th>추천</th>
            <td>
            	<span class="text-red-700">${article.goodReactionPoint }</span>
            </td>
          </tr>
          <tr>
            <th>제목</th>
            <td>
				<input type="text" class="w-96 input input-bordered w-full max-w-xs" name="title" placeholder="제목" value="${article.title }"/>
            </td>
          </tr>
          <tr>
            <th>내용</th>
            <td>
            	<textarea class="w-full textarea textarea-bordered" name="body" placeholder="내용" >${article.body }</textarea>
            </td>
          </tr>
          <tr>
            <th>수정</th>
            <td>
            	<input type="submit" class="btn btn-active btn-primary" value="수정"/>
            	<button type="button" class="btn btn-active btn-primary" onclick="history.back();">뒤로가기</button>
            </td>
          </tr>
        </tbody>
      </table>
	
	<div class="btns">
		<button class="btn-text-link" type="button" onclick="history.back();">뒤로가기</button>
		<a class="btn-text-link" href="../article/modify?id=${article.id }">게시물 수정</a>
		<c:if test="${article.extra__actorCanDelete }">
			<a class="btn-text-link" onclick="if(confirm('정말 삭제하시겠습니까?') == false )return false;" href="../article/doDelete?id=${article.id }">게시물 삭제</a>
		</c:if>
	</div>
	</form>
	</div>
</section>
<%@include file="../common/foot.jspf" %>