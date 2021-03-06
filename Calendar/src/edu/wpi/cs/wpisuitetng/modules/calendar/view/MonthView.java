/*******************************************************************************
 * Copyright (c) 2012 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Team Underscore 
 *  DreamInCode user: Alpha02
 *    
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.calendar.view;

import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MonthView extends JPanel {

	private final long serialVersionUID = 1L;
	JLabel lblMonth, lblYear;
	JButton btnPrev, btnNext;
	JTable tblCalendar;
	DefaultTableModel mtblCalendar;
	JScrollPane stblCalendar;
	int realYear, realMonth, realDay, currentYear, currentMonth;
	JComboBox cmbYear;

	public MonthView() {

		setLayout(null);
		// Set look and feel will make the look of the Janeway different on all fronts
		// Leave the look and feel default for now, make it prettier later
		createControls();
		createAndSetBorder();
		registerActionListeners();
		addControls();
		createBounds();
		createDate();
		addHeaders();
		createBackground();
		createTableProperties();
		populateTable();
		refreshCalendar(realMonth, realYear);
	}

	// Create the UI controls for the Calendar and surrounding components
	// Buttons to change month
	// Dropdown to change year 
	// Label for current selected month
	private void createControls() {
		lblMonth = new JLabel("January");
		lblYear = new JLabel("Change year:");
		cmbYear = new JComboBox();
		btnPrev = new JButton("<");
		btnNext = new JButton(">");
		mtblCalendar = new DefaultTableModel() {

			private  final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		tblCalendar = new JTable(mtblCalendar);
		//I can not for the life of me make the cells selectable, setSelectable(true) does not work
		stblCalendar = new JScrollPane(tblCalendar);
	}

	private void createAndSetBorder() {
		this.setBorder(BorderFactory.createTitledBorder("Calendar"));
	}

	// Set the action listenrer for the button components 
	private void registerActionListeners() {
		btnPrev.addActionListener(new btnPrev_Action());
		btnNext.addActionListener(new btnNext_Action());
		cmbYear.addActionListener(new cmbYear_Action());
	}

	private void addControls() {
		this.add(lblMonth);
		this.add(lblYear);
		this.add(cmbYear);
		this.add(btnPrev);
		this.add(btnNext);
		this.add(stblCalendar);
	}

	private void createBounds() {
		this.setBounds(0, 0, 626, 600);
		lblMonth.setBounds(88, 25, 100, 25);
		lblYear.setBounds(520, 230, 80, 20);
		cmbYear.setBounds(520, 261, 80, 20);
		btnPrev.setBounds(10, 25, 50, 25);
		btnNext.setBounds(260, 25, 50, 25);
		stblCalendar.setBounds(10, 50, 500, 420);
	}

	private void createDate() {
		final GregorianCalendar cal = new GregorianCalendar(); // Create calendar
		realDay = cal.get(GregorianCalendar.DAY_OF_MONTH); // Get day
		realMonth = cal.get(GregorianCalendar.MONTH); // Get month
		realYear = cal.get(GregorianCalendar.YEAR); // Get year
		currentMonth = realMonth; // Match month and year
		currentYear = realYear;
	}

	private void addHeaders() {
		final String[] headers = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
		for (int i = 0; i < 7; i++) {
			mtblCalendar.addColumn(headers[i]);
		}
	}

	private void createBackground() {
		tblCalendar.getParent().setBackground(tblCalendar.getBackground());
	}

	private void createTableProperties() {
		// No resize/reorder
		tblCalendar.getTableHeader().setResizingAllowed(false);
		tblCalendar.getTableHeader().setReorderingAllowed(false);

		// Single cell selection
		tblCalendar.setColumnSelectionAllowed(true);
		tblCalendar.setRowSelectionAllowed(true);
		tblCalendar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Set row/column count
		tblCalendar.setRowHeight(62);
		mtblCalendar.setColumnCount(7);
		mtblCalendar.setRowCount(6);
	}

	private void populateTable() {
		for (int i = realYear - 100; i <= realYear + 100; i++) {
			cmbYear.addItem(String.valueOf(i));
		}
	}

	private  void refreshCalendar(int month, int year) {
		// Variables
		final String[] months = { "January", "February", "March", "April", "May", "June", "July",
				"August", "September", "October", "November", "December" };
		final int nod, som; // Number Of Days, Start Of Month

		// Allow (or disallow) buttons
		btnPrev.setEnabled(true);
		btnNext.setEnabled(true);
		if (month == 0 && year <= realYear - 10) {
			btnPrev.setEnabled(false);
		} // Too early
		if (month == 11 && year >= realYear + 100) {
			btnNext.setEnabled(false);
		} // Too late
		lblMonth.setText(months[month]); // Refresh the month label (at the top)

		// Re-align label with calendar
		lblMonth.setBounds(160 - lblMonth.getPreferredSize().width / 2, 25, 180, 25); 
		// Select the correct year in the combo box
		cmbYear.setSelectedItem(String.valueOf(year)); 

		// Clear table
		// THIS NEEDS MODIFICATION TO SUPPORT BUTTON CELLS IN THE CALENDAR 
		// MAKE CHANGES HERE TO REMOVE BUTTONS 
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				mtblCalendar.setValueAt(null, i, j);
			}
		}

		// Get first day of month and number of days
		final GregorianCalendar cal = new GregorianCalendar(year, month, 1);
		nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		som = cal.get(GregorianCalendar.DAY_OF_WEEK);

		// Draw calendar
		// THIS NEEDS MODIFICATION TO ADD BUTTONS TO THE CALENDAR 
		for (int i = 1; i <= nod; i++) {
			int row = new Integer((i + som - 2) / 7);
			int column = (i + som - 2) % 7;
			mtblCalendar.setValueAt(i, row, column);
		}

		// Apply renderers
		tblCalendar.setDefaultRenderer(tblCalendar.getColumnClass(0), new tblCalendarRenderer());
	}

	class tblCalendarRenderer extends DefaultTableCellRenderer {
		private  final long serialVersionUID = 1L;

		public Component getTableCellRendererComponent(JTable table, Object value, boolean selected,
				boolean focused, int row, int column) {
			super.getTableCellRendererComponent(table, value, selected, focused, row, column);

			// Adding this code allows for selection to be used on this component
			// For now I think this should work in place of event listeners
			// an if statement checking if selected should work in theory
			if (column == 0 || column == 6) { // Highlight the week-end
				setBackground(new Color(255, 220, 220));
			}
			else { // Week
				setBackground(Color.white);
			}
			if (value != null) {
				if (Integer.parseInt(value.toString()) == realDay && currentMonth == realMonth 
						&& currentYear == realYear) { // Today
					setBackground(new Color(220, 220, 255));
				}
			}
			setBorder(null);
			setForeground(Color.black);
			return this;
		}
	}

	class btnPrev_Action implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (currentMonth == 0) { // Back one year
				currentMonth = 11;
				currentYear -= 1;
			}
			else { // Back one month
				currentMonth -= 1;
			}
			refreshCalendar(currentMonth, currentYear);
		}
	}

	class btnNext_Action implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (currentMonth == 11) { // Forward one year
				currentMonth = 0;
				currentYear += 1;
			}
			else { // Forward one month
				currentMonth += 1;
			}
			refreshCalendar(currentMonth, currentYear);
		}
	}

	class cmbYear_Action implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (cmbYear.getSelectedItem() != null) {
				final String b = cmbYear.getSelectedItem().toString();
				currentYear = Integer.parseInt(b);
				refreshCalendar(currentMonth, currentYear);
			}
		}
	}
}
