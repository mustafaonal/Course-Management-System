package cengOnline;

import java.util.List;
import java.util.ArrayList;

public class Course {

	private int courseID;
	private String name_short;
	private String courseName;
	private List<Teacher> teachers;
	private List<Student> students;
	private List<Assignment> assignments;
	private List<Announcement> announcements;
	private List<Post> posts;
	
	public Course(int courseID, String name_short, String courseName)
	{
		super();
		this.courseID = courseID;
		this.name_short = name_short;
		this.courseName = courseName;
		this.teachers = new ArrayList<Teacher>();
		this.students = new ArrayList<Student>();
		this.assignments = new ArrayList<Assignment>();
		this.announcements = new ArrayList<Announcement>();
		this.posts = new ArrayList<Post>();
	}
	
	public Course(int courseID, String name_short, String courseName, List<Teacher> teachers, List<Student> students) {
		super();
		this.courseID = courseID;
		this.name_short = name_short;
		this.courseName = courseName;
		this.teachers = teachers;
		this.students = students;
	}
	public Course(int courseID, String name_short, String courseName, List<Teacher> teachers, List<Student> students,
			List<Assignment> assignments, List<Announcement> announcements) {
		super();
		this.courseID = courseID;
		this.name_short = name_short;
		this.courseName = courseName;
		this.teachers = teachers;
		this.students = students;
		this.assignments = assignments;
		this.announcements = announcements;
	}

	
	
	@Override
	public String toString() {
		String newString = "Course ID: " + courseID + "  " + name_short + " " + courseName + " \nStudent List \n";
		
		for(Student st : this.students)
		{
			newString = newString + st.toString() + "\n";
		}
		newString = newString + "\nTeacher List\n";
		for(Teacher tc : this.teachers)
		{
			newString = newString + tc.toString() + "\n";
		}
		return newString;
	}

	public List<Announcement> getAnnouncements() {
		return announcements;
	}

	public void addtoAnnouncements(Announcement announcement) {
		this.announcements.add(announcement);
	}

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public String getName_short() {
		return name_short;
	}

	public void setName_short(String name_short) {
		this.name_short = name_short;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void addToPosts(Post post) {
		this.posts.add(post);
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void addtoTeachers(Teacher teacher) {
		this.teachers.add(teacher);
	}

	public List<Student> getStudents() {
		return students;
	}

	public void addtoStudents(Student student) {
		this.students.add(student);
	}

	public List<Assignment> getAssignments() {
		return assignments;
	}

	public void addtoAssignments(Assignment assignment) {
		this.assignments.add(assignment);
	}

	
	
}
