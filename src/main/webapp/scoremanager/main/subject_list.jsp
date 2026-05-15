<%-- 科目一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>
 
	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目管理</h2>
			
			<div class="my-3 text-end">
				<a href="SubjectCreate.action">新規登録</a>
			</div>
 
			<c:choose>
				<c:when test="${subjects != null && subjects.size() > 0}">
					<table class="table table-hover mt-2 border-top">
						<thead>
							<tr>
								<th class="w-25">科目コード</th>
								<th class="w-50">科目名</th>
								<th></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="subject" items="${subjects}">
								<tr>
									<td class="align-middle">${subject.cd}</td>
									<td class="align-middle">${subject.name}</td>
									<td class="align-middle"><a href="SubjectUpdate.action?cd=${subject.cd}">変更</a></td>
									<td class="align-middle"><a href="SubjectDelete.action?cd=${subject.cd}">削除</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:when>
				<c:otherwise>
					<div class="alert alert-secondary mt-3">科目情報が存在しませんでした</div>
				</c:otherwise>
			</c:choose>
		</section>
	</c:param>
</c:import>