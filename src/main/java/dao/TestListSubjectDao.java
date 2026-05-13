package dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.TestListSubject;
 
public class TestListSubjectDao extends Dao {
 
	/**

	 * filterメソッド 年度、クラス番号、科目、学校を指定して、科目別成績一覧を取得する

	 *

	 * @param entYear:int 入学年度

	 * @param classNum:String クラス番号

	 * @param subject:Subject 科目

	 * @param school:School 学校

	 * @return 科目別成績一覧のリスト:List<TestListSubject>

	 * @throws Exception

	 */

	public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school) throws Exception {

		List<TestListSubject> list = new ArrayList<>();

		Connection connection = getConnection();

		PreparedStatement statement = null;
 
		try {

			// 対象となる年度・クラスの学生を全て抽出し、該当科目の成績を外部結合(LEFT JOIN)する

			String sql = "SELECT st.ent_year, st.no AS student_no, st.name AS student_name, st.class_num, t.no, t.point "

					+ "FROM student st "

					+ "LEFT JOIN test t ON st.no = t.student_no AND t.subject_cd = ? AND t.school_cd = ? "

					+ "WHERE st.ent_year = ? AND st.class_num = ? AND st.school_cd = ? "

					+ "ORDER BY st.no asc, t.no asc";
 
			statement = connection.prepareStatement(sql);

			statement.setString(1, subject.getCd());

			statement.setString(2, school.getCd());

			statement.setInt(3, entYear);

			statement.setString(4, classNum);

			statement.setString(5, school.getCd());

			ResultSet resultSet = statement.executeQuery();
 
			TestListSubject currentStudent = null;
 
			while (resultSet.next()) {

				String studentNo = resultSet.getString("student_no");
 
				// 学生が切り替わるか、最初のループの場合に新しいBeanを作成

				if (currentStudent == null || !currentStudent.getStudentNo().equals(studentNo)) {

					currentStudent = new TestListSubject();

					currentStudent.setEntYear(resultSet.getInt("ent_year"));

					currentStudent.setStudentNo(studentNo);

					currentStudent.setStudentName(resultSet.getString("student_name"));

					currentStudent.setClassNum(resultSet.getString("class_num"));

					list.add(currentStudent);

				}
 
				// テストの回数(no)と得点(point)が存在する場合はMapにセット

				int no = resultSet.getInt("no");

				if (!resultSet.wasNull()) {

					int point = resultSet.getInt("point");

					currentStudent.putPoint(no, point);

				}

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
 