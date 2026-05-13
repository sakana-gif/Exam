<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/common/base.jsp">
	<c:param name="title">得点管理システム</c:param>
	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績参照</h2>
			<c:choose>
				<c:when test="${not empty student}">
					<h3 class="h5 mb-3">氏名：${student.name} (${student.no})</h3>
					<table class="table table-hover">
						<thead>
							<tr>
								<th>科目名</th>
								<th>科目コード</th>
								<th>回数</th>
								<th>点数</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="test" items="${tests}">
								<tr>
									<td>${test.subjectName}</td>
									<td>${test.subjectCd}</td>
									<td>${test.num}</td>
									<td>${test.point}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:when>
				<c:otherwise>
					<div class="alert alert-danger">学生情報が存在しませんでした</div>
				</c:otherwise>
			</c:choose>
			<div class="mt-3"><a href="TestList.action">戻る</a></div>
		</section>
	</c:param>
</c:import>
 