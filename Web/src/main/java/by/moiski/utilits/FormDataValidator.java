package by.moiski.utilits;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormDataValidator {

	private static final Pattern LOGIN = Pattern.compile("^[a-zA-Z0-9]{3,10}$");
	private static final Pattern PASSWORD = Pattern.compile("^[a-zA-Z0-9]{3,10}$");
	private static final Pattern EMAIL = Pattern.compile("^[a-zA-Z0-9.]+@[a-zA-Z0-9]+\\.[a-zA-Z]+$");
	private static final Pattern FIRSTNAME = Pattern.compile("^[a-zA-Zа-яА-ЯёЁ ]+$");
	private static final Pattern LASTNAME = Pattern.compile("^[a-zA-Zа-яА-ЯёЁ ]+$");

	public static boolean loginValadate(CharSequence sequence) {
		Matcher matcher = LOGIN.matcher(sequence);
		return matcher.matches();
	}

	public static boolean passwordValadate(CharSequence sequence) {
		Matcher matcher = PASSWORD.matcher(sequence);
		return matcher.matches();
	}

	public static boolean emailValadate(CharSequence sequence) {
		Matcher matcher = EMAIL.matcher(sequence);
		return matcher.matches();
	}

	public static boolean firstNameValadate(CharSequence sequence) {
		Matcher matcher = FIRSTNAME.matcher(sequence);
		return matcher.matches();
	}

	public static boolean lastNameValadate(CharSequence sequence) {
		Matcher matcher = LASTNAME.matcher(sequence);
		return matcher.matches();
	}

}
