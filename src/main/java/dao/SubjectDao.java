package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao extends Dao {

   
    public List<Subject> filter(School school) throws Exception {

        List<Subject> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(
                "select * from subject where school_cd = ? order by cd asc"
            );
            statement.setString(1, school.getCd());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Subject subject = new Subject();
                subject.setCd(resultSet.getString("cd"));
                subject.setName(resultSet.getString("name"));
                subject.setSchool(school);
                list.add(subject);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            close(statement, connection);
        }

        return list;
    }

    
    public Subject get(String cd, School school) throws Exception {

        Subject subject = null;
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(
                "select * from subject where cd = ? and school_cd = ?"
            );
            statement.setString(1, cd);
            statement.setString(2, school.getCd());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                subject = new Subject();
                subject.setCd(resultSet.getString("cd"));
                subject.setName(resultSet.getString("name"));
                subject.setSchool(school);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            close(statement, connection);
        }

        return subject;
    }

  
     
    public boolean save(Subject subject) throws Exception {

        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;

        try {
            statement = connection.prepareStatement(
                "insert into subject(school_cd, cd, name) values(?, ?, ?)"
            );
            statement.setString(1, subject.getSchool().getCd());
            statement.setString(2, subject.getCd());
            statement.setString(3, subject.getName());

            count = statement.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            close(statement, connection);
        }

        return count > 0;
    }

    public boolean delete(Subject subject) throws Exception {

        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;

        try {
            statement = connection.prepareStatement(
                "delete from subject where school_cd = ? and cd = ?"
            );
            statement.setString(1, subject.getSchool().getCd());
            statement.setString(2, subject.getCd());

            count = statement.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            close(statement, connection);
        }

        return count > 0;
    }
}