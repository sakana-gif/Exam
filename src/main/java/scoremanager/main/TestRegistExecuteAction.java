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

		School school = teacher.getSchool();

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
 