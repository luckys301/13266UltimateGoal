package org.firstinspires.ftc.teamcode.opmode.auto;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.commands.drive.trajectory.DriveForwardCommand;
import org.firstinspires.ftc.teamcode.subsystems.drive.mecDrive.MecDrive;
import org.firstinspires.ftc.teamcode.subsystems.drive.mecDrive.MecDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.pipelines.aprilTagPipeline.TagVision;
import org.firstinspires.ftc.teamcode.util.teleop.MatchOpMode;

@Autonomous
//@Disabled
public class AprilTagAutoImplementation extends MatchOpMode {
    private int tagNum = 0;

    private MecDriveSubsystem mecDriveSubsystem;
    private TagVision tagVision;


    @Override
    public void robotInit() {
        mecDriveSubsystem = new MecDriveSubsystem(new MecDrive(hardwareMap, telemetry, true), telemetry, hardwareMap);
        mecDriveSubsystem.init();
        tagVision = new TagVision(hardwareMap,  telemetry);
        while (!isStarted() && !isStopRequested())
        {
            tagVision.updateTagOfInterest();
            tagVision.tagToTelemetry();
            telemetry.update();
        }
        this.matchStart();
    }

    public void matchStart() {tagNum = tagVision.getTag();
        switch (tagNum) {
            case 1: { //Left
                schedule(
                        new SequentialCommandGroup(new DriveForwardCommand(mecDriveSubsystem, 3))
                );
                return;
            }
            case 2: { //Mid
                schedule(
                        new SequentialCommandGroup(new DriveForwardCommand(mecDriveSubsystem, 7))
                );
                return;
            }
            case 3:
            default: { //Right
                schedule(
                        new SequentialCommandGroup()
                );
                return;
            }
        }
    }
}