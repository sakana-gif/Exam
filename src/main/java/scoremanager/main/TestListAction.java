package scoremanager.main;
 
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;
 
public class TestListAction extends Action {
 
	@Override

	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//ローカル変数の宣言 1

		HttpSession session = req.getSession();

		Teacher teacher = (Teacher) session.getAttribute("user");

		School school = teacher.getSchool();

		ClassNumDao cDao = new ClassNumDao();

		SubjectDao sDao = new SubjectDao();

		LocalDate todaysDate = LocalDate.now();

		int year = todaysDate.getYear();

		List<Integer> entYearSet = new ArrayList<>();

		List<String> classNumSet = cDao.filter(school);

		List<Subject> subjectSet = sDao.filter(school);
 
		//リクエストパラメータ―の取得 2

		// なし
 
		//DBからデータ取得 3

		for (int i = year - 10; i <= year + 1; i++) {

			entYearSet.add(i);

		}
 
		//ビジネスロジック 4

		// なし
 
		//DBへデータ保存 5

		// なし
 
		//レスポンス値をセット 6

		req.setAttribute("ent_year_set", entYearSet);

		req.setAttribute("class_num_set", classNumSet);

		req.setAttribute("subject_set", subjectSet);
 
		//JSPへフォワード 7

		req.getRequestDispatcher("test_list.jsp").forward(req, res);

	}

}
 