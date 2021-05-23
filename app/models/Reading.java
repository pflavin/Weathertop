package models;

import javax.persistence.Entity;

import play.db.jpa.Model;
import java.lang.Math;

import java.time.LocalTime;
import java.time.LocalDate;

@Entity
public class Reading extends Model {
  public int code;
  public double temperature;
  public double windSpeed;
  public double windDirection;
  public int pressure;
  public String codeDesc;
  public double tempF;
  public double windChill;
  public int bftSpd=15;
  public String winDir;
  public LocalDate rDate;
  public LocalTime rTime;


  public Reading(int code, float temperature, float windSpeed, double windDirection, int pressure,LocalTime rtime, LocalDate rDate) {
    this.code = code;
    this.temperature = temperature;
    this.windSpeed = windSpeed;
    this.windDirection = windDirection;
    this.pressure = pressure;
    this.rTime=rTime.now();
    this.rDate=rDate.now();
  }

  public String getCodeDesc() {
    codeDesc = "";
    switch (code) {
      case 100:
        codeDesc = "Clear";
        break;
      case 200:
        codeDesc = "Partial Clouds";
        break;
      case 300:
        codeDesc = "Cloudy";
        break;
      case 400:
        codeDesc = "Light Showers";
        break;
      case 500:
        codeDesc = "Heavy Showers";
        break;
      case 600:
        codeDesc = "Rain";
        break;
      case 700:
        codeDesc = "Snow";
        break;
      case 800:
        codeDesc = "Thunder";
        break;
      default:
        codeDesc = "Invalid Code";
        break;
    }
    return codeDesc;
  }

  public double getTempF() {
    tempF = (temperature * (9.0 / 5.0)) + 32;
    return tempF;
  }

  public double getWindChill() {
    windChill = 13.12 + (0.6215 * temperature) + (11.37 * (Math.pow(windSpeed, 0.16))) + (0.3965 * temperature * (Math.pow(windSpeed, 0.16)));
    return windChill;
  }

  public double getBftSpd() {
    double[] bftMin = {0, 1, 6, 12, 20, 29, 39, 50, 62, 75, 89, 103};
    double[] bftMax = {1, 5, 11, 19, 28, 38, 49, 61, 74, 88, 102, 117};
    for(int i=0;i<11;i++) {
      if ((windSpeed>bftMin[i])&&(windSpeed<bftMax[i])) {
        bftSpd=i;
      }
    }
    return bftSpd;
  }

  public String getWinDir() {
    String[] winDirArr = {"N","NNE","NE","ENE","E","ESE","SE","SSE","S","SSW","SW","WSW","W","WNW","NW","WNW"};
    double[] winAngle = {11.25, 33.75, 56.25, 78.75, 101.25, 123.75, 146.25, 168.75, 191.25, 213.75, 236.25, 258.75,281.25,303.75,326.25,348.75};
    for(int i=0;i<16;i++) {
      if ((windDirection>winAngle[i])&&(windDirection<winAngle[i+1])) {
        winDir=winDirArr[i+1];
      }
    }
    return winDir;
  }


}
