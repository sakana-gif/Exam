<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-4">科目情報登録</h2>
            <form action="SubjectCreateExecute.action" method="post">
                <div class="row mb-3">
                    <div class="col-2">
                        <label class="form-label">科目コード</label>
                    </div>
                    <div class="col-4">
                        <input type="text" name="cd" class="form-control" 
                               placeholder="科目コードを入力してください" maxlength="3" required>
                    </div>
                </div>

                <div class="row mb-3">
                    <div class="col-2">
                        <label class="form-label">科目名</label>
                    </div>
                    <div class="col-4">
                        <input type="text" name="name" class="form-control" 
                               placeholder="科目名を入力してください" maxlength="20" required>
                    </div>
                </div>

                <div class="row mt-4">
                    <div class="col-2"></div>
                    <div class="col-4">
                        <button type="submit" class="btn btn-secondary">登録して終了</button>
                    </div>
                </div>
            </form>
            <div class="mt-3">
                <a href="SubjectList.action">戻る</a>
            </div>
        </section>
    </c:param>
</c:import>