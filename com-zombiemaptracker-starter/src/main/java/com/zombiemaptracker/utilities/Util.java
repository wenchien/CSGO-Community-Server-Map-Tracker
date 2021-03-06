package com.zombiemaptracker.utilities;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress; 
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Iterator;
import javax.swing.DefaultListModel;
import com.zombiemaptracker.beans.TrackConfig;

public class Util {
	public static void trackMap(TrackConfig tc) throws IOException {
		int port = tc.getPortNumber();
		String host = tc.getIpAddress();
		byte[] message = "Source Engine Query\0".getBytes();
		byte[] specialMsg = addSpecialBytes(message);
		byte[] received = new byte[1024];
		// Get the internet address of the specified host
		InetAddress address = InetAddress.getByName(host);

		// Initialize a datagram packet with data and address
		DatagramPacket packet = new DatagramPacket(specialMsg, specialMsg.length,
				address, port);
		
		// Create a datagram socket, send the packet through it, close it.
		DatagramSocket dsocket = new DatagramSocket();
		dsocket.send(packet);
		DatagramPacket receivePacket = new DatagramPacket(received, received.length);
		dsocket.receive(receivePacket);
		String modifiedSentence = new String(receivePacket.getData());
		System.out.println("FROM SERVER:" + modifiedSentence);
		String[] split = modifiedSentence.split(" ");
		String mapName = "";
		for (int i = 0; i < split.length; i++) {
			if  (split[i].contains("ze_")) {
				
				mapName = split[i];
				tc.setServerInfo(mapName);
				break;
			}
			else {
				continue;
			}
		}
		System.out.println(mapName);

		dsocket.close();
	}
	//need invalid or down server checks
	
	public static void refreshMapTrack(final DefaultListModel<TrackConfig> mapTrackingList) throws IOException {
		for (Object ct : mapTrackingList.toArray()) {
			if (ct instanceof TrackConfig) {
				Util.trackMap((TrackConfig)ct);
			}
		}
	}
	//https://developer.valvesoftware.com/wiki/Server_queries
	//See A2S_Info Section
	public static byte[] addSpecialBytes(byte[] old) {
		byte[] modified = new byte[old.length + 5];
		modified[0] = (byte)0xFF;
		modified[1] = (byte)0xFF;
		modified[2] = (byte)0xFF;
		modified[3] = (byte)0xFF;
		modified[4] = (byte)0x54;
		System.arraycopy(old, 0, modified, 5, modified.length - 5);
		return modified;
	}
}
