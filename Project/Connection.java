package FinalRobot;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;

public class Connection implements Behavior {
	
	private static String IPaddress = "10.0.1.4";
	private static int port = 1234;
	public static Socket connection = new Socket();
	public static DataInputStream dis;
	public static DataOutputStream dos;
	private static int MAX_READ = 30;
	private static BufferedInputStream in = null;
	private static OutputStream out = null;
	
	Connection() {}
	
	public void action() {
		
		byte[] buffer = new byte[MAX_READ];
		String reply = "";
		//(new TunePlayer()).start();
		
		LCD.drawString("Waiting  ", 0, 0);
		SocketAddress sa = new InetSocketAddress(IPaddress, port);
		try {
			connection.connect(sa, 1500); // Timeout possible
		} catch (Exception ex) {
			// This connection fail is just ignored - we were probably not trying to connect because there was no
			// Android device
			// Could be Timeout or just a normal IO exception
			LCD.drawString(ex.getMessage(), 0,6);
			connection = null;
		}
		
		if (connection != null) {
		try {
			in = new BufferedInputStream(connection.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			out = connection.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LCD.drawString("Connected", 0, 0);
		}	
		
		LCD.drawString("Waiting  ", 0, 1);
		while (!Button.ESCAPE.isDown()) {
			if (connection != null) {
				try {
					if (in.available() > 0) {
						LCD.drawString("Chars read: ", 0, 2);
						LCD.drawInt(in.available(), 12, 2);
						int read = in.read(buffer, 0, MAX_READ);
						//LCD.drawChar('[', 3, 3);
						for (int index= 0 ; index < read ; index++) {	
							reply += (char)buffer[index];
							LCD.drawChar((char)buffer[index], index + 0, 3);
						}
						
						reply = reply.substring(4, reply.length());
						//LCD.drawString(reply, 0, 4);
						String[] strarr = reply.split(" ", 5);
						String di = strarr[0];
						int fr = Integer.parseInt(strarr[1]);
						int du = Integer.parseInt(strarr[2]);
						LCD.drawString(di, 0, 5);
						LCD.drawInt(fr, 0, 6);
						LCD.drawInt(du, 0, 7);
						reply = "";
						
						Sound.playTone(fr, du);
						
						LCD.drawChar(']', read + 4, 3);
						out.write("Reply:".getBytes(), 0, 6);
						out.write(buffer, 0, read);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
	}
		
	
	public void suppress() {}
	
	public boolean takeControl() {
		return true;
	}
	
}