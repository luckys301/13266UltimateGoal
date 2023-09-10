package org.firstinspires.ftc.teamcode.opmode.teleop.misc.motor;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

//@Disabled
@TeleOp
public class NormalServoTest extends OpMode {
    com.qualcomm.robotcore.hardware.Servo servo;
    double pos = 0.3;
    @Override
    public void init() {
        servo = hardwareMap.get(com.qualcomm.robotcore.hardware.Servo.class, "clawS2");
    }

    @Override
    public void loop() {
        if(gamepad1.dpad_down){pos -= 0.001;}
        else if(gamepad1.dpad_up){pos += 0.001;}

        if(gamepad1.right_bumper){pos = 0;}
        else if(gamepad1.left_bumper){pos = 1;}

        pos = Math.min(Math.max(pos, 0), 1);
        servo.setPosition(pos);
        telemetry.addData("NormalServoTest Pos: ", servo.getPosition());
        telemetry.addData("Desired Pos: ", pos);
        telemetry.update();

    }
}