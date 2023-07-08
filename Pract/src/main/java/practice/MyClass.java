package practice;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Path("/")
public class MyClass {

    @Inject
    BuildingsRepository BuildingsRepository;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/update")
    public void updateFrom() throws FileNotFoundException {
        String filename = Objects.requireNonNull(getClass().getResource("/home/paul/source/practice2023/lpu.json")).getAuthority();
//        BuildingsRepository.addOrModify();
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
