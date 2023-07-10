package com.example.entity;

import javax.json.JsonObject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import static java.lang.Math.*;

@Entity
@Table(name = "Buildings")
public class Building {
    //    static final double dst = 111134.861111;
    public final static double R = 6372795;
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "city_type")
    private String city_type;

    @Column(name = "city_name")
    private String city_name;

    @Column(name = "regeon_name")
    private String regeon_name;

    @Column(name = "full_addr")
    private String full_addr;

    @Column(name = "name")
    private String name;

    @Column(name = "geo_lat")
    private double geo_lat;

    @Column(name = "geo_lon")
    private double geo_lon;

    @Column(name = "med_care_name")
    private String med_care_name;

    @Column(name = "last_update")
    private long last_update;

    public Building() {
    }

    public Building(long id, String city_type, String city_name, String regeon_name, String full_addr, String name, double geo_lat, double geo_lon, String med_care_name, long last_update) {
        setId(id);
        setCity_type(city_type);
        setCity_name(city_name);
        setRegeon_name(regeon_name);
        setFull_addr(full_addr);
        setName(name);
        setGeo_lat(geo_lat);
        setGeo_lon(geo_lon);
        setMed_care_name(med_care_name);
        setLast_update(last_update);
    }

    public Building(JsonObject object) {
        setId(Long.parseLong(object.getJsonString("ID").getString()));
        setCity_type(object.getJsonString("CITYTYPE").getString());
        setCity_name(object.getJsonString("CITYNAME").getString());
        setRegeon_name(object.getJsonString("REGIONLNAME").getString());
        setFull_addr(object.getJsonString("LADDRNAME").getString());
        setName(object.getJsonString("LNAME").getString());
        setGeo_lat(Double.parseDouble(object.getJsonString("GEO_LAT").getString()));
        setGeo_lon(Double.parseDouble(object.getJsonString("GEO_LON").getString()));
        setMed_care_name(object.getJsonString("MEDCARENAME").getString());
        setLast_update(object.getJsonNumber("LAST_UPDATE").longValue());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity_type() {
        return city_type;
    }

    public void setCity_type(String city_type) {
        this.city_type = city_type;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getRegeon_name() {
        return regeon_name;
    }

    public void setRegeon_name(String regeon_name) {
        this.regeon_name = regeon_name;
    }

    public String getFull_addr() {
        return full_addr;
    }

    public void setFull_addr(String full_addr) {
        this.full_addr = full_addr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGeo_lat() {
        return geo_lat;
    }

    public void setGeo_lat(double geo_lat) {
        this.geo_lat = geo_lat;
    }

    public double getGeo_lon() {
        return geo_lon;
    }

    public void setGeo_lon(double geo_lon) {
        this.geo_lon = geo_lon;
    }

    public String getMed_care_name() {
        return med_care_name;
    }

    public void setMed_care_name(String med_care_name) {
        this.med_care_name = med_care_name;
    }

    public long getLast_update() {
        return last_update;
    }

    public void setLast_update(long last_update) {
        this.last_update = last_update;
    }

    static public double getRadian(double grad) {
        return grad * PI / 180;
    }

    static public double getDegrees(double radians) {
        return radians * 180 / PI;
    }

    static public double sqr(double src) {
        return src * src;
    }

    public double distance(double lat, double lon) {
//        double dfi = getRadian(abs(lat - geo_lat));
//        double mlat = getRadian(abs((lat + geo_lat) / 2));
//        double dalpha = cos(mlat) * getRadian(abs(lon - geo_lon));
//        return R * sqrt(dfi * dfi + dalpha * dalpha);
//        return R * 2 * asin(sqrt(sqr(sin((geo_lat - lat) / 2)) + cos(geo_lat) * cos(lat) * sqr(sin(abs(geo_lat - lat) / 2))));
        double fi1 = getRadian(lat);
        double fi2 = getRadian(geo_lat);
        double dfi = fi2 - fi1;
        double alpha1 = getRadian(lon);
        double alpha2 = getRadian(geo_lon);
        double dalpha = alpha2 - alpha1;
        double ch1 = cos(fi2) * sin(dalpha);
        double ch2 = cos(fi1) * sin(fi2) - sin(fi1) * cos(fi2) * cos(dalpha);
        double zn = sin(fi1) * sin(fi2) + cos(fi1) * cos(fi2) * cos(dalpha);
        return R * atan(sqrt(sqr(ch1) + sqr(ch2)) / zn);
    }
}