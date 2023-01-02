package com.punojsoft.solidprinciple.openclosed.end;

//Base class : closed for modification
public abstract class Subscriber {
    protected Long subscriberId;
    protected String address;
    protected Long phoneNumber;
    protected int baseRate;

    //Open For Extension
    protected abstract double calculateBill();
}
