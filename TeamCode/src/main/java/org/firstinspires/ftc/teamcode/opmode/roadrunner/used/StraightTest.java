package org.firstinspires.ftc.teamcode.opmode.roadrunner.used;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.opmode.roadrunner.RoadrunnerValues;
import org.firstinspires.ftc.teamcode.subsystems.drive.mecDrive.MecDrive;

/*
 * This is a simple routine to test translational drive capabilities.
 */
//@Disabled
@Autonomous(group = "drive")
public class StraightTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Telemetry telemetry = new MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().getTelemetry());

        MecDrive drive = new MecDrive(hardwareMap, telemetry, true);

        Trajectory trajectory = drive.trajectoryBuilder(new Pose2d())
                .forward(RoadrunnerValues.StraightTest.DISTANCE)
                .build();

        waitForStart();

        if (isStopRequested()) return;

        drive.followTrajectory(trajectory);

        Pose2d poseEstimate = drive.getPoseEstimate();
        telemetry.addData("finalX", poseEstimate.getX());
        telemetry.addData("finalY", poseEstimate.getY());
        telemetry.addData("finalHeading", poseEstimate.getHeading());
        telemetry.update();

        while (!isStopRequested() && opModeIsActive()) ;
    }
}
