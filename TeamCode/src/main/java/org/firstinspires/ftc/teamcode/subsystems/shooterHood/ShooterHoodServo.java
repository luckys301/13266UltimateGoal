package org.firstinspires.ftc.teamcode.subsystems.shooterHood;


import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.NebulaConstants;
import org.firstinspires.ftc.teamcode.util.nebulaHardware.NebulaServo;

@Config
public class ShooterHoodServo extends SubsystemBase
{
    public enum ShooterPos {
        IN(0.51),
        OUT (0.5),
        ;

        public final double shooterPos;
        ShooterPos(double shooterPos) {
            this.shooterPos = shooterPos;
        }
    }
    Telemetry telemetry;
    private final NebulaServo clawS1;     //Claw

    public ShooterHoodServo(Telemetry tl, HardwareMap hw, boolean isEnabled) {
        clawS1 = new NebulaServo(hw,
            NebulaConstants.ServoHood.hoodSName,
            NebulaConstants.ServoHood.hoodDirection,
            NebulaConstants.ServoHood.minAngle,
            NebulaConstants.ServoHood.maxAngle,
            isEnabled);
        clawS1.setPosition(ShooterPos.IN.shooterPos);  //Port 3

        this.telemetry = tl;
    }

    @Override
    public void periodic() {
        telemetry.addData("Claw Servo 1 Pos: ", clawS1.getPosition());
    }

    public void setPosition(double pos) {
        clawS1.setPosition(pos);
    }
    public Command setPositionCommand(ShooterHoodServo.ShooterPos pos) {
        return new InstantCommand(()->{setPosition(pos.shooterPos);});
    }
}