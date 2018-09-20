package com.example.device_managment_client;

import java.time.LocalDateTime;



public class DeviceStatus {

    private String temperature;
    private boolean isOpen;
    private String openDuration;
    private int memory;
    private int cpu;
    private LocalDateTime requestDate;

    public DeviceStatus(String temperature, boolean isOpen, String openDuration, int memory, int cpu, LocalDateTime requestDate) {
        this.temperature = temperature;
        this.openDuration = openDuration;
        this.isOpen = isOpen;
        this.memory = memory;
        this.cpu = cpu;
        this.requestDate = requestDate;
    }

    public DeviceStatus(){}

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public String getOpenDuration() {
        return openDuration;
    }

    public void setOpenDuration(String openDuration) {
        this.openDuration = openDuration;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public int getCpu() {
        return cpu;
    }

    public void setCpu(int cpu) {
        this.cpu = cpu;
    }

    public LocalDateTime getrequestDate() {
        return requestDate;
    }

    public void setrequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

}