package org.firstinspires.ftc.teamcode.util.container;

public class Value<T> {
    public volatile T value;
    public Value(T value){
        this.value =value;
    }
    public T get(){
        return value;
    }
    public void set(T newValue){
        this.value = newValue;
    }
}
