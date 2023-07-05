package com.example.controller;

import com.example.entity.Buildings;
import com.example.repository.BuildingsRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

import org.jboss.logging.Logger;

@Path("/")
public class MyController {
    @Inject
    BuildingsRepository BuildingsRepository;
    private static Logger logger;

    static {
        logger = Logger.getLogger(MyController.class);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/update")
    public void updateFrom() {

        return;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getFrom")
    public List<Buildings> g(@QueryParam("value") int value) {
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
        BuildingsRepository.persist(obj);
        return "Added " + obj.getName();
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