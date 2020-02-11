import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.geometry.Line;
import lejos.robotics.geometry.Rectangle;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.localization.PoseProvider;
import lejos.robotics.mapping.LineMap;
import lejos.robotics.navigation.DestinationUnreachableException;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Pose;
import lejos.robotics.navigation.Waypoint;
import lejos.robotics.pathfinding.ShortestPathFinder;

public class PilotSquare {
	
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
		PoseProvider pp = new OdometryPoseProvider(mp);
		Navigator nav = new Navigator(mp,pp);
		
		Rectangle rect = new Rectangle(0,0,120,90);
		Line[] lines = new Line[8];
		lines[0] = new Line(25,30,25,80);
		lines[1] = new Line(24,32,58,32);
		lines[2] = new Line(56,30,56,80);
		lines[3] = new Line(24,78,58,78);
		
		lines[4] = new Line(50,40,70,40);
		lines[5] = new Line(52,42,52,20);
		lines[6] = new Line(50,22,70,22);
		lines[7] = new Line(68,20,68,40);
		LineMap lm = new LineMap(lines,rect);
		
		ShortestPathFinder spp = new ShortestPathFinder(lm);
		Pose p1 = new Pose(20,15,90);
		Waypoint wp = new Waypoint(70,80);
		
		try {
			spp.findRoute(p1, wp, lm);
		} catch (DestinationUnreachableException e) {
			e.printStackTrace();
		}
		
		//Path path = new Path();
		//path.add(new Waypoint (100,0));
		//path.add(new Waypoint (100,100));
		//path.add(new Waypoint (0,100));
		//path.add(new Waypoint (0,0));
		
		mp.setAngularSpeed(ANGULAR_SPEED);
		mp.setLinearSpeed(LINEAR_SPEED);
		
		nav.followPath();
		nav.waitForStop();
		
		LCD.drawString("" + pp.getPose(),0,0);
		Button.ENTER.waitForPressAndRelease();
		
		//mp.travel(50);
		//mp.rotate(-90);
		//for(int i = 0; i < 4; i++) {
			//mp.travel(50);
			//mp.rotate(-90);
		//}
	}

}
