import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;

public class StraightLine {
	
	final static float WHEEL_DIAMETER = 56;
	final static float AXLELENGTH = 117;
	final static float ANGULAR_SPEED = 90;
	final static float LINEAR_SPEED = 70;

	public static void main(String[] args) {
		BaseRegulatedMotor mLeft = new EV3LargeRegulatedMotor(MotorPort.A);
		Wheel wLeft = WheeledChassis.modelWheel(mLeft, WHEEL_DIAMETER).offset(-AXLELENGTH/2);
		
		BaseRegulatedMotor mRight = new EV3LargeRegulatedMotor(MotorPort.B);
		Wheel wRight = WheeledChassis.modelWheel(mRight, WHEEL_DIAMETER).offset(AXLELENGTH/2);
		
		Chassis chassis = new WheeledChassis(new Wheel[] {wRight,wLeft},WheeledChassis.TYPE_DIFFERENTIAL);
		MovePilot mp = new MovePilot(chassis);
		
		for (int i = 0; i < 2; i++) {
			mp.travel(0.5);
			mp.rotate(180);
		}
	}

}
