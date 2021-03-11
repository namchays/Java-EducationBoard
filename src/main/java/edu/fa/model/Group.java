package edu.fa.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import edu.fa.Constants;
@NamedQueries({
	@NamedQuery(name=Constants.GROUP_BY_NAME,query = "from Group WHERE name = :name")
})
@Entity
@Table(name = "Groups")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Group {
	@Id
	@GeneratedValue 
	private int id;
	
	private String name;
	@ManyToMany
	private Set<Fresher> freshers = new HashSet<Fresher>();
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return id+" - "+name;
	}
	public Group(int id, String name, Set<Fresher> freshers) {
		super();
		this.id = id;
		this.name = name;
		this.freshers = freshers;
	}
	public Group(String name, Set<Fresher> freshers) {
		super();
		this.name = name;
		this.freshers = freshers;
	}
	public Set<Fresher> getFreshers() {
		return freshers;
	}
	public void setFreshers(Set<Fresher> freshers) {
		this.freshers = freshers;
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
	public Group() {
		super();
		System.out.println("created Gr");
	}
	public Group(String name) {
		super();
		this.name = name;
	}
	
	
}
