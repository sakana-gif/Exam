package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectCreateAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // Session information is retrieved but not strictly used for list-fetching here
        // as subjects usually just need a code and a name input.
        
        // Forward to the registration screen
        req.getRequestDispatcher("subject_create.jsp").forward(req, res);
    }
}