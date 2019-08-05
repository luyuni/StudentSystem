package cn.niyulu.entity;

public class Score {
	private Integer id;
	private Student student_id;
	private Double java;
	private Double database;
	private Double c;
	private Double linearalgebra;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Student getStudent_id() {
		return student_id;
	}

	public void setStudent_id(Student student_id) {
		this.student_id = student_id;
	}

	public Double getJava() {
		return java;
	}
	public void setJava(Double java) {
		this.java = java;
	}
	public Double getDatabase() {
		return database;
	}
	public void setDatabase(Double database) {
		this.database = database;
	}
	public Double getC() {
		return c;
	}
	public void setC(Double c) {
		this.c = c;
	}
	public Double getLinearalgebra() {
		return linearalgebra;
	}
	public void setLinearalgebra(Double linearalgebra) {
		this.linearalgebra = linearalgebra;
	}
	
}
