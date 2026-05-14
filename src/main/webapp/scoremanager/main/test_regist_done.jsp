<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/common/base.jsp">
	<c:param name="title">得点管理システム</c:param>
	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>
			<div class="alert alert-secondary mt-4">成績の登録が完了しました。</div>
			<div class="mt-3">
				<a href="TestRegist.action" class="btn btn-outline-secondary">成績管理へ戻る</a>
				<a href="TestList.action" class="btn btn-outline-primary ms-2">成績参照へ</a>
			</div>
		</section>
	</c:param>
</c:import>