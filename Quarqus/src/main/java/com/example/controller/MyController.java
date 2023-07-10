package com.example.controller;

import com.example.entity.Building;
import com.example.repository.BuildingsRepository;

import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/")
public class MyController {
    @Inject
    BuildingsRepository BuildingsRepository;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/updateJSONS")
    public void updateFromJSONS(JsonArray jsonArray) {
        BuildingsRepository.addOrModify(jsonArray);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/updateJSON")
    public void updateFromJSON(JsonObject jsonObject) {
        BuildingsRepository.addOrModify(jsonObject);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getFrom")
    public List<Building> getFrom(@QueryParam("lat") double lat, @QueryParam("lon") double lon, @QueryParam("dist") double dist) {
        return BuildingsRepository.getAllWith(lat, lon, dist);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAll")
    @Transactional
    public List<Building> getAll() {
        return BuildingsRepository.listAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("/get/{id}")
    public Building getByID(@PathParam("id") long id) {
        return BuildingsRepository.findById(id);
    }

}