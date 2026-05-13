<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/common/base.jsp">
	<c:param name="title">得点管理システム</c:param>
	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績一覧（科目）</h2>
			<h3 class="h5 mb-3">科目：${subject.name}</h3>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>入学年度</th>
						<th>クラス</th>
						<th>学生番号</th>
						<th>氏名</th>
						<th class="text-center">1回</th>
						<th class="text-center">2回</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="test" items="${tests}">
						<tr>
							<td>${test.entYear}</td>
							<td>${test.classNum}</td>
							<td>${test.studentNo}</td>
							<td>${test.studentName}</td>
							<td class="text-center">${test.getPoint(1) != -1 ? test.getPoint(1) : '-'}</td>
							<td class="text-center">${test.getPoint(2) != -1 ? test.getPoint(2) : '-'}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="mt-3"><a href="TestList.action">戻る</a></div>
		</section>
	</c:param>
</c:import>
 