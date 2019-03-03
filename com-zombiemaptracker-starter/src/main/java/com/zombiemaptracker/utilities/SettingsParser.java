package com.zombiemaptracker.utilities;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class SettingsParser {
	private File fileLoc;
	private static SettingsParser sp;
	private String name;
	private String ip;
	private String port;
	private SettingsParser(File fileLoc) {
		this.fileLoc = fileLoc;
	}
	
	public static SettingsParser getInstance(File fileLoc) {
		if (sp == null) {
			sp = new SettingsParser(fileLoc);
		}
		return sp;
	}
	public void load() {
		String checker = "";
		try (Scanner scanner = new Scanner(this.fileLoc)){
			while(scanner.hasNextLine()) {
				checker = scanner.nextLine();
				if (checker.startsWith("[")) {
					continue;
				}
				else {
					String[] parser = checker.split(":");
					setName(parser[0]);
					setIp(parser[1]);
					setPort(parser[2]);
				}
			}

		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}
}
