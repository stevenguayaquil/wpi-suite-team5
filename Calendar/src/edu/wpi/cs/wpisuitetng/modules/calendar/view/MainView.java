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
package edu.wpi.cs.wpisuitetng.modules.calendar.view;

import javax.swing.JPanel;
import java.awt.BorderLayout;

/**
 * This panel fills the main content area of the tab for this module.
 * 
 * @author Team 5 (B13)
 *
 */
@SuppressWarnings("serial")
public class MainView extends JPanel {

	/** The panel containing the post board */
	private final CalendarPanel calendarPanel;
	
	/**
	 * Construct the panel.
	 */
	public MainView() {
		setLayout(new BorderLayout(0, 0));
		// Add the calendar panel to this view
		calendarPanel = new CalendarPanel();
		add(calendarPanel);
	}
}
