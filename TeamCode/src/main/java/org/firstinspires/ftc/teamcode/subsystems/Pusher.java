package org.firstinspires.ftc.teamcode.subsystems;


import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.shooter.ShooterValue;
import org.firstinspires.ftc.teamcode.util.NebulaConstants;
import org.firstinspires.ftc.teamcode.util.nebulaHardware.NebulaServo;

@Config
public class Pusher extends SubsystemBase
{
    public enum PusherPos {
        SHOOT(0.51),
        NORMAL(0.2);

        public final double clawPosition;
        PusherPos(double clawPosition) {
            this.clawPosition = clawPosition;
        }
    }
    Telemetry telemetry;
    private final NebulaServo clawS1;     //Claw

    public Pusher(Telemetry tl, HardwareMap hw, boolean isEnabled) {
        clawS1 = new NebulaServo(hw,
            NebulaConstants.Claw.clawSName,
            NebulaConstants.Claw.clawDirection,
            NebulaConstants.Claw.minAngle,
            NebulaConstants.Claw.maxAngle,
            isEnabled);
        clawS1.setPosition(PusherPos.NORMAL.clawPosition);  //Port 3

        this.telemetry = tl;
    }

    @Override
    public void periodic() {
        telemetry.addData("Claw Servo 1 Pos: ", clawS1.getPosition());
    }

    public void shoot() {
        clawS1.setPosition(PusherPos.SHOOT.clawPosition);
        new WaitCommand(100);
        clawS1.setPosition(PusherPos.NORMAL.clawPosition);
    }
    public Command shootCommand() {
        return new InstantCommand(this::shoot);
    }


}