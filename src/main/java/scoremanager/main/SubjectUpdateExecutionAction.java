package scoremanager.main;

import javax.security.auth.Subject;

import bean.School;
import bean.Teacher;
//import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectUpdateExecutionAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        School school = ((Teacher) req.getSession().getAttribute("user")).getSchool();

        String cd = req.getParameter("cd");
        String name = req.getParameter("name");

        Subject subject = new Subject();

//        subject.setCd(cd);
//        subject.setName(name);
//        subject.setSchool(school);
//
//        SubjectDao dao = new SubjectDao();

//        dao.save(subject);

        req.setAttribute("subject", subject);

        req.getRequestDispatcher("subject_update_done.jsp").forward(req, res);
    }
}