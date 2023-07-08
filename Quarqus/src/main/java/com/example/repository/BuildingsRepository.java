package com.example.repository;

import com.example.entity.Buildings;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.transaction.Transactional;
import java.util.Optional;

@ApplicationScoped
public class BuildingsRepository implements PanacheRepository<Buildings> {

    @Transactional
    public void addOrModify(Buildings obj) {
        Optional<Buildings> old = findByIdOptional(obj.getId());
        if (old.isPresent() && (old.get().getLast_update() >= obj.getLast_update()))
            return;
        persist(obj);
    }

    public void addOrModify(JsonObject obj) {
        addOrModify(new Buildings(obj));
    }

    public void addOrModify(JsonArray jsonArray) {
        for (javax.json.JsonValue value : jsonArray) {
            addOrModify(value.asJsonObject());
        }
    }
}