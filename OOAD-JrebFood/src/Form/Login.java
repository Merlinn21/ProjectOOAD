package Form;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.border.MatteBorder;

import Controller.UserController;
import Database.Connect;
import Model.UserModel;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField emailTextField;
	private JPasswordField passwordField;
	
	Connection con;
    Statement statement;
    ResultSet rs;
    String sql;
	
	public void OpenLoginForm() {
		Login frame = new Login();
		frame.setVisible(true);
		frame.setResizable(false);
	}

	public Login() {
		Connect db = new Connect();
		db.ConnectDatabase();
		con = db.con;
		statement = db.stm;
		
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 345, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel NorthPanel = new JPanel();
		NorthPanel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		contentPane.add(NorthPanel, BorderLayout.NORTH);
		
		JLabel loginLabel = new JLabel("Jreb Food");
		loginLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		NorthPanel.add(loginLabel);
		
		JPanel CenterPanel = new JPanel();
		contentPane.add(CenterPanel, BorderLayout.CENTER);
		CenterPanel.setLayout(null);
		
		JLabel emailLabel = new JLabel("Email:");
		emailLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		emailLabel.setBounds(21, 24, 61, 22);
		CenterPanel.add(emailLabel);
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		passwordLabel.setBounds(21, 68, 111, 22);
		CenterPanel.add(passwordLabel);
		
		emailTextField = new JTextField();
		emailTextField.setBounds(153, 24, 143, 22);
		CenterPanel.add(emailTextField);
		emailTextField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(154, 69, 143, 20);
		CenterPanel.add(passwordField);
		
		JButton loginButton = new JButton("LOGIN");
		loginButton.setBounds(105, 123, 104, 23);
		CenterPanel.add(loginButton);
		
		JButton registerButton = new JButton("REGISTER");
		
		registerButton.setBounds(105, 161, 104, 23);
		CenterPanel.add(registerButton);
		
		registerButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new Register().OpenRegisterForm();
				dispose();
			}
		});
		
		loginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Boolean loginStatus = CheckUser(emailTextField.getText(), new String(passwordField.getPassword()));
				String userRole = UserController.getInstance().GetCurrentUser().getUserRole();
				
				if(loginStatus) {
					switch(userRole) {
					case "User":
						new UserMenu().OpenUserMenuForm();
						break;
					case "Driver":
						break;
					case "Chef":
						break;
					case "Manager":
						break;
					}
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "Wrong password or username");
				}
			
			}
		});
	}
	
	private Boolean CheckUser(String email, String pass) {
		// Untuk cek apakah email dan password yang telah di input user sudah ada di database atau belum
		// Jika sudah akan return true jika tidak ditemukan akan return false
		Boolean status = null;
		
		String email1 = "";
		String pass1 = "";
		String name = "";
		String phone="";
		String role ="User";
		
		UserModel model = new UserModel();
        //sql = "SELECT user_email, password FROM user WHERE user_email='"+ email +"' AND password ='"+ pass + "'";
		sql = "SELECT * FROM user";
        try {
			rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				name = rs.getString("user_name");
				phone = rs.getString("phone_number");
				role = rs.getString("user_role");
                email1 = rs.getString("user_email");
                pass1 = rs.getString("password");
                
                model.setUserName(name);
                model.setPhoneNumber(phone);
                model.setUserRole(role);
                model.setEmail(email1);
                model.setPassword(pass1);
                
                if(email1.equals(email) || pass1.equals(pass)) {
    				UserController.getInstance().SetCurrentUser(model);
    				status = true;
    				break;
    			}
    				
    			else
    				status = false;		
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return status;
	}
}
