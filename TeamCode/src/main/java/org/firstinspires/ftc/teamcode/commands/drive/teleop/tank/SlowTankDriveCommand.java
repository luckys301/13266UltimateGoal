package org.firstinspires.ftc.teamcode.commands.drive.teleop.tank;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import org.firstinspires.ftc.teamcode.subsystems.drive.sixWheelDrive.TankDriveSubsystem;

public class SlowTankDriveCommand extends DefaultTankDriveCommand {
    public SlowTankDriveCommand(TankDriveSubsystem drive, GamepadEx driverGamepad) {
        super(drive, driverGamepad);
        this.multiplier = 0.5;
    }
}