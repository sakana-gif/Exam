package scoremanager.main;
 
import java.util.List;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;
 
public class TestListSubjectExecuteAction extends Action {
 
	@Override

	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//ローカル変数の宣言 1

		HttpSession session = req.getSession();

		Teacher teacher = (Teacher) session.getAttribute("user");

		School school = teacher.getSchool();

		int entYear = Integer.parseInt(req.getParameter("f1"));

		String classNum = req.getParameter("f2");

		String subjectCd = req.getParameter("f3");

		SubjectDao subDao = new SubjectDao();

		TestListSubjectDao tlsDao = new TestListSubjectDao();
 
		//リクエストパラメータ―の取得 2

		// 宣言時に実施
 
		//DBからデータ取得 3

		Subject subject = subDao.get(subjectCd, school);

		List<TestListSubject> tests = tlsDao.filter(entYear, classNum, subject, school);
 
		//ビジネスロジック 4

		// なし
 
		//DBへデータ保存 5

		// なし
 
		//レスポンス値をセット 6

		req.setAttribute("subject", subject);

		req.setAttribute("tests", tests);
 
		//JSPへフォワード 7

		req.getRequestDispatcher("test_list_subject.jsp").forward(req, res);

	}

}
 