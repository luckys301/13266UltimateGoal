package org.firstinspires.ftc.teamcode.subsystems.misc;


import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.nebulaHardware.NebulaServo;
@Deprecated
public class ServoImplementation extends SubsystemBase {
    public enum positions{
        CLOSE_POS_S1(2),
        AUTO_CLOSE_S1(56),
        AUTO_OPEN_S1(78),
        OPEN_POS_S1(2);
        private final Double position;

        private positions(double position) {
            this.position = position;
        }
    }
    //Claw Variables
    public final static double CLOSE_POS_S1 = 0.51,
                                AUTO_CLOSE_S1 = 0.5,
                                AUTO_OPEN_S1 = 0.24,
                                OPEN_POS_S1 = 0.2;



    Telemetry telemetry;
    private final NebulaServo clawS1;     //Claw

    public ServoImplementation(Telemetry tl, HardwareMap hw) {
        this.clawS1 = new NebulaServo(hw,
            "clawS2",
            NebulaServo.Direction.Forward,
            0,
            360,
            true);
        this.clawS1.setPosition(positions.CLOSE_POS_S1.position);  //Port 3

        this.telemetry = tl;
    }

    @Override
    public void periodic() {
        telemetry.addData("Claw Servo 1 Angle: ", clawS1.getAngle());
        telemetry.addData("Claw Servo 1 Pos: ", clawS1.getPosition());
    }

    public void setClawS1(double clawServo1Pos) {
        clawS1.setPosition(clawServo1Pos);
    }


    public void clawAutoClose() {
        setClawS1(AUTO_CLOSE_S1);
    }
    public void clawClose() {
        setClawS1(CLOSE_POS_S1);
    }

    public void clawOpen() {
        setClawS1(OPEN_POS_S1);
    }
    public void clawAutoOpen() {
        setClawS1(AUTO_OPEN_S1);
    }

    public boolean isClawOpen(){
        return (clawS1.getPosition()==CLOSE_POS_S1) || (clawS1.getPosition()==AUTO_CLOSE_S1);
    };

}