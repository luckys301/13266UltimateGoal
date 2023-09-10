package org.firstinspires.ftc.teamcode.commands.example;

import com.arcrobotics.ftclib.command.ParallelCommandGroup;

import org.firstinspires.ftc.teamcode.subsystems.drive.mecDrive.MecDriveSubsystem;

public class ParallelExCommand extends ParallelCommandGroup{
    public ParallelExCommand(MecDriveSubsystem mecDriveSubsystem){
        addRequirements(mecDriveSubsystem);    //Add Subsystems that you need to run this Command
        addCommands(
            //Commands that will run automatically - Each subsystem can only be used once
            //Can also be implemented like below and/or by extending ParallelCommandGroup
            new ParallelCommandGroup(
                    //Commands
            )
        );
    }
}