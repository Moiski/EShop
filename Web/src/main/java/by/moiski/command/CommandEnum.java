package by.moiski.command;

import by.moiski.command.administrator.AddNewProductCommand;
import by.moiski.command.administrator.BlackListCommand;
import by.moiski.command.administrator.SaveProductCommand;
import by.moiski.command.administrator.ShowAllProductsCommand;
import by.moiski.command.administrator.ShowAllUsersCommand;
import by.moiski.command.client.AddToCartCommand;
import by.moiski.command.client.OrderCommand;
import by.moiski.command.client.RemoveFromCartCommand;
import by.moiski.command.client.ShowCartCommand;
import by.moiski.command.client.ShowProductsClientFilter;
import by.moiski.command.client.ShowProductsCommand;
import by.moiski.command.client.ShowUserOrdersCommand;

public enum CommandEnum {

	REGISTRATION {
		{
			this.command=new RegistrCommand();
		}
	},
	GETREGISTRATIONFORM {
		{
			this.command=new GetRegistrFormCommand();
		}
	},
	LOGIN {
		{
			this.command=new LoginCommand();
		}
	},
	SHOWALLUSERS {
		{
			this.command=new ShowAllUsersCommand();
		}
	},
	LOGOUT {
		{
			this.command=new LogoutCommand();
		}
	},
	SHOWPRODUCTS {
		{
			this.command=new ShowProductsCommand();
		}
	},
	ADDTOCART {
		{
			this.command=new AddToCartCommand();
		}
	},
	SHOWCART {
		{
			this.command=new ShowCartCommand();
		}
	},
	REMOVEPRODUCTFROMCART {
		{
			this.command=new RemoveFromCartCommand();
		}
	},
	BLACKLIST {
		{
			this.command=new BlackListCommand();
		}
	},
	SHOWALLPRODUCTS {
		{
			this.command=new ShowAllProductsCommand();
		}
	}, ADDNEWPRODUCT {
		{
			this.command = new AddNewProductCommand();
		}
	}, SAVEPRODUCTS {
		{
			this.command = new SaveProductCommand();
		}
	}, ORDER {
		{
			this.command = new OrderCommand();
		}
	}, SHOWUSERORDERS {
		{
			this.command = new ShowUserOrdersCommand();
		}
	}, SHOWPRODUCTSCLIENTFILTER {
		{
			this.command = new ShowProductsClientFilter();
		}	
	};
		
	public ActionCommand getCurrentCommand() {
		return command;
	}

	ActionCommand command;

}
