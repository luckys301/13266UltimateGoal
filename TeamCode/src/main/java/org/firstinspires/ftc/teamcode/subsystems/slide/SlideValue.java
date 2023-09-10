package org.firstinspires.ftc.teamcode.subsystems.slide;

public class SlideValue {
    public enum SlideEnum {
        REST(0.0),
        GROUND(0.0),
        LOW(0.0),
        MID(0.0),
        HIGH(0.0),
        AUTO_MID(0.0),
        AUTO_HIGH(0.0),
        FIVE(0.0),
        FOUR(0.0),
        THREE(0.0),
        TWO(0.0),
        ONE(0.0),
        MANUAL(0.0);
        public double value;
        SlideEnum(double value) {
            this.value = value;
        }
    }
    public SlideEnum slideEnum;
    public volatile double slidePosition;
    public volatile boolean shouldSlideDrop;

    protected SlideValue(SlideEnum slideEnum, double slidePosition, boolean shouldSlideDrop) {
        this.slideEnum = slideEnum;
        this.slidePosition = slidePosition;
        this.shouldSlideDrop = shouldSlideDrop;
    }
    protected static SlideValue make(SlideValue.SlideEnum slideEnum, double pivotPosition, boolean shouldSlideDrop) {
        return new SlideValue(slideEnum, pivotPosition, shouldSlideDrop);
    }

//    protected double getSlidePosition(){
//        return slidePosition;
//    }
//    protected boolean getShouldSlideDrop(){
//        return shouldSlideDrop;
//    }
//    protected SlideEnum getEnum(){
//        return slideEnum;
//    }
    public final boolean equals(Object other) {
        return this==other;
    }

}
