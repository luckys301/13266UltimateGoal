package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.drive.teleop.mec.DefaultMecDriveCommand;
import org.firstinspires.ftc.teamcode.subsystems.drive.mecDrive.MecDrive;
import org.firstinspires.ftc.teamcode.subsystems.drive.mecDrive.MecDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.pivot.Pivot;
import org.firstinspires.ftc.teamcode.util.teleop.MatchOpMode;

@TeleOp(group = "Subsystem test")
public class TeleOpDrivetrainOnly extends MatchOpMode {

    // Gamepad
    private GamepadEx driverGamepad;

    // Subsystems
    private MecDriveSubsystem mecDriveSubsystem;

    @Override
    public void robotInit() {
        driverGamepad = new GamepadEx(gamepad1);
        mecDriveSubsystem = new MecDriveSubsystem(new MecDrive(hardwareMap, telemetry, true), telemetry, hardwareMap);
        mecDriveSubsystem.init();
        mecDriveSubsystem.setDefaultCommand(new DefaultMecDriveCommand(mecDriveSubsystem, driverGamepad, false));
    }


    @Override
    public void configureButtons() {
    }

    @Override
    public void matchLoop() {
    }
    @Override
    public void disabledPeriodic() {
        telemetry.addData("RESET", Pivot.RESET.pivotPosition);
    }
    @Override
    public void matchStart() { }
    @Override
    public void robotPeriodic(){
    }
}
