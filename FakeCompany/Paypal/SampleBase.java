package Paypal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.paypal.base.rest.JSONFormatter;
import com.paypal.base.rest.PayPalRESTException;

public class SampleBase<T> {

	protected T instance = null;

	/**
	 * Initialize sample base
	 *
	 * @throws PayPalRESTException
	 * @throws JsonSyntaxException
	 * @throws JsonIOException
	 * @throws FileNotFoundException
	 */
	public SampleBase(T instance)
			throws PayPalRESTException, JsonSyntaxException, JsonIOException, FileNotFoundException {
		this.instance = instance;
	}

	protected <C> C load(String jsonFile, Class<C> clazz) throws IOException {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File(getClass().getClassLoader()
					.getResource(jsonFile).getFile())));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.getProperty("line.separator"));
				line = br.readLine();
			}
			return (C) JSONFormatter.fromJSON(sb.toString(), clazz);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
