package com.example.controller;

import com.example.entity.Building;
import com.example.repository.BuildingsRepository;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
@Tag(name = "Интеграция лечебных учреждений в ДМС для отображения на карте")
public class MyController {
    @Inject
    BuildingsRepository BuildingsRepository;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/updateJSONS")
    @Tag(name = "Обновить БД")
    @APIResponse(name = "Обновить базу данных из списка JSON объектов, которые передаются")
    public void updateFromJSONS(@Parameter(name = "список JSON обектов") JsonArray jsonArray) {
        BuildingsRepository.addOrModify(jsonArray);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/updateJSON")
    @Tag(name = "Обновить БД")
    @APIResponse(name = "Обновить базу данных из JSON объекта, который передаётся")
    public void updateFromJSON(@Parameter(name = "JSON обект") JsonObject jsonObject) {
        BuildingsRepository.addOrModify(jsonObject);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getFrom")
    @Tag(name = "Получить из БД")
    @APIResponse(name = "Получть здания по удалённости от точки")
    public List<Building> getFrom(@QueryParam("lat") @Parameter(name = "lat", description = "Широта в градусах") double lat, @QueryParam("lon") @Parameter(name = "lon", description = "Долгота в градусах") double lon, @QueryParam("dist") @Parameter(name = "distance", description = "Расстояние в метрах") double dist) {
        return BuildingsRepository.getAllWith(lat, lon, dist);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAll")
    @Tag(name = "Получить из БД")
    @APIResponse(name = "Получть все здания")
    @Transactional
    public List<Building> getAll() {
        return BuildingsRepository.listAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("/get/{externalId}")
    @Tag(name = "Получить из БД")
    @APIResponse(name = "Получть здания по externalId")
    public Building getByID(@PathParam("externalId") @Parameter(name = "ID из сервиса") long externalId) {
        return BuildingsRepository.find("external_id", externalId).firstResult();
    }

//    @POST
//    @Path("/load")
//    public void load() {
//        String filename = "/home/paul/source/practice2023/lpu.json";
//        InputStream fis = null;
//        try {
//            fis = new FileInputStream(filename);
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        JsonReader reader = Json.createReader(fis);
//        JsonArray personObject = reader.readArray();
//        BuildingsRepository.addOrModify(personObject);
//    }
}