package org.firstinspires.ftc.teamcode.commands.example;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystems.drive.mecDrive.MecDriveSubsystem;

public class SelectCommand extends SequentialCommandGroup{
    public SelectCommand(MecDriveSubsystem mecDriveSubsystem){
        addRequirements(mecDriveSubsystem);    //Add Subsystems that you need to run this Command - not necessary
        addCommands(
//            SelectCommand wobbleCommand = new SelectCommand(
//                    // the first parameter is a map of commands
//                    new HashMap<Object, Command>() {{
//                        put(Height.ZERO, new DriveForwardCommand(mecDriveSubsystem,3));
//                        put(Height.ONE, new DriveForwardCommand(mecDriveSubsystem,3));
//                        put(Height.FOUR, new DriveForwardCommand(mecDriveSubsystem,3));
//                    }},
//                    // the selector
//                    this::height
//            )
        );
    }
}