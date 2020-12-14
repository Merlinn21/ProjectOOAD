package Form;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import javax.swing.text.JTextComponent;

import Controller.UserController;
import Database.Connect;
import Model.UserModel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Register {

	private JFrame frmRegister;
	private JTextField nameTxtField;
	private JTextField emailTxtField;
	private JPasswordField passwordField;
	private JPasswordField confirmPasswordField;
	private JTextField phoneTextField;
	
	Connection con;
    Statement statement;
    ResultSet rs;
    String sql;

	public Register() {
		Connect db = new Connect();
		db.ConnectDatabase();
		con = db.con;
		statement = db.stm;
		
		initialize();
	}
	
	public void OpenRegisterForm() {
		Register window = new Register();
		window.frmRegister.setVisible(true);
		window.frmRegister.setResizable(false);
	}

	private void RegisterUser(String name, String email, String phone, String pass, String role) {
		sql = "INSERT INTO user (user_name, user_email, phone_number, password, user_role)" + " values (?, ?, ?, ?, ?)";
		UserModel model = new UserModel();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2,  email);
			pstmt.setString(3, phone);
			pstmt.setString(4,  pass);
			pstmt.setString(5,  role);
			
            model.setUserName(name);
            model.setPhoneNumber(phone);
            model.setUserRole(role);
            model.setEmail(email);
            model.setPassword(pass);
            
            UserController.getInstance().SetCurrentUser(model);
			pstmt.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void initialize() {

		frmRegister = new JFrame();
		frmRegister.setTitle("Register");
		frmRegister.setBounds(100, 100, 375, 380);
		frmRegister.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRegister.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel NorthPanel = new JPanel();
		NorthPanel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		frmRegister.getContentPane().add(NorthPanel, BorderLayout.NORTH);
		
		JLabel loginLabel = new JLabel("Jreb Food");
		loginLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		NorthPanel.add(loginLabel);
		
		JPanel CenterPanel = new JPanel();
		frmRegister.getContentPane().add(CenterPanel, BorderLayout.CENTER);
		CenterPanel.setLayout(null);
		
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		nameLabel.setBounds(30, 20, 65, 14);
		CenterPanel.add(nameLabel);
		
		JLabel emailLabel = new JLabel("Email");
		emailLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		emailLabel.setBounds(30, 50, 65, 14);
		CenterPanel.add(emailLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPassword.setBounds(30, 110, 81, 14);
		CenterPanel.add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblConfirmPassword.setBounds(30, 140, 133, 14);
		CenterPanel.add(lblConfirmPassword);
		
		JLabel phoneLabel = new JLabel("Phone Number");
		phoneLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		phoneLabel.setBounds(30, 80, 133, 14);
		CenterPanel.add(phoneLabel);
		
		nameTxtField = new JTextField();
		nameTxtField.setBounds(200, 19, 140, 20);
		CenterPanel.add(nameTxtField);
		nameTxtField.setColumns(10);
		
		emailTxtField = new JTextField();
		emailTxtField.setColumns(10);
		emailTxtField.setBounds(200, 49, 140, 20);
		CenterPanel.add(emailTxtField);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(200, 109, 140, 20);
		CenterPanel.add(passwordField);
		
		confirmPasswordField = new JPasswordField();
		confirmPasswordField.setBounds(200, 139, 140, 20);
		CenterPanel.add(confirmPasswordField);
		
		JButton registerButton = new JButton("REGISTER");	
		registerButton.setBounds(120, 203, 120, 23);
		CenterPanel.add(registerButton);
		
		JButton backButton = new JButton("BACK");
		backButton.setBounds(120, 237, 120, 23);
		CenterPanel.add(backButton);
		
		JButton resetButton = new JButton("RESET");
		resetButton.setBounds(120, 267, 120, 23);
		CenterPanel.add(resetButton);
		
		phoneTextField = new JTextField();
		phoneTextField.setBounds(200, 80, 140, 20);
		CenterPanel.add(phoneTextField);
		phoneTextField.setColumns(10);
		
		phoneTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent key) {
	            if (key.getKeyChar() >= '0' && key.getKeyChar() <= '9') {
	            	if(phoneTextField.getText().length() < 12)
	            		phoneTextField.setEditable(true);
	            	else
	            		phoneTextField.setEditable(false);
	            }
	            else {
	            	if(key.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE) 
	            		phoneTextField.setEditable(true);
	            	else
	            		phoneTextField.setEditable(false);
	            }
			}
		});
		
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Login().OpenLoginForm();
				frmRegister.dispose();
			}
		});
		
		registerButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(nameTxtField.getText().isEmpty() || emailTxtField.getText().isEmpty() || phoneTextField.getText().isEmpty() || passwordField.getPassword().length == 0 || confirmPasswordField.getPassword().length == 0) {
					JOptionPane.showMessageDialog(frmRegister, "All Field must be field", "Error", JOptionPane.ERROR_MESSAGE);
				}else {
					RegisterUser(nameTxtField.getText(), emailTxtField.getText(), phoneTextField.getText(), new String(passwordField.getPassword()), "User");
					new UserMenu().OpenUserMenuForm();
				}
				
			}
		});
		
		resetButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				nameTxtField.setText("");
				emailTxtField.setText("");
				phoneTextField.setText("");
				passwordField.setText("");
				confirmPasswordField.setText("");
			}
		});
	}
}
