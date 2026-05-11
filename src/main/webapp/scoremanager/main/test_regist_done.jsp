<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
  <c:param name="title">得点管理システム</c:param>
  <c:param name="content">
    <section class="me-4">
      <h2 class="h3 mb-4">登録完了</h2>
      <div class="alert alert-success">
        成績の登録が正常に完了しました。
      </div>
      <a href="TestRegist.action" class="btn btn-secondary">戻る</a>
    </section>
  </c:param>
</c:import>