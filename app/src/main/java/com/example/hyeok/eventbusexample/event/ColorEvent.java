package com.example.hyeok.eventbusexample.event;

public class ColorEvent {

    private Integer colorCode;
    private Integer amount;

    public ColorEvent(Integer colorCode, Integer amount){
        this.colorCode = colorCode;
        this.amount = amount;
    }

    public void setColorAmount(Integer colorCode, Integer amount){
        this.colorCode = colorCode;
        this.amount = amount;
    }

    public Integer getColorCode(){
        return this.colorCode;
    }

    public Integer getAmount(){
        return this.amount;
    }

}
