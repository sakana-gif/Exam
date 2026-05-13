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
 
	/**

	 * getメソッド 成績情報を1件取得する

	 */

	public Test get(Student student, Subject subject, School school, int no) throws Exception {

		Test test = new Test();

		Connection connection = getConnection();

		PreparedStatement statement = null;
 
		try {

			statement = connection.prepareStatement(

					"select * from test where student_no=? and subject_cd=? and school_cd=? and no=?");

			statement.setString(1, student.getNo());

			statement.setString(2, subject.getCd());

			statement.setString(3, school.getCd());

			statement.setInt(4, no);

			ResultSet resultSet = statement.executeQuery();
 
			if (resultSet.next()) {

				test.setStudent(student);

				test.setSubject(subject);

				test.setSchool(school);

				test.setNo(resultSet.getInt("no"));

				test.setPoint(resultSet.getInt("point"));

				test.setClassNum(resultSet.getString("class_num"));

			} else {

				test = null;

			}

		} catch (Exception e) {

			throw e;

		} finally {

			if (statement != null) {

				try { statement.close(); } catch (SQLException sqle) { throw sqle; }

			}

			if (connection != null) {

				try { connection.close(); } catch (SQLException sqle) { throw sqle; }

			}

		}

		return test;

	}
 
	/**

	 * saveメソッド リストの成績を一括保存する

	 */

	public boolean save(List<Test> list) throws Exception {

		Connection connection = getConnection();

		try {

			for (Test test : list) {

				save(test, connection);

			}

		} catch (Exception e) {

			throw e;

		} finally {

			if (connection != null) connection.close();

		}

		return true;

	}
 
	/**

	 * saveメソッド（1件保存用）

	 */

	private boolean save(Test test, Connection connection) throws Exception {

		PreparedStatement statement = null;

		int count = 0;

		try {

			Test old = get(test.getStudent(), test.getSubject(), test.getSchool(), test.getNo());

			if (old == null) {

				statement = connection.prepareStatement(

						"insert into test(student_no, subject_cd, school_cd, class_num, no, point) values(?, ?, ?, ?, ?, ?)");

				statement.setString(1, test.getStudent().getNo());

				statement.setString(2, test.getSubject().getCd());

				statement.setString(3, test.getSchool().getCd());

				statement.setString(4, test.getClassNum());

				statement.setInt(5, test.getNo());

				statement.setInt(6, test.getPoint());

			} else {

				statement = connection.prepareStatement(

						"update test set point=?, class_num=? where student_no=? and subject_cd=? and school_cd=? and no=?");

				statement.setInt(1, test.getPoint());

				statement.setString(2, test.getClassNum());

				statement.setString(3, test.getStudent().getNo());

				statement.setString(4, test.getSubject().getCd());

				statement.setString(5, test.getSchool().getCd());

				statement.setInt(6, test.getNo());

			}

			count = statement.executeUpdate();

		} catch (Exception e) {

			throw e;

		} finally {

			if (statement != null) statement.close();

		}

		return count > 0;

	}

    /**

     * filterメソッド（成績登録画面用）

     */

    public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school) throws Exception {

        List<Test> list = new ArrayList<>();

        Connection connection = getConnection();

        PreparedStatement statement = null;

        try {

            String sql = "SELECT s.no AS student_no, s.name AS student_name, s.ent_year, s.class_num, t.point "

                       + "FROM student s "

                       + "LEFT JOIN test t ON s.no = t.student_no AND t.subject_cd = ? AND t.school_cd = ? AND t.no = ? "

                       + "WHERE s.ent_year = ? AND s.class_num = ? AND s.school_cd = ? "

                       + "ORDER BY s.no ASC";
 
            statement = connection.prepareStatement(sql);

            statement.setString(1, subject.getCd());

            statement.setString(2, school.getCd());

            statement.setInt(3, num);

            statement.setInt(4, entYear);

            statement.setString(5, classNum);

            statement.setString(6, school.getCd());
 
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                Test t = new Test();

                Student s = new Student();

                s.setNo(rs.getString("student_no"));

                s.setName(rs.getString("student_name"));

                s.setEntYear(rs.getInt("ent_year"));

                s.setClassNum(rs.getString("class_num"));
 
                t.setStudent(s);

                t.setSubject(subject);

                t.setSchool(school);

                t.setNo(num);

                t.setClassNum(rs.getString("class_num"));

                t.setPoint(rs.getObject("point") != null ? rs.getInt("point") : -1);

                list.add(t);

            }

        } catch (Exception e) { throw e; } finally {

            if (statement != null) statement.close();

            if (connection != null) connection.close();

        }

        return list;

    }

}
 