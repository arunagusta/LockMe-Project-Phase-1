package com.simplilearn.lockme.application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import com.simplilearn.lockme.model.UserCredentials;
import com.simplilearn.lockme.model.Users;

// Main Application
public class Authentication {
	// Input Data
	private static Scanner keyboard;
	private static Scanner input;
	private static Scanner lockerInput;
	// Output Data
	private static PrintWriter output;
	private static PrintWriter lockerOutput;
	// Model to store data.
	private static Users users;
	private static UserCredentials userCredentials;
	// Exit Process
	static boolean exit;

	public static void main(String[] args) {
		initApp();
		welcomeScreen();
		signInOptions();

	}

	public static void signInOptions() {
		System.out.println("1 . Registration ");
		System.out.println("2 . Login ");
		System.out.println("0 . Exit ");

		int option = keyboard.nextInt();
		switch (option) {

		case 0:
			exit = true;
			System.out.println("Thanks For Using Our App");
			break;
		case 1:
			registerUser();
			break;
		case 2:
			loginUser();
			break;
		default:
			System.out.println("Please select one valid option ^-^ ");
			break;
		}
		keyboard.close();
		input.close();
	}

	public static void lockerOptions(String inpUsername) {
		System.out.println("1 . Fetch All Stored Credentials ");
		System.out.println("2 . Store Credentials ");
		System.out.println("3.  Exit");
		int option = keyboard.nextInt();
		switch (option) {
		case 1:
			fetchCredentials(inpUsername);
			break;
		case 2:
			storeCredentials(inpUsername);
			break;
		case 3:
			exit = true;
			System.out.println("Thanks For Using Our App");
			break;
		default:
			System.out.println("Please select one valid option ^-^ ");
			break;
		}
		lockerInput.close();
	}

	public static void registerUser() {
		System.out.println("==========================================");
		System.out.println("*					*");
		System.out.println("*   Wecome To Registration	*");
		System.out.println("*					*");
		System.out.println("==========================================");

		System.out.println("Enter Username :");
		String username = keyboard.next();
		users.setUsername(username);

		System.out.println("Enter Password :");
		String password = keyboard.next();
		users.setPassword(password);

		output.println(users.getUsername());
		output.println(users.getPassword());

		System.out.println("User Registration Suscessful !");
		output.close();

		System.out.println("0.  Exit");
		int option = keyboard.nextInt();
		switch (option) {

		case 0:
			exit = true;
			System.out.println("Thanks For Using Our App");
			break;

		default:
			System.out.println("Please select one valid option ^-^ ");

			break;

		}

	}

	public static void loginUser() {
		System.out.println("==========================================");
		System.out.println("*					*");
		System.out.println("*   Welcome Provide Credentials To login	 *");
		System.out.println("*					*");
		System.out.println("==========================================");
		System.out.println("Enter Username :");
		String inpUsername = keyboard.next();
		boolean found = false;
		while (input.hasNext() && !found) {
			if (input.next().equals(inpUsername)) {
				System.out.println("Enter Password :");
				String inpPassword = keyboard.next();
				if (input.next().equals(inpPassword)) {
					System.out.println("Login Successful ^_^ ");
					found = true;
					lockerOptions(inpUsername);
					break;
				}
			}
		}
		if (!found) {
			System.out.println("User Not Found : Invalid User Id  Or Password ! ");
		}

	}

	public static void welcomeScreen() {
		System.out.println("=    Devloped By -> Arun Sharma     =");
		System.out.println("*					*");
		System.out.println("*        <-Welcome To LockMe->     	*");
		System.out.println("*   Your Personal Digital Locaker	*");
		System.out.println("*    Most-Loved Password Manager	*");
		System.out.println("==========================================");

	}

	// Store Credentials
	public static void storeCredentials(String loggedInUser) {
		System.out.println("==========================================");
		System.out.println("*					*");
		System.out.println("*   Welcome To Locker Please Store Credentials	^_^ *");
		System.out.println("*					*");
		System.out.println("==========================================");

		userCredentials.setLoggedInUser(loggedInUser);

		System.out.println("Enter Site Name :");
		String siteName = keyboard.next();
		userCredentials.setSiteName(siteName);

		System.out.println("Enter Username :");
		String username = keyboard.next();
		userCredentials.setUsername(username);

		System.out.println("Enter Password :");
		String password = keyboard.next();
		userCredentials.setPassword(password);

		lockerOutput.println(userCredentials.getLoggedInUser());
		lockerOutput.println(userCredentials.getSiteName());
		lockerOutput.println(userCredentials.getUsername());
		lockerOutput.println(userCredentials.getPassword());

		System.out.println("Your Credentials Are Stored Securely !");

		lockerOutput.close();

		System.out.println("     ");
		System.out.println("0. Exit");
		int option = keyboard.nextInt();
		switch (option) {

		case 0:
			exit = true;
			System.out.println("Thanks For Using Our App");
			break;

		default:
			System.out.println("Please select one valid option ^-^ ");

			break;

		}

	}

	// Fetch Credentials
	public static void fetchCredentials(String inpUsername) {
		System.out.println("==========================================");
		System.out.println("*					             *");
		System.out.println("*   Welcome To Your Locker ^-^ 	 *");
		System.out.println("*   Your Stored Credentials Are  *");
		System.out.println("*					             *");
		System.out.println("==========================================");
		System.out.println(inpUsername);

		while (lockerInput.hasNext()) {
			if (lockerInput.next().equals(inpUsername)) {
				System.out.println("Site Name: " + lockerInput.next());
				System.out.println("User Name: " + lockerInput.next());
				System.out.println("User Password: " + lockerInput.next());
			}

		}
		{
			System.out.println("<***********************>");
			System.out.println("     ");
			System.out.println("0. Exit");
			int option = keyboard.nextInt();
			switch (option) {

			case 0:
				exit = true;
				System.out.println("Thanks For Using Our App");

				break;

			default:
				System.out.println("Please select one valid option ^-^ ");

				break;

			}
		}
	}

	public static void initApp() {

		File dbFile = new File("database.txt");
		File lockerFile = new File("locker-file.txt");

		try {
			// Read data from database file
			input = new Scanner(dbFile);

			// Read data from locker file
			lockerInput = new Scanner(lockerFile);

			// Read data from keyboard
			keyboard = new Scanner(System.in);

			// Out Put
			output = new PrintWriter(new FileWriter(dbFile, true));
			lockerOutput = new PrintWriter(new FileWriter(lockerFile, true));

			users = new Users();
			userCredentials = new UserCredentials();

		} catch (IOException e) {
			System.out.println(" File Not Found ");
		}

	}

}