package cengOnline;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Post {
	private int postId;
	private int courseId;
	private String postText;
	private String sender;
	private List<Comment> comments;
	private Date date;
	
	
	public Post(int postId, int courseId, String postText, String sender) {
		this.postId = postId;
		this.courseId = courseId;
		this.postText = postText;
		this.sender = sender;
		this.comments = new ArrayList<Comment>();
	} 
	public Post(int postId, int courseId, String postText, String sender, Date date) {
		this.postId = postId;
		this.courseId = courseId;
		this.postText = postText;
		this.sender = sender;
		this.comments = new ArrayList<Comment>();
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Post(int postId, String postText, String sender) {
		this.postId = postId;
		this.postText = postText;
		this.sender = sender;
	}
	
	
	public List<Comment> getComments() {
		return comments;
	}

	public void addToComments(Comment comment) {
		this.comments.add(comment);
	}

	public int getCourseId() {
		return courseId;
	}



	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}


	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getPostText() {
		return postText;
	}

	public void setPostText(String postText) {
		this.postText = postText;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}
	
	

}
