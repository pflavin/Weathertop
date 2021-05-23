package controllers;

import models.Station;
import models.Reading;
import play.Logger;
import play.mvc.Controller;
import java.time.LocalTime;
import java.time.LocalDate;

public class StationCtrl extends Controller
{
  public LocalTime timeNow;
  public static void index(Long id)
  {
    Station station = Station.findById(id);
    Logger.info ("Station id = " + id);
    render("station.html", station);
  }
  
  public static void deletereading(Long id, Long readingid)
  {
    Station station = Station.findById(id);
    Reading reading = Reading.findById(readingid);
    Logger.info ("Removing" + reading.code);
    station.readings.remove(reading);
    station.save();
    reading.delete();
    render("station.html", station);
  }

  public static void addReading(Long id, int code, float temperature, float windSpeed,double windDirection, int pressure, LocalTime rTime, LocalDate rDate )
  {
    Reading reading = new Reading(code, temperature, windSpeed, windDirection,pressure, rTime, rDate);
    Station station = Station.findById(id);
    station.readings.add(reading);
    station.save();
    redirect ("/stations/" + id);
  }

}
