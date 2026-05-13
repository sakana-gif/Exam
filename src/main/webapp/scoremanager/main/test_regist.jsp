<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/common/base.jsp">
	<c:param name="title">得点管理システム</c:param>
	<c:param name="content">
		<section class="me-4">
<<<<<<< HEAD
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>
			
			<form action="TestRegist.action" method="post" class="row g-3 border-bottom pb-4 mb-4">
				<div class="col-md-2">
					<label class="form-label">入学年度</label>
					<select name="f1" class="form-select">
						<option value="0">--------</option>
						<c:forEach var="year" items="${ent_year_set}">
							<option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-md-2">
					<label class="form-label">クラス</label>
					<select name="f2" class="form-select">
						<option value="">--------</option>
						<c:forEach var="num" items="${class_num_set}">
							<option value="${num}" <c:if test="${num == f2}">selected</c:if>>${num}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-md-4">
					<label class="form-label">科目</label>
					<select name="f3" class="form-select">
						<option value="">--------</option>
						<c:forEach var="sub" items="${subject_set}">
							<option value="${sub.cd}" <c:if test="${sub.cd == f3}">selected</c:if>>${sub.name}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-md-2">
					<label class="form-label">回数</label>
					<select name="f4" class="form-select">
						<option value="0">--------</option>
						<c:forEach var="n" items="${num_set}">
							<option value="${n}" <c:if test="${n == f4}">selected</c:if>>${n}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-md-2 pt-4">
					<button type="submit" class="btn btn-secondary w-100 text-nowrap">検索</button>
=======
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績登録</h2>
			<form action="TestRegist.action" method="get">
				<div
					class="row border rounded align-items-center pt-3 pb-3 mx-3 mb-3">
					<div class="col-2">
						<label class="form-label">入学年度</label> <select name="ent_year"
							class="form-select" required>
							<option value="">--------</option>
							<c:forEach var="year" items="${ent_year_set}">
								<option value="${year}"
									<c:if test="${year==ent_year }">selected</c:if>>${year}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-2">
						<label class="form-label">クラス</label> <select name="class_num"
							class="form-select" required>
							<option value="">--------</option>
							<c:forEach var="num" items="${class_num_set}">
								<option value="${num}"
									<c:if test="${num==class_num}">selected</c:if>>${num}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-4">
						<label class="form-label">科目</label> <select name="subject_cd"
							class="form-select" required>
							<option value="">--------</option>
							<c:forEach var="subject" items="${subject_set}">
								<option value="${subject.cd}"
									<c:if test="${subject.cd==subject_cd}">selected</c:if>>${subject.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-2">
						<label class="form-label">回数</label> <select name="no"
							class="form-select" required>
							<c:forEach var="n" items="${num_set}">
								<option value="${n}" <c:if test="${n==no }">selected</c:if>>${n}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col d-flex align-items-end">
						<button type="submit" class="btn btn-secondary">検索</button>
					</div>
>>>>>>> branch 'master' of https://github.com/sakana-gif/Exam.git
				</div>
			</form>
<<<<<<< HEAD
 
			<c:if test="${not empty tests}">
				<h3 class="h5 mb-3">科目：${tests[0].subject.name} (${f4}回)</h3>
				<form action="TestRegistExecute.action" method="post">
					<input type="hidden" name="subject_cd" value="${f3}">
					<input type="hidden" name="num" value="${f4}">
					<table class="table table-hover">
						<thead>
=======

			<c:if test="${subject!=null}">
				<div>科目：${subject.name}（${no}回）</div>
			</c:if>

			<c:choose>

				<c:when test="${tests.size()>0 }">

					<form action="TestRegistExecute.action" method="post">
						<table class="table table-hover">
>>>>>>> branch 'master' of https://github.com/sakana-gif/Exam.git
							<tr>
								<th>入学年度</th>
								<th>クラス</th>
								<th>学生番号</th>
								<th>氏名</th>
								<th width="150">点数</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="test" items="${tests}">
								<tr>
									<td>${test.student.entYear}</td>
									<td>${test.student.classNum}</td>
									<td>${test.student.no}</td>
									<td>${test.student.name}</td>
									<td>
										<input type="hidden" name="student_no_set" value="${test.student.no}">
										<input type="number" name="point_${test.student.no}"
											class="form-control" min="0" max="100"
											value="${test.point != -1 ? test.point : ''}">
									</td>
								</tr>
							</c:forEach>
<<<<<<< HEAD
						</tbody>
					</table>
					<div class="mt-4">
						<button type="submit" class="btn btn-secondary px-4">登録して終了</button>
					</div>
				</form>
			</c:if>
=======
						</table>

						<input type="hidden" name="no" value="${no}"> <input
							type="hidden" name="subject_cd" value="${subject_cd}">
						<div class="col-12 mt-3">
							<button type="submit" class="btn btn-secondary">登録して終了</button>
						</div>
					</form>
				</c:when>
				<c:otherwise>
					<c:if test="${subject!=null}">
						<div>学生情報が存在しませんでした。</div>
					</c:if>
				</c:otherwise>

			</c:choose>
			<div class="mt-2 text-warning">${error}</div>
>>>>>>> branch 'master' of https://github.com/sakana-gif/Exam.git
		</section>
	</c:param>
</c:import>
 