package org.firstinspires.ftc.teamcode.subsystems.pivot;

public class PivotValue {
    protected enum PivotEnum {
        RESET(123456),
        TRANSFER(0.0),
        INTAKE_FRONT(0.0),
        INTAKE_BACK(0.0),
        AUTO_INTAKE_FRONT(0.0),
        AUTO_INTAKE_BACK(0.0),
        AUTO_FRONT(0.0),
        AUTO_HIGH_FRONT(0.0),
        AUTO_BACK(0.0),
        AUTO_HIGH_BACK(0.0),
        HIGH_BACK(0.0),
        BACK(0.0),
        HIGH_FRONT(0.0),
        FRONT(0.0),
        DROP_FRONT(0.0),
        DROP_BACK(0.0),
        AUTO_DROP_BACK(0.0), REST(0.0), GROUND(0.0),
        MANUAL(0.0) // To use when the subsystem is going Manually - No Need to make PivotValue,
        ;
        public double value;
        PivotEnum(double value) {
            this.value = value;
        }
    }
    public PivotEnum pivotEnum;
    public volatile double pivotPosition;
    public volatile boolean shouldSensorWork;


    protected PivotValue(PivotEnum pivotEnum, double pivotPosition, boolean shouldSensorWork) {
        this.pivotEnum = pivotEnum;
        this.pivotPosition = pivotPosition;
        this.shouldSensorWork = shouldSensorWork;
    }
    protected static PivotValue make(PivotEnum pivotEnum, double pivotPosition, boolean shouldSensorWork) {
        return new PivotValue(pivotEnum, pivotPosition, shouldSensorWork);
    }

//    protected double getPivotPosition(){
//        return pivotPosition;
//    }
//    protected boolean getShouldSensorWork(){
//        return shouldSensorWork;
//    }
//    protected PivotEnum getEnum(){
//        return pivotEnum;
//    }
    public final boolean equals(Object other) {
        return this==other;
    }
}