<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/common/base.jsp">
	<c:param name="title">得点管理システム</c:param>
	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績参照</h2>
			
			<div class="card mb-4">
				<div class="card-header bg-light">科目情報</div>
				<div class="card-body">
					<form action="TestListSubjectExecute.action" method="post" class="row g-3">
						<div class="col-md-3">
							<label class="form-label">入学年度</label>
							<select name="f1" class="form-select" required>
								<option value="">--------</option>
								<c:forEach var="year" items="${ent_year_set}">
									<option value="${year}">${year}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-md-3">
							<label class="form-label">クラス</label>
							<select name="f2" class="form-select" required>
								<option value="">--------</option>
								<c:forEach var="num" items="${class_num_set}">
									<option value="${num}">${num}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-md-4">
							<label class="form-label">科目</label>
							<select name="f3" class="form-select" required>
								<option value="">--------</option>
								<c:forEach var="sub" items="${subject_set}">
									<option value="${sub.cd}">${sub.name}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-md-2 pt-4">
							<button type="submit" class="btn btn-secondary w-100 text-nowrap">検索</button>
						</div>
					</form>
				</div>
			</div>
 
			<div class="card">
				<div class="card-header bg-light">学生情報</div>
				<div class="card-body">
					<form action="TestListStudentExecute.action" method="post" class="row g-3">
						<div class="col-md-10">
							<label class="form-label">学生番号</label>
							<input type="text" name="f4" class="form-control" placeholder="学生番号を入力してください" required>
						</div>
						<div class="col-md-2 pt-4">
							<button type="submit" class="btn btn-secondary w-100 text-nowrap">検索</button>
						</div>
					</form>
				</div>
			</div>
			
			<div class="mt-4 text-primary small">
				科目情報を選択または学生情報を入力して検索ボタンをクリックしてください
			</div>
		</section>
	</c:param>
</c:import>
 