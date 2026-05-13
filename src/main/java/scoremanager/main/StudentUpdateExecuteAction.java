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

public class StudentUpdateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        School school = ((Teacher) req.getSession().getAttribute("user")).getSchool();

        String no       = req.getParameter("no");
        String name     = req.getParameter("name");
        String classNum = req.getParameter("class_num");
        int entYear     = Integer.parseInt(req.getParameter("ent_year"));
        String isAttendStr = req.getParameter("is_attend");

        int year = LocalDate.now().getYear();
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }
        req.setAttribute("ent_year_set", entYearSet);
        req.setAttribute("class_num_set", new ClassNumDao().filter(school));

        if (name == null || name.trim().isEmpty()) {
            req.setAttribute("error", "このフィールドを入力してください");
            req.setAttribute("no", no);
            req.setAttribute("name", name);
            req.setAttribute("class_num", classNum);
            req.setAttribute("ent_year", entYear);
            req.getRequestDispatcher("student_update.jsp").forward(req, res);
            return;
        }
        
        boolean isAttend = (isAttendStr != null);
        
        Student student = new Student();
        student.setNo(no);
        student.setName(name.trim());
        student.setEntYear(entYear);
        student.setClassNum(classNum);
        student.setAttend(isAttend);
        student.setSchool(school);

        new StudentDao().update(student);

        req.getRequestDispatcher("student_update_done.jsp").forward(req, res);
    }
}