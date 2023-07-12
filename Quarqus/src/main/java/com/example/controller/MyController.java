package com.example.controller;

import com.example.entity.Building;
import com.example.repository.BuildingsRepository;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Timestamp;
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

    @POST
    @Path("/load")
    public void load(){
        String filename = "/home/paul/source/practice2023/lpu.json";
        InputStream fis = null;
        try {
            fis = new FileInputStream(filename);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        JsonReader reader = Json.createReader(fis);
        JsonArray personObject = reader.readArray();
        BuildingsRepository.addOrModify(personObject);
    }
}