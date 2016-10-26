package by.moiski.command.factoty;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

//import org.apache.log4j.Logger;

import by.moiski.command.ActionCommand;
import by.moiski.command.CommandEnum;
import by.moiski.command.EmptyCommand;

public class ActionFactory {
	
	private Logger log = Logger.getLogger(getClass());

	public ActionCommand defineCommand(HttpServletRequest request) {
		
		ActionCommand current = new EmptyCommand();
		String action = request.getParameter("command");
		if (action == null || action.isEmpty()) {
			return current;
		}
		try {
			CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
			current = currentEnum.getCurrentCommand();

		} catch (IllegalArgumentException e) {
			log.error("So command does't exist: " + e);
		}
		return current;
	}

}
