package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import bean.School;
import bean.Teacher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectCreateAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        
        if (req.getParameter("end") == null) {
            req.getRequestDispatcher("subject_create.jsp").forward(req, res);
            return;
        }

        
        String cd   = req.getParameter("cd");
        String name = req.getParameter("name");

        Map<String, String> errors = new HashMap<>();
        if (cd == null || cd.trim().isEmpty()) {
            errors.put("1", "このフィールドを入力してください");
        } else if (cd.trim().length() != 3) {
            errors.put("1", "科目コードは3文字で入力してください");
        }
        if (name == null || name.trim().isEmpty()) {
            errors.put("2", "このフィールドを入力してください");
        }

        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            req.setAttribute("cd", cd);
            req.setAttribute("name", name);
            req.getRequestDispatcher("subject_create.jsp").forward(req, res);
            return;
        }

        
        req.getRequestDispatcher("SubjectCreateExecute.action").forward(req, res);
    }
}