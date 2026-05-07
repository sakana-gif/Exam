package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class StudentCreateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // セッションから学校情報を取得
        School school = ((Teacher) req.getSession().getAttribute("user")).getSchool();

        // セレクトボックス用データをセット
        int year = LocalDate.now().getYear();
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }
        req.setAttribute("ent_year_set", entYearSet);
        req.setAttribute("class_num_set", new ClassNumDao().filter(school));

        // パラメータ取得
        String no       = req.getParameter("no").trim();
        String name     = req.getParameter("name").trim();
        String classNum = req.getParameter("class_num");
        int entYear     = Integer.parseInt(req.getParameter("ent_year"));

        // 学生番号の重複確認
        StudentDao studentDao = new StudentDao();
        if (studentDao.get(no) != null) {
            req.setAttribute("error", "学生番号が重複しています");
            req.setAttribute("ent_year", entYear);
            req.setAttribute("no", no);
            req.setAttribute("name", name);
            req.setAttribute("class_num", classNum);
            req.getRequestDispatcher("student_create.jsp").forward(req, res);
            return;
        }

        // 学生を登録
        Student student = new Student();
        student.setNo(no);
        student.setName(name);
        student.setEntYear(entYear);
        student.setClassNum(classNum);
        student.setAttend(true);
        student.setSchool(school);

        studentDao.save(student);

        req.getRequestDispatcher("sutudent_create_done.jsp").forward(req, res);
    }
}