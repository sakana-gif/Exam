<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/common/base.jsp">

  <c:param name="title">
    得点管理システム
  </c:param>

  <c:param name="content">
    <section class="me-4">

      <h2 class="h3 mb-4">成績参照</h2>

      <%-- 科目・クラス検索 --%>
      <form action="TestListSubjectExecute.action" method="get">
        <div class="row border-bottom pb-3 mb-4">

          <div class="col-3">
            <label class="form-label">入学年度</label>
            <select name="f1" class="form-select">
              <option value="">--------</option>
              <c:forEach var="year" items="${ent_year_set}">
                <option value="${year}" <c:if test="${year == f1}">selected</c:if>>
                  ${year}
                </option>
              </c:forEach>
            </select>
          </div>

          <div class="col-3">
            <label class="form-label">クラス</label>
            <select name="f2" class="form-select">
              <option value="">--------</option>
              <c:forEach var="num" items="${class_num_set}">
                <option value="${num}" <c:if test="${num == f2}">selected</c:if>>
                  ${num}
                </option>
              </c:forEach>
            </select>
          </div>

          <div class="col-3">
            <label class="form-label">科目</label>
            <select name="f3" class="form-select">
              <option value="">--------</option>
              <c:forEach var="subject" items="${subject_set}">
                <option value="${subject.cd}" <c:if test="${subject.cd == f3}">selected</c:if>>
                  ${subject.name}
                </option>
              </c:forEach>
            </select>
          </div>

          <div class="col-2 d-flex align-items-end">
            <button type="submit" class="btn btn-secondary">検索</button>
          </div>

          <div class="mt-2 text-warning">${error }</div>

        </div>
      </form>

      <%-- 学生番号検索 --%>
      <form action="TestListStudentExecute.action" method="get">
        <div class="row border-bottom pb-3 mb-4">
          <h5>学生情報</h5>
          <div class="col-4">
            <label class="form-label">学生番号</label>
            <input class="form-control" type="text" name="student_no" value="${student_no }" placeholder="学生番号を入力してください" />
          </div>
          <div class="col-2 d-flex align-items-end">
            <button type="submit" class="btn btn-secondary">検索</button>
          </div>
          <div class="mt-2 text-warning">${error }</div>
        </div>
      </form>

      <c:choose>
        <c:when test="${not empty tests}">
          <%-- 成績一覧テーブル --%>
        </c:when>
        <c:otherwise>
          <p>入学年度とクラスを選択して検索してください</p>
        </c:otherwise>
      </c:choose>

    </section>
  </c:param>
</c:import>

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
 
