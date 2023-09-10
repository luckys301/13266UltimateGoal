// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.ProfiledPIDCommand;
import com.arcrobotics.ftclib.controller.wpilibcontroller.ProfiledPIDController;
import com.arcrobotics.ftclib.trajectory.TrapezoidProfile;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.drive.mecDrive.MecDriveSubsystem;

public class TurnToTeleop extends ProfiledPIDCommand {
  /** Creates a new TurnToTeleop. */
  // private static DriverStation driverStation;
  private final MecDriveSubsystem drive;
  private final Telemetry tl;
  // private Timer timer;
  // private WriteOnlyBoolean atSetpointWriter = new WriteOnlyBoolean(false, "PID Auto balance at positionn", Drive.class.getSimpleName());
  public TurnToTeleop(MecDriveSubsystem drive, double goal, Telemetry tl) {//TODO:Test
    super(
        new ProfiledPIDController(
            0.001,
            0,
            0.0000,
            new TrapezoidProfile.Constraints(0.01, 0.01)),
        drive::getHeading,
        goal,
        (output, setpoint) -> {
            // Use the output (and setpoint, if desired) here
            drive.mecDrive(0, 0, output);
          });

    addRequirements(drive);
    this.drive = drive;
    getController().setTolerance(1); //degrees
    this.tl = tl;
  }

  @Override
  public void execute() {
    super.execute();
    tl.addData("Drive Heading", drive.getHeading());
    tl.addData("contorller", getController().getPositionError());
  }
  @Override
  public void initialize() {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}