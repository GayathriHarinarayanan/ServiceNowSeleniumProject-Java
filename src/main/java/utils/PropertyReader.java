package utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyReader {

	static FileInputStream fileInputStream = null;
	static Properties properties = null;
	static Map<String, String> PROP_KEYS = new HashMap<>();

	static {
		File file = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");
		try {
			// reading the properties file
			fileInputStream = new FileInputStream(file);
			properties = new Properties();
			properties.load(fileInputStream);
			// populating the hash map
			for (Object key : properties.keySet()) {
				PROP_KEYS.put(key.toString(), properties.getProperty(key.toString()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String readKey(String key) throws Exception {
		if (key == null || PROP_KEYS.get(key) == null) {
			throw new Exception(key + "not found!");
		} else {
			return PROP_KEYS.get(key);
		}
	}
}
