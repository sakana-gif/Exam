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

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        
        String cd   = req.getParameter("cd").trim();
        String name = req.getParameter("name").trim();

        SubjectDao subjectDao = new SubjectDao();
        if (subjectDao.get(cd, school) != null) {
            req.setAttribute("error", "科目コードが重複しています");
            req.setAttribute("cd", cd);
            req.setAttribute("name", name);
            req.getRequestDispatcher("subject_create.jsp").forward(req, res);
            return;
        }

        Subject subject = new Subject();
        subject.setSchool(school);
        subject.setCd(cd);
        subject.setName(name);

        subjectDao.save(subject);

        req.getRequestDispatcher("subject_create_done.jsp").forward(req, res);
    }
}