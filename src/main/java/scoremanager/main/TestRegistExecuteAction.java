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

		//ローカル変数の宣言 1

		HttpSession session = req.getSession();

		Teacher teacher = (Teacher) session.getAttribute("user");
<<<<<<< HEAD
=======

		//セッションない場合login画面にリダイレクト
		if (teacher == null) {
			res.sendRedirect(req.getContextPath() + "/scoremanager/login.jsp");
			return;
		}
>>>>>>> branch 'master' of https://github.com/sakana-gif/Exam.git

<<<<<<< HEAD
=======
		String[] studentsNo = req.getParameterValues("student_no");
		String noStr = req.getParameter("no");
		int no = 0;
		String subject_cd = req.getParameter("subject_cd");

		TestDao testDao = new TestDao();
		StudentDao studentDao = new StudentDao();
		SubjectDao subjectDao = new SubjectDao();
>>>>>>> branch 'master' of https://github.com/sakana-gif/Exam.git
		School school = teacher.getSchool();
<<<<<<< HEAD
=======
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
>>>>>>> branch 'master' of https://github.com/sakana-gif/Exam.git

		String subjectCd = req.getParameter("subject_cd");

		int num = Integer.parseInt(req.getParameter("num"));

		String[] studentNoSet = req.getParameterValues("student_no_set");

		SubjectDao subDao = new SubjectDao();

		StudentDao stuDao = new StudentDao();

		TestDao testDao = new TestDao();

		List<Test> testList = new ArrayList<>();
 
		//リクエストパラメータ―の取得 2

		// 宣言時に実施
 
		//DBからデータ取得 3

		Subject subject = subDao.get(subjectCd, school);
 
		//ビジネスロジック 4

		if (studentNoSet != null) {

			for (String no : studentNoSet) {

				String pointStr = req.getParameter("point_" + no);

				if (pointStr != null && !pointStr.equals("")) {

					int point = Integer.parseInt(pointStr);

					Student student = stuDao.get(no);

					Test test = new Test();

					test.setStudent(student);

					test.setSubject(subject);

					test.setSchool(school);

					test.setNo(num);

					test.setPoint(point);

					test.setClassNum(student.getClassNum());

					testList.add(test);

				}

			}

		}
 
		//DBへデータ保存 5

		testDao.save(testList);
 
		//レスポンス値をセット 6

		// なし
 
		//JSPへフォワード 7

		req.getRequestDispatcher("test_regist_done.jsp").forward(req, res);

	}

}
 