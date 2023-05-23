package com.schedulerMain; 

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException; 
// having fun during a 
// 


public class SchedulerMain implements MouseListener,KeyListener {
	static Schedule sched = new Schedule(new weekDay("friday"));
	static GridBagLayout gbl = new GridBagLayout();
	String textfile; 
	static GridBagConstraints gcon = new GridBagConstraints();
	static Map<String, String> classFormats = new HashMap<>();
	static Map<String, String> formatAssignments = new HashMap<>();
	static boolean inProcess = false; 
	static boolean inProcessBusy = false; 
	static JFrame frame = new JFrame("Scheduler");
	static JPanel panel = new JPanel(gbl);
	static String currentCourse; 
	static String formatSelected; 
	
	static ArrayList<Assignment> assignList = new ArrayList<Assignment>();
	static String longerDesc = "Upon Entry:\n" + 
			"Discuss: Liu, “The Literomancer,” pp. 74-111\n" + 
			"HW: Liu, “Simulacrum,” pp. 112-122";
	static Assignment assign = new Assignment("C","SciFi & Fantasy", "05/09/2023","Ken Liu Reading",longerDesc);
	static Assignment assign2 = new Assignment("A","English 200", "05/06/2023","English Narrative","Review your discussion board posts, and pick one you would like to revise to submit to me, to The Sun");
	static Assignment assign3 = new Assignment("B", "Math 110", "05/010/2023", "#1-10", "");
	static Assignment assign4 = new Assignment("B", "Math 110", "05/010/2023", "#1-10", "");
	static Assignment assign5 = new Assignment("B", "Math 110", "05/010/2023", "#1-10", "");
	
	static JLabel assignmentsBtn = new JLabel("Assignments");
	static JButton addFormatBtn = new JButton("Add Format");
	static JButton assignment1 = new JButton();
	static JButton assignment2 = new JButton();
	static JButton assignment3 = new JButton();
	static JButton assignment4 = new JButton();
	static JButton assignment5 = new JButton();
	static JButton cycle = new JButton("(+busy)");
	
	static JLabel yesterday = new JLabel(sched.getShownWeekDays()[0].getWeekDayName());
	static JLabel today = new JLabel(sched.getShownWeekDays()[1].getWeekDayName());
	static JLabel tomorrow = new JLabel(sched.getShownWeekDays()[2].getWeekDayName());
	static JButton[] formats = new JButton[36];
	static String[] formatText = new String[36];
	static Color[] formatBack = new Color[36];
	static String fileName; 
	static ActionListener e;
	
	static JButton selectedAssignment; // eventually this will change to an Assignment class
	
	public static void main(String[] args) throws IOException {
		String deskTopPath = System.getProperty("user.home") + "/Desktop/";
        
        
        fileName = "tave.txt";
        
       
        String filePath = deskTopPath + fileName;
        
       
   
		File file = new File(filePath);
		if (!file.exists()) {
			file.createNewFile();
			
		}
		
		
		frame.addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent e) {
		    	FileReader fileReader;
		    	BufferedReader bufferedReader; 
				
				try {
					fileReader = new FileReader(filePath);
					bufferedReader = new BufferedReader(fileReader);
					 String line; 
				        String course; 
				   
				        while ((line = bufferedReader.readLine()) != null) {
				            if (!line.isEmpty() && containsElement(sched.getFormatsInADay(), line.charAt(0)) && line.charAt(0) != '!') {
				            	System.out.println(line); 
				            	course = line.substring(4); 
				            
				            	System.out.println("easkjfgaksdjf");
				            	classFormats.put(Character.toString(line.charAt(0)).toUpperCase(), course); 
				            	
				            }
				        }
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				System.out.println("works"); 
				saveScheduleToFile(file); 
				
				
				System.exit(0); 

		    }
		});
		
		
		assignList.add(new Assignment("C","SciFi & Fantasy", "05/09/2023","Ken Liu Reading",longerDesc));
		assignList.add(new Assignment("A","English 200", "05/06/2023","English Narrative","Review your discussion board posts, and pick one you would like to revise to submit to me, to The Sun"));
		assignList.add(new Assignment("B", "Math 110", "05/010/2023", "#1-10", ""));
		assignList.add(new Assignment("B", "Math 110", "05/010/2023", "#1-10", ""));
		assignList.add(new Assignment("B", "Math 110", "05/010/2023", "#1-10", ""));
		
		assignment1.setText(assignList.get(0).toString());
		assignment1.setFont(new Font("Arial", Font.PLAIN,16));
		assignment1.setBackground(Color.yellow);
		assignment1.setOpaque(true);
		assignment1.setBorderPainted(false);
		
		assignment2.setText(assignList.get(1).toString());
		assignment2.setFont(new Font("Arial", Font.PLAIN, 16));
		assignment2.setBackground(Color.green);
		assignment2.setOpaque(true);
		assignment2.setBorderPainted(false);
		
		assignment3.setText(assignList.get(2).toString());
		assignment3.setFont(new Font("Arial", Font.PLAIN, 16));
		assignment3.setBackground(Color.cyan);
		assignment3.setOpaque(true);
		assignment3.setBorderPainted(false);
		
		assignment4.setText(assignList.get(3).toString());
		assignment4.setFont(new Font("Arial", Font.PLAIN, 16));
		assignment4.setBackground(Color.pink);
		assignment4.setOpaque(true);
		assignment4.setBorderPainted(false);
		
		assignment5.setText(assignList.get(4).toString());
		assignment5.setFont(new Font("Arial", Font.PLAIN, 16));
		assignment5.setBackground(Color.orange);
		assignment5.setOpaque(true);
		assignment5.setBorderPainted(false);
		
		for (int i=0; i<formats.length; i++) {
			
			
			
            
			String data = "";
			
			
			if (i >= 0 && i <= 6) {
				//System.out.println(classFormats.get(sched.shownWeekDays[0].formatsInOrder[i][0].toUpperCase())); 
				if (!(classFormats.get(sched.getShownWeekDays()[0].getFormatsInOrder()[i][0].toUpperCase()).equals(null) && !(classFormats.get(sched.getShownWeekDays()[0].getFormatsInOrder()[i][0].toUpperCase()) == "null"))) {
					data = classFormats.get(sched.getShownWeekDays()[0].getFormatsInOrder()[i][0].toUpperCase()); 
					System.out.println(data + "classformats"); 
				}
				
				else {
					
					data = "<html>" + "<br>" + sched.getShownWeekDays()[0].getFormatsInOrder()[i][0] + "<br />" + sched.getShownWeekDays()[0].getFormatsInOrder()[i][1] + "</html>";
					System.out.println(data + "else"); 
			
				}
				
			}
			
			else if (i >= 12 && i <= 18) {
				//System.out.println(classFormats.get(sched.getShownWeekDays()[1].getFormatsInOrder()[i-12][0].toUpperCase())); 
				if (!(classFormats.get(sched.getShownWeekDays()[1].getFormatsInOrder()[i-12][0].toUpperCase()) == null) && !(classFormats.get(sched.getShownWeekDays()[1].getFormatsInOrder()[i-12][0].toUpperCase()) == "null")) {
					data = classFormats.get(sched.getShownWeekDays()[1].getFormatsInOrder()[i-12][0].toUpperCase()); 
				}
				
				else {
					data = "<html>" + "<br>" + sched.getShownWeekDays()[1].getFormatsInOrder()[i-12][0] + "<br />" + sched.getShownWeekDays()[1].getFormatsInOrder()[i-12][1] + "</html>";
				}
				
			}
			else if (i >= 24 && i <= 30) {
				//System.out.println(classFormats.get(sched.getShownWeekDays()[2].getFormatsInOrder()[i-24][0].toUpperCase())); 
				if (!(classFormats.get(sched.getShownWeekDays()[2].getFormatsInOrder()[i-24][0].toUpperCase()) == null) && !(classFormats.get(sched.getShownWeekDays()[2].getFormatsInOrder()[i-24][0].toUpperCase()) == "null")) {
					data = classFormats.get(sched.getShownWeekDays()[2].getFormatsInOrder()[i-24][0].toUpperCase()); 
				}
				
				else {
					data = "<html>" + "<br>" + sched.getShownWeekDays()[2].getFormatsInOrder()[i-24][0] + "<br />" + sched.getShownWeekDays()[2].getFormatsInOrder()[i-24][1] + "</html>";
				}
				
			}
			else {
				data = "busy";
			}
			
			
			formats[i] = new JButton(data);
			formatText[i] = data;
			formatBack[i] = Color.white;
			frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
	}
	
		
		SchedulerMain main = new SchedulerMain();
		
		repaint();
		
		cycle.addActionListener(e -> addBusyFormat());
		assignment5.addActionListener(e -> diffButtonPressed());
		addFormatBtn.addActionListener(e -> addFormat());
		
		

		
		
		
		
		
//		TEST CODE TO FIGURE OUT GRIDBAGLAYOUT:
		
//		GridBagLayout gbl = new GridBagLayout();
//		GridBagConstraints gcon = new GridBagConstraints();
////		next 3 lines are copied from
////		https://www.youtube.com/watch?v=Gv1mANs_fCA&ab_channel=CodingExamples
//		gcon.weightx = 1;
//		gcon.weighty = 1;
//		gcon.fill = GridBagConstraints.BOTH;
//		
//		JFrame frame = new JFrame("Test");
//		JPanel panel = new JPanel(gbl);
//		
//		JLabel label = new JLabel("Name :");
//		JTextField name = new JTextField("This is text",10);
//		JTextField name2 = new JTextField("Name 2",10);
//		JTextField name3 = new JTextField(0);
//		
////		this is mostly directly copied from 
////		https://www.youtube.com/watch?v=Gv1mANs_fCA&ab_channel=CodingExamples
//		JButton btn1 = new JButton();
//		JButton btn2 = new JButton();
//		JButton btn3 = new JButton();
//		JButton btn4 = new JButton();
//		JButton btn5 = new JButton();
//		JButton btn6 = new JButton();
//		JButton btn7 = new JButton();
//		JButton btn8 = new JButton();
//		JButton btn9 = new JButton();
//		
//		gcon.gridx = 0;
//		gcon.gridy = 0;
//		gcon.gridwidth = 2;
//		gcon.gridheight = 1;
//		gbl.setConstraints(btn1, gcon);
//		panel.add(btn1);
//		
//		gcon.gridx = 2;
////		gcon.gridy = 0;		not necessary, same as above
////		gcon.gridwidth = 2;
////		gcon.gridheight = 1;
//		gbl.setConstraints(btn2, gcon);
//		panel.add(btn2);
//		
//		gcon.gridy = 1;
//		gcon.gridwidth = 1;
//		gcon.gridheight = 1;
//		
//		gcon.gridx = 0;
//		gbl.setConstraints(btn3, gcon);
//		panel.add(btn3);
//		
//		gcon.gridx = 1;
//		gbl.setConstraints(btn4, gcon);
//		panel.add(btn4);
//		
//		gcon.gridx = 2;
//		gbl.setConstraints(btn5, gcon);
//		panel.add(btn5);
//		
//		gcon.gridx = 3;
//		gbl.setConstraints(btn6, gcon);
//		panel.add(btn6);
//		
//		gcon.gridwidth = 3;
//		gcon.gridx = 0;
//		
//		gcon.gridy = 2;
//		gbl.setConstraints(btn7, gcon);
//		panel.add(btn7);
//		
//		gcon.gridy = 3;
//		gbl.setConstraints(btn8, gcon);
//		panel.add(btn8);
//		
//		gcon.gridwidth = 1;
//		gcon.gridheight = 2;
//		gcon.gridx = 3;
//		gcon.gridy = 2;
//		gbl.setConstraints(btn9, gcon);
//		panel.add(btn9);
//		
////		panel.add(name);
////		panel.add(name2);
////		panel.add(name3);
//		
//		frame.add(panel);
//		
////		label.setSize(100,100);
//		
////		label.setAlignmentX(100);
////		System.out.print(label.getAlignmentX());
//		
//		name.setSize(100,40);
//		name.setAlignmentX(100);
//		
//		name2.setSize(200,80);
//		
//		frame.setSize(400,400);
//		frame.setVisible(true);
		
	}
	
	public SchedulerMain() {
		
		addFormatBtn.addMouseListener(this);
		addFormatBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		assignmentsBtn.addMouseListener(this);
		assignmentsBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		assignment1.addMouseListener(this);
		assignment2.addMouseListener(this);
		assignment3.addMouseListener(this);
		assignment4.addMouseListener(this);
		assignment5.addMouseListener(this);
		cycle.addMouseListener(this);
		cycle.setFont(new Font("Arial", Font.PLAIN, 16));
		
		yesterday.addMouseListener(this);
		yesterday.setFont(new Font("Arial", Font.PLAIN, 10));
		today.addMouseListener(this);
		today.setFont(new Font("Arial", Font.PLAIN, 10));
		tomorrow.addMouseListener(this);
		tomorrow.setFont(new Font("Arial", Font.PLAIN, 10));
		for (int i=0; i<formats.length; i++){
			formats[i].addMouseListener(this);
			formats[i].setFont(new Font("Arial", Font.PLAIN, 16));
		}
	}
	
	static void repaint() {
		
		gcon.weightx = 1;
		gcon.weighty = 1;
		gcon.fill = GridBagConstraints.BOTH;
		
		// Add the upper area w/ assignments
		gcon.gridx = 0;
		gcon.gridy = 0;
		gcon.gridwidth = 2;
		gcon.gridheight = 1;
		gbl.setConstraints(assignmentsBtn, gcon);
		panel.add(assignmentsBtn);
		
		gcon.gridy = 1;
		gbl.setConstraints(addFormatBtn, gcon);
		panel.add(addFormatBtn);
		
		gcon.gridy = 0;
		gcon.gridwidth = 2;
		gcon.gridheight = 2;
		
		gcon.gridx = 2;
		gbl.setConstraints(assignment1, gcon);
		panel.add(assignment1);
		
		gcon.gridx = 4;
		gbl.setConstraints(assignment2, gcon);
		panel.add(assignment2);
		
		gcon.gridx = 6;
		gbl.setConstraints(assignment3, gcon);
		panel.add(assignment3);
		
		gcon.gridx = 8;
		gbl.setConstraints(assignment4, gcon);
		panel.add(assignment4);
		
		gcon.gridx = 10;
		gbl.setConstraints(assignment5, gcon);
		panel.add(assignment5);
		
		gcon.gridx = 12;
		gcon.gridwidth = 1;
		gbl.setConstraints(cycle, gcon);
		panel.add(cycle);
		
		// lower area with formats
		gcon.gridx = 0;
		gcon.gridwidth = 1;
		gcon.gridheight = 1;
		
		gcon.gridy = 2;
		gbl.setConstraints(yesterday, gcon);
		panel.add(yesterday);
		
		gcon.gridy = 3;
		gbl.setConstraints(today, gcon);
		panel.add(today);
		
		gcon.gridy = 4;
		gbl.setConstraints(tomorrow, gcon);
		panel.add(tomorrow);
		
		for (int y=2; y<5; y++) {
			gcon.gridy = y;
			for (int x=1; x<13; x++) {
				gcon.gridx = x;
				gbl.setConstraints(formats[((y-2)*12) + (x-1)], gcon);
				panel.add(formats[((y-2)*12) + x-1]);
			}
		}
		
		frame.add(panel);
		frame.setSize(1500,500);
		frame.setVisible(true); 
		
		
	}
	
	static boolean isInFormatsBtnList(JButton btn) {
		for (int i=0; i<formats.length; i++) {
			if (formats[i] == btn) {
				return true;
			}
		}
		return false;
	}
	
	static int findFormatInList(JButton btn) {
		for (int i=0; i<formats.length; i++) {
			if (formats[i]==btn) {
				return i;
			}
		}
		return -1;
	}
	
	public void keyPressed(KeyEvent e) {
		System.out.println("hi"); 
		sched.cycleThroughDays();
	}
	
	static void buttonPressed() {
		System.out.println("Button Pressed");
	}
	
	static void diffButtonPressed() {
		System.out.println("Different Button Pressed");
	}
	
	public void mousePressed(MouseEvent e) {
		String txt;
//       saySomething("Mouse pressed; # of clicks: " + e.getClickCount(), e);
//		Point p = new Point(e.getXOnScreen(), e.getYOnScreen());
		
//		saySomething("X : " + (e.getXOnScreen()-frame.getX()), e);
//		saySomething("Y : " + (e.getYOnScreen()-frame.getY()), e);
		if (e.getSource().getClass() != JButton.class) {
			return;
		}
		JButton tempBtn = (JButton) e.getSource();
		
		if (inProcess && isInFormatsBtnList(tempBtn)) {
	
			 
			ArrayList<Integer> indices = getAllIndicesAddFormat(tempBtn.getText().substring(10,11));
			
			classFormats.put(tempBtn.getText().substring(10,11),currentCourse); 
			tempBtn.setText(currentCourse); 
			for (int hi : indices) {
				if (!isAssignment(formats[hi])) {
					formats[hi].setText(currentCourse);
				}
				formatText[hi] = currentCourse;
			}
			inProcess = false; 
			currentCourse = ""; 
		}
		
		else if (inProcessBusy && isInFormatsBtnList(tempBtn)) {
			int index = findFormatInList(tempBtn);
			classFormats.put(tempBtn.getText().substring(10,11),"busy"); 
			tempBtn.setText("busy"); 
			formatText[index] = "busy";
			tempBtn.setBackground(Color.gray);
			formatBack[index] = Color.gray;
			tempBtn.setOpaque(true); 
			tempBtn.setBorderPainted(false);
			inProcessBusy = false; 
		}
		
		else {
		
			if (e.getSource().getClass() != JButton.class) {
				return;
			}
			
			
			if (tempBtn.getText().length() <= 0) {
				return;
			}
			if (tempBtn.getText().length() >= 19) {
				txt = tempBtn.getText().substring(0, 19);
			}
			else {
				txt = tempBtn.getText().substring(0, tempBtn.getText().length());
			}
			
			if (txt.equals("Empty") && selectedAssignment == null) {
				addAssignment(tempBtn);
				System.out.println("EmptyPress");
				return;
			}
			
			if (isAssignment(tempBtn) && selectedAssignment == null) {
				selectedAssignment = tempBtn;
				return;
			}
			
			
			if (selectedAssignment == null || selectedAssignment == tempBtn) {
				return;
			}
			
			int formatIndex = findFormatInList(selectedAssignment);
			
			if (isAssignment(tempBtn)) {
				String storedTxt = selectedAssignment.getText();
				Font storedFont = selectedAssignment.getFont();
				Color storedBack = selectedAssignment.getBackground();
				
				selectedAssignment.setText(tempBtn.getText());
				selectedAssignment.setFont(tempBtn.getFont());
				selectedAssignment.setBackground(tempBtn.getBackground());
				
				tempBtn.setText(storedTxt);
				tempBtn.setFont(storedFont);
				tempBtn.setBackground(storedBack);
				
				selectedAssignment = null;
			}
			else if (isInFormatsBtnList(tempBtn)) {
				System.out.println("FormatBtn Pressed");
				tempBtn.setText(selectedAssignment.getText());
				tempBtn.setFont(selectedAssignment.getFont());
				tempBtn.setBackground(selectedAssignment.getBackground());
				tempBtn.setOpaque(true); 
				tempBtn.setBorderPainted(false);
				
				if (formatIndex >= 0) { 
					selectedAssignment.setText(formatText[formatIndex]);
					selectedAssignment.setBackground(formatBack[formatIndex]);
					if (formatBack[formatIndex] == Color.white) {
						selectedAssignment.setBorderPainted(true);
						selectedAssignment.setOpaque(false);
					}
				}
				else {
					selectedAssignment.setText("Empty");
					selectedAssignment.setBackground(Color.white);
					selectedAssignment.setBorderPainted(true);
					selectedAssignment.setOpaque(false);
				}
				
				selectedAssignment = null;
			}
			else if (txt.equals("Empty")) {
				System.out.println("EmptyBtn Pressed");
				tempBtn.setText(selectedAssignment.getText()); 
				tempBtn.setFont(selectedAssignment.getFont());
				tempBtn.setBackground(selectedAssignment.getBackground());
				tempBtn.setOpaque(true); 
				tempBtn.setBorderPainted(false); 
				
				if (formatIndex >= 0) { 
					selectedAssignment.setText(formatText[formatIndex]);
					selectedAssignment.setBackground(formatBack[formatIndex]);
					selectedAssignment.setBorderPainted(true);
					selectedAssignment.setOpaque(false);
				}
				else {
					selectedAssignment.setText("Empty");
					selectedAssignment.setBackground(Color.white);
					selectedAssignment.setBorderPainted(true);
					selectedAssignment.setOpaque(false);
				}
			}
			
			//formatAssignments.put(selectedAssignment.getText().toUpperCase(), name.getText()); 
			selectedAssignment = null;
		}
		
    }
	
	static void addFormat() {
		
	    JTextField course = new JTextField();
	    String message = "Please enter the course you would like to add, then select the format";
	   
	    JOptionPane.showOptionDialog(frame, new Object[] {message, course},
	    	      "Add Format", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null); 
	    
	    if (course.getText().length() <= 0) {
	    	return;
	    }
	    
	    inProcess = true;  
	    inProcessBusy = false;
	    currentCourse = course.getText(); 
	 
	}
	
	static void addBusyFormat() {
		
	    
	    inProcessBusy = true;  
	    inProcess = false;
	    currentCourse = "";
	    
	 
	}
	
	static void addAssignment(JButton tempBtn) {
	    String message = "Please enter the subject of the assignment";
	    JTextField name = new JTextField();
	    String message2 = "Please enter the format it is due";
	    JTextField format = new JTextField();
	    String message3 = "Please enter the due date";
	    JTextField duedate = new JTextField();
	    String message4 = "Please enter the description";
	    JTextField description = new JTextField(); 
	    formatAssignments.put(format.getText().toUpperCase(), name.getText()); 
	   
	    JOptionPane.showOptionDialog(frame, new Object[] {message, name, message2, format, message3, duedate, message4, description},
	    	      "Add Assignment", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null); 
	    
	    assignList.add(new Assignment(format.getText(), name.getText(), duedate.getText(), description.getText(), ""));
	    
	    tempBtn.setText(assignList.get(assignList.size()-1).toString());
		tempBtn.setFont(new Font("Arial", Font.PLAIN,16));
		tempBtn.setBackground(Color.yellow);
		tempBtn.setOpaque(true);
		tempBtn.setBorderPainted(false);
//	    currentCourse = name.getText(); 
	}

    public void mouseReleased(MouseEvent e) {
//       saySomething("Mouse released; # of clicks: " + e.getClickCount(), e);
    }

    public void mouseEntered(MouseEvent e) {
//       saySomething("Mouse entered", e);
    }

    public void mouseExited(MouseEvent e) {
//       saySomething("Mouse exited", e);
    }

    public void mouseClicked(MouseEvent e) {
//       saySomething("Mouse clicked (# of clicks: " + e.getClickCount() + ")", e);
    }
    
    public static void refreshScreen() {
    	for (int i=0; i<formats.length; i++) {
			String data = "";
			
			
			if (i >= 0 && i <= 6) {
				// data = "<html>     fnord<br />foo</html>"
				data = "<html>" + "<br>" + sched.getShownWeekDays()[0].getFormatsInOrder()[i][0] + "<br />" + sched.getShownWeekDays()[0].getFormatsInOrder()[i][1] + "</html>";
			}
			else if (i >= 12 && i <= 18) {
				data = "<html>" + "<br>" + sched.getShownWeekDays()[1].getFormatsInOrder()[i-12][0] + "<br />" + sched.getShownWeekDays()[1].getFormatsInOrder()[i-12][1] + "</html>";
			}
			else if (i >= 24 && i <= 30) {
				data = "<html>" + "<br>" + sched.getShownWeekDays()[2].getFormatsInOrder()[i-24][0] + "<br />" + sched.getShownWeekDays()[2].getFormatsInOrder()[i-24][1] + "</html>";
			}
			else {
				data = "busy";
			}
			
			
			formats[i] = new JButton(data);
			formatText[i] = data;
			
		}
    	
    }

    void saySomething(String eventDescription, MouseEvent e) {
        System.out.println(eventDescription);
    }
    
    static boolean isAssignment(JButton tempBtn) {
    	String txt;
    	if (tempBtn.getText().length() <= 0) {
			return false;
		}
		
		if (tempBtn.getText().length() >= 19) {
			txt = tempBtn.getText().substring(0, 19);
		}
		else {
			txt = tempBtn.getText().substring(0, tempBtn.getText().length());
		}
		if (txt.equals("<html> <p><b>Format")) {
			System.out.println("AssignBtn Pressed");
			return true;
		}
		
		return false;
    }


	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	} 
	
	public ArrayList<Integer> getAllIndicesAddFormat(String format) {
		ArrayList<Integer> indices = new ArrayList<Integer>(); 
		for (int i = 0; i < sched.getShownWeekDays()[0].getFormatsInOrder().length; i++) {
			if (sched.getShownWeekDays()[0].getFormatsInOrder()[i][0].equals(format)) {
				indices.add(i); 
			}
			
		}
		for (int i = 0; i < sched.getShownWeekDays()[1].getFormatsInOrder().length; i++) {
			if (sched.getShownWeekDays()[1].getFormatsInOrder()[i][0].equals(format)) {
				indices.add(i+12); 
			}
			
		} 
		for (int i = 0; i < sched.getShownWeekDays()[2].getFormatsInOrder().length; i++) {
			if (sched.getShownWeekDays()[2].getFormatsInOrder()[i][0].equals(format)) {
				indices.add(i+24); 
			}
			
		} 
		return indices;
				
				
		
	}

// 
	public void keyReleased(KeyEvent e) {

		
		// TODO Auto-generated method stub
		
	}
	


	

	public static void saveScheduleToFile(File file) {
		 try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			 
	            writer.write("!START FILE");
	            writer.newLine();
	            writer.newLine();
	            writer.write("!START FORMATS:");
	            writer.newLine();
	            for (char format : sched.getFormatsInADay()) {
	                writer.write(format + " - " + classFormats.get(Character.toUpperCase(format)));
	              
	                writer.newLine();
	            }
	            writer.newLine();

	            for (int i = 0; i < sched.getShownWeekDays().length; i++) {
	                weekDay day = sched.getShownWeekDays()[i];
	                writer.write("!START");
	                writer.newLine();
	                writer.write(i == 0 ? "!PREVIOUS" : (i == 1 ? "!CURRENT" : "!NEXT"));
	                writer.newLine();
	                writer.write("!day - \"" + day.getWeekDayName() + "\"");
	                writer.newLine();

	                for (int j = 0; j < formatAssignments.size(); j++) {
	                    for (String format : day.getFormatInWeekday()) {
	                    	if (formatAssignments.containsKey(format.toUpperCase())) {
	                    		writer.write(j + ": " + formatAssignments.get(format.toUpperCase()));
	                    	}
	                    }
	               
	                }

	                writer.write("!END");
	                writer.newLine();
	                writer.newLine();
	            }

	            writer.write("!END FILE");
	        } catch (IOException e) {
	            e.printStackTrace();
	            
	        }
	    }
	
	public static boolean containsElement(char[] strings, char c) {
	    for (char element : strings) {
	        if (element== c) {
	            return true;
	        }
	    }
	    return false;
		}
	}


