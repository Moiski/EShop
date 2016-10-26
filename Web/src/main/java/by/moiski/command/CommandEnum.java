package by.moiski.command;

import by.moiski.command.administrator.ShowAllUsersCommand;
import by.moiski.command.client.AddToCartCommand;
import by.moiski.command.client.RemoveFromCartCommand;
import by.moiski.command.client.ShowCartCommand;
import by.moiski.command.client.ShowProductsCommand;

public enum CommandEnum {

	REGISTRATION {
		{
			this.command = new RegistrCommand();
		}
	}, GETREGISTRATIONFORM {
		{
			this.command = new GetRegistrFormCommand();
		}	
	}, LOGIN{
		{
			this.command = new LoginCommand();
		}
	},SHOWALLUSERS {
		{
			this.command = new ShowAllUsersCommand();
		}
	}, LOGOUT {
		{
			this.command = new LogoutCommand();
		}
	},SHOWPRODUCTS{
		{
			this.command = new ShowProductsCommand();
		}
	}, ADDTOCART{
		{
			this.command = new AddToCartCommand();
		}
	}, SHOWCART{
		{
			this.command = new ShowCartCommand();
		}
	}, REMOVEFROMCART {
		{
			this.command = new RemoveFromCartCommand();
		}
	};

	public ActionCommand getCurrentCommand() {
		return command;
	}
	
	ActionCommand command;

}
