package org.firstinspires.ftc.teamcode.opmode.roadrunner;


import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Vector2d;
@Config

public class RoadrunnerValues {
    public static AutomaticFeedforwardTuner automaticFeedforwardTuner;
    public static class AutomaticFeedforwardTuner {
        public static double DISTANCE = 100, MAX_POWER = 0.7;
    }
    public static BackAndForth backAndForth;
    public static class BackAndForth {
        public static double DISTANCE = 50;
    }
    public static DriveVelocityPIDTuner driveVelocityPIDTuner;
    public static class DriveVelocityPIDTuner {
        public static double DISTANCE = 72;
        public enum Mode {
            DRIVER_MODE,
            TUNING_MODE
        }
    }
    public static FollowerPIDTuner followerPIDTuner;
    public static class FollowerPIDTuner {
        public static double DISTANCE = 72;
    }
    public static ManualFeedforwardTuner manualFeedforwardTuner;
    public static class ManualFeedforwardTuner {
        public static double DISTANCE = 60;
    }
    public static MaxAngularVeloTuner maxAngularVeloTuner;
    public static class MaxAngularVeloTuner {
        public static double RUNTIME = 4,
            maxAngVelocity = 0;
    }
    public static MaxVelocityTuner maxVelocityTuner;
    public static class MaxVelocityTuner {
        public static double RUNTIME = 60,
            maxVelocity = 0;
    }
    public static MotionDirectionDebugger motionDirectionDebugger;
    public static class MotionDirectionDebugger {
        public static double MOTOR_POWER=0.7;
    }
    public static SplineTest splineTest;
    public static class SplineTest {
        public static double x = 30,
            y=30,
            endTangent=0;
        public static Vector2d vector = new Vector2d(x,y);
    }
    public static StrafeTest strafeTest;
    public static class StrafeTest {
        public static double DISTANCE = 60;
    }
    public static StraightTest straightTest;
    public static class StraightTest {
        public static double DISTANCE = 60;
    }
    public static TrackingWheelForwardOffsetTuner trackingWheelForwardOffsetTuner;
    public static class TrackingWheelForwardOffsetTuner {
        public static double ANGLE = 4;
        public static int NUM_TRIALS = 5;
        public static long DELAY = 1000;
    }
    public static TrackingWheelLateralDistanceTuner trackingWheelLateralDistanceTuner;
    public static class TrackingWheelLateralDistanceTuner {
        public static int NUM_TURNS = 10;
    }
    public static TrackWidthTuner trackWidthTuner;
    public static class TrackWidthTuner {
        public static double ANGLE = 180; // deg
        public static int NUM_TRIALS = 5;
        public static int DELAY = 1000; // ms
    }
    public static TurnTest turnTest;
    public static class TurnTest {
        public static double ANGLE = 90; // deg
    }


//    public static RoadrunnerValues roadrunner;
}
