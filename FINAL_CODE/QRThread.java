package ROBOBOP;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.utility.Delay;

public class QRThread extends Thread {

	public static Socket connection = new Socket();
	public static DataInputStream dis;
	public static DataOutputStream dos;
	private static int MAX_READ = 30;
	
	private BaseRegulatedMotor mLeft;
	private BaseRegulatedMotor mRight;
	public QRThread(BaseRegulatedMotor mLeft, BaseRegulatedMotor mRight) {
		this.mLeft = mLeft;
		this.mRight = mRight;
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
						int read = in.read(buffer, 0, MAX_READ);
						for (int index= 0 ; index < read ; index++) {	
							reply += (char)buffer[index];
						}
						
						reply = reply.substring(4, reply.length());
						String[] strarr = reply.split(" ", 5);
						int fr = Integer.parseInt(strarr[0]);
						int du = Integer.parseInt(strarr[1]);
						int last = Integer.parseInt(strarr[2]);
						
						if (last == 999) {
							Sound.playTone(fr, du);
							mLeft.stop();
							mRight.stop();
							Delay.msDelay(200);
							mLeft.setSpeed(200);
					        mRight.setSpeed(1000);
					        mRight.forward();
					        mLeft.backward();
					        Delay.msDelay(5000);
					        System.exit(0);
						}
						
						LCD.drawString(reply, 0, 5);
						LCD.drawInt(fr, 0, 6);
						LCD.drawInt(du, 0, 7);
						reply = "";
						
						Sound.playTone(fr, du);
						
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
