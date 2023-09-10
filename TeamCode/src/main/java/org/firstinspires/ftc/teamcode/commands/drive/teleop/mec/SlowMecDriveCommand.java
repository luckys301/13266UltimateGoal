package org.firstinspires.ftc.teamcode.commands.drive.teleop.mec;

import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.subsystems.drive.mecDrive.MecDriveSubsystem;

public class SlowMecDriveCommand extends DefaultMecDriveCommand {
    public SlowMecDriveCommand(MecDriveSubsystem drive, GamepadEx driverGamepad, boolean isFieldCentric) {
        super(drive, driverGamepad, isFieldCentric);
        this.multiplier = 0.3;
    }
}