package scoremanager.main;
 
import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;
 
public class SubjectDeleteExecuteAction extends Action {
 
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");
		School school = teacher.getSchool();
		String cd = "";
		Subject subject = null;
		SubjectDao sDao = new SubjectDao();
 
		//リクエストパラメータ―の取得 2
		cd = req.getParameter("cd");
 
		//DBからデータ取得 3
		subject = sDao.get(cd, school);
 
		//ビジネスロジック 4
		// なし
 
		//DBへデータ保存 5
		if (subject != null) {
			sDao.delete(subject);
		}
 
		//レスポンス値をセット 6
		// なし
 
		//JSPへフォワード 7
		req.getRequestDispatcher("subject_delete_done.jsp").forward(req, res);
	}
}