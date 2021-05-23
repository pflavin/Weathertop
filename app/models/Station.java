package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Station extends Model
{
  public String name;
  public int lastReading;
  public double lat;
  public double lng;
  public double tempMax;
  public double tempMin;
  public double prsMax;
  public double prsMin;
  @OneToMany(cascade = CascadeType.ALL)
  public List<Reading> readings = new ArrayList<Reading>();


  public Station(String name,double lat,double lng)
  {
    this.name = name;
    this.lat=lat;
    this.lat=lng;
  }

  public int getLastReading(Station station) {
    if (station.readings.size()>0) {
      lastReading= station.readings.size()-1;
    }
    return lastReading;
  }

  public double getTempMax(Station station) {
    tempMax=station.readings.get(0).temperature;;
    int i=0;
    while (i<station.readings.size()) {
     if (tempMax<station.readings.get(i).temperature) {
       tempMax=station.readings.get(i).temperature;
    }
      i++;
    }
    return tempMax;
  }

  public double getTempMin(Station station) {
    tempMin=station.readings.get(0).temperature;;
    int i=0;
    while (i<station.readings.size()) {
      if (tempMin>station.readings.get(i).temperature) {
        tempMin=station.readings.get(i).temperature;
      }
      i++;
    }
    return tempMin;
  }

  public double getPrsMax(Station station) {
    prsMax=station.readings.get(0).pressure;;
    int i=0;
    while (i<station.readings.size()) {
      if (prsMax<station.readings.get(i).pressure) {
        prsMax=station.readings.get(i).pressure;
      }
      i++;
    }
    return prsMax;
  }

  public double getPrsMin(Station station) {
    prsMin=station.readings.get(0).pressure;;
    int i=0;
    while (i<station.readings.size()) {
      if (prsMin>station.readings.get(i).pressure) {
        prsMin=station.readings.get(i).pressure;
      }
      i++;
    }
    return prsMin;
  }


}