package edu.fa;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.derby.tools.sysinfo;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import edu.fa.model.Address;
import edu.fa.model.Course;
import edu.fa.model.Fresher;
import edu.fa.model.Group;
import edu.fa.model.Syllabus;

public class Management {

	public static void main(String[] args) {
//		createCourseSyllabuses();
//		getCourseSyllabuses(1);
//		createFresher();
//		createFresherAndCourse();
//		createFresherAndGroup();
		createGroup();
		showFirstLevel();
		ConnectionUtil.getSessionFactory().close();
	}

	private static void createFresher() {
		Address address = new Address("Duy tan", "Cau Giay");
		Fresher fresher = new Fresher("ducky fresher", address);
		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(address);
		session.save(fresher);
		session.getTransaction().commit();
		Fresher course2 = (Fresher) session.get(Fresher.class, 1);
		System.out.println(course2);
	}

	private static void createFresherAndAddress() {
		Address address = new Address("Duy tan", "Cau Giay");
		Fresher fresher = new Fresher("ducky fresher", address);
		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(address);
		session.save(fresher);
		session.getTransaction().commit();
		Fresher course2 = (Fresher) session.get(Fresher.class, 1);
		System.out.println(course2);
	}
	private static void createFresherAndCourse() {
		List<Course> courses = new ArrayList<Course>();
		courses.add(new Course("Java", new Date()));
		courses.add(new Course("Hibernate", new Date()));
		Fresher fresher = new Fresher("ducky fresher", courses);
		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		for(Course course : courses) {
			session.save(course);
		}
		session.save(fresher);
		session.getTransaction().commit();
		Fresher course2 = (Fresher) session.get(Fresher.class, 1);
		System.out.println(course2);
	}
	private static void createFresherAndGroup() {
		Fresher fresher1 = new Fresher("nam");
		Fresher fresher2 = new Fresher("Lan");
		Group group1 = new Group("Group 1");
		Group group2 = new Group("Group 2");
		Set<Fresher> freshers = new HashSet<Fresher>();
		freshers.add(fresher2);
		freshers.add(fresher1);
		
		Set<Group> groups = new HashSet<Group>();
		groups.add(group2);
		groups.add(group1);
		
		fresher1.setGroup(groups);
		fresher2.setGroup(groups);
		group1.setFreshers(freshers);
		group2.setFreshers(freshers);
		
		
		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
	
		session.save(fresher1);
		session.save(fresher2);
		session.save(group1);
		session.save(group2);
		session.getTransaction().commit();
		Fresher course2 = (Fresher) session.get(Fresher.class, 1);
		System.out.println(course2);
	}
	private static void createGroup() {
		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Group javaGroup = new Group("Java Group");
		Group jsGroup = new Group("javascript Group");
		session.save(javaGroup);
		session.save(jsGroup);
		
		session.getTransaction().commit();
	}
	private static void getGroup() {
		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Group group =(Group) session.get(Group.class, 1);
		
		
		System.out.println(group);
		group.setName("new set name");
		session.delete(group);
		session.getTransaction().commit();
		System.out.println(group);
		
	}
	private static void queryGroupUsingHQL() {
		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String queryStr = "FROM Group WHERE id =:id and name like :name";
	
		Query query = session.createQuery(queryStr);
		query.setParameter("id", 1);
		query.setString("name", "Java%");
		List<Group> groups = (List<Group>)query.list();
		System.out.println(groups);
		
	}
	private static void selectFrom() {
		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String queryStr = "SELECT name FROM Group WHERE id =:id and name like :name";
		
		Query query = session.createQuery(queryStr);
		query.setParameter("id", 1);
		query.setString("name", "Java%");
		List<Group> groups = (List<Group>)query.list();
		System.out.println(groups);
		
	}
	private static void UpdateGroupUsingHQL() {
	try {
		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String queryStr = "update Group set name = :name WHERE id = :id";
		
		Query query = session.createQuery(queryStr);
		query.setParameter("id", 1);
		query.setParameter("name", "Funny Update");
		int result = query.executeUpdate();
		System.out.println("result"+result);
		session.getTransaction().commit();
	
	} catch (Exception e) {
		e.printStackTrace();
	}		
	}
	private static void deleteGroupUsingHQL() {
		try {
			SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			String queryStr = "delete from Group WHERE id = :id";
			
			Query query = session.createQuery(queryStr);
			query.setParameter("id", 1);
			int result = query.executeUpdate();
			System.out.println("result"+result);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	private static void useCriteria() {
		try {
			SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Criteria groupCriteria = session.createCriteria(Group.class);
//			groupCriteria.add(Restrictions.or(Restrictions.eq("id",1),Restrictions.like("name","java%")));
			SimpleExpression eq = Restrictions.eq("id",1);
			SimpleExpression like = Restrictions.like("name","java%");
			LogicalExpression le = Restrictions.or(eq,like);
			groupCriteria.add(le);
			System.out.println(groupCriteria.list());
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	private static void useNameQuery() {
		try {
			SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.getNamedQuery(Constants.GROUP_BY_NAME);
			query.setParameter("name", "Java Group");
			System.out.println(query.list());
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	private static void showFirstLevel() {
		try {
			SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Group group1 = (Group) session.get(Group.class, 1);
			System.out.println(group1);
			session.getTransaction().commit();
			session.close();

			
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			group1 =null;
			group1 = (Group) session.get(Group.class, 1);
			session.getTransaction().commit();
			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	private static void getCourseSyllabuses(int id) {
		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Course course2 = (Course) session.get(Course.class, id);
		System.out.println(course2.getName());
		session.getTransaction().commit();
		session.close();
		System.out.println(course2.getSyllabuses());
	}

	private static void createCourseSyllabuses() {
		List<Syllabus> syllabuses = new ArrayList<Syllabus>();
		syllabuses.add(new Syllabus("Hibernate content", 32));

		syllabuses.add(new Syllabus("Hibernate content 2", 33));

		Course course = new Course("Hibernate", new Date(), syllabuses);
		/// insert course

		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(course);
		session.getTransaction().commit();
		Course course2 = (Course) session.get(Course.class, 1);
		System.out.println(course2);
	}

}
