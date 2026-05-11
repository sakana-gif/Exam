package scoremanager.main;

import javax.security.auth.Subject;

import bean.School;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectUpdateAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        School school = ((Teacher) req.getSession().getAttribute("user")).getSchool();

        String cd = req.getParameter("cd");

        SubjectDao subjectDao = new SubjectDao();

        Subject subject = subjectDao.get(cd, school);

        req.setAttribute("subject", subject);

        req.getRequestDispatcher("subject_update.jsp").forward(req, res);
    }
}