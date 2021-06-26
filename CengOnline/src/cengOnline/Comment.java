package cengOnline;

import java.util.Date;

public class Comment {
    private int commentId;
    private int postId; 
	private String sender;
	private String commentText;
	private Date date;
	
	
	public Comment(int commentId, int postId, String sender, String commentText, Date date) {
		this.commentId = commentId;
		this.postId = postId;
		this.sender = sender;
		this.commentText = commentText;
		this.date = date;
	}
	
	
	public Comment(int postId, String sender, String commentText, Date date) {
		this.postId = postId;
		this.sender = sender;
		this.commentText = commentText;
		this.date = date;
	}



	public int getCommentId() {
		return commentId;
	}


	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}


	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	
}
