<%-- 科目変更完了JSP --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">

    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">

        <section>

            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                科目情報変更完了
            </h2>

            <p>科目情報を変更しました。</p>

            <a href="SubjectList.action">科目一覧へ戻る</a>

        </section>

    </c:param>

</c:import>