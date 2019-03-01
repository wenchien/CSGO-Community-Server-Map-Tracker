package com.zombiemaptracker.ui.concept;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress; 
import java.net.InetSocketAddress; 
import java.net.UnknownHostException; 
public class socketTester {
	public static void main (String[] args) {
		try {
			//String host = "70.42.74.162";
			int port = 27015;
			String host = "216.52.148.47";
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
			System.out.println("Data Is sent");
			DatagramPacket receivePacket = new DatagramPacket(received, received.length);
			dsocket.receive(receivePacket);
			String modifiedSentence = new String(receivePacket.getData());
			System.out.println("FROM SERVER:" + modifiedSentence);
			String[] split = modifiedSentence.split(" ");
			String mapName = "";
			for (int i = 0; i < split.length; i++) {
				if  (split[i].contains("ze_")) {
					mapName = split[i];
					break;
				}
				else {
					continue;
				}
			}
			System.out.println(mapName);

			dsocket.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public static byte[] addSpecialBytes(byte[] raw) {
		byte[] bytes = new byte[raw.length + 5];
		bytes[0] = (byte)0xFF;
		bytes[1] = (byte)0xFF;
		bytes[2] = (byte)0xFF;
		bytes[3] = (byte)0xFF;
		bytes[4] = (byte)0x54;
		System.arraycopy(raw, 0, bytes, 5, bytes.length - 5);
		return bytes;
	}
}
