package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // セッションからログインユーザ（Teacher）を取得し、学校情報を取り出す
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        // 1. 入学年度リストの準備（今年を基準に前後10年）
        int currentYear = LocalDate.now().getYear();
        List<Integer> ent_year_set = new ArrayList<>();
        for (int y = currentYear - 10; y <= currentYear + 10; y++) {
            ent_year_set.add(y);
        }

        // 2. クラス番号リストをDBから取得
        ClassNumDao classNumDao = new ClassNumDao();
        List<String> class_num_set = classNumDao.filter(school);

        // 3. レスポンス値をセット
        req.setAttribute("ent_year_set", ent_year_set);
        req.setAttribute("class_num_set", class_num_set);

        // 4. 成績参照の初期画面（検索条件入力画面）へフォワード
        req.getRequestDispatcher("test_list.jsp").forward(req, res);
    }
}