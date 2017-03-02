package com.example.weblogin;

public class Student {
	
	String idno, fname, gname, campus, course, year;

	public Student(String idno, String fname, String gname, String campus,
			String course, String year) {
		super();
		this.idno = idno;
		this.fname = fname;
		this.gname = gname;
		this.campus = campus;
		this.course = course;
		this.year = year;
	}

	public String getIdno() {
		return idno;
	}

	public void setIdno(String idno) {
		this.idno = idno;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	

}
