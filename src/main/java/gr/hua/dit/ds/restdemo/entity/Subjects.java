package gr.hua.dit.ds.restdemo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "subs")
public class Subjects implements Serializable{

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "sub1")
	private String sub1;

	@Column(name = "sub2")
	private String sub2;

	@Column(name = "sub3")
	private String sub3;

	@Column(name = "sub4")
	private String sub4;

	@Column(name = "sub5")
	private String sub5;

	@Column(name = "grade1")
	private double grade1;

	@Column(name = "grade2")
	private double grade2;

	@Column(name = "teacher_name1")
	private String teacher_name1;

	@Column(name = "teacher_name2")
	private String teacher_name2;

	@Column(name = "state")
	private String state;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,CascadeType.REFRESH })
	@JoinColumn(name = "username", nullable = false)
	private User user;
	
	//Constructors
	public Subjects() {
		super();
	}

	public Subjects(User user, String sub1, String sub2, String sub3, String sub4, String sub5, double grade1, double grade2, String teacher_name1, String teacher_name2, String state) {
		super();
		this.user = user;
		this.sub1 = sub1;
		this.sub2 = sub2;
		this.sub3 = sub3;
		this.sub4 = sub4;
		this.sub5 = sub5;
		this.grade1 = grade1;
		this.grade2 = grade2;
		this.teacher_name1 = teacher_name1;
	    this.teacher_name2 = teacher_name2;
	    this.state = state;
	}

	//Setters+getters


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSub1() {
		return sub1;
	}

	public void setSub1(String sub1) {
		this.sub1 = sub1;
	}

	public String getSub2() {
		return sub2;
	}

	public void setSub2(String sub2) {
		this.sub2 = sub2;
	}

	public String getSub3() {
		return sub3;
	}

	public void setSub3(String sub3) {
		this.sub3 = sub3;
	}

	public String getSub4() {
		return sub4;
	}

	public void setSub4(String sub4) {
		this.sub4 = sub4;
	}

	public String getSub5() {
		return sub5;
	}

	public void setSub5(String sub5) {
		this.sub5 = sub5;
	}

	public double getGrade1() {
		return grade1;
	}

	public void setGrade1(double grade1) {
		this.grade1 = grade1;
	}

	public double getGrade2() {
		return grade2;
	}

	public void setGrade2(double grade2) {
		this.grade2 = grade2;
	}

	public String getTeacher_name1() {
		return teacher_name1;
	}

	public void setTeacher_name1(String teacher_name1) {
		this.teacher_name1 = teacher_name1;
	}

	public String getTeacher_name2() {
		return teacher_name2;
	}

	public void setTeacher_name2(String teacher_name2) {
		this.teacher_name2 = teacher_name2;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Subjects [id=" + id + ", sub1=" + sub1 + ", sub2=" + sub2 + ", sub3=" + sub3 + ", sub4="
				+ sub4 + ", sub5=" + sub5 + ", user=" + user + ", grade1 =" + grade1 + ", grade2" + grade2 + ", teacher_name1 =" + teacher_name1 +",teacher_name2 =" + teacher_name2 + ", state = " + state + " ]";
	}

	
}
