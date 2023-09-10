package org.firstinspires.ftc.teamcode.opmode.roadrunner.unused;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.opmode.roadrunner.RoadrunnerValues;
import org.firstinspires.ftc.teamcode.subsystems.drive.mecDrive.MecDrive;
import org.firstinspires.ftc.teamcode.subsystems.drive.mecDrive.MecDriveConstants;

import java.util.Objects;

/**
 * This routine is designed to calculate the maximum velocity your bot can achieve under load. It
 * will also calculate the effective kF value for your velocity PID.
 * <p>
 * Upon pressing start, your bot will run at max power for RUNTIME seconds.
 * <p>
 * Further fine tuning of kF may be desired.
 */
@Disabled
@Autonomous(group = "drive")
public class MaxVelocityTuner extends LinearOpMode {
//    public static double RUNTIME = 2.0;

    private ElapsedTime timer;
//    private double maxVelocity = 0.0;

    private VoltageSensor batteryVoltageSensor;

    @Override
    public void runOpMode() throws InterruptedException {
        MecDrive drive = new MecDrive(hardwareMap, telemetry, true);

        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        batteryVoltageSensor = hardwareMap.voltageSensor.iterator().next();

        Telemetry telemetry = new MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().getTelemetry());

        telemetry.addLine("Your bot will go at full speed for " + RoadrunnerValues.MaxVelocityTuner.RUNTIME + " seconds.");
        telemetry.addLine("Please ensure you have enough space cleared.");
        telemetry.addLine("");
        telemetry.addLine("Press start when ready.");
        telemetry.update();

        waitForStart();

        telemetry.clearAll();
        telemetry.update();

        drive.setDrivePower(new Pose2d(1, 0, 0));
        timer = new ElapsedTime();

        while (!isStopRequested() && timer.seconds() < RoadrunnerValues.MaxVelocityTuner.RUNTIME) {
            drive.updatePoseEstimate();

            Pose2d poseVelo = Objects.requireNonNull(drive.getPoseVelocity(), "poseVelocity() must not be null. Ensure that the getWheelVelocities() method has been overridden in your localizer.");

            RoadrunnerValues.MaxVelocityTuner.maxVelocity = Math.max(poseVelo.vec().norm(), RoadrunnerValues.MaxVelocityTuner.maxVelocity);
        }

        drive.setDrivePower(new Pose2d());

        double effectiveKf = MecDriveConstants.getMotorVelocityF(veloInchesToTicks(RoadrunnerValues.MaxVelocityTuner.maxVelocity));

        telemetry.addData("Max Velocity", RoadrunnerValues.MaxVelocityTuner.maxVelocity);
        telemetry.addData("Max Recommended Velocity", RoadrunnerValues.MaxVelocityTuner.maxVelocity * 0.8);
        telemetry.addData("Voltage Compensated kF", effectiveKf * batteryVoltageSensor.getVoltage() / 12);
        telemetry.update();

        while (!isStopRequested() && opModeIsActive()) idle();
    }

    private double veloInchesToTicks(double inchesPerSec) {
        return inchesPerSec / (2 * Math.PI * MecDriveConstants.WHEEL_RADIUS) / MecDriveConstants.GEAR_RATIO * MecDriveConstants.TICKS_PER_REV;
    }
}
