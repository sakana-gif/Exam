package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDao extends Dao {

    private String baseSql = "SELECT t.subject_cd, s.name AS subject_name, t.no, t.point FROM test t JOIN subject s ON t.subject_cd = s.cd WHERE trim(t.student_no) = ?";

    private List<TestListStudent> postFilter(ResultSet rSet) throws Exception {
        List<TestListStudent> list = new ArrayList<>();
        try {
            while (rSet.next()) {
                TestListStudent testStudent = new TestListStudent();
                testStudent.setSubjectName(rSet.getString("subject_name"));
                testStudent.setSubjectCd(rSet.getString("subject_cd"));
                testStudent.setNum(rSet.getInt("no"));
                testStudent.setPoint(rSet.getInt("point"));
                list.add(testStudent);
            }
        } catch (SQLException e) {
            throw e;
        }
        return list;
    }

    /**
     * filterメソッド 生徒cdを指定して成績データを取得する
     * @param student 学生
     * @return List<TestListStudent>
     * @throws Exception
     */
    public List<TestListStudent> filter(Student student) throws Exception {

        List<TestListStudent> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            statement = connection.prepareStatement(baseSql);
            statement.setString(1, student.getNo());
            rSet = statement.executeQuery();
            list = postFilter(rSet);
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }

        return list;
    }
}