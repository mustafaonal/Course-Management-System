package cengOnline;

import java.util.ArrayList;
import java.util.List;

public class MailBox {

	private int mailID;
	private List<Message> inbox;
	private List<Message> sent;
	
	public MailBox(int mailID, List<Message> inbox, List<Message> sent) {
		super();
		this.mailID = mailID;
		this.inbox = new ArrayList<Message>();
		this.sent = new ArrayList<Message>();
	}

	public MailBox(int mailID) {
		super();
		this.mailID = mailID;
		this.inbox = new ArrayList<Message>();
		this.sent = new ArrayList<Message>();
		
	}

	public MailBox() {
		super();
		this.inbox = new ArrayList<Message>();
		this.sent = new ArrayList<Message>();
		
	}
	public int getMailID() {
		return mailID;
	}

	public void setMailID(int mailID) {
		this.mailID = mailID;
	}

	
	public List<Message> getInbox() {
		return inbox;
	}

	public void addToInbox(Message inbox) {
		this.inbox.add(inbox);
	}

	public List<Message> getSent() {
		return sent;
	}

	public void addToSent(Message sent) {
		this.sent.add(sent);
	}

	@Override
	public String toString() {
		String mail =  "MailBox mailID=" + mailID + "\nInbox";
		for(Message m : inbox)
		{
			mail = mail  +  inbox.toString() + "\n";
		}
		
		mail = mail + "\nSent:";
		for(Message m : sent)
		{
			mail = mail + sent.toString() + "\n";
		}
		return mail;
	}
	
	
	
	
}
