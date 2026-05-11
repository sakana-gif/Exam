<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
  <c:param name="title">得点管理システム</c:param>
  <c:param name="content">
    <section class="me-4">
      <h2 class="h3 mb-4">成績登録</h2>
      <form action="TestRegistExecute.action" method="post">
        <div class="row border-bottom pb-3 mb-4">
          <div class="col-3">
            <label class="form-label">入学年度</label>
            <select name="ent_year" class="form-select" required>
              <option value="">--------</option>
              <c:forEach var="year" items="${ent_year_set}">
                <option value="${year}">${year}</option>
              </c:forEach>
            </select>
          </div>
          <div class="col-3">
            <label class="form-label">クラス</label>
            <select name="class_num" class="form-select" required>
              <option value="">--------</option>
              <c:forEach var="num" items="${class_num_set}">
                <option value="${num}">${num}</option>
              </c:forEach>
            </select>
          </div>
          <div class="col-3">
            <label class="form-label">科目</label>
            <select name="subject_cd" class="form-select" required>
              <option value="">--------</option>
              <c:forEach var="subject" items="${subject_set}">
                <option value="${subject.cd}">${subject.name}</option>
              </c:forEach>
            </select>
          </div>
          <div class="col-3">
            <label class="form-label">回数</label>
            <select name="no" class="form-select" required>
              <c:forEach var="n" items="${num_set}">
                <option value="${n}">${n}回目</option>
              </c:forEach>
            </select>
          </div>
          <div class="col-12 mt-3 text-end">
            <button type="submit" class="btn btn-primary">登録する</button>
          </div>
        </div>
      </form>
    </section>
  </c:param>
</c:import>