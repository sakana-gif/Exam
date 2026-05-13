<%-- 科目別成績一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp" >
	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目別成績一覧</h2>

			<%-- 成績一覧 --%>
			<c:choose>
				<c:when test="${testList.size()>0 }">
					<div>検索結果：${testList.size() }件</div>
					<table class="table table-hover">
						<tr>
							<th>入学年度</th>
							<th>クラス</th>
							<th>学生番号</th>
							<th>氏名</th>
							<th>1回目</th>
							<th>2回目</th>
						</tr>
						<c:forEach var="test" items="${testList }">
							<tr>
								<td>${test.entYear }</td>
								<td>${test.classNum }</td>
								<td>${test.studentNo }</td>
								<td>${test.studentName }</td>
								<td>${test.getPoint(1) }</td>
								<td>${test.getPoint(2) }</td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
					<div>学生情報が存在しませんでした。</div>
				</c:otherwise>
			</c:choose>

			<a href="TestList.action">戻る</a>
		</section>
	</c:param>
</c:import>