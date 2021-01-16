package com.weather.forecast.model;

public class Weather {
    String minTemp;
    String maxTemp;
    String desc;
    String date;
    String safety; // 'Carry umbrella' or 'Use sunscreen lotion'

    public void setMinTemp(String value) {
        minTemp = value;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMaxTemp(String value) {
        Double maxt = Double.parseDouble(value);

        if(maxt > 40 ) {
            safety = "Use sunscreen lotion";
        }
        maxTemp = value;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setDesc(String value) {
        desc = value;
    }
    public String getDesc() {
        if(desc == "rain") {
            safety = "Carry umbrella";
        }

        return desc;
    }

    public void setDate(String value) {
        date = value;
    }

    public String getDate() {
        return date.substring(0,10);
    }

    public String getSafety() {
        return safety;
    }
}
