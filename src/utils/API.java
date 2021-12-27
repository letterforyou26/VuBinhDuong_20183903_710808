package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;

import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;


/**
 * Class provide method send request to server and receive data return
 * Date: 12/07/2021
 * @author Duong
 * @version 1.0
 */


public class API {

	public static DateFormat DATE_FORMATER = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private static Logger LOGGER = Utils.getLogger(Utils.class.getName());

	/**
	 * Method which calls GET api
	 * @param url: url to server resource
	 * @param token: string to authenticate
	 * @return response: receive from server
	 * @throws Exception
	 */
	public static String get(String url, String token) throws Exception {
		
		// 1. setup
		HttpURLConnection conn = setupConnection(url, "GET", token);
		LOGGER.info("Request URL: " + url + "\n");
		
		// 2. read data receive from server
		String response = readResponse(conn);
		
		return response;
	}

	int var;

	/**
	 * Method which calls POST api
	 * @param url: url to server resource
	 * @param data: data send to server
	 * @return response: receive from server
	 * @throws IOException
	 */
	public static String post(String url, String data, String token) throws IOException {
		allowMethods("PATCH");
		
		// 1. setup
		HttpURLConnection conn = setupConnection(url, "PATCH", token);
		
		// 2. send data
		Writer writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		writer.write(data);
		writer.close();
		
		// 3. read data receive from server
		String response = readResponse(conn);
		
		return response;
	}
	
	/**
	 * setup connection to server
	 * @param url: path to server
	 * @param method: type of http request
	 * @param token: authenticate user
	 * @return connection
	 * @throws IOEexception
	 */
	private static HttpURLConnection setupConnection(String url, String method, String token) throws IOException {
		// 1. setup
		URL line_api_url = new URL(url);
		LOGGER.info("Request Info:\nRequest URL: " + url + "\n");
		HttpURLConnection conn = (HttpURLConnection) line_api_url.openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod(method);
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Authorization", "Bearer " + token);
		return conn;
	}
	
	/**
	 * method read data receive from server
	 * @param conn: connection to server
	 * @return response: data receive from server
	 * @throws IOException
	 */
	private static String readResponse(HttpURLConnection conn) throws IOException {
		// 2. read data receive from server
		BufferedReader in;
		String inputLine;
		if (conn.getResponseCode() / 100 == 2) {
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			in = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder response = new StringBuilder();
		while ((inputLine = in.readLine()) != null)
			response.append(inputLine + "\n");
		in.close();
		String res = response.substring(0, response.length() - 1).toString();
		LOGGER.info("Respone Info: " + res);
		return res;
	}

	/**
	 * Method which calls others api (PATCH, PUT,...)
	 * @deprecated only works for Java <= 11
	 * @param methods: http type request (PATCH, PUT,...)
	 */
	private static void allowMethods(String... methods) {
		try {
			Field methodsField = HttpURLConnection.class.getDeclaredField("methods");
			methodsField.setAccessible(true);

			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(methodsField, methodsField.getModifiers() & ~Modifier.FINAL);

			String[] oldMethods = (String[]) methodsField.get(null);
			Set<String> methodsSet = new LinkedHashSet<>(Arrays.asList(oldMethods));
			methodsSet.addAll(Arrays.asList(methods));
			String[] newMethods = methodsSet.toArray(new String[0]);

			methodsField.set(null/* static field */, newMethods);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
	}

}
