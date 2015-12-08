package model;

import java.util.List;

public class ListNotification {

	   private List<Notification> notif;

	public ListNotification(List<Notification> areas) {
		this.notif = areas;
	}

	public List<Notification> getNotification() {
		return notif;
	}

	public void setNotification(List<Notification> notif) {
		this.notif = notif;
	}
	   
	   
	
	   
}
