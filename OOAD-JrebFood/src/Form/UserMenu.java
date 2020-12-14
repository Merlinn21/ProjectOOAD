package Form;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.UserController;
import Database.Connect;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserMenu extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable menuTable;
	private JButton addToCardBtn;
	private JButton ViewCart;
	private JButton transactionBtn;
	private JButton logout_btn;
	private JLabel nameLabel;
	
	Connection con;
    Statement statement;
    ResultSet rs;
    String sql;
    
	public void OpenUserMenuForm() {
		UserMenu frame = new UserMenu();
		frame.setResizable(false);
		frame.setVisible(true);
	}

	public UserMenu() {
		Connect db = new Connect();
		db.ConnectDatabase();
		con = db.con;
		statement = db.stm;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		menuTable = new JTable();
		scrollPane = new JScrollPane(menuTable);
		scrollPane.setBounds(10, 41, 664, 402);
		contentPane.add(scrollPane);
		
		addToCardBtn = new JButton("Add to Cart");
		addToCardBtn.setBounds(30, 477, 180, 23);
		contentPane.add(addToCardBtn);
		
		ViewCart = new JButton("View Cart");
		ViewCart.setBounds(30, 511, 180, 23);
		contentPane.add(ViewCart);
		
		transactionBtn = new JButton("Transaction History");
		transactionBtn.setBounds(472, 477, 180, 23);
		contentPane.add(transactionBtn);
		
		logout_btn = new JButton("Logout");
		logout_btn.setBounds(472, 511, 180, 23);
		contentPane.add(logout_btn);
		
		JLabel JrebMenuLabel = new JLabel("Jreb Food Menu");
		JrebMenuLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		JrebMenuLabel.setBounds(250, 11, 177, 19);
		contentPane.add(JrebMenuLabel);
		
		JLabel helloLabel = new JLabel("Hello, ");
		helloLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		helloLabel.setBounds(288, 454, 46, 14);
		contentPane.add(helloLabel);
		
		nameLabel = new JLabel(UserController.getInstance().GetCurrentUser().getUserName());
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nameLabel.setBounds(322, 454, 46, 14);
		contentPane.add(nameLabel);
		

	}
}
