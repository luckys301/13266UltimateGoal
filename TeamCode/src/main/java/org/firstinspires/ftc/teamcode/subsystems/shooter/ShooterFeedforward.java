package org.firstinspires.ftc.teamcode.subsystems.shooter;

import com.arcrobotics.ftclib.controller.wpilibcontroller.SimpleMotorFeedforward;
import com.arcrobotics.ftclib.trajectory.TrapezoidProfile;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.NebulaConstants;

public class ShooterFeedforward extends Shooter {
    protected SimpleMotorFeedforward shooterFeedforward;

    protected TrapezoidProfile.State start = new TrapezoidProfile.State(getShooterRPM(), getShooterRPM());
    protected TrapezoidProfile.State goal;
    protected TrapezoidProfile.Constraints constraints;
    protected TrapezoidProfile trapezoidProfile;
    public ShooterFeedforward(Telemetry tl, HardwareMap hw, boolean isEnabled) {
        super(tl, hw, isEnabled);
        shooterFeedforward = new SimpleMotorFeedforward(
            NebulaConstants.Shooter.ks,
            NebulaConstants.Shooter.ka,
            NebulaConstants.Shooter.kv);
        constraints = new TrapezoidProfile.Constraints(
            0,// radians per second
            0);//radians per second per second

        trapezoidProfile = new TrapezoidProfile(constraints, goal, start);
    }

    @Override
    public void periodic() {
//        slideController.setF(NebulaConstants.Slide.slidePID.f * Math.cos(Math.toRadians(slideController.getSetPoint())));
        trapezoidProfile = new TrapezoidProfile(constraints, goal, start);
        start = trapezoidProfile.calculate(0.02);
        double output = (controller.calculate(getShooterRPM()) +
            (shooterFeedforward.calculate(start.position, start.velocity)));
        motorGroup.setPower(output);
        telemetry.addData("Shooter RPM:", getShooterRPM());
        telemetry.addData("Shooter Required RPM:", controller.getSetPoint());
    }
}