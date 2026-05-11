package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import bean.ClassScore;
import bean.School;

public class ClassScoreDao extends Dao {

    public List<ClassScore> filter(School school, String classNum, String subjectCd) throws Exception {

        List<ClassScore> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            

        } catch (Exception e) {
            throw e;
        } finally {
            close(statement, connection);
        }

        return list;
    }
}