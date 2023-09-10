package org.firstinspires.ftc.teamcode.commands.example;

import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitUntilCommand;

import org.firstinspires.ftc.teamcode.commands.drive.trajectory.DriveForwardCommand;
import org.firstinspires.ftc.teamcode.subsystems.drive.mecDrive.MecDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.sensor.SensorColor;

public class ColorSensorCommand extends SequentialCommandGroup{
    public ColorSensorCommand(MecDriveSubsystem mecDriveSubsystem, SensorColor sensorColor){
        addRequirements(mecDriveSubsystem, sensorColor);    //Add Subsystems that you need to run this Command - not necessary
        addCommands(
                new WaitUntilCommand(sensorColor::grabbedRedCone).withTimeout(14),  //Waits Until Condition is True or Timeout
                new ConditionalCommand(
                        new SequentialCommandGroup(//On True
                                new DriveForwardCommand(mecDriveSubsystem, 5)
                        ),
                        new InstantCommand(),//On False
                        sensorColor::grabbedRedCone//Condition that is tested - // ()-> (sensorColor.grabbedRedCone())
                )
        );
    }
}