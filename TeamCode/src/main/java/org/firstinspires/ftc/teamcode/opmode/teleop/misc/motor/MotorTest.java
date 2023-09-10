package org.firstinspires.ftc.teamcode.opmode.teleop.misc.motor;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.util.nebulaHardware.NebulaMotor;

//@Disabled
@TeleOp
public class MotorTest extends OpMode {
    NebulaMotor motor;
    @Override
    public void init() {
//        motor = hardwareMap.get(NebulaMotor.class, "leftRear");
        motor = new NebulaMotor(hardwareMap,
                "leftRear",
            NebulaMotor.MotorType.RPM_435,
                NebulaMotor.Direction.Forward,
                NebulaMotor.IdleMode.Coast,
                false);
    }

    @Override
    public void loop() {
        if(gamepad1.dpad_down){motor.setPower(1);}
        else if(gamepad1.dpad_up){motor.setPower(-1);}
        else{motor.setPower(0);}
    }
}