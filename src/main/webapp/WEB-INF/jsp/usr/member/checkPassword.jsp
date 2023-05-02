<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="비밀번호 확인"/>
<%@include file="../common/head.jspf" %>

<script>
	let MemberCheckPassword__submitDone = false;
	function MemberCheckPassword__submitDone(form){
		if (MemberCheckPassword__submitDone) {
			return;
	}
	
		form.loginPwConfirm.value = form.loginPwConfirm.value.trim();

		if(form.loginPwInput.value.length == 0){
			alert('비밀번호확인을 입력해주세요.');
			form.loginPwInput.focus();
			return;	
		
		}
		
		
	form.loginPw.value = form.loginPwInput.value;
	form.loginPwInput.value = '';
	
	MemberCheckPassword__submitDone = true;
	form.submit();
}
</script>




<section class="mt-5">
	<div class="container mx-auto">
		<form class="table-box-type-1" method="POST" action="../member/doChceckPassword" onsubmit="MemberCheckPassword__submit(this); return false;">
		<input type="hidden" name="replaceUri" value="${param.replaceUri }" />
		<input type="hidden" name="loginPw" />
			<table>
				<colgroup>
					<col width="200"/>
				</colgroup>
				<tbody>
					<tr>
						<th>로그인아이디</th>
						<td>${rq.loginedMember.loginId }</td>
					</tr>
					<tr>
						<th>로그인비밀번호</th>
						<td><input required="required" type="password" name="loginPwInput" class="w-96 input input-bordered w-full max-w-xs" placeholder="로그인비밀번호"/></td>
					</tr>
					<tr>
						<th>비밀번호 확인</th>
						<td>
							<input type="submit" class="btn btn-active btn-primary" value="비밀번호 확인"/>
							<button type="button" class="btn btn-active btn-primary" onclick="history.back();">뒤로가기</button>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</section>

<%@include file="../common/foot.jspf" %>