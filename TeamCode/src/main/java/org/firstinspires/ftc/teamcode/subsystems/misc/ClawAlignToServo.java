package org.firstinspires.ftc.teamcode.subsystems.misc;

import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.NebulaConstants;
import org.firstinspires.ftc.teamcode.util.nebulaHardware.NebulaI2CMonitor;
import org.firstinspires.ftc.teamcode.util.nebulaHardware.NebulaServo;

public class ClawAlignToServo implements Subsystem {
    public enum Position{
        UP(35),
        DOWN(215),
        UP_LEFT(UP.position-15),
        UP_RIGHT(UP.position+15);

        private final Double position;

        private Position(double position) {
            this.position = position;
        }
    }

    private NebulaServo wrist;
    private final NebulaI2CMonitor behindLeftSensor,
        behindRightSensor,
        poleLeftSensor,
        poleRightSensor;

    private int wristAngle = 35;
    private double[] sensorData = {0, 0, 0};
    double readout = 0;
    boolean coneDetected, leftDetected,
        rightDetected, middleDropDetected,
        leftDropDetected, rightDropDetected, open;
    private int noneCount = 0, rightCount = 0, leftCount = 0;

    public ClawAlignToServo(Telemetry tl, HardwareMap hardwareMap){
        wrist = new NebulaServo(hardwareMap,
            "wrist",
            NebulaServo.Direction.Forward,
            0,
            360,
            true);

        behindLeftSensor = new NebulaI2CMonitor(
            NebulaConstants.ClawAlignToServo.backL, hardwareMap);
        behindRightSensor = new NebulaI2CMonitor(
            NebulaConstants.ClawAlignToServo.backR, hardwareMap);

        poleLeftSensor = new NebulaI2CMonitor(
            NebulaConstants.ClawAlignToServo.poleL, hardwareMap);
        poleRightSensor = new NebulaI2CMonitor(
            NebulaConstants.ClawAlignToServo.poleR, hardwareMap);
    }

    @Override
    public void periodic(){

    }
    public void setPos(double clawServo1Pos) {
        wrist.setPosition(clawServo1Pos);
    }
    public void setPos(Position position) {
        wrist.setPosition(position.position);
    }

    //Position should be the position of the SLIDES
    public boolean behindCheck(int position, int loop){
        if(loop % 5 != 0) return false;
        sensorData = new double[]{behindLeftSensor.check(), 0, behindRightSensor.check()};
        readout = sensorData[2];
        switch (position) {
            case 1://Slide Positions
            case 2:
            case 3:
                leftDetected = sensorData[0] < 5;
                rightDetected = sensorData[2] < 5;

                if ((leftDetected && rightDetected) || (!leftDetected && !rightDetected)){

                    noneCount++; leftCount = 0; rightCount = 0;

                    if(noneCount > 0) {
//                        outtake();//Outtake
                    }

                    return false;
                } else if (rightDetected) {
                    setPos(Position.UP_LEFT);//Move Left

                    noneCount = 0; leftCount = 0; rightCount++;

                    if(rightCount > 1) ;//Lower Arm
                    if(rightCount > 2) ;//Lower Slide
                    return true;

                } else if (leftDetected) {
                    setPos(Position.UP_RIGHT);//Move Right

                    noneCount = 0; leftCount++; rightCount = 0;

                    if(leftCount > 1) ;//Lower Arm
                    if(leftCount > 2) ;//Lower Slide
                    return true;
                }
                break;
            default:
                setPos(Position.UP);
                break;
        }
        return true;
    }

    public boolean outtakeUpdate(int num, int loop){
        if(loop % 5 != 0) return false;
        sensorData = new double[]{poleLeftSensor.check(), 0, poleRightSensor.check()};
        switch (num) {
            case 1:
            case 2:
            case 3:
                leftDetected = 4.5 < sensorData[0] && sensorData[0] < 10;
                rightDetected = 4.5 < sensorData[2] && sensorData[2] < 10;

                if ((leftDetected && rightDetected) || (!leftDetected && !rightDetected))
                {
                    //outtake();
                }
                else if (rightDetected) setPos(Position.UP_LEFT);//Move Left
                else if (leftDetected) setPos(Position.UP_RIGHT);//Move Right
                break;
            default:
                setPos(Position.UP);
                break;
        }
        return true;
    }
    public double getLeft() { return leftCount; }
    public double getRight() { return rightCount; }

//    public ArrayList<String> brokenSensors(){
//        ArrayList<String> broken = new ArrayList<>();
//        if(!behindLeftSensor.working()) broken.add("BL");
//        if(!behindRightSensor.working()) broken.add("BR");
//        if(!poleLeftSensor.working()) broken.add("L");
//        if(!poleRightSensor.working()) broken.add("R");
//        return broken;
//    }
}