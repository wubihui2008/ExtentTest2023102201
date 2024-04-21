package org.example.test1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
	public String configFile;
	public Properties pros;
	
	public ConfigManager(String _configFile) {
		this.configFile = _configFile;
		init();
	}
	
	public void init() {
		pros = new Properties();
		try {
			pros.load(new FileInputStream(new File(configFile)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(-1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public Properties getPros() {
		return pros;
	}
	

}
