package scoremanager.main;

import bean.Teacher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 本来はここで画面から入力された点数リストを受け取りますが、
        // 今回は完了画面への遷移をメインに作成します
        
        // 保存処理の例（TestDaoのsaveを呼び出す）
        // TestDao dao = new TestDao();
        // dao.save(testList);

        // 完了画面へフォワード
        req.getRequestDispatcher("test_regist_done.jsp").forward(req, res);
    }
}