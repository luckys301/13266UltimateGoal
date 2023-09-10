package org.firstinspires.ftc.teamcode.commands.drive.teleop.mec;

import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.subsystems.drive.mecDrive.MecDriveSubsystem;
import org.firstinspires.ftc.teamcode.util.NebulaConstants;

public class DefaultMecDriveCommand extends CommandBase {
    private final MecDriveSubsystem drive;
    private final GamepadEx driverGamepad;

    protected double multiplier;
    boolean isFieldCentric;

    private static final PIDCoefficients X_TIPPING_PID = new PIDCoefficients(3, 0, 0);
    private static final PIDController xTipController = new PIDController(X_TIPPING_PID.kP, X_TIPPING_PID.kI, X_TIPPING_PID.kD);
    private static final PIDCoefficients Y_TIPPING_PID = new PIDCoefficients(3, 0, 0);
    private static final PIDController yTipController = new PIDController(Y_TIPPING_PID.kP, Y_TIPPING_PID.kI, Y_TIPPING_PID.kD);

    public DefaultMecDriveCommand(MecDriveSubsystem drive, GamepadEx driverGamepad, boolean isFieldCentric) {
        this.drive = drive;
        this.driverGamepad = driverGamepad;
        this.multiplier = 1.0;
        addRequirements(this.drive);

        this.isFieldCentric = isFieldCentric;
        xTipController.setTolerance(NebulaConstants.Drive.tippingTolerance);
    }

    @Override
    public void execute() {
//        if(driverGamepad.getButton(GamepadKeys.Button.LEFT_BUMPER)) {// ToDO:IS the other command working or is this:
//            multiplier = 0.3;
//        } else {
//            multiplier = 1;
//        }
        double y = NebulaConstants.Gamepad.isDriverOneDeadband(driverGamepad.getLeftY()),
            x = NebulaConstants.Gamepad.isDriverOneDeadband(driverGamepad.getLeftX()),
            rx = NebulaConstants.Gamepad.isDriverOneDeadband(driverGamepad.getRightX());

        y = NebulaConstants.Gamepad.cubeInput(y);
        x = NebulaConstants.Gamepad.cubeInput(x);
        rx = NebulaConstants.Gamepad.cubeInput(rx);

        //TODO:See if this works
        if (drive.getDegreeRoll() > NebulaConstants.Drive.tippingTolerance) {
            x = xTipController.calculate(drive.getDegreePitch(), 0);//Make sure this is the right IMU
        }
        if (drive.getDegreeRoll() < -NebulaConstants.Drive.tippingTolerance) {
            x = -xTipController.calculate(drive.getDegreePitch(), 0);//Make sure this is the right IMU
        }
        if (drive.getDegreePitch() > NebulaConstants.Drive.tippingTolerance) {
            y = yTipController.calculate(drive.getDegreeRoll(), 0);//Make sure this is the right IMU
        }
        if (drive.getDegreePitch() < -NebulaConstants.Drive.tippingTolerance) {
            y = -yTipController.calculate(drive.getDegreeRoll(), 0);//Make sure this is the right IMU
        }
        drive.fieldCentric(
            (y * multiplier),
            (x * multiplier),
            -(rx * multiplier)
        );

    }


    @Override
    public void end(boolean interrupted) {
        drive.stop();
    }
}
