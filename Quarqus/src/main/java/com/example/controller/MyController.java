package com.example.controller;

import com.example.entity.Buildings;
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
import java.sql.SQLException;
import java.util.List;

@Path("/")
public class MyController {
    @Inject
    BuildingsRepository BuildingsRepository;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/update")
    public void updateFrom() throws FileNotFoundException {
        String filename = "/home/paul/source/practice2023/lpu.json";
        InputStream fis = new FileInputStream(filename);
        JsonReader reader = Json.createReader(fis);
        JsonArray personObject = reader.readArray();
        BuildingsRepository.addOrModify(personObject);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getFrom")
    public List<Buildings> getFrom(@QueryParam("value") int value) {
        return BuildingsRepository.listAll();
    }

//    @GET
//    @Produces(MediaType.TEXT_PLAIN)
//    public String hello() {
//        logger.info("Hello");
//        return "hello";
//    }

    @POST
    @Path("/add/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public String add(Buildings obj) throws SQLException {
        BuildingsRepository.addOrModify(obj);
        return "Added " + obj.getName();
    }

    @POST
    @Path("/addJson/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public void addJSON(JsonObject obj) throws SQLException {
        BuildingsRepository.addOrModify(obj);

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAll")
    @Transactional
    public List<Buildings> getAll() {
        return BuildingsRepository.findAll().list();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("/get/{id}")
    public Buildings getByID(@PathParam("id") long id) {
        return BuildingsRepository.findById(id);
    }

}