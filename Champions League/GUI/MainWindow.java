import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class MainWindow {

    private JFrame frame;
    private Database db;
	
    /**
     * Create the application.
     * @throws SQLException 
     * @throws ClassNotFoundException 
     */
    public MainWindow() throws ClassNotFoundException, SQLException {
            initialize();
    }

    /**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
    private void initialize() throws ClassNotFoundException, SQLException {
	db = new Database();
	
	frame = new JFrame();
	frame.setBounds(100, 100, 900, 299);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);
	
	JButton view1_btn = new JButton("Players' Info");
	
	view1_btn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		
		JTable table1;
		try {
			JFrame frame = new JFrame();
			JLabel lblHeading = new JLabel("Players' Info");
			table1 = new JTable(db.players(), db.playersTitle());
		       Container c = frame.getContentPane();
		       c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
		       c.add(lblHeading,BorderLayout.PAGE_START);
		       c.add(table1.getTableHeader());
		       c.add(table1);
		       frame.add(new JScrollPane(table1));
		       frame.pack();
		       frame.setVisible(true);
		} catch (SQLException e) {
            // TODO Auto-generated catch block
		}
		
		}
	});
	
	
	view1_btn.setBounds(0, 0, 225, 131);
	frame.getContentPane().add(view1_btn);
	
        
	JButton view2_btn = new JButton("Match Info");
	
	view2_btn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		JTable table1;
		try {
			JFrame frame = new JFrame();
			JLabel lblHeading = new JLabel("Match Info");
			table1 = new JTable(db.matchInfo(), db.matchInfoTitle());
		       Container c = frame.getContentPane();
		       c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
		       c.add(lblHeading,BorderLayout.PAGE_START);
		       c.add(table1.getTableHeader());
		       c.add(table1);
		       frame.add(new JScrollPane(table1));
		       frame.pack();
		       frame.setVisible(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
	});
	
	
	view2_btn.setBounds(225, 0, 225, 131);
	frame.getContentPane().add(view2_btn);
        
        //-------------------------------------------------------------
        // 3rd button
        //-------------------------------------------------------------
        JButton view3_btn = new JButton("Team Results");
	
	view3_btn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		JTable table1;
		try {
			JFrame frame = new JFrame();
			JLabel lblHeading = new JLabel("Team Results");
			table1 = new JTable(db.groupTeams(), db.groupTeamsTitle());
		       Container c = frame.getContentPane();
		       c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
		       c.add(lblHeading,BorderLayout.PAGE_START);
		       c.add(table1.getTableHeader());
		       c.add(table1);
		       frame.add(new JScrollPane(table1));
		       frame.pack();
		       frame.setVisible(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
	});
	
	
	view3_btn.setBounds(0, 129, 225, 131);
	frame.getContentPane().add(view3_btn);

        JButton view4_btn = new JButton("Groups");
	
	view4_btn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		JTable table1;
		try {
			JFrame frame = new JFrame();
			JLabel lblHeading = new JLabel("Groups");
			table1 = new JTable(db.group(), db.groupTitle());
		       Container c = frame.getContentPane();
		       c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
		       c.add(lblHeading,BorderLayout.PAGE_START);
		       c.add(table1.getTableHeader());
		       c.add(table1);
		       frame.add(new JScrollPane(table1));
		       frame.pack();
		       frame.setVisible(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
	});
	
	
	view4_btn.setBounds(225, 129, 225, 131);
	frame.getContentPane().add(view4_btn);
        
        
	JButton view5_btn = new JButton("Insert Match");
	
	view5_btn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
                    JTextField ssn = new JTextField(10);
                          JTextField matchType = new JTextField(25);

                          JTextField fName = new JTextField(25);
                          JTextField lName = new JTextField(25);
                          JTextField age = new JTextField(25);
                          JTextField nationality = new JTextField(25);  
                          
                          JTextField location = new JTextField(25);
                          JTextField date = new JTextField(25);
                          
                          JTextField team1 = new JTextField(25);
                          JTextField team1goals = new JTextField(25);
                          JTextField team2 = new JTextField(25);
                          JTextField team2goals = new JTextField(25);
                          

                          Box b = Box.createVerticalBox();
                          b.add(new JLabel("type:"));
                          b.add(matchType);
                          b.add(new JLabel("location:"));
                          b.add(location);
                          b.add(new JLabel("date:"));
                          b.add(date);
                          
                          b.add(new JLabel("Referee:"));
                          b.add(new JLabel("First Name:"));
                          b.add(fName);
                          b.add(new JLabel("Last Name:"));
                          b.add(lName);
                          b.add(new JLabel("Age:"));
                          b.add(age);
                          b.add(new JLabel("Nationality:"));
                          b.add(nationality);
                          
                          b.add(new JLabel("Team results:"));
                          b.add(new JLabel("Team 1:"));
                          b.add(team1);
                          b.add(new JLabel("Team 1 goals scored:"));
                          b.add(team1goals);
                          b.add(new JLabel("Team 2:"));
                          b.add(team2);
                          b.add(new JLabel("Team 2 goals scored:"));
                          b.add(team2goals);
                          
                          b.setVisible(true);

                          JPanel myPanel = new JPanel();
                          myPanel.add(b);

                          JOptionPane.showConfirmDialog(null, myPanel, 
                                   "Please Enter New Match", JOptionPane.OK_CANCEL_OPTION);
                          try {
                            db.insertMatch(matchType.getText(), location.getText(), date.getText(), fName.getText(), lName.getText(), Integer.parseInt(age.getText()), nationality.getText(), team1.getText(), Integer.parseInt(team1goals.getText()), team2.getText(), Integer.parseInt(team2goals.getText()));
                            JOptionPane.showMessageDialog(null, "The match was inserted successfully!", "PopUp!", JOptionPane.INFORMATION_MESSAGE);
                    } catch (SQLException e) {
                            e.printStackTrace();
                    }
		}
	});

	view5_btn.setBounds(450, 0, 225, 131);
	frame.getContentPane().add(view5_btn);
        
        JButton view6_btn = new JButton("Update Database (group scores)");
	
	view6_btn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
                    try {
                            db.updateDatabase();
                            JOptionPane.showMessageDialog(null, "Updated successfully!", "PopUp!", JOptionPane.INFORMATION_MESSAGE);
                    } catch (SQLException e) {

                            e.printStackTrace();
                    }
		}
	});

	view6_btn.setBounds(450, 130, 215, 125);
	frame.getContentPane().add(view6_btn);

        JButton view7_btn = new JButton("Reset Database (group scores)");        
        view7_btn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
                    try {
                            db.resetDatabase();
                            JOptionPane.showMessageDialog(null, "Reset successfully!", "PopUp!", JOptionPane.INFORMATION_MESSAGE);
                    } catch (SQLException e) {
                            e.printStackTrace();
                    }
		}
	});

	view7_btn.setBounds(670, 0, 215, 130);
	frame.getContentPane().add(view7_btn);
        
        JButton view8_btn = new JButton("Search Player");
	
	view8_btn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {

                          JTextField fName = new JTextField(25);
                          JTextField lName = new JTextField(25);
                          
                          

                          Box b = Box.createVerticalBox();
                          b.add(new JLabel("First name:"));
                          b.add(fName);
                          b.add(new JLabel("Last name:"));
                          b.add(lName);
                      
                          
                          b.setVisible(true);

                          JPanel myPanel = new JPanel();
                          myPanel.add(b);

                          JOptionPane.showConfirmDialog(null, myPanel, 
                                   "Please Enter New Match", JOptionPane.OK_CANCEL_OPTION);
                          try {
                            db.searchPlayer(fName.getText(), lName.getText());
                            
                    } catch (SQLException e) {
                            e.printStackTrace();
                    }
                          
                          JTable table1;
		try {
			JFrame frame = new JFrame();
			JLabel lblHeading = new JLabel("Player");
			table1 = new JTable(db.searchPlayer(fName.getText(), lName.getText()), db.playerTitle());
		       Container c = frame.getContentPane();
		       c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
		       c.add(lblHeading,BorderLayout.PAGE_START);
		       c.add(table1.getTableHeader());
		       c.add(table1);
		       frame.add(new JScrollPane(table1));
		       frame.pack();
		       frame.setVisible(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
                }
                    
	});

	view8_btn.setBounds(665, 130, 215, 125);
	frame.getContentPane().add(view8_btn);
        
        
        frame.setBounds(100, 100, 900, 299);
        

	}
    /**
    * Launch the application.
     * @param args
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MainWindow window = new MainWindow();
                window.frame.setVisible(true);
            } catch (ClassNotFoundException | SQLException e) {
            }
        });
    }
}