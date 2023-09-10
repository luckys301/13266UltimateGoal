package org.firstinspires.ftc.teamcode.opmode.teleop;

import android.annotation.SuppressLint;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.util.CycleTracker.CycleLog;
import org.firstinspires.ftc.teamcode.util.teleop.MatchOpMode;

import java.text.SimpleDateFormat;
import java.util.Date;

@TeleOp(group = "Test")
public class FileTeleop extends MatchOpMode {
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = new Date();


    //    CycleTrackergpt cycleTrackergpt = new CycleTrackergpt();
//    CycleTrackerprint cycleTrackerprint = new CycleTrackerprint(telemetry);
//    SampleMyBlock sampleMyBlock = new SampleMyBlock(telemetry);
    CycleLog cycleLog = new CycleLog(dateFormat.format(date), true, telemetry);


//    java.util.prefs.AbstractPreferences
    @Override
    public void robotInit() {

    }


    @Override
    public void configureButtons() {
    }

    @Override
    public void matchLoop() {

    }
    @Override
    public void disabledPeriodic() {

//        cycleTrackergpt.
//        cycleTrackerprint.close();

        cycleLog.update();
        cycleLog.close();
    }
    @Override
    public void matchStart() {
//        cycleTrackergpt.logDateToFile("TestOne.txt", telemetry);
//        cycleTrackerprint.setData("tewr", 2);
//        sampleMyBlock.writeToFile(23, "This PC\\Control Hub v1.0\\Internal shared storage\\FIRST\\data");

        cycleLog.addData("Test", "string");
        cycleLog.addData("3", 123);
        cycleLog.addData("double", 0.66);
//        cycleLog.addData("dg");

    }
    @Override
    public void robotPeriodic(){
    }
}
