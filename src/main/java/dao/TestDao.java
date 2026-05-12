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

	private String baseSql = "select * from test where school_cd = ?";

	public Test get(Student student, Subject subject, School school, int no) throws Exception {
		Test test = null;
		Connection connection = getConnection();
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(baseSql + " and student_no = ? and subject_cd = ? and no = ?");
			statement.setString(1, school.getCd().trim());
			statement.setString(2, student.getNo().trim());
			statement.setString(3, subject.getCd().trim());
			statement.setInt(4, no);

			ResultSet resultSet = statement.executeQuery();
			List<Test> list = postFilter(resultSet, school);
			if (list.size() > 0) {
				test = list.get(0);
			} else {
				System.out.println("0件");
				System.out.println(student.getNo().trim());
				System.out.println(resultSet);

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

	private List<Test> postFilter(ResultSet rSet, School school) throws Exception {
		List<Test> list = new ArrayList<>();
		StudentDao studentDao = new StudentDao();
		SubjectDao subjectDao = new SubjectDao();
		try {
			while (rSet.next()) {
				Test test = new Test();
				test.setNo(rSet.getInt("no"));
				test.setPoint(rSet.getInt("point"));
				test.setClassNum(rSet.getString("class_num").trim());
				// Note: You would typically use StudentDao and SubjectDao here to populate objects
				test.setSchool(school);

				// 05/12 追加：Student,をtestに入れる
				test.setStudent(studentDao.get(rSet.getString("STUDENT_NO").trim()));
				test.setSubject(subjectDao.get(rSet.getString("SUBJECT_CD").trim(), school));

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

		// Combining the base SQL with filters for test management
		String sql = "select * from test join student on test.student_no = student.no where test.school_cd = ? and student.ent_year = ? and test.class_num = ? and test.subject_cd = ? and test.no = ?";

		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, school.getCd().trim());
			statement.setInt(2, entYear);
			statement.setString(3, classNum.trim());
			statement.setString(4, subject.getCd().trim());
			statement.setInt(5, num);

			ResultSet resultSet = statement.executeQuery();
			list = postFilter(resultSet, school);
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