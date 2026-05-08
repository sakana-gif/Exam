package bean;

import java.io.Serializable;

public class Subject implements Serializable {
    private String schoolCd;
    private String cd;
    private String name;
	/**
	 * @return schoolCd
	 */
	public String getSchoolCd() {
		return schoolCd;
	}
	/**
	 * @param schoolCd セットする schoolCd
	 */
	public void setSchoolCd(String schoolCd) {
		this.schoolCd = schoolCd;
	}
	/**
	 * @return cd
	 */
	public String getCd() {
		return cd;
	}
	/**
	 * @param cd セットする cd
	 */
	public void setCd(String cd) {
		this.cd = cd;
	}
	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name セットする name
	 */
	public void setName(String name) {
		this.name = name;
	}
}
