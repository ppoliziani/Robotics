package FinalRobot;

import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;

public class InterpretQR implements Behavior {

    private MovePilot pilot;
    public InterpretQR(MovePilot p) {
        this.pilot = p;
        pilot.setLinearSpeed(50);
    }
    
    public void play(int frequency, int duration) {
    	Sound.playTone(frequency, duration);
    	pilot.travel(50);
    }

    public void action() {
    	
        String qr = "LEFT 10000 500"; // make call from watcher thread to get QR code like Thread.QRCODE;
        String[] stringarr = qr.split(" ", 5);
        
        String direction = stringarr[0];
        int frequency = Integer.parseInt(stringarr[1]);
        int duration = Integer.parseInt(stringarr[2]);
        
        switch(direction) {
        case "LEFT":
        	LCD.drawString("Turing LEFT", 1, 1);
        	play(frequency, duration);
        	pilot.arcForward(500);
        	
        	
        	break;
        case "RIGHT":
        	LCD.drawString("Turning RIGHT", 1, 1);
        	play(frequency, duration);
        	pilot.arcForward(-500);
        	

        	break;
        default:
        	LCD.drawString("Continuing FORWARD", 1, 1);
        	play(frequency, duration);
        	//go straight
        	
        	
        	break;
        	
        }
    }

    public void suppress() {}

    public boolean takeControl() {
    	//when QR code is read
        return true;
    }
}