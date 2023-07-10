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

    @Transactional
    public void addOrModify(Building obj) {
        Optional<Building> old = findByIdOptional(obj.getId());
        if (old.isPresent() && (old.get().getLast_update() >= obj.getLast_update()))
            return;
        persist(obj);
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

    static public double getDegrees(double radians) {
        return radians * 180 / PI;
    }

    public List<Building> getAllWith(double lat, double lon, double dist) {
        double dlat = getDegrees(dist / Building.R);
        double lat1 = lat - dlat;
        double lat2 = lat + dlat;
        double dlon = dlat / cos(Building.getRadian(max(abs(lat1), abs(lat2))));
        double lon1 = lon - dlon;
        double lon2 = lon + dlon;
        List<Building> buildings = list("geo_lat >= ?1 and geo_lat <= ?2 and geo_lon >= ?3 and geo_lon <= ?4", lat1, lat2, lon1, lon2);
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