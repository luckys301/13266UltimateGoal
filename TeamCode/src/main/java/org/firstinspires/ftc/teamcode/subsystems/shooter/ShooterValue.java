package org .firstinspires.ftc.teamcode.subsystems.shooter;

public class ShooterValue {
    protected enum ShooterEnum {
        OUTTAKE(0.0),
        INTAKE(0.0),
        STOP(0.0),
        MANUAL(0.0) // To use when the subsystem is going Manually - No Need to make PivotValue,
        ;
        public final double value;
        ShooterEnum(double value) {
            this.value = value;
        }
    }
    public ShooterEnum shooterEnum;
    public volatile double shooterRPM;


    protected ShooterValue(ShooterEnum shooterEnum, double shooterRPM) {
        this.shooterEnum = shooterEnum;
        this.shooterRPM = shooterRPM;
    }
    protected static ShooterValue make(ShooterEnum shooterEnum, double shooterRPM) {
        return new ShooterValue(shooterEnum, shooterRPM);
    }

//    protected double getSlideRPM(){
//        return shooterRPM;
//    }
//    protected ShooterEnum getEnum(){
//        return shooterEnum;
//    }
    public final boolean equals(Object other) {
        return this==other;
    }
}