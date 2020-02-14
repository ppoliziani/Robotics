import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Driver {
	
	final static float WHEEL_DIAMETER = 56;
	final static float AXEL_LENGTH = 117;
	final static float ANGULAR_SPEED = 90;
	final static float LINEAR_SPEED = 70;
	
	public static void main(String[] args) {
		
		MovePilot mp = getPilot(MotorPort.A, MotorPort.B, WHEEL_DIAMETER, AXEL_LENGTH); 
		mp.setLinearSpeed(200);
		
		Behavior trundle = new Trundle(mp);
		Behavior backup = new Backup(mp);
		Behavior dark = new Dark(mp);
		Behavior light = new Light(mp);
		Behavior stop = new EmergencySTOP(mp);
		Behavior charge = new BatteryLevel();
		
		float[] distance = new float[1];
		EV3UltrasonicSensor ultraSensor = new EV3UltrasonicSensor(SensorPort.S2);
		SampleProvider ultraSP = ultraSensor.getDistanceMode();
		
		Arbitrator ab = new Arbitrator(new Behavior[] { trundle, backup, dark, light, stop, charge }); 
		ab.go();
	
	}
	
	public static MovePilot getPilot(Port left, Port right, float diam, float offset) {
				
		BaseRegulatedMotor mLeft = new EV3LargeRegulatedMotor(left);
		Wheel wLeft = WheeledChassis.modelWheel(mLeft, diam).offset(-offset);
		
		BaseRegulatedMotor mRight = new EV3LargeRegulatedMotor(right);
		Wheel wRight = WheeledChassis.modelWheel(mRight, diam).offset(offset);
		
		Chassis chassis = new WheeledChassis(new Wheel[] {wRight,wLeft},WheeledChassis.TYPE_DIFFERENTIAL);
		MovePilot mp = new MovePilot(chassis);
		
		return new MovePilot(chassis);
	}

}
