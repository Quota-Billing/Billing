import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

public class MyServer {

	public static ArrayList userlist = new ArrayList();
	private static final String FILENAME = "src/userList";

	/**
	 * Runs the server.
	 * 
	 * @return
	 */
	public static ArrayList readfile() {
		BufferedReader br = null;
		FileReader fr = null;

		try {
			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
				userlist.add(sCurrentLine);
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

		return userlist;

	}

	public static void adduser(String userName) {
		userlist.add(userName);
		System.out.println(userlist);
		BufferedWriter bw = null;
		FileWriter fw = null;
		ArrayList tmp = new ArrayList();
		try {

			fw = new FileWriter(FILENAME, true);
			bw = new BufferedWriter(fw);
			bw.newLine();
			bw.write(userName);

			System.out.println("Done");

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

	}

	public static void main(String[] args) throws IOException {

		ServerSocket listener = new ServerSocket(9090);
		try {
			while (true) {
				Socket socket = listener.accept();
				try {
					PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
					out.println(new Date().toString());

				} finally {
					socket.close();
				}
			}
		} finally {
			listener.close();
		}
	}
}
