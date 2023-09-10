package org.firstinspires.ftc.teamcode.opmode.teleop.misc.motor;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.util.teleop.GamepadTrigger;
import org.firstinspires.ftc.teamcode.util.teleop.MatchOpMode;

@TeleOp
public class NeveRestMotorTest extends MatchOpMode {
    // Gamepad
    private GamepadEx driverGamepad;
    private DcMotor neveRestM;
//    private NeveRest20Gearmotor neveRest20Gearmotor;
//    private NeveRest40Gearmotor neveRest40Gearmotor;
//    private NeveRest60Gearmotor neveRest60Gearmotor;
    @Override
    public void robotInit() {
        driverGamepad = new GamepadEx(gamepad1);
        neveRestM = hardwareMap.get(DcMotor.class, "neveRest");
        neveRestM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }


    @Override
    public void configureButtons() {
        new GamepadTrigger(driverGamepad, GamepadKeys.Trigger.RIGHT_TRIGGER)
            .whileHeld(new InstantCommand(()-> {neveRestM.setPower(1);}))
            .whenReleased(new InstantCommand(()-> {neveRestM.setPower(0);}));
        new GamepadTrigger(driverGamepad, GamepadKeys.Trigger.LEFT_TRIGGER)
            .whileHeld(new InstantCommand(()-> {neveRestM.setPower(-1);}))
            .whenReleased(new InstantCommand(()-> {neveRestM.setPower(0);}));
        ;
    }

    @Override
    public void matchLoop() {}
    @Override
    public void disabledPeriodic() { }
    @Override
    public void matchStart() { }
    @Override
    public void robotPeriodic(){
    }
}
