package bean;

import java.io.Serializable;

public class ClassScore implements Serializable {

    private String classNum;   
    private String studentNo;  
    private String studentName;
    private String subjectCd;  
    private String subjectName;
    private int no;            
    private int point;
	/**
	 * @return classNum
	 */
	public String getClassNum() {
		return classNum;
	}
	/**
	 * @param classNum セットする classNum
	 */
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}
	/**
	 * @return studentNo
	 */
	public String getStudentNo() {
		return studentNo;
	}
	/**
	 * @param studentNo セットする studentNo
	 */
	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}
	/**
	 * @return studentName
	 */
	public String getStudentName() {
		return studentName;
	}
	/**
	 * @param studentName セットする studentName
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	/**
	 * @return subjectCd
	 */
	public String getSubjectCd() {
		return subjectCd;
	}
	/**
	 * @param subjectCd セットする subjectCd
	 */
	public void setSubjectCd(String subjectCd) {
		this.subjectCd = subjectCd;
	}
	/**
	 * @return subjectName
	 */
	public String getSubjectName() {
		return subjectName;
	}
	/**
	 * @param subjectName セットする subjectName
	 */
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	/**
	 * @return no
	 */
	public int getNo() {
		return no;
	}
	/**
	 * @param no セットする no
	 */
	public void setNo(int no) {
		this.no = no;
	}
	/**
	 * @return point
	 */
	public int getPoint() {
		return point;
	}
	/**
	 * @param point セットする point
	 */
	public void setPoint(int point) {
		this.point = point;
	}         

}