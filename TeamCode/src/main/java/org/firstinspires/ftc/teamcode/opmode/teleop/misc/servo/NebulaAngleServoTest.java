package org.firstinspires.ftc.teamcode.opmode.teleop.misc.servo;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.util.nebulaHardware.NebulaServo;

//@Disabled
@TeleOp
public class NebulaAngleServoTest extends OpMode {
    NebulaServo servo;
    double angle = 150;
    @Override
    public void init() {
        servo = new NebulaServo(hardwareMap, "clawS2", NebulaServo.Direction.Forward, 0,360, true);
    }

    @Override
    public void loop() {
        if(gamepad1.dpad_down){
            angle -= 1;}
        else if(gamepad1.dpad_up){
            angle += 1;}

        if(gamepad1.right_bumper){
            angle = 60;}
        else if(gamepad1.left_bumper){
            angle = 300;}

        angle = Math.min(Math.max(angle, 0), 360);
        servo.turnToAngle(angle);
        telemetry.addData("NormalServoTest Pos: ", servo.getAngle());
        telemetry.addData("Desired Pos: ", angle);
        telemetry.update();

    }
}