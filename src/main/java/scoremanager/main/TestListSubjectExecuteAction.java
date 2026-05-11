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

public class TestListSubjectExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // セッションから情報を取得
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        // クラス番号リストを取得
        ClassNumDao classNumDao = new ClassNumDao();
        List<String> class_num_set = classNumDao.filter(school);
        req.setAttribute("class_num_set", class_num_set);

        // 科目リストを取得
        SubjectDao subjectDao = new SubjectDao();
        List<Subject> subject_set = subjectDao.filter(school);
        req.setAttribute("subject_set", subject_set);

        // 入学年度リスト
        int currentYear = LocalDate.now().getYear();
        List<Integer> ent_year_set = new ArrayList<>();
        for (int y = currentYear - 10; y <= currentYear + 10; y++) {
            ent_year_set.add(y);
        }
        req.setAttribute("ent_year_set", ent_year_set);

        // 科目別の成績参照画面へフォワード
        req.getRequestDispatcher("test_list_subject.jsp").forward(req, res);
    }
}