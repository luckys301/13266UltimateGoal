package org.firstinspires.ftc.teamcode.subsystems.shooter;

import com.arcrobotics.ftclib.trajectory.TrapezoidProfile;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.NebulaConstants;
import org.firstinspires.ftc.teamcode.util.odoPod.Encoder;

public class ShooterFeedforwardThroughBore extends ShooterFeedforward {
    protected final Encoder slideEncoder;// Plugged into Motor;
    public ShooterFeedforwardThroughBore(Telemetry tl, HardwareMap hw, boolean isEnabled) {
        super(tl, hw, isEnabled);
        slideEncoder = new Encoder(
            hw.get(DcMotorEx.class, NebulaConstants.Slide.slideMName1));
        slideEncoder.getCurrentPosition();
        slideEncoder.setDistancePerPulse(1);
    }

    @Override
    public void periodic() {
//        slideController.setF(NebulaConstants.Slide.slidePID.f * Math.cos(Math.toRadians(slideController.getSetPoint())));
        constraints = new TrapezoidProfile.Constraints(
            0,// radians per second
            0);//radians per second per second

//        trapezoidProfile = new TrapezoidProfile(constraints, goal, start);
        start = trapezoidProfile.calculate(0.02);
        double output = (controller.calculate(getShooterRPM()) +
            (shooterFeedforward.calculate(start.position, start.velocity)));
        motorGroup.setPower(output);//TODO: Probably shouldn't be like this

        telemetry.addData("Shooter RPM:", getShooterRPM());
        telemetry.addData("Shooter Required RPM:", controller.getSetPoint());
    }

//    @Override
//    public void resetEncoder() {
//        encoderOffset = -(slideEncoder.getCurrentPosition()) + encoderOffset;
//    }
    @Override
    public double getShooterRPM() {
        return slideEncoder.getCurrentPosition();
    }
}