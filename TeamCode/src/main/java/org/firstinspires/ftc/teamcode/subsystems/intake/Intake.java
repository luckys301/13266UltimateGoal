package org.firstinspires.ftc.teamcode.subsystems.intake;


import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.NebulaConstants;
import org.firstinspires.ftc.teamcode.util.nebulaHardware.NebulaMotor;
import org.firstinspires.ftc.teamcode.util.nebulaHardware.NebulaMotorGroup;

@Config
public class Intake extends SubsystemBase {
    public final PIDFController controller;
    public final NebulaMotorGroup motorGroup;

    public enum IntakeRPM {
        OUTTAKE(100),
        INTAKE(-100,true),
        STOP(0);

        public final double speed;
        public final boolean reset;
        IntakeRPM(double speed) {
            this.speed = speed;
            this.reset = false;
        }
        IntakeRPM(double speed, boolean reset) {
            this.speed = speed;
            this.reset = reset;
        }
    }
    IntakeRPM shooterRPM = IntakeRPM.STOP;
    Telemetry telemetry;
    public final NebulaMotor motor, motor2;

    public Intake(Telemetry tl, HardwareMap hw, Boolean isEnabled) {
        motor = new NebulaMotor(hw, NebulaConstants.Intake.intakeMName,
            NebulaConstants.Intake.intakeType, NebulaConstants.Intake.intakeDirection,
            NebulaMotor.IdleMode.Coast, isEnabled);
        motor2 = new NebulaMotor(hw, NebulaConstants.Intake.intakeM2Name,
            NebulaConstants.Intake.intakeType, NebulaConstants.Intake.intake2Direction,
            NebulaMotor.IdleMode.Coast, isEnabled);
        motorGroup = new NebulaMotorGroup(motor, motor2);
//        motor.setDistancePerPulse(1);
        controller = new PIDFController(
            NebulaConstants.Intake.intakePID.p,
            NebulaConstants.Intake.intakePID.i,
            NebulaConstants.Intake.intakePID.d,
            NebulaConstants.Intake.intakePID.f,
            getShooterRPM(),
            getShooterRPM());
        controller.setTolerance(NebulaConstants.Intake.intakeTolerance);
        this.telemetry = tl;
    }

    @Override
    public void periodic() {
        double output = (controller.calculate(getShooterRPM()));
        telemetry.addData("Intake RPM:", getShooterRPM());
        telemetry.addData("Intake Required RPM:", controller.getSetPoint());

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

//    public void setSetPoint(IntakeRPM speed) {
////        if(pos.pivotPosition>NebulaConstants.Pivot.MAX_POSITION ||
////            pos.pivotPosition<NebulaConstants.Pivot.MIN_POSITION){
////            motor.stopMotor();
////            return;
////        }
//        controller.setSetPoint(speed.speed);
//        shooterRPM = speed;
//        if(speed.reset){
//            NebulaConstants.Intake.intakeTime.reset();
//        }
//    }
    public void setSetPoint(double setPoint, boolean reset) {
//        if(setPoint>NebulaConstants.Intake.MAX_POSITION ||
//            setPoint<NebulaConstants.Intake.MIN_POSITION){
//            motor.stop();
//            return;
//        }
        controller.setSetPoint(setPoint);
        if(reset){NebulaConstants.Intake.intakeTime.reset();}
    }

    //TODO: Test!
    public Command setSetPointCommand(double setPoint, boolean reset) {
        return new InstantCommand(()->{setSetPoint(setPoint, reset);});
    }
    public Command setSetPointCommand(IntakeRPM pos) {
        return setSetPointCommand(pos.speed, pos.reset);
//        return new InstantCommand(()->{setSetPoint(pos);});
    }

    public void encoderReset() {//Motors wouldn't need reset
        motorGroup.resetEncoder();
    }
    public double getSetPoint(){
        return controller.getSetPoint();
    }

    public boolean isIntaked(){//TODO:Needs to have something where it times
        if(NebulaConstants.Intake.intakeTime.seconds()>2){
            return controller.getVelocityError()>100;//Whatever the Number is
        }
        return false;
    }
}
