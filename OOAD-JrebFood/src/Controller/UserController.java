package Controller;

import Form.Login;
import Model.UserModel;

public class UserController{

	private UserModel user;
	private static UserController controller;
	
	public UserController() {
		// TODO Auto-generated constructor stub
	}
	
	public static UserController getInstance() {
		if(controller == null) {
			controller = new UserController();
		}
		
		return controller;
	}
	
	public void LoginForm() {
		new Login().OpenLoginForm();
	}
	
	public void SetCurrentUser(UserModel user) {
		this.user = user;
	}
	
	public UserModel GetCurrentUser() {
		return user;
	}
}
