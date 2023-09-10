package org.firstinspires.ftc.teamcode.subsystems.sensor;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class DigitalChannelSensor extends SubsystemBase implements HardwareDevice {
    //Uses the same Class as Touch Sensor(Has Own Class),Magnetic Limit Switch, and any other Digital Sensor
    private Telemetry telemetry;
    private DigitalChannel digitalChannel;
    public DigitalChannelSensor(HardwareMap hardwareMap , Telemetry tl) {
        this.telemetry = tl;
        this.digitalChannel = hardwareMap.get(DigitalChannel.class, "digitalTouch");
    }

    public void periodic() {
        telemetry.update();
    }

    public boolean isPressed(){
        return digitalChannel.getState();
        //true if the touch sensor is being pressed
        //false if not being pressed
    }

    @Override
    public void disable() {digitalChannel.close();}

    @Override
    public String getDeviceType() {return "Color Sensor";}

}