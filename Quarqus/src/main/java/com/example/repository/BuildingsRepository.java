package com.example.repository;

import com.example.entity.Buildings;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BuildingsRepository implements PanacheRepository<Buildings> {

}