package org.firstinspires.ftc.teamcode.subsystems.drive.testMecDrive;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
import com.acmerobotics.roadrunner.trajectory.constraints.AngularVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.MecanumVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.MinVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.ProfileAccelerationConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryAccelerationConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryVelocityConstraint;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.teamcode.util.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.TrajectorySequenceBuilder;

import java.util.Arrays;
import java.util.List;

interface AbstractMecDriveInterface {
    TrajectoryBuilder trajectoryBuilder(Pose2d startPose);
    TrajectoryBuilder trajectoryBuilder(Pose2d startPose, boolean reversed);
    TrajectoryBuilder trajectoryBuilder(Pose2d startPose, double startHeading);
    TrajectorySequenceBuilder trajectorySequenceBuilder(Pose2d startPose);

    void breakFollowing();// Break Following
    void followTrajectoryAsync(Trajectory trajectory);
    void followTrajectorySequenceAsync(TrajectorySequence trajectorySequence);
    void followTrajectorySequence(TrajectorySequence trajectorySequence);
    void followTrajectory(Trajectory trajectory);
    void resetImu();
    void setMode(DcMotor.RunMode runMode);
    void setMotorPowers(double lFP, double lRP, double rFP, double rRP);
    void setPIDFCoefficients(DcMotor.RunMode runMode, PIDFCoefficients coefficients);
    void setWeightedDrivePower(Pose2d drivePower);
    void setZeroPowerBehavior(DcMotor.ZeroPowerBehavior zeroPowerBehavior);
    void turnAsync(double angle);
    void turn(double angle);
    void update();
    void waitForIdle();

    Pose2d getLastError();

    boolean isBusy();

    List<Double> getWheelPositions();
    List<Double> getWheelVelocities();
    Double getExternalHeadingVelocity();
    double getDegreeHeading();
    double getDegreePitch();
    double getDegreeRoll();
    double getRawExternalHeading();

//    @Override
//    public Double getExternalHeadingVelocity() {
//        return (double) imu.getRobotAngularVelocity(AngleUnit.RADIANS).zRotationRate;
//    }

    static TrajectoryVelocityConstraint getVelocityConstraint(double maxVel, double maxAngularVel, double trackWidth) {
        return new MinVelocityConstraint(Arrays.asList(
                new AngularVelocityConstraint(maxAngularVel),
                new MecanumVelocityConstraint(maxVel, trackWidth)
        ));
    }
    static TrajectoryAccelerationConstraint getAccelerationConstraint(double maxAccel) {
        return new ProfileAccelerationConstraint(maxAccel);
    }
}