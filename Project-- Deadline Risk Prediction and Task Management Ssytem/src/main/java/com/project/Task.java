package com.project;

public class Task {

    private int id;
    private String name;
    private int totalDays;
    private int daysPassed;
    private int progress;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getTotalDays() { return totalDays; }
    public void setTotalDays(int totalDays) { this.totalDays = totalDays; }

    public int getDaysPassed() { return daysPassed; }
    public void setDaysPassed(int daysPassed) { this.daysPassed = daysPassed; }

    public int getProgress() { return progress; }
    public void setProgress(int progress) { this.progress = progress; }

    // ✅ RISK METHOD
    public String getRisk() {
        if (progress >= 80) {
            return "LOW RISK - On track";
        } else if (progress >= 50) {
            return "MEDIUM RISK - Stay focused";
        } else {
            return "HIGH RISK - Start working now";
        }
    }
}