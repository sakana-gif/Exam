package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {

	// TEST,STUDENT,SCHOOLテーブルを参照し、
	// ENT_YEAR CLASS_NUM STUDENT_NO STUDENT_NAME NO1 NO2 を一括取得
	private String baseSql = "SELECT st.ENT_YEAR, st.CLASS_NUM, t.STUDENT_NO, st.NAME AS STUDENT_NAME,  MAX(CASE WHEN t.NO = 1 THEN t.POINT END) AS NO1, MAX(CASE WHEN t.NO = 2 THEN t.POINT END) AS NO2 FROM TEST t INNER JOIN STUDENT st ON t.STUDENT_NO = st.NO AND t.SCHOOL_CD = st.SCHOOL_CD INNER JOIN SUBJECT sub ON t.SUBJECT_CD = sub.CD AND t.SCHOOL_CD = sub.SCHOOL_CD WHERE st.ENT_YEAR = ? AND t.SCHOOL_CD = ? AND sub.CD = ? GROUP BY t.SCHOOL_CD, t.STUDENT_NO, st.NAME, st.ENT_YEAR, st.CLASS_NUM";

	//filterのテンプレートは使わない
	@SuppressWarnings("unused")
	private List<TestListSubject> postFilter(ResultSet rSet) throws Exception {
		return null;
	}

	/**
	 * filterメソッド 生徒cdを指定して成績データを取得する
	 *
	 * @param cd:String
	 *            生徒cd
	 * @return List<TestListStudent>
	 * @throws Exception
	 */
	public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school) throws Exception {

		List<TestListSubject> list = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet rSet = null;

		try {
			statement = connection.prepareStatement(baseSql);
			statement.setInt(1, entYear);
			statement.setString(2, school.getCd());
			statement.setString(3, subject.getCd());
			rSet = statement.executeQuery();

			while (rSet.next()) {
				TestListSubject testSubject = new TestListSubject();
				Map<Integer, Integer> points = new HashMap<Integer, Integer>();

				testSubject.setEntYear(entYear);
				testSubject.setClassNum(classNum);
				testSubject.setStudentNo(rSet.getString("STUDENT_NO"));
				testSubject.setStudentName(rSet.getString("STUDENT_NAME"));

				//１回目と２回目の点数をpointsに入れる
				points.put(1, rSet.getInt("NO1"));
				points.put(2, rSet.getInt("NO2"));
				//mapでpointsを入れる
				testSubject.setPoints(points);

				list.add(testSubject);
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