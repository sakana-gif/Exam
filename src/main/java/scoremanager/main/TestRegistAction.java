package scoremanager.main;
 
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
		//ローカル変数の宣言 1
		HttpSession session = req.getSession();//セッション
		Teacher teacher = (Teacher) session.getAttribute("user");// ログインユーザー
		School school = teacher.getSchool();// ログインユーザーの所属学校
		String entYearStr = req.getParameter("f1");// 入力された入学年度
		String classNum = req.getParameter("f2");// 入力されたクラス番号
		String subjectCd = req.getParameter("f3");// 入力された科目コード
		String numStr = req.getParameter("f4");// 入力された回数
		int entYear = 0;
		int num = 0;
		Subject subject = null;
		List<Test> tests = null;// 成績リスト
		
		ClassNumDao cDao = new ClassNumDao();
		SubjectDao sDao = new SubjectDao();
		TestDao tDao = new TestDao();
		
		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();
		List<Integer> entYearSet = new ArrayList<>();
		List<String> classNumSet = cDao.filter(school);
		List<Subject> subjectSet = sDao.filter(school);
		List<Integer> numSet = new ArrayList<>();
 
		//リクエストパラメータ―の取得 2
		// ローカル変数宣言時に実施
 
		//DBからデータ取得 3
		// プルダウン用データの準備
		for (int i = year - 10; i <= year + 1; i++) {
			entYearSet.add(i);
		}
		numSet.add(1);
		numSet.add(2);
 
		//ビジネスロジック 4
		if (entYearStr != null && classNum != null && subjectCd != null && numStr != null
				&& !entYearStr.equals("0") && !classNum.equals("") && !subjectCd.equals("") && !numStr.equals("0")) {
			
			entYear = Integer.parseInt(entYearStr);
			num = Integer.parseInt(numStr);
			subject = sDao.get(subjectCd, school);
			
			// 絞り込み条件に一致する学生と成績を取得
			tests = tDao.filter(entYear, classNum, subject, num, school);
		}
 
		//DBへデータ保存 5
		// なし
 
		//レスポンス値をセット 6
		req.setAttribute("f1", entYearStr);
		req.setAttribute("f2", classNum);
		req.setAttribute("f3", subjectCd);
		req.setAttribute("f4", numStr);
		req.setAttribute("tests", tests);
		req.setAttribute("ent_year_set", entYearSet);
		req.setAttribute("class_num_set", classNumSet);
		req.setAttribute("subject_set", subjectSet);
		req.setAttribute("num_set", numSet);
 
		//JSPへフォワード 7
		req.getRequestDispatcher("test_regist.jsp").forward(req, res);
	}
}