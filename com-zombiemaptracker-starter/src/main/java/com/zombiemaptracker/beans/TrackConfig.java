package com.zombiemaptracker.beans;

import java.io.Serializable;

public class TrackConfig implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ipAddress;
	private int portNumber;
	private String file;
	private int interval;
	private String serverInfo;
	private String serverName;
	
	public TrackConfig() {
		
	}
	
	public TrackConfig(String serverName, String ip, int port, String file) {
		this.serverName = serverName;
		this.ipAddress = ip;
		this.portNumber = port;
		this.file = file;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public int getPortNumber() {
		return portNumber;
	}

	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	@Deprecated
	public int getInterval() {
		return interval;
	}
	
	@Deprecated
	public void setInterval(int interval) {
		this.interval = interval;
	}
	
	public String getServerInfo() {
		return serverInfo;
	}

	public void setServerInfo(String serverInfo) {
		this.serverInfo = serverInfo;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ipAddress == null) ? 0 : ipAddress.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TrackConfig other = (TrackConfig) obj;
		if (ipAddress == null) {
			if (other.ipAddress != null)
				return false;
		} else if (!ipAddress.equals(other.ipAddress))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return serverName + serverInfo;
	}
}
