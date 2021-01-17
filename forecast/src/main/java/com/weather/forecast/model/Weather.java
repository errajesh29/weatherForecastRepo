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
        Double maxtemp = Double.parseDouble(value); // in forenhite
        Double degree = (maxtemp - 32)  * 5/9; // in degree

        if(degree > 40 && safety == null) { // check degree more than 40 , if not already rain set
            safety = "Use sunscreen lotion";
        }
        maxTemp = value;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setDesc(String value) {
        if(value.toLowerCase().equals("rain")) {
            safety = "Carry umbrella";
        }
        desc = value;
    }
    public String getDesc() {
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
