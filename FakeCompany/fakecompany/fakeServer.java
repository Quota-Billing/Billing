package fakecompany;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

public class fakeServer {
	
	public static ArrayList dbinfo;
	public static void main(String[] args) throws IOException {
		port(8080); // Set the port to run on

		Database mydb = Database.getInstance();


		post(Path.ADD_USER, new addUserController()); 
		post("/notificationToPartner", (req, res) -> {
			System.out.println("get quota user");
			String confirm = JOptionPane.showInputDialog(
					"This is this user's due, please confirm that:");
			return "ok";
		});
		post(Path.CHARGE_USER, new ChargeUserController());
		
		post("/recurringBill", new RecurringBillHandler());
		
	}
}