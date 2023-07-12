package com.example.repository;

import com.example.entity.Building;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.transaction.Transactional;
import java.util.*;

import static java.lang.Math.*;

@ApplicationScoped
public class BuildingsRepository implements PanacheRepository<Building> {

    static public double getDegrees(double radians) {
        return radians * 180 / PI;
    }

    @Transactional
    public void addOrModify(Building obj) {
        Optional<Building> old = findByIdOptional(obj.getId());
        if (old.isEmpty()) persist(obj);
        else if (old.get().getLast_update().before(obj.getLast_update()))
            update(old.get().getChangeQuery(obj) + " where id = " + obj.getId());

    }

    public void addOrModify(JsonObject obj) {
        addOrModify(new Building(obj));
    }

    public void addOrModify(JsonArray jsonArray) {
        for (javax.json.JsonValue value : jsonArray) {
            addOrModify(value.asJsonObject());
        }
        System.out.println("Out");
    }

    public List<Building> getAllWith(double lat, double lon, double dist) {
        double dlat = getDegrees(dist / Building.R);
        List<Building> buildings;
        if ((90 - abs(lat)) < dlat) {
            if (lat > 0) {
                double latq = lat - dlat;
                buildings = list("geo_lat >= ?1", latq);
            } else {
                double latq = lat + dlat;
                buildings = list("geo_lat <= ?1", latq);
            }
        } else {
            double lat1 = lat - dlat;
            double lat2 = lat + dlat;
            double dlon = dlat / cos(Building.getRadian(max(abs(lat1), abs(lat2))));
            double lon1 = lon - dlon;
            double lon2 = lon + dlon;
            buildings = list("geo_lat >= ?1 and geo_lat <= ?2 and geo_lon >= ?3 and geo_lon <= ?4", lat1, lat2, lon1, lon2);
        }
//        List<Building> buildings = listAll();
        List<Building> result = new ArrayList<>();
        Set<Map.Entry<String, String>> set = new HashSet<>();
        for (var building : buildings) {
            double dst = building.distance(lat, lon);
            Map.Entry<String, String> cur = new AbstractMap.SimpleEntry<>(building.getFull_addr(), building.getMed_care_name());
            if (dst < dist && !set.contains(cur)) {
                result.add(building);
                set.add(cur);
            }
        }
        return result;
    }
}