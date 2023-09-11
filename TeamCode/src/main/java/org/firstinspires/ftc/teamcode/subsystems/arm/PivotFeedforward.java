package org.firstinspires.ftc.teamcode.subsystems.pivot;

import com.arcrobotics.ftclib.controller.wpilibcontroller.ArmFeedforward;
import com.arcrobotics.ftclib.trajectory.TrapezoidProfile;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.NebulaConstants;
import org.firstinspires.ftc.teamcode.util.misc.Util;

import java.util.logging.Level;

public class PivotFeedforward extends Pivot {
    //Zero HAS TO be parallel to the ground and encoder needs to be in radians
    protected ArmFeedforward armFeedforward;

    protected TrapezoidProfile.State start;
    protected TrapezoidProfile.State goal;
    protected TrapezoidProfile.Constraints constraints;
    protected TrapezoidProfile trapezoidProfile;

    public PivotFeedforward(Telemetry tl, HardwareMap hw, boolean isEnabled) {
        super(tl, hw, isEnabled);

        armFeedforward = new ArmFeedforward(
            NebulaConstants.Pivot.ks,
            NebulaConstants.Pivot.kcos,
            NebulaConstants.Pivot.ka,
            NebulaConstants.Pivot.kv);
        constraints = new TrapezoidProfile.Constraints(
            0,// radians per second
            0);//radians per second per second

        start = new TrapezoidProfile.State(getEncoderDistance(), armMotor.getVelocity());
        goal = new TrapezoidProfile.State(0,0);
        trapezoidProfile = new TrapezoidProfile(constraints, goal, start);
    }

    @Override
    public void periodic() {
        //Might need to make manual only feedforward
        if (armAutomatic) {
//            controller.setF(NebulaConstants.Pivot.pivotPID.f * Math.cos(Math.toRadians(controller.getSetPoint())));
            //^^^ Not needed in my opinion
            trapezoidProfile = new TrapezoidProfile(constraints, goal, start);
            start = trapezoidProfile.calculate(0.02);
            double output = (controller.calculate(getEncoderDistance()) +
                (armFeedforward.calculate(start.position, start.velocity)));

            telemetry.addData("CLaw Motor Output:", output);
            telemetry.addData("Arm Velocity:", armMotor.getVelocity());

            armMotor.setPower(output);
        }
        Util.logger(this, telemetry, Level.INFO, "Arm Encoder Pos: ", armMotor.getPosition());
        Util.logger(this, telemetry, Level.INFO, "Arm Pos: ", pivotPos);

    }
}
