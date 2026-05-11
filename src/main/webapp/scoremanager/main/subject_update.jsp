<%-- 科目変更JSP --%>
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
                科目情報変更
            </h2>

            <form action="SubjectUpdateExecute.action" method="post">

                <!-- 科目コード -->
                <div>
                    <label for="cd">科目コード</label>
                    <input class="form-control"
                           type="text"
                           id="cd"
                           name="cd"
                           value="${subject.cd}"
                           readonly />
                </div>

                <!-- 科目名 -->
                <div class="mt-3">
                    <label for="name">科目名</label>
                    <input class="form-control"
                           type="text"
                           id="name"
                           name="name"
                           value="${subject.name}"
                           required
                           maxlength="30" />
                </div>

                <!-- エラー -->
                <div class="mt-2 text-warning">
                    ${error}
                </div>

                <!-- ボタン -->
                <div class="mx-auto py-2">
                    <button class="btn btn-secondary" name="end">
                        変更して終了
                    </button>
                </div>

            </form>

            <a href="SubjectList.action">戻る</a>

        </section>

    </c:param>

</c:import>