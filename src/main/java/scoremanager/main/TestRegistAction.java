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
<<<<<<< HEAD
 
		//DBへデータ保存 5
		// なし
 
		//レスポンス値をセット 6
		req.setAttribute("f1", entYearStr);
		req.setAttribute("f2", classNum);
		req.setAttribute("f3", subjectCd);
		req.setAttribute("f4", numStr);
=======
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
		
		
>>>>>>> branch 'master' of https://github.com/sakana-gif/Exam.git
		req.setAttribute("tests", tests);
<<<<<<< HEAD
		req.setAttribute("ent_year_set", entYearSet);
		req.setAttribute("class_num_set", classNumSet);
		req.setAttribute("subject_set", subjectSet);
		req.setAttribute("num_set", numSet);
 
		//JSPへフォワード 7
=======
		req.setAttribute("subject", subject);
		
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
>>>>>>> branch 'master' of https://github.com/sakana-gif/Exam.git
		req.getRequestDispatcher("test_regist.jsp").forward(req, res);
	}
}