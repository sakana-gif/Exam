package bean;
 
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
 
public class TestListSubject implements Serializable {
 
	private int entYear;         // 入学年度

	private String studentNo;    // 学生番号

	private String studentName;  // 氏名

	private String classNum;     // クラス

	private Map<Integer, Integer> points; // 回数と点数のマップ
 
	public TestListSubject() {

		this.points = new HashMap<>();

	}
 
	public int getEntYear() {

		return entYear;

	}
 
	public void setEntYear(int entYear) {

		this.entYear = entYear;

	}
 
	public String getStudentNo() {

		return studentNo;

	}
 
	public void setStudentNo(String studentNo) {

		this.studentNo = studentNo;

	}
 
	public String getStudentName() {

		return studentName;

	}
 
	public void setStudentName(String studentName) {

		this.studentName = studentName;

	}
 
	public String getClassNum() {

		return classNum;

	}
 
	public void setClassNum(String classNum) {

		this.classNum = classNum;

	}
 
	public Map<Integer, Integer> getPoints() {

		return points;

	}
 
	public void setPoints(Map<Integer, Integer> points) {

		this.points = points;

	}
 
	public int getPoint(int key) {

		if (points.containsKey(key)) {

			return points.get(key);

		}

		return -1; // 未受験の場合は-1を返す

	}
 
	public void putPoint(int key, int value) {

		points.put(key, value);

	}

}
 