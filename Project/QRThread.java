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
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.MovePilot;

public class QRThread extends Thread {
	
	private static String IPaddress = "10.0.1.4";
	private static int port = 1234;
	public static Socket connection = new Socket();
	public static DataInputStream dis;
	public static DataOutputStream dos;
	private static int MAX_READ = 30;
	private static BufferedInputStream in = null;
	private static OutputStream out = null;
	
	private MovePilot pilot;
	
	public QRThread(MovePilot mp) {
		this.pilot = mp;		
	}
	
	public void run() {
		byte[] buffer = new byte[MAX_READ];
		String reply = "";
		Connection con = new Connection();
		Socket connection = con.getConnection();
		BufferedInputStream in = con.getIn();
		
		LCD.drawString("QR reading  ", 0, 6);
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
							//LCD.drawChar((char)buffer[index], index + 0, 3);
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
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
