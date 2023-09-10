package org.firstinspires.ftc.teamcode.subsystems.intake;


import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.NebulaConstants;
import org.firstinspires.ftc.teamcode.util.nebulaHardware.NebulaMotor;
import org.firstinspires.ftc.teamcode.util.nebulaHardware.NebulaMotorGroup;

@Config
public class PowerIntake extends SubsystemBase {
    public final NebulaMotorGroup motorGroup;

    public enum IntakePower {
        OUTTAKE(0.5),
        INTAKE(-0.5,true),
        STOP(0);

        public final double power;
        public final boolean reset;
        IntakePower(double power) {
            this.power = power;
            this.reset = false;
        }
        IntakePower(double power, boolean reset) {
            this.power = power;
            this.reset = reset;
        }
    }
    IntakePower shooterRPM = IntakePower.STOP;
    Telemetry telemetry;
    public final NebulaMotor motor, motor2;

    public PowerIntake(Telemetry tl, HardwareMap hw, Boolean isEnabled) {
        motor = new NebulaMotor(hw, NebulaConstants.Intake.intakeMName,
            NebulaConstants.Intake.intakeType, NebulaConstants.Intake.intakeDirection,
            NebulaMotor.IdleMode.Coast, isEnabled);
        motor2 = new NebulaMotor(hw, NebulaConstants.Intake.intakeM2Name,
            NebulaConstants.Intake.intakeType, NebulaConstants.Intake.intake2Direction,
            NebulaMotor.IdleMode.Coast, isEnabled);
        motorGroup = new NebulaMotorGroup(motor, motor2);
//        motor.setDistancePerPulse(1);
        this.telemetry = tl;
    }

    @Override
    public void periodic() {
        telemetry.addData("Intake Speed:", motorGroup.getVelocity());
    }

    public void setPower(double power, boolean reset) {
        motorGroup.setPower(power);
        if(reset){
            NebulaConstants.Intake.intakeTime.reset();
        }
    }

    //TODO: Test!
    public Command setSetPointCommand(double power, boolean reset) {
        return new InstantCommand(()->{
            setPower(power, reset);});
    }
    public Command setSetPointCommand(IntakePower pos) {
        return setSetPointCommand(pos.power, pos.reset);
//        return new InstantCommand(()->{setSetPoint(pos);});
    }

    public void encoderReset() {//Motors wouldn't need reset
        motorGroup.resetEncoder();
    }

    @Deprecated //To FInd Alternative or Not Use
    public boolean isIntaked(){//TODO:Needs to have something where it times
        if(NebulaConstants.Intake.intakeTime.seconds()>2){
//            return controller.getVelocityError()>100;//Whatever the Number is
        }
        return false;
    }
}
