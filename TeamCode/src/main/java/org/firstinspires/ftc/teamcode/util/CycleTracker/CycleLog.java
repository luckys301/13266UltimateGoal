package org.firstinspires.ftc.teamcode.util.CycleTracker;

import android.annotation.SuppressLint;
import android.os.Environment;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

///**
// * Created by Gabriel on 2018-01-03.
// * A simple way to log data to a file.
// */

public class CycleLog {
    private static final String BASE_FOLDER_NAME = "LoggedDATA";
    private Writer fileWriter;
    private String line;
    private boolean logTime;
    private String startTime;
//    private Array dataMap;
    private List<String> dataMap;
    private boolean disabled = false;
    private Telemetry tl;
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = new Date();

    public CycleLog(long filename, boolean logTime, Telemetry tl) {
        startTime = dateFormat.format(date);    //Time
        this.logTime = logTime;
        String directoryPath = Environment.getExternalStorageDirectory().getPath()+"/"+BASE_FOLDER_NAME;
        File directory = new File(directoryPath);
        //noinspection ResultOfMethodCallIgnored
        directory.mkdir();
        try {
            fileWriter = new FileWriter(directoryPath+"/"+ startTime+".txt");
        } catch (IOException e) {
            tl.addLine("File Writer");
        }
        this.tl = tl;
    }
    public CycleLog(String filename, boolean logTime, Telemetry tl) {
        startTime = dateFormat.format(date);    //Time
        this.logTime = logTime;
        String directoryPath = Environment.getExternalStorageDirectory().getPath()+"/"+BASE_FOLDER_NAME;
        File directory = new File(directoryPath);
        //noinspection ResultOfMethodCallIgnored
        directory.mkdir();
        try {
            fileWriter = new FileWriter(directoryPath+"/"+filename+".txt");
        } catch (IOException e) {
            tl.addLine("File Writer");
        }
        this.tl = tl;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public void close() {
        try {
            fileWriter.close();
        } catch (IOException e) {
            tl.addLine("Close");
        }
    }

    public void update() {
//        if (disabled) return;//add after fixing
        try {
            if (logTime) {
//                long timeDifference = System.nanoTime()-startTime;
//                line = timeDifference/1E9+","+line;
                for(int i =0; i<=dataMap.size(); i++){
                    line = line + dataMap.get(i) + "\n";
                }
                line = line + "\nTime: trgetertertertetertertertetertert"+ startTime;
            }
            fileWriter.write(line);
            line = "";
        } catch (IOException e) {
            tl.addLine("Update");
        }
    }

//    public void addData(String data) {
////        if (disabled) return;// Put BACK IN TODO
////        if (!line.equals("")) line += ", \n";
//        line = line+ data;
//    }
    public void addData(String string, Object data) {
//        addData(data.toString());
        dataMap.add(string + data);
//            put(string, data);
//        line = line + data + ",\n";
    }
//    public void addData(boolean data) {
////        addData(data);
//        line+=data;
////        addData(data + "");
////        addData(String.valueOf(data));
//    }
//    public void addData(byte data) {
////        addData(data);
//        line+=data;
////        addData(data + "");
////        addData(String.valueOf(data));
//    }
//    public void addData(char data) {
////        addData(data);
//        line+=data;
////        addData(data + "");
////        addData(String.valueOf(data));
//    }
//    public void addData(short data) {
////        addData(data);
//        line+=data;
////        addData(data + "");
////        addData(String.valueOf(data));
//    }
//    public void addData(int data) {
////        addData(data);
//        line+=data;
////        addData(data + "");
////        addData(String.valueOf(data));
//    }
//    public void addData(long data) {
////        addData(data);
//        line+=data;
////        addData(data + "");
////        addData(String.valueOf(data));
//    }
//    public void addData(float data) {
////        addData(data);
//        line+=data;
////        addData(data + "");
////        addData(String.valueOf(data));
//    }
//    public void addData(double data) {
////        addData(data);
//        line+=data;
////        addData(data + "");
////        addData(String.valueOf(data));
//    }
}