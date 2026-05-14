package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao {

	private String basesql = "select * from student left join test on test.student_no = student.no and test.subject_cd = ? and test.no = ? where student.ent_year = ? and student.class_num = ?";

	public Test get(Student student, Subject subject, School school, int num) throws Exception {
		Test test = null;
		Connection connection = getConnection();
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(basesql + " and student.no = ?");
			statement.setString(1, subject.getCd());
			statement.setInt(2, num);
			statement.setInt(3, student.getEntYear());
			statement.setString(4, student.getClassNum());
			statement.setString(5, student.getNo());

			ResultSet resultSet = statement.executeQuery();
			List<Test> list = postFilter(resultSet, subject, school, num);
			if (list.size() > 0) {
				test = list.get(0);
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}
		return test;
	}

	private List<Test> postFilter(ResultSet rSet, Subject subject, School school, int num) throws Exception {
		List<Test> list = new ArrayList<>();
		StudentDao studentDao = new StudentDao();
		try {
			while (rSet.next()) {
				Test test = new Test();
				test.setNo(num);
				test.setPoint(rSet.getInt("point"));
				test.setClassNum(rSet.getString("class_num").trim());
				// Note: You would typically use StudentDao and SubjectDao here to populate objects
				test.setSchool(school);

				// 05/13 追加：Student,Subjectをtestに入れる
				test.setStudent(studentDao.get(rSet.getString("student.no").trim()));
				test.setSubject(subject);

				list.add(test);
			}
		} catch (SQLException e) {
			throw e;
		}
		return list;
	}

	public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school) throws Exception {
		List<Test> list = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(basesql);
			statement.setString(1, subject.getCd());
			statement.setInt(2, num);
			statement.setInt(3, entYear);
			statement.setString(4, classNum.trim());

			//			
			//			statement.setString(1, school.getCd().trim());
			//			statement.setInt(2, entYear);
			//			statement.setString(3, classNum.trim());
			//			statement.setString(4, subject.getCd().trim());
			//			statement.setInt(5, num);

			ResultSet resultSet = statement.executeQuery();
			list = postFilter(resultSet, subject, school,num);
		} catch (Exception e) {
			throw e;
		} finally {
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}
		return list;
	}

	public boolean save(List<Test> list) throws Exception {
		Connection connection = getConnection();
		try {
			for (Test test : list) {
				save(test, connection);
			}
			return true;
		} catch (Exception e) {
			throw e;
		} finally {
			if (connection != null)
				connection.close();
		}
	}

	private boolean save(Test test, Connection connection) throws Exception {
		PreparedStatement statement = null;
		int count = 0;

		try {
			// Check if record exists using specific primary keys for Test
			String checkSql = "select count(*) from test where student_no = ? and subject_cd = ? and no = ?";
			statement = connection.prepareStatement(checkSql);
			statement.setString(1, test.getStudent().getNo().trim());
			statement.setString(2, test.getSubject().getCd().trim());
			statement.setInt(3, test.getNo());
			ResultSet rs = statement.executeQuery();
			rs.next();

			if (rs.getInt(1) == 0) {
				// Insert
				statement = connection.prepareStatement(
						"insert into test(student_no, subject_cd, school_cd, no, point, class_num) values(?, ?, ?, ?, ?, ?)");
				statement.setString(1, test.getStudent().getNo().trim());
				statement.setString(2, test.getSubject().getCd().trim());
				statement.setString(3, test.getSchool().getCd().trim());
				statement.setInt(4, test.getNo());
				statement.setInt(5, test.getPoint());
				statement.setString(6, test.getClassNum().trim());
			} else {
				// Update
				statement = connection.prepareStatement(
						"update test set point = ? where student_no = ? and subject_cd = ? and no = ?");
				statement.setInt(1, test.getPoint());
				statement.setString(2, test.getStudent().getNo().trim());
				statement.setString(3, test.getSubject().getCd().trim());
				statement.setInt(4, test.getNo());
			}
			count = statement.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			if (statement != null)
				statement.close();
		}
		return count > 0;
	}
}