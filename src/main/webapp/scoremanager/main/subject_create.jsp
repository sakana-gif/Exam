<<<<<<< HEAD
<%-- 科目登録JSP --%>
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
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目登録</h2>
			<form action="SubjectCreate.action" method="get">
				<div>
					<label for="cd">科目コード</label><br>
					<input class="form-control" type="text" id="cd" name="cd" value="${cd }" required maxlength="3" placeholder="科目コードを入力してください" />
				</div>
				<div class="mt-2 text-warning">${errors.get("1") }</div>
				<div class="mt-2 text-warning">${error }</div>
				<div>
					<label for="name">科目名</label><br>
					<input class="form-control" type="text" id="name" name="name" value="${name }" required maxlength="20" placeholder="科目名を入力してください" />
				</div>
				<div class="mt-2 text-warning">${errors.get("2") }</div>
				<div class="mx-auto py-2">
					<button class="btn btn-secondary" name="end">登録して終了</button>
				</div>
			</form>
			<a href="SubjectList.action">戻る</a>
		</section>
	</c:param>
=======
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
>>>>>>> branch 'master' of https://github.com/sakana-gif/Exam.git
</c:import>