package org.firstinspires.ftc.teamcode.subsystems;


import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.NebulaConstants;
import org.firstinspires.ftc.teamcode.util.nebulaHardware.NebulaServo;

@Config
public class Claw extends SubsystemBase
{
    public enum ClawPos {
        CLOSE_POS_S1(0.51),
        OPEN_POS_S1(0.2);

        public final double clawPosition;
        ClawPos(double clawPosition) {
            this.clawPosition = clawPosition;
        }
    }
    Telemetry telemetry;
    private final NebulaServo clawS1;     //Claw

    public Claw(Telemetry tl, HardwareMap hw, boolean isEnabled) {
        clawS1 = new NebulaServo(hw,
            NebulaConstants.Claw.clawSName,
            NebulaConstants.Claw.clawDirection,
            NebulaConstants.Claw.minAngle,
            NebulaConstants.Claw.maxAngle,
            isEnabled);
        clawS1.setPosition(ClawPos.CLOSE_POS_S1.clawPosition);  //Port 3

        this.telemetry = tl;
    }

    @Override
    public void periodic() {
        telemetry.addData("Claw Servo 1 Pos: ", clawS1.getPosition());
    }

    public void clawClose() {
        clawS1.setPosition(ClawPos.CLOSE_POS_S1.clawPosition);
    }
    public void clawOpen() {
        clawS1.setPosition(ClawPos.OPEN_POS_S1.clawPosition);
    }

    public boolean isClawOpen(){
//        return clawS1.getPosition()==PusherPos.OPEN_POS_S1;
        return (clawS1.getPosition()==ClawPos.CLOSE_POS_S1.clawPosition);
    };

}