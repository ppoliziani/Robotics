import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;
import static lejos.hardware.Sound.playTone;

public class InterpretQRCode implements Behavior {

    private MovePilot pilot;
    private String QRString;
    public InterpretQRCode(MovePilot p) {
        this.pilot = p;
        pilot.setLinearSpeed(10);
    }

    public void action() {
        // Probably better to call zxing() in action() and split.
        // QRString = zxing();
        String[] stringArr = QRString.split(" ");
        switch(stringArr[0]) {
            case "LEFT":
                int toneLeft = Integer.parseInt(stringArr[1]);
                int durationLeft = Integer.parseInt(stringArr[2]);
                pilot.rotate(-90);
                pilot.travel(500);
                playTone(toneLeft, durationLeft);
                break;
            case "RIGHT":
                int toneRight = Integer.parseInt(stringArr[1]);
                int durationRight = Integer.parseInt(stringArr[2]);
                pilot.rotate(90);
                pilot.travel(500);
                playTone(toneRight, durationRight);
                break;
            case "FORWARD":
                int toneForward = Integer.parseInt(stringArr[1]);
                int durationForward = Integer.parseInt(stringArr[2]);
                pilot.travel(500);
                playTone(toneForward, durationForward);
                break;
        }
    }

    public void suppress() {}

    public boolean takeControl() {
        return false; // boolean method for the camera detecting a QR Code.
    }
    public static void main(String[] args) { }
}
