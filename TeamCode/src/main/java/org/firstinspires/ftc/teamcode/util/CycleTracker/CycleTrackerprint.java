package org.firstinspires.ftc.teamcode.util.CycleTracker;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

// NOT WORK - Dosn't init
public class CycleTrackerprint {//TODO:Remove Static
//    private final Telemetry tl;
    private final File file = new File("FIRST\\data");
    private PrintStream stream;
    Telemetry tl;


    public CycleTrackerprint(Telemetry tl) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            tl.addLine("Create File");
//            return;
        }
        try {
            stream = new PrintStream(file);
        } catch (IOException e) {
            tl.addLine("Print Stream");
            return;
        }
        this.tl = tl;
    }

    public void setData(String string, double num){
        stream.println(string+num);
    }

    public void close() {
        stream.close();
    }
}
