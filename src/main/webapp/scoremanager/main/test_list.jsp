<%-- 成績参照 初期画面 JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
  <c:param name="title">
    得点管理システム
  </c:param>

  <c:param name="content">
    <section class="me-4">

      <h2 class="h3 mb-4">成績参照</h2>

      <%-- 科目・クラス検索 --%>
      <form action="TestList.action" method="get">
        <div class="row border-bottom pb-3 mb-4">

          <div class="col-4">
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

          <div class="col-4">
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

          <div class="col-2 d-flex align-items-end">
            <button type="submit" class="btn btn-secondary" id="searchBtn">検索</button>
          </div>

        </div>
      </form>

      <%-- 学生番号検索 --%>
      <form action="TestListStudentExecute.action" method="get">
        <div class="row border-bottom pb-3 mb-4">
          <h5>学生毎</h5>
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
          <%-- ここに成績一覧のテーブルを記述（必要であれば追加します） --%>
        </c:when>
        <c:otherwise>
          <p>入学年度とクラスを選択して検索してください</p>
        </c:otherwise>
      </c:choose>

    </section>
  </c:param>
</c:import>