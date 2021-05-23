package controllers;

import java.util.List;

import models.Member;
import models.Reading;
import models.Station;
import play.Logger;
import play.mvc.Controller;

public class Dashboard extends Controller
{
  public static void index() 
  {
    Logger.info("Rendering Dashboard");
    Member member = Accounts.getLoggedInMember();
    List<Station> stations = member.stationlist;
    render ("dashboard.html",member, stations);
  }

  public static void deleteStationlist (Long id)
  {
    Member member = Accounts.getLoggedInMember();
    Station station = Station.findById(id);
    member.stationlist.remove(station);
    member.save();
    Logger.info ("Removing" + station.name);
    redirect ("/dashboard");
  }

  public static void addStation (String name, double lat, double lng)
  {
    Member member = Accounts.getLoggedInMember();
    Station station = new Station(name,lat,lng);
    member.stationlist.add(station);
    member.save();
    Logger.info ("Adding a new station called latitide: " + lat +"longitude: "+lng);
    redirect ("/dashboard");
  }

}
