package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class StudentCreateAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // セッションから学校情報を取得
        School school = ((Teacher) req.getSession().getAttribute("user")).getSchool();

        // セレクトボックス用データをセット
        setSelectOptions(req, school);

        // 初期表示
        if (req.getParameter("end") == null) {
            req.getRequestDispatcher("student_create.jsp").forward(req, res);
            return;
        }

        // パラメータ取得
        String entYearStr = req.getParameter("ent_year");
        String no         = req.getParameter("no");
        String name       = req.getParameter("name");
        String classNum   = req.getParameter("class_num");

        // バリデーション
        Map<String, String> errors = new HashMap<>();
        if (entYearStr == null || entYearStr.equals("0")) errors.put("1", "入学年度を選択してください");
        if (no == null || no.trim().isEmpty())            errors.put("2", "学生番号を入力してください");
        if (name == null || name.trim().isEmpty())        errors.put("3", "氏名を入力してください");

        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            req.setAttribute("ent_year", entYearStr);
            req.setAttribute("no", no);
            req.setAttribute("name", name);
            req.setAttribute("class_num", classNum);
            req.getRequestDispatcher("student_create.jsp").forward(req, res);
            return;
        }

        // ExecuteActionへ転送
        req.getRequestDispatcher("StudentCreateExecuteAction.action").forward(req, res);
    }

    // セレクトボックス用データをセット
    private void setSelectOptions(HttpServletRequest req, School school) throws Exception {
        int year = LocalDate.now().getYear();
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }
        req.setAttribute("ent_year_set", entYearSet);
        req.setAttribute("class_num_set", new ClassNumDao().filter(school));
    }
}