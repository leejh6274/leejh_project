<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="게시물 내용"/>
<%@include file="../common/head.jspf" %>

<script>
const params = {}
params.id = parseInt('${param.id}');
</script>


<script>
function ArticleDetail__increaseHitCount() {
	const localStorageKey = 'article__' + params.id + '__viewDone';
	
	if(localStorage.getItem(localStorageKey)) {
		return;
	}
	
	localStorage.setItem(localStorageKey, true);
	
	$.get( 										  //(겟은 form역할)
		'../article/doIncreaseHitCountRd', {      //(''안에 얘는 액션역할)
			id: params.id,
			ajaxMode : 'Y'
		}, function(data) {
			$('.article-detail__hit-count').empty().html(data.data1);
		}, 'json');
	}
	
	$(function(){
		//실전
		//ArticleDetail__increaseHitCount();
		
		//임시코드
		setTimeout(ArticleDetail__increaseHitCount, 1000); //새로고침으로 조회수 뻥튀기 금지시키려고 시간 값 주기(1초)
	})
</script>

<section class="mt-5">
	<div class="container mx-auto px-3">
    <div class="table-box-type-1">
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
            	<div class="flex items-center">
            		<span class="text-red-700">${article.goodReactionPoint }</span>
            		<span>&nbsp;</span>
            		
            		<c:if test="${actorCanMakeReactionPoint }">    <!-- 추천/비추천을 하기위해 로그인이 되어있는지 확인하는 c:if test -->
            			<button class="btn btn-xs btn-outline btn-primary">
            				좋아요 😊
            			</button>
            			<span>&nbsp;</span>
            			<button class="btn btn-xs btn-outline btn-secondary">
            				싫어요 😟
            			</button>
            		</c:if>
            		
            	</div>
            </td>
          </tr>
          <tr>
            <th>제목</th>
            <td>${article.title}</td>
          </tr>
          <tr>
            <th>내용</th>
            <td>${article.body}</td>
          </tr>
        </tbody>
      </table>
    </div>
 
	
	<div class="btns">
		<button class="btn btn-link" type="button" onclick="history.back();">뒤로가기</button>
		<c:if test="${article.extra__actorCanModify }">
			<a class="btn btn-link" href="../article/modify?id=${article.id }">게시물 수정</a>
		</c:if>
		
		<c:if test="${article.extra__actorCanDelete }">
			<a class="btn btn-link" onclick="if(confirm('정말 삭제하시겠습니까?') == false )return false;" href="../article/doDelete?id=${article.id }">게시물 삭제</a>
		</c:if>
	</div>
	</div>
</section>
<%@include file="../common/foot.jspf" %>