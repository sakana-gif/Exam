package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

       
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        
        ClassNumDao classNumDao = new ClassNumDao();
        List<String> class_num_set = classNumDao.filter(school);
        req.setAttribute("class_num_set", class_num_set);

       
        SubjectDao subjectDao = new SubjectDao();
        List<Subject> subject_set = subjectDao.filter(school);
        req.setAttribute("subject_set", subject_set);

      
        int currentYear = LocalDate.now().getYear();
        List<Integer> ent_year_set = new ArrayList<>();
        for (int y = currentYear - 10; y <= currentYear + 10; y++) {
            ent_year_set.add(y);
        }
        req.setAttribute("ent_year_set", ent_year_set);

        
        String entYearStr = req.getParameter("f1");
        String classNum   = req.getParameter("f2");
        String subjectCd  = req.getParameter("f3");

        
        if (entYearStr == null || entYearStr.isEmpty() ||
            classNum == null || classNum.isEmpty() ||
            subjectCd == null || subjectCd.isEmpty()) {
            req.setAttribute("error", "入学年度とクラスと科目を選択してください");
            req.getRequestDispatcher("test_list.jsp").forward(req, res);
            return;
        }

        int entYear = Integer.parseInt(entYearStr);

      
        Subject subject = subjectDao.get(subjectCd, school);

      
        TestListSubjectDao testListSubjectDao = new TestListSubjectDao();
        List<TestListSubject> testList = testListSubjectDao.filter(entYear, classNum, subject, school);

        req.setAttribute("f1", entYear);
        req.setAttribute("f2", classNum);
        req.setAttribute("f3", subjectCd);
        req.setAttribute("testList", testList);

        req.getRequestDispatcher("test_list_subject.jsp").forward(req, res);
    }
}