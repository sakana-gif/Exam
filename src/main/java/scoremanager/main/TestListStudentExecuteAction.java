package scoremanager.main;
 
import java.util.List;

import bean.Student;
import bean.TestListStudent;
import dao.StudentDao;
import dao.TestListStudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;
 
public class TestListStudentExecuteAction extends Action {
 
	@Override

	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//ローカル変数の宣言 1

		String studentNo = req.getParameter("f4");

		StudentDao sDao = new StudentDao();

		TestListStudentDao tlsDao = new TestListStudentDao();
 
		//リクエストパラメータ―の取得 2

		// 宣言時に実施
 
		//DBからデータ取得 3

		Student student = sDao.get(studentNo);

		List<TestListStudent> tests = null;

		if (student != null) {

			tests = tlsDao.filter(student);

		}
 
		//ビジネスロジック 4

		// なし
 
		//DBへデータ保存 5

		// なし
 
		//レスポンス値をセット 6

		req.setAttribute("student", student);

		req.setAttribute("tests", tests);
 
		//JSPへフォワード 7

		req.getRequestDispatcher("test_list_student.jsp").forward(req, res);

	}

}
 