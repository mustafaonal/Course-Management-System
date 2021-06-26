package cengOnline;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Assignment {
	
	private int assnID;
	private String title;
	private String instructions;
	private String due_date;
	private List<Attachment> attachments;

	
	public Assignment(int assnID, String title, String instructions, String due_date) {
		super();
		this.assnID = assnID;
		this.title = title;
		this.instructions = instructions;
		this.due_date = due_date;
		this.attachments=new ArrayList<Attachment>();
	}


	public Assignment(int assnID, String title, String instructions, String due_date,List<Attachment> attachments) {
		super();
		this.assnID = assnID;
		this.title = title;
		this.instructions = instructions;
		this.due_date = due_date;
		this.attachments = attachments;
	}

	
	
	public Assignment() {
		super();
	}


	@Override
	public String toString() {
		return "Assignment Title:" + title + " Instructions:" + instructions + " Due Date:" + due_date;
	}


	public int getAssnID() {
		return assnID;
	}


	public void setAssnID(int assnID) {
		this.assnID = assnID;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getInstructions() {
		return instructions;
	}


	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}


	public String getDue_date() {
		return due_date;
	}


	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}


	public List<Attachment> getAttachments() {
		return attachments;
	}


	public void addToAttachments(Attachment attachment) {
		this.attachments.add(attachment);
	}

}
