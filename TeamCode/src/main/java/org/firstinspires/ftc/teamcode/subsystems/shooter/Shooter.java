package org.firstinspires.ftc.teamcode.subsystems.shooter;


import static org.firstinspires.ftc.teamcode.subsystems.shooter.ShooterValue.make;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.shooter.ShooterValue.ShooterEnum;
import org.firstinspires.ftc.teamcode.util.NebulaConstants;
import org.firstinspires.ftc.teamcode.util.nebulaHardware.NebulaMotor;
import org.firstinspires.ftc.teamcode.util.nebulaHardware.NebulaMotorGroup;

@Config
public class Shooter extends SubsystemBase {
    protected final PIDFController controller;
    protected final NebulaMotorGroup motorGroup;

    public static ShooterValue INTAKE = make(ShooterEnum.INTAKE,1);
    public static ShooterValue OUTTAKE = make(ShooterEnum.OUTTAKE,5);
    public static ShooterValue STOP = make(ShooterEnum.STOP,1);

    protected ShooterEnum shooterRPM;
    protected Telemetry telemetry;
    protected final NebulaMotor motor, motor2;

    public Shooter(Telemetry tl, HardwareMap hw, Boolean isEnabled) {
        motor = new NebulaMotor(hw, NebulaConstants.Shooter.shooterMName,
            NebulaConstants.Shooter.shooterType, NebulaConstants.Shooter.shooterDirection,
            NebulaMotor.IdleMode.Coast, isEnabled);
        motor2 = new NebulaMotor(hw, NebulaConstants.Shooter.shooterM2Name,
            NebulaConstants.Shooter.shooterType, NebulaConstants.Shooter.shooter2Direction,
            NebulaMotor.IdleMode.Coast, isEnabled);
        motorGroup = new NebulaMotorGroup(motor, motor2);
        motorGroup.setDistancePerPulse(NebulaConstants.Shooter.shooterDistancePerPulse);
        controller = new PIDFController(
            NebulaConstants.Shooter.shooterPID.p,
            NebulaConstants.Shooter.shooterPID.i,
            NebulaConstants.Shooter.shooterPID.d,
            NebulaConstants.Shooter.shooterPID.f,
            getShooterRPM(),
            getShooterRPM());
        controller.setTolerance(NebulaConstants.Shooter.shooterTolerance);
        this.telemetry = tl;
        shooterRPM = ShooterEnum.STOP;
    }

    @Override
    public void periodic() {
        double output = (controller.calculate(getShooterRPM()));
        telemetry.addData("Shooter RPM:", getShooterRPM());
        telemetry.addData("Shooter Required RPM:", controller.getSetPoint());

        motorGroup.setPower(output);
    }


    public double getShooterRPM() {
        return motorGroup.getCorrectedVelocity();//TODO:Fix RPM Math
//        return 60 * ((double) motorGroup.getCorrectedVelocity() /
//            (double) Constants.SHOOTER_TPR);
    }
    // motor.getRPM() *60/motor.getCPR()
    // rpm_right = (float)(right_wheel_pulse_count * 60 / ENC_COUNT_REV);
    //    ang_velocity_right = rpm_right * rpm_to_radians;
    //    ang_velocity_right_deg = ang_velocity_right * rad_to_deg;

    public void setSetPoint(double setPoint) {
        if(NebulaConstants.Gamepad.overrideSafety){
            if(setPoint>NebulaConstants.Shooter.MAX_SPEED ||
                setPoint<NebulaConstants.Shooter.MIN_SPEED){
                motorGroup.stop();
                return;
            }
        }
        controller.setSetPoint(setPoint);
    }

    //TODO: Test!
    public Command setSetPointCommand(double setPoint) {
        shooterRPM = ShooterEnum.MANUAL;
        return new InstantCommand(()->{setSetPoint(setPoint);});
    }
    public Command setSetPointCommand(ShooterValue pos) {
        shooterRPM = pos.shooterEnum;
        return new InstantCommand(()->{setSetPoint(pos.shooterRPM);});
    }

    public void encoderReset() {//Motors wouldn't need reset
        motorGroup.resetEncoder();
    }
    public double getSetPoint(){
        return controller.getSetPoint();
    }
}
