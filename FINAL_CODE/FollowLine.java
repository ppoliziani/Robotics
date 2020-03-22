package ROBOBOP;

import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class FollowLine implements Behavior {
	
	private int DEFAULT_SPEED = 60;
	
	private BaseRegulatedMotor mLeft;
	private BaseRegulatedMotor mRight;
    private Thread QRwatcher;
    private Thread Colourwatcher;
    FollowLine() {}
    
	FollowLine(BaseRegulatedMotor mLeft, BaseRegulatedMotor mRight, Thread QRwatcher, Thread Colourwatcher) { 
		this.mLeft = mLeft;
		this.mRight = mRight;
		this.QRwatcher = QRwatcher;
		this.Colourwatcher = Colourwatcher;
	}
	
	public void action() {
		
		QRwatcher.start();
		Colourwatcher.start();
		
		Delay.msDelay(8000);
		
		mLeft.synchronizeWith(new BaseRegulatedMotor[] {mRight});
		mLeft.setSpeed(DEFAULT_SPEED);
		mRight.setSpeed(DEFAULT_SPEED);
		
		mLeft.startSynchronization();
		mLeft.forward();
		mRight.forward();
		mLeft.endSynchronization();
		
	}
	
	public int getSpeed() { return DEFAULT_SPEED; }
	
	public void suppress() {}
	
	public boolean takeControl() {
		Connection con = new Connection();
		return (con.getConnected());		
	}
}
