package com.example.entity;

import javax.json.JsonObject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

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
    private Timestamp last_update;

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

    protected String getString(JsonObject object, String tag) {
        String res = object.getJsonString(tag).getString();
        if (res == null)
            return "";
        else return res;
    }

    protected Long getLongFromString(JsonObject object, String tag) {
        String parcing = getString(object, tag);
        if (Objects.equals(parcing, ""))
            return 0L;
        else
            return Long.parseLong(parcing);
    }

    protected Double getDoubleFromString(JsonObject object, String tag) {
        String parcing = getString(object, tag);
        if (Objects.equals(parcing, ""))
            return 0.0;
        else
            return Double.parseDouble(parcing);
    }

    protected Long getLongFromNumber(JsonObject object, String tag) {
        return object.getJsonNumber(tag).longValue();
    }

    public Building(JsonObject object) {
//        setId(Long.parseLong(object.getJsonString("ID").getString()));
        setId(getLongFromString(object, "ID"));
//        setCity_type(object.getJsonString("CITYTYPE").getString());
        setCity_type(getString(object, "CITYTYPE"));
//        setCity_name(object.getJsonString("CITYNAME").getString());
        setCity_name(getString(object, "CITYNAME"));
//        setRegeon_name(object.getJsonString("REGIONLNAME").getString());
        setRegeon_name(getString(object, "REGIONLNAME"));
//        setFull_addr(object.getJsonString("LADDRNAME").getString());
        setFull_addr(getString(object, "LADDRNAME"));
//        setName(object.getJsonString("LNAME").getString());
        setName(getString(object, "LNAME"));
//        setGeo_lat(Double.parseDouble(object.getJsonString("GEO_LAT").getString()));
        setGeo_lat(getDoubleFromString(object, "GEO_LAT"));
//        setGeo_lon(Double.parseDouble(object.getJsonString("GEO_LON").getString()));
        setGeo_lon(getDoubleFromString(object, "GEO_LON"));
//        setMed_care_name(object.getJsonString("MEDCARENAME").getString());
        setMed_care_name(getString(object, "MEDCARENAME"));
//        setLast_update(object.getJsonNumber("LAST_UPDATE").longValue());
        setLast_update(getLongFromNumber(object, "LAST_UPDATE"));
    }

    static public double getRadian(double grad) {
        return grad * PI / 180;
    }

    static public double sqr(double src) {
        return src * src;
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

    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(long last_update) {
        this.last_update = new Timestamp(last_update * 1000);
    }

    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    }

    public double distance(double lat, double lon) {
        double fi1 = getRadian(lat);
        double sfi1 = sin(fi1);
        double cfi1 = cos(fi1);
        double fi2 = getRadian(geo_lat);
        double sfi2 = sin(fi2);
        double cfi2 = cos(fi2);
        double dfi = fi2 - fi1;
        double alpha1 = getRadian(lon);
        double alpha2 = getRadian(geo_lon);
        double dalpha = alpha2 - alpha1;
        double cdalpha = cos(dalpha);
        double sdalpha = sin(dalpha);
//        return R * acos(sfi1 * sfi2 + cfi1 * cfi2 * cdalpha); //Сферическая теорема косинусов
//        return R * 2 * asin(sqrt(sqr(sin(dfi / 2)) + cfi1 * cfi2 * sqr(sin(dalpha / 2)))); //Формула гаверсинусов
        double ch1 = cfi2 * sdalpha;
        double ch2 = cfi1 * sfi2 - sfi1 * cfi2 * cdalpha;
        double zn = sfi1 * sfi2 + cfi1 * cfi2 * cdalpha;
        return R * atan(sqrt(sqr(ch1) + sqr(ch2)) / zn); //Формула гаверсинусов модификация для антиподов
    }

    //    protected void add(Map<String, Object> map, String name, Object oldObj, Object newObj) {
    protected String add(String name, Object oldObj, Object newObj) {
        if (!Objects.equals(oldObj, newObj))
            return ", " + name + " = '" + newObj + "'";
        return "";
    }

    public String getChangeQuery(Building newObj) {
        String query = "";
        query += add("city_type", getCity_type(), newObj.getCity_type());
        query += add("city_name", getCity_name(), newObj.getCity_name());
        query += add("regeon_name", getRegeon_name(), newObj.getRegeon_name());
        query += add("full_addr", getFull_addr(), newObj.getFull_addr());
        query += add("name", getName(), newObj.getName());
        query += add("geo_lat", getGeo_lat(), newObj.getGeo_lat());
        query += add("geo_lon", getGeo_lon(), newObj.getGeo_lon());
        query += add("med_care_name", getMed_care_name(), newObj.getMed_care_name());
        query += add("last_update", getLast_update(), newObj.getLast_update());
        return query.substring(2);
    }
}