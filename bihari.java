package bihari;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class bihari extends JFrame {

	private JPanel contentPane;
	private JTextField text_Obtained;
	private JTextField text_Total;
	private JTextField text_Percent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					bihari frame = new bihari();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public bihari() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Obtained marks");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(10, 29, 182, 32);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Total Marks");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(10, 116, 182, 32);
		contentPane.add(lblNewLabel_1);
		
		text_Obtained = new JTextField();
		text_Obtained.setFont(new Font("Tahoma", Font.PLAIN, 25));
		text_Obtained.setBounds(226, 29, 128, 32);
		contentPane.add(text_Obtained);
		text_Obtained.setColumns(10);
		
		text_Total = new JTextField();
		text_Total.setFont(new Font("Tahoma", Font.PLAIN, 25));
		text_Total.setBounds(226, 116, 128, 32);
		contentPane.add(text_Total);
		text_Total.setColumns(10);
		
		JButton btnNewButton = new JButton("Calculate");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Omarks ,Tmarks,perc;
				Omarks = text_Obtained.getText();
				Tmarks = text_Total.getText();
				int Omarks1 = Integer.parseInt(Omarks);
				
				int Tmarks1 = Integer.parseInt(Tmarks);
				
				float Omarks2 = Omarks1;
				float Tmarks2 = Tmarks1;
				float a = (Omarks2/Tmarks2);
				float b = (a*100);
				
				
				
				perc = Float.toString(b);
				text_Percent.setText(perc);
				
				String url = "jdbc:sqlserver://DESKTOP-H55I65M;databaseName=project;encrypt=false;";
				String username = "sa";
				String password = "12345";
				
				try {
					Connection connection =  DriverManager.getConnection(url, username, password);
					String sql = "insert into perTable values(?,?,?)";
				    PreparedStatement stmt = connection.prepareStatement(sql);
				    stmt.setString(1,Tmarks);
				    stmt.setString(2,Omarks);
				    stmt.setString(3, perc);
				    
				    //Statement st = connection.createStatement();
				    int r = stmt.executeUpdate();
				    if(r>0) {
				    	System.out.println("jai shree ram");
				    	
				    	
				    }
				    connection.close();
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(298, 209, 128, 32);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Percentage");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(10, 209, 112, 32);
		contentPane.add(lblNewLabel_2);
		
		text_Percent = new JTextField();
		text_Percent.setFont(new Font("Tahoma", Font.PLAIN, 25));
		text_Percent.setBounds(132, 196, 89, 57);
		contentPane.add(text_Percent);
		text_Percent.setColumns(10);
	}
}
