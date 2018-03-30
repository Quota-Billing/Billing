package Paypal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.JsonSerializer;

import flexjson.JSONSerializer;

public class JsonPharser {

	public JsonPharser() {

	}

	public static void pharse(ArrayList<String> merchant, ArrayList<String> billing, int[] amounts, int[] value,
			int[] taxs, String[] names, ArrayList<String> discount, int dispercent, String[] notes, String filename)
			throws IOException {
		

		FileWriter writer;

		writer = new FileWriter(filename);

		HashMap<String, Object> object = new HashMap<String, Object>() {
			{
				put("merchant_info", new HashMap<String, Object>() {
					{
						put("email", merchant.get(0));
						put("first_name", merchant.get(1));
						put("last_name", merchant.get(2));
						put("business_name", merchant.get(3));
						put("phone", new HashMap<String, Object>() {
							{
								put("country_code", merchant.get(4));
								put("national_number", merchant.get(5));
							}
						});
						put("address", new HashMap<String, Object>() {
							{
								put("line1", merchant.get(6));
								put("city", merchant.get(7));
								put("state", merchant.get(8));
								put("postal_code", merchant.get(9));
								put("country_code", merchant.get(10));
							}
						});
					}
				});
				put("billing_info", new Object[] { new HashMap<String, Object>() {
					{
						put("email", billing.get(0));
						put("first_name", billing.get(1));
						put("last_name", billing.get(2));

					}
				} });
				put("shipping_info", new HashMap<String, Object>() {
					{
						put("first_name", billing.get(3));
						put("last_name", billing.get(4));
						put("address", new HashMap<String, Object>() {
							{
								put("line1", billing.get(5));
								put("city", billing.get(6));
								put("state", billing.get(7));
								put("postal_code", billing.get(8));
								put("country_code", billing.get(9));
							}
						});
					}
				});
				Object[] itemsarray = new Object[names.length];
				for (int i = 0; i < names.length; i++) {
					HashMap<String, Object> item = new HashMap<String, Object>();
					item.put("name", names[i]);
					item.put("quantity", amounts[i]);
					final int value1 = value[i];
					item.put("unit_price", new HashMap<String, Object>() {
						{
							put("currency", "USD");
							put("value", value1);
						}
					});
					final int value2 = taxs[i];
					item.put("tax", new HashMap<String, Object>() {
						{
							put("name", "Tax");
							put("percent", value2);
						}
					});

					itemsarray[i] = item;
				}
				put("items", itemsarray);
				put("discount", new HashMap<String, Object>() {
					{
						put("percent", dispercent);
					}
				});
				put("shipping_cost", new HashMap<String, Object>() {
					{
						put("amount", new HashMap<String, Object>() {
							{
								put("value", discount.get(1));
								put("currency", discount.get(0));
							}
						});
					}
				});
				put("note", notes[0]);
				put("terms", notes[1]);
			}
		};

		JSONSerializer json = new JSONSerializer();
		json.prettyPrint(true);
		System.out.println(json.deepSerialize(object));
		writer.write(json.deepSerialize(object));
		// System.out.println(writer1.toString());
		writer.close();

	}
}
