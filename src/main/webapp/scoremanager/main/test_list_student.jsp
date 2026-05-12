<%-- 学生別成績一覧JSP --%>
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
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生別成績一覧</h2>

			<%-- 学生情報 --%>
			<div class="px-4 mb-3">
				<p>学生番号：${student.no }</p>
				<p>氏名：${student.name }</p>
				<p>クラス：${student.classNum }</p>
			</div>

			<%-- 成績一覧 --%>
			<c:choose>
				<c:when test="${testList.size()>0 }">
					<div>検索結果：${testList.size() }件</div>
					<table class="table table-hover">
						<tr>
							<th>科目コード</th>
							<th>科目名</th>
							<th>回数</th>
							<th>得点</th>
						</tr>
						<c:forEach var="test" items="${testList }">
							<tr>
								<td>${test.subjectCd }</td>
								<td>${test.subjectName }</td>
								<td>${test.num }</td>
								<td>${test.point }</td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
					<div>成績情報が存在しませんでした。</div>
				</c:otherwise>
			</c:choose>

			<a href="TestList.action">戻る</a>
		</section>
	</c:param>
</c:import>