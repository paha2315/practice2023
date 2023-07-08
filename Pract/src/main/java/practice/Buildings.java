package practice;

import io.vertx.core.json.JsonObject;

import javax.persistence.*;

@Entity
@Table(name = "Buildings")
public class Buildings {
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

    public Buildings() {
    }

    public Buildings(long id, String city_type, String city_name, String regeon_name, String full_addr, String name, double geo_lat, double geo_lon, String med_care_name, long last_update) {
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

    public Buildings(JsonObject object) {
        setId(object.getInteger("ID"));
        setCity_type(object.getString("CITYTYPE"));
        setCity_name(object.getString("CITYNAME"));
        setRegeon_name(object.getString("REGIONLNAME"));
        setFull_addr(object.getString("LADDRNAME"));
        setName(object.getString("LNAME"));
        setGeo_lat(object.getDouble("GEO_LAT"));
        setGeo_lon(object.getDouble("GEO_LON"));
        setMed_care_name(object.getString("MEDCARENAME"));
        setLast_update(object.getLong("LAST_UPDATE"));
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
}