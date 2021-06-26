package cengOnline;
import java.util.Date;
public class Message {

	private int messageID;
	private String sender;
	private String receiver;
	private String title;
	private Date date;
	private String message;
	
	public Message(int messageID, String string, String string2, String title,Date date, String message) {
		super();
		this.messageID = messageID;
		this.sender = string;
		this.receiver = string2;
		this.title = title;
		this.date = date;
		this.message = message;
	}
	
	
	
	@Override
	public String toString() {
		return "ID:" + this.messageID + "Title:" + this.title + "Date:" + this.date + "Message:" + this.message;
		
	}



	public int getMessageID() {
		return messageID;
	}
	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}
	public String getSender() {
		return this.sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
