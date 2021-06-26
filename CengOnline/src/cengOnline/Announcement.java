package cengOnline;

import java.util.Date;

public class Announcement {
	
	private int announceID;
	private String announcement;
	private Date date;
	
	public Announcement(int announceID, String announcement, Date date) {
		super();
		this.announceID = announceID;
		this.announcement = announcement;
		this.date = date;
	}
	public int getAnnounceID() {
		return announceID;
	}
	public void setAnnounceID(int announceID) {
		this.announceID = announceID;
	}
	public String getAnnouncement() {
		return announcement;
	}
	public void setAnnouncement(String announcement) {
		this.announcement = announcement;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	
	public String toString()
	{
		return this.announceID + " " + this.announcement + " " + this.date;
 	}
}


