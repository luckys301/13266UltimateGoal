package org.firstinspires.ftc.teamcode.subsystems.shooterHood;


import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.NebulaConstants;
import org.firstinspires.ftc.teamcode.util.nebulaHardware.NebulaMotor;

@Config
public class ShooterHood extends SubsystemBase {
    public final PIDFController controller;

    public enum Position {
        IN(0),
        OUT(1),
        ALL_OUT(2);

        public final double pos;
        Position(double pos) {
            this.pos = pos;
        }
    }
    Position position = Position.IN;
    Telemetry telemetry;
    public final NebulaMotor motor;
//    private String posWriter = "NONE";

    public ShooterHood(Telemetry tl, HardwareMap hw, Boolean isEnabled) {
        motor = new NebulaMotor(hw, NebulaConstants.Hood.shooterMName,
            NebulaConstants.Hood.hoodType, NebulaConstants.Hood.shooterDirection,
            NebulaMotor.IdleMode.Coast, isEnabled);
        controller = new PIDFController(
            NebulaConstants.Hood.shooterPID.p,
            NebulaConstants.Hood.shooterPID.i,
            NebulaConstants.Hood.shooterPID.d,
            NebulaConstants.Hood.shooterPID.f,
            motor.getPosition(),
            motor.getPosition());
        controller.setTolerance(NebulaConstants.Hood.shooterTolerance);
        this.telemetry = tl;
        setSetPointCommand(Position.IN);
    }
//    ServoController;
//    ControlSystem;
//    ControlHubPasswordManager;//Reset password
//    ControlHubDeviceNameManager;
//    ControllerConfiguration;
    @Override
    public void periodic() {
        double output = (controller.calculate(motor.getPosition()));
        telemetry.addData("Shooter Hood Position:", motor.getPosition());
        telemetry.addData("Shooter Required Position:", controller.getSetPoint());

        motor.setPower(output);
    }
//    public void setSetPoint(Position pos) {
////        if(pos.pivotPosition>NebulaConstants.Pivot.MAX_POSITION ||
////            pos.pivotPosition<NebulaConstants.Pivot.MIN_POSITION){
////            motor.stopMotor();
////            return;
////        }
//        controller.setSetPoint(pos.pos);
//        position = pos;
//    }
    public void setSetPoint(double setPoint) {
        if(setPoint>NebulaConstants.Hood.MAX_POSITION ||
            setPoint<NebulaConstants.Hood.MIN_POSITION){
            motor.stop();
            return;
        }
//        posWriter = String.valueOf(setPoint);
        controller.setSetPoint(setPoint);
    }

    //TODO: Test!
    public Command setSetPointCommand(double setPoint) {
        return new InstantCommand(()->{setSetPoint(setPoint);});
    }
    public Command setSetPointCommand(Position pos) {
//        return new InstantCommand(()->{setSetPoint(pos);});
        return setSetPointCommand(pos.pos);
    }

    public void encoderReset() {//Motors wouldn't need reset
        motor.resetEncoder();
    }
    public double getSetPoint(){
        return controller.getSetPoint();
    }
}
