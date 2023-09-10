package org.firstinspires.ftc.teamcode.util.CycleTracker;

import android.annotation.SuppressLint;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

//No work; Initializes, but no file, etc
public class CycleTrackergpt {
    public CycleTrackergpt(){

    }

    public static void logDateToFile(String fileName, Telemetry telementry) {
        // Create a SimpleDateFormat object to format the date
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Create a File object
        File file = new File(fileName);

        FileWriter writer;
        try{
            // Create a FileWriter object to write to the file
            writer = new FileWriter(file);
        } catch (IOException e){
            telementry.addLine("File Writer");
            return;
        }


        // Get the current date and format it
        String currentDate = dateFormat.format(new Date());
        try{
            // Write the date to the file
            writer.write(currentDate);
        } catch (IOException e){
            telementry.addLine("Writing Current Date");
            e.printStackTrace();
            return;
        }

        try{
            // Close the writer
            writer.close();
        } catch (IOException e){
            telementry.addLine("Writer Close");
            e.printStackTrace();
            return;
        }
        // Print a success message
        System.out.println("Date logged to file successfully!");

    }
}
