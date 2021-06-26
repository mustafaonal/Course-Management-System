package cengOnline;

public class Attachment {
	private int studentID;
	private int assignmentID;
	private String attachment;
	private int grade;
	
	public Attachment(int studentID, int assignmentID, String attachment) {
		super();
		this.studentID = studentID;
		this.assignmentID = assignmentID;
		this.attachment = attachment;
	}
	
	

	public Attachment(int studentID, int assignmentID) {
		super();
		this.studentID = studentID;
		this.assignmentID = assignmentID;
	}
	
	public Attachment(int studentID, int assignmentID, String attachment, int grade) {
		super();
		this.studentID = studentID;
		this.assignmentID = assignmentID;
		this.attachment = attachment;
		this.grade = grade;
	}


	public int getGrade() {
		return grade;
	}



	public void setGrade(int grade) {
		this.grade = grade;
	}



	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	public int getAssignmentID() {
		return assignmentID;
	}

	public void setAssignmentID(int assignmentID) {
		this.assignmentID = assignmentID;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	

}
