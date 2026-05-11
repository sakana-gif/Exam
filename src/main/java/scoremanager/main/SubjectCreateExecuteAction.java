package scoremanager.main;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // Retrieve parameters
        String cd = req.getParameter("cd");
        String name = req.getParameter("name");

        // Get school from session
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        // Create Subject bean and populate it
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(school);

        // Save to database via DAO
        SubjectDao sDao = new SubjectDao();
        sDao.save(subject);

        // Forward to the completion screen
        req.getRequestDispatcher("subject_create_done.jsp").forward(req, res);
    }
}