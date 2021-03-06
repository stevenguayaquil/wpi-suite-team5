/*******************************************************************************
 * Copyright (c) 2012 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Team Underscore 
 *    
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.calendar;

import edu.wpi.cs.wpisuitetng.Session;
import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
// I'm not sure if we need this, but
// import edu.wpi.cs.wpisuitetng.modules.postboard.model.PostBoardMessage; 
// I'll just comment it out to be safe.
// import edu.wpi.cs.wpisuitetng.database.data;
import java.util.List;
import java.util.Date;


public class Event {
	private String eventName;
	private String eventDescr;
	private Date startDate;
	private Date endDate;
	// String category;
	// Repeat repeat;
	// List<String> participants;
	// boolean committed;
	
	public Event(String eventName, String eventDescr, Date startDate, Date endDate, 
			boolean committed, List<String> participants, String category, Repeat repeat){
		this.eventName = eventName;
		this.eventDescr = eventDescr;
		this.startDate = startDate;
		this.endDate = endDate;
		// this.category = category;
		// this.repeat = repeat;
		// this.committed = committed;
		// this.participants = participants;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventDescr() {
		return eventDescr;
	}

	public void setEventDescr(String eventDescr) {
		this.eventDescr = eventDescr;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	// It seems that these are all kept in EventCldr 
	// But I will keep these here for safekeeping
	
	// Stores a new event in the database
	// public void storeEvent(){
	//	 save(this);
	//	 System.out.println("Stored "+this.eventName);
	// }
	
	// Uses input from the GUI to update the event
	// Find the event, delete it, then create the updated ver. of it
	// public void updateEvent(){
	//	 save(this);	
	// }
	
	// Deletes an event from the database
	// public void deleteEvent(){
	//	 delete(this);
	// }
	
	

}
