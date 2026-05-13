<%-- 学生変更JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp" >
	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section>
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生情報変更</h2>
			<form action="StudentUpdateExecute.action" method="get">
				<div>
					<label for="ent_year">入学年度</label>
					<input class="form-control-plaintext p-2" type="text" id="ent_year" name="ent_year" value="${student.entYear}" readonly/>
				</div>
				<div>
					<label for="ent_year">学生番号</label>
					<input class="form-control-plaintext p-2" type="text" id="no" name="no" value="${student.no}" readonly/>
				</div>
				<div>
					<label for="name">氏名</label><br>
					<input class="form-control" type="text" id="name" name="name" value="${student.name}"/>
				</div>
				<div class="mt-2 text-warning">${error}</div>
				<div class="mx-auto py-2">
					<label for="class_num">クラス</label>
					<select class="form-select" id="class_num" name="class_num">
						<c:forEach var="num" items="${class_num_set }">
							<option value="${num }" <c:if test="${num==student.classNum}">selected</c:if>>${num }</option>
						</c:forEach>
					</select>
				</div>
				<div
					class="d-flex gap-2 align-items-center col-2 px-0">
					<label for="student-is_attend-check" class="mb-0"> 在学中 </label> <input
						class="form-check-input m-0" type="checkbox"
						id="student-is_attend-check" name="is_attend" value="true"
						<c:if test="${student.attend}">checked</c:if> />
				</div>
				<div class="mx-auto py-2">
					<button class="btn btn-primary" name="end">変更</button>
				</div>
			</form>
			<a href="StudentList.action">戻る</a>
		</section>
	</c:param>
</c:import>