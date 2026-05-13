package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");

		//セッションない場合login画面にリダイレクト
		if (teacher == null) {
			res.sendRedirect(req.getContextPath() + "/scoremanager/login.jsp");
			return;
		}

		String[] studentsNo = req.getParameterValues("student_no");
		String noStr = req.getParameter("no");
		int no = 0;
		String subject_cd = req.getParameter("subject_cd");

		TestDao testDao = new TestDao();
		StudentDao studentDao = new StudentDao();
		SubjectDao subjectDao = new SubjectDao();
		School school = teacher.getSchool();
		List<Test> list = new ArrayList<>();

		if (noStr != null && !noStr.isEmpty()) {
			no = Integer.parseInt(noStr);
		}

		for (String studentNo : studentsNo) {
			Student student = studentDao.get(studentNo);
			Subject subject = subjectDao.get(subject_cd, school);
			String pointStr = req.getParameter("point_" + studentNo);
			int point = 0;

			if (pointStr != null && !pointStr.isEmpty()) {
				point = Integer.parseInt(pointStr);
			}

			if (point < 0 || point > 100) {
				req.setAttribute("error", "０～１００の間で入力してください");
				req.getRequestDispatcher("test_regist.jsp").forward(req, res);
				return;
			}

			Test test = testDao.get(student, subject, school, no);
			test.setPoint(point);

			list.add(test);
		}

		testDao.save(list);

		// 完了画面へフォワード
		req.getRequestDispatcher("test_regist_done.jsp").forward(req, res);
	}
}