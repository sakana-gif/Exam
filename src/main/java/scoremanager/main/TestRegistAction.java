package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");
		
		//セッションない場合login画面にリダイレクト
		if (teacher == null) {
			res.sendRedirect(req.getContextPath() + "/scoremanager/login.jsp");
		    return;
		}
		
		School school = teacher.getSchool();
		String entYearStr = "";
		String classNum = "";
		String subjectStr = "";
		String noStr = "";
		int entYear = 0;
		int no = 0;
		Map<String, String> errors = new HashMap<>();
		List<Test> tests = null;

		entYearStr = req.getParameter("ent_year");
		classNum = req.getParameter("class_num");
		subjectStr = req.getParameter("subject_cd");
		noStr = req.getParameter("no");
		
		
		
		if (entYearStr != null) {
			entYear = Integer.parseInt(entYearStr);
		}
		if (noStr != null) {
			no = Integer.parseInt(noStr);
		}

		req.setAttribute("ent_year", entYear);
		req.setAttribute("class_num", classNum);
		req.setAttribute("subject_cd", subjectStr);
		req.setAttribute("no", no);

		SubjectDao subjectDao = new SubjectDao();
		Subject subject = subjectDao.get(subjectStr, school);
		
		//入力欄チェック
		if (entYear == 0 || classNum == null || classNum.equals("0") || subjectStr == null || no == 0) {
			errors.put("error", "入力値が間違っています");
			req.setAttribute("errors", errors);
		}else {
			//学生一覧取得
			TestDao testDao = new TestDao();
			tests = testDao.filter(entYear, classNum, subject, no, school);
		}

		req.setAttribute("tests", tests);

		// クラス番号リストを取得
		ClassNumDao classNumDao = new ClassNumDao();
		List<String> class_num_set = classNumDao.filter(school);
		req.setAttribute("class_num_set", class_num_set);

		// 入学年度リスト
		int currentYear = LocalDate.now().getYear();
		List<Integer> ent_year_set = new ArrayList<>();
		for (int y = currentYear - 10; y <= currentYear + 1; y++) {
			ent_year_set.add(y);
		}
		req.setAttribute("ent_year_set", ent_year_set);

		// 科目リストを取得
		//        SubjectDao subjectDao = new SubjectDao();
		List<Subject> subject_set = subjectDao.filter(school);
		req.setAttribute("subject_set", subject_set);

		// 回数リスト（1回、2回など）
		List<Integer> no_set = new ArrayList<>();
		no_set.add(1);
		no_set.add(2);
		req.setAttribute("num_set", no_set);

		// 成績登録画面へ
		req.getRequestDispatcher("test_regist.jsp").forward(req, res);
	}
}