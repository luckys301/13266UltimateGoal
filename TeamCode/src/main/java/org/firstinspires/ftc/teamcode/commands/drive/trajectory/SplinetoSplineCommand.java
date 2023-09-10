package org.firstinspires.ftc.teamcode.commands.drive.trajectory;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
import com.acmerobotics.roadrunner.trajectory.constraints.MinVelocityConstraint;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.drive.mecDrive.MecDriveSubsystem;
import org.firstinspires.ftc.teamcode.util.PoseStorage;
import org.firstinspires.ftc.teamcode.util.Trajectories;

public class SplinetoSplineCommand extends CommandBase{

    MecDriveSubsystem drive;
    Trajectory trajectory;
    boolean reverse;
    Pose2d splinePos;
    double endHeading;

    MinVelocityConstraint maxVelConstraint;
    public SplinetoSplineCommand(MecDriveSubsystem drive, MinVelocityConstraint constraint, Pose2d splinePos, double endHeading, boolean reverse) {
        this.drive = drive;
        this.maxVelConstraint = constraint;
        this.reverse = reverse;
        this.splinePos = splinePos;
        this.endHeading = endHeading;

        this.addRequirements(drive);
    }

    public SplinetoSplineCommand(MecDriveSubsystem drive, Pose2d splinePos, double endHeading) {
        this(drive, Trajectories.velConstraint, splinePos, endHeading, false);
    }

    public SplinetoSplineCommand(MecDriveSubsystem drive, Pose2d splinePos, double endHeading, boolean reverse) {
        this(drive, Trajectories.velConstraint, splinePos, endHeading, reverse);
    }


    @Override
    public void initialize() {
//        trajectory = new TrajectoryBuilder(drive.getPoseEstimate(), reverse, maxVelConstraint, Trajectories.accelConstraint)
//                .splineToSplineHeading(splinePos, endHeading)
//                .build();

        trajectory = new TrajectoryBuilder(PoseStorage.currentPose, reverse, maxVelConstraint, Trajectories.accelConstraint)
                .splineToSplineHeading(splinePos, endHeading)
                .build();
        drive.followTrajectory(trajectory);

    }

    @Override
    public void execute() {
        drive.update();
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            drive.stop();
        }
    }

    @Override
    public boolean isFinished() {
        PoseStorage.currentPose = trajectory.end();
        return !drive.isBusy();
    }
}