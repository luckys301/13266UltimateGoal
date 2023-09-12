package org.firstinspires.ftc.teamcode.util;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.util.nebulaHardware.NebulaMotor;
import org.firstinspires.ftc.teamcode.util.nebulaHardware.NebulaServo;


//This will be used to store all Initialization Values for Subsystems, etc.
//Positions will
@Config
public class NebulaConstants {
    //TODO: make some things final
    // TODO: servos = have set angles
    //Arm Distance Per Pulse
    //Math.PI/2
    //(Counts per revolution*Gear ratio(5:1=5 / 1:5))/(Turns per revolution * 2π)

    //Slide Distance Per Pulse
    //(COUNTS_PER_PULSE * GEAR_RATIO) / (GEAR_DIAMETER_INCHES * Math.PI);

    public static Pivot pivot;
    public static class Pivot {
        public final static String pivotMName = "clawM";
        public static NebulaMotor.Direction pivotDirection = NebulaMotor.Direction.Reverse;
//        public int pivotDistancePerPulse = 360 / (gear_ratio * pulses_per_rev);// For Internal Encoder
        public static NebulaMotor.IdleMode pivotIdleMode = NebulaMotor.IdleMode.Brake;
        public final static NebulaMotor.MotorType pivotType = NebulaMotor.MotorType.RPM_312;
        public static PIDFCoefficients pivotPID = new PIDFCoefficients(.005, 0.00, 0.0,0);
        public static int pivotTolerance = 10;
        public static double ks=0,
            kcos=0,
            ka=0,
            kv=0;
        public static double maxVelocity = 0,
            maxAcceleration = 0,
            MIN_POSITION = Math.toRadians(20),
            MAX_POSITION = Math.toRadians(230);
    }
    public static Claw claw;
    public static class Claw {
        public final static String clawSName = "clawS2";  //EH3
        public final static NebulaServo.Direction clawDirection = NebulaServo.Direction.Reverse;
        public static double minAngle = 0, maxAngle = 360;
    }
    public static Slide slide;
    public static class Slide {
        public final static String slideMName1 = "lift";
        public final static String slideMName2 = "lift2";
        public static NebulaMotor.Direction slideDirection1 = NebulaMotor.Direction.Reverse,
                                            slideDirection2 = NebulaMotor.Direction.Forward;
        public static PIDFCoefficients slidePID = new PIDFCoefficients(0.005, 0.00, 0,0);//.0075, 0., .003, 0)
        public static int slideTolerance = 10;
//        public int slideDistancePerPulse = (COUNTS_PER_PULSE * GEAR_RATIO) / (GEAR_DIAMETER_INCHES * Math.PI);
//        public int slideDistancePerPulse = (GEAR_DIAMETER_INCHES * Math.PI);
        public static int slideDistancePerPulse = 1;
        public static NebulaMotor.IdleMode slideIdleMode = NebulaMotor.IdleMode.Brake;
        public final static NebulaMotor.MotorType slideType = NebulaMotor.MotorType.RPM_312;
        public static double ks=0,
            kcos=0,
            ka=0,
            kv=0;
        public static double maxVelocity = 0,
            maxAcceleration = 0,
            MIN_POSITION = 0,//mm
            MAX_POSITION = 0;
    }
    public static Drive drive;
    public static class Drive {//TODO:FIX CONSTANTS
        public final static String leftFrontM = "leftFront",
            leftRearM = "leftRear",
            rightRearM = "rightRear",
            rightFrontM = "rightFront";
        public static NebulaMotor.Direction leftFrontDir = NebulaMotor.Direction.Reverse,
            leftRearDir = NebulaMotor.Direction.Reverse,
            rightRearDir = NebulaMotor.Direction.Forward,
            rightFrontDir = NebulaMotor.Direction.Forward;
        public static NebulaMotor.IdleMode driveIdleMode = NebulaMotor.IdleMode.Brake;
        public final static NebulaMotor.MotorType driveType = NebulaMotor.MotorType.RPM_435;
        public static boolean isSquaredInputs = true;
        public static double tippingTolerance = 5;//This probably needs to be less
    }
    public static ClawAlignToServo clawAlignToServo;
    public static class ClawAlignToServo {//14361
        public final static String backL = "bL",
            backR = "bR",
            poleR = "pR",
            poleL = "pL";
    }
    public static class Gamepad {
        public static double isDriverOneDeadband(double value) {
            if(Math.abs(value)<0.1){
                return 0;
            } else return value;
        }
        public static double isDriverTwoDeadband(double value) {
            if(Math.abs(value)<0.1){
                return 0;
            } else return value;
        }
        public static boolean overrideSafety = false;
    }
    public static Shooter shooter;
    public static class Shooter {
        public final static String shooterMName = "shooterM",
            shooterM2Name = "shooterM2";
        public static NebulaMotor.Direction shooterDirection = NebulaMotor.Direction.Reverse,
            shooter2Direction = NebulaMotor.Direction.Reverse;
        public static int shooterDistancePerPulse = 1;
        //        public int pivotDistancePerPulse = 360 / (gear_ratio * pulses_per_rev);// For Internal Encoder
        public static NebulaMotor.IdleMode shooterIdleMode = NebulaMotor.IdleMode.Brake;
        public final static NebulaMotor.MotorType shooterType = NebulaMotor.MotorType.RPM_312;
        public static PIDFCoefficients shooterPID = new PIDFCoefficients(.005, 0.00, 0.0,0);
        public static int shooterTolerance = 1;
        public static double ks=0,
            ka=0,
            kv=0;
        public static double maxVelocity = 0,
            maxAcceleration = 0,
            MIN_SPEED = -400,
            MAX_SPEED = 400;
    }
    public static Hood hood;
    public static class Hood {
        public final static String shooterMName = "shooterM",
            shooterM2Name = "shooterM2";
        public static NebulaMotor.Direction shooterDirection = NebulaMotor.Direction.Reverse,
            shooter2Direction = NebulaMotor.Direction.Reverse;
//        public int distancePerPulse = 360 / (gear_ratio * pulses_per_rev);// For Internal Encoder

        public static NebulaMotor.IdleMode hoodIdleMode = NebulaMotor.IdleMode.Brake;
        public final static NebulaMotor.MotorType hoodType = NebulaMotor.MotorType.RPM_312;
        public static PIDFCoefficients shooterPID = new PIDFCoefficients(.005, 0.00, 0.0,0);
        public static int shooterTolerance = 1;
        public static double ks=0,
            ka=0,
            kv=0;
        public static double maxVelocity = 0,
            maxAcceleration = 0,
            MIN_POSITION = 20,
            MAX_POSITION = 200;
    }
    public static ServoHood servoHood;
    public static class ServoHood {
        public final static String hoodSName = "hood";  //EH3
        public final static NebulaServo.Direction hoodDirection = NebulaServo.Direction.Reverse;
        public static double minAngle = 0, maxAngle = 360;
    }
    public static Intake intake;
    public static class Intake {
        public final static String intakeMName = "intakeM",
            intakeM2Name = "intakeM2";//TODO: Remove 2nd Intake Motor
        public static NebulaMotor.Direction intakeDirection = NebulaMotor.Direction.Reverse,
            intake2Direction = NebulaMotor.Direction.Reverse;
//        public int pivotDistancePerPulse = 360 / (gear_ratio * pulses_per_rev);// For Internal Encoder

        public static NebulaMotor.IdleMode intakeIdleMode = NebulaMotor.IdleMode.Brake;
        public final static NebulaMotor.MotorType intakeType = NebulaMotor.MotorType.RPM_312;
        public static PIDFCoefficients intakePID = new PIDFCoefficients(.005, 0.00, 0.0,0);
        public static int intakeTolerance = 1;
        public static double ks=0,
            ka=0,
            kv=0;
        public static double maxVelocity = 0,
            maxAcceleration = 0;
        public static ElapsedTime intakeTime = new ElapsedTime(0);
    }
    public static CamServo camServo;
    public static class CamServo {
        public final static String camSName = "cam";  //EH3
        public final static NebulaServo.Direction camDirection = NebulaServo.Direction.Reverse;
        public static double minAngle = 0, maxAngle = 360;
    }
//From RobotAutoDriveByGyro_Linear.java
//    // Calculate the COUNTS_PER_INCH for your specific drive train.
//    // Go to your motor vendor website to determine your motor's COUNTS_PER_MOTOR_REV
//    // For external drive gearing, set DRIVE_GEAR_REDUCTION as needed.
//    // For example, use a value of 2.0 for a 12-tooth spur gear driving a 24-tooth spur gear.
//    // This is gearing DOWN for less speed and more torque.
//    // For gearing UP, use a gear ratio less than 1.0. Note this will affect the direction of wheel rotation.
//    static final double     COUNTS_PER_MOTOR_REV    = 537.7 ;   // eg: GoBILDA 312 RPM Yellow Jacket
//    static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // No External Gearing.
//    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
//    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
//        (WHEEL_DIAMETER_INCHES * 3.1415);
    public static double squareInput(double value) {
        return value * Math.abs(value);
    }
    public static double cubeInput(double value) {
        return value * Math.abs(value) * Math.abs(value);
    }

}
