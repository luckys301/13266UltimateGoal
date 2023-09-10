package org.firstinspires.ftc.teamcode.subsystems.misc;


import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.NebulaConstants;
import org.firstinspires.ftc.teamcode.util.nebulaHardware.NebulaServo;

public class CameraServo extends SubsystemBase {
    //Claw Variables
    public final double blueRight = 0.4,
                        blueLeft = 0.98;

    Telemetry telemetry;
    private final NebulaServo camServo;

    public CameraServo(HardwareMap hM, Telemetry tl) {
        camServo = new NebulaServo(hM, NebulaConstants.CamServo.camSName,
            NebulaConstants.CamServo.camDirection,
            NebulaConstants.CamServo.minAngle,
            NebulaConstants.CamServo.maxAngle,true);
        this.camServo.setPosition(0.5);  //Port
        this.telemetry = tl;
    }

    @Override
    public void periodic() {
        telemetry.addData("CAM Servo 1 Pos: ", camServo.getPosition());
    }

    public void set(double clawServo1Pos) {
        camServo.setPosition(clawServo1Pos);
    }

}