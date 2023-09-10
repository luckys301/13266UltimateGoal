package org.firstinspires.ftc.teamcode.opmode.auto;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.util.teleop.MatchOpMode;

@Autonomous(group = "RED/BLUE")
public class TestAutonWithoutCam extends MatchOpMode {
    //Write Out Auto Paths, values, subsystems, etc
    @Override
    public void robotInit() {
        //Initialize Subsystems
    }


    public void matchStart() {
        schedule(//Schedule Auto Commands
                new SequentialCommandGroup()
        );
    }


};