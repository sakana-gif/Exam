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
 
	/**
	 * filterメソッド 学生を指定して、その学生が受験したすべての科目の成績一覧を取得する
	 *
	 * @param student:Student 学生
	 * @return 学生別成績一覧のリスト:List<TestListStudent>
	 * @throws Exception
	 */
	public List<TestListStudent> filter(Student student) throws Exception {
		List<TestListStudent> list = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;
 
		try {
			// SUBJECTテーブルとTESTテーブルを結合
			String sql = "SELECT s.name AS subject_name, s.cd AS subject_cd, t.no, t.point "
					+ "FROM subject s "
					+ "JOIN test t ON s.cd = t.subject_cd AND s.school_cd = t.school_cd "
					+ "WHERE t.student_no = ? AND t.school_cd = ? "
					+ "ORDER BY s.cd asc, t.no asc";
 
			statement = connection.prepareStatement(sql);
			statement.setString(1, student.getNo());
			statement.setString(2, student.getSchool().getCd());
			ResultSet resultSet = statement.executeQuery();
 
			while (resultSet.next()) {
				TestListStudent testList = new TestListStudent();
				testList.setSubjectName(resultSet.getString("subject_name"));
				testList.setSubjectCd(resultSet.getString("subject_cd"));
				testList.setNum(resultSet.getInt("no"));
				testList.setPoint(resultSet.getInt("point"));
				list.add(testList);
			}
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