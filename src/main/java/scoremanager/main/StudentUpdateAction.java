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

public class StudentUpdateAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        School school = ((Teacher) req.getSession().getAttribute("user")).getSchool();
        String no = req.getParameter("no");
        StudentDao studentDao = new StudentDao();
        Student student = studentDao.get(no);
        int year = LocalDate.now().getYear();
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }
        req.setAttribute("ent_year_set", entYearSet);
        req.setAttribute("class_num_set", new ClassNumDao().filter(school));
        req.setAttribute("student", student);

        req.getRequestDispatcher("student_update.jsp").forward(req, res);
    }
}