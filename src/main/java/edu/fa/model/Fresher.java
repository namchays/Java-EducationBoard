package edu.fa.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Fresher {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	
	@OneToOne
	private Address address;
	@OneToMany
	private List<Course> course = new ArrayList<Course>();
	@ManyToMany
	private Set<Group> group = new HashSet<Group>();
	


	public Fresher(String name, Set<Group> group) {
		super();
		this.name = name;
		this.group = group;
	}


	public Set<Group> getGroup() {
		return group;
	}


	public void setGroup(Set<Group> group) {
		this.group = group;
	}


	public Fresher(String name, List<Course> course) {
		super();
		this.name = name;
		this.course = course;
	}
	
	
	public Fresher(String name, Address address, List<Course> course) {
		super();
		this.name = name;
		this.address = address;
		this.course = course;
	}
	public List<Course> getCourse() {
		return course;
	}
	public void setCourse(List<Course> course) {
		this.course = course;
	}
	public Fresher(String name, Address address) {
		super();
		this.name = name;
		this.address = address;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Fresher() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Fresher(String name) {
		super();
		this.name = name;
	}
	
}
