package com.project;

public class RiskCal {

    public static String getRisk(int totalDays, int daysPassed) {

        int progress = (daysPassed * 100) / totalDays;

        if (progress >= 80) {
            return "LOW RISK 🟢 - Great job! Keep it up 💪";
        } 
        else if (progress >= 50) {
            return "MEDIUM RISK 🟡 - You are halfway there, stay focused 🎯";
        } 
        else {
            return "HIGH RISK 🔴 - Don't give up! Start working now 🚀 You can do it!";
        }
    }
}