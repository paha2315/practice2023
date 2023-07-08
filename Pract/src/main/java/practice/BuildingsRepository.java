package practice;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class BuildingsRepository implements PanacheRepository<Buildings> {

    public void addOrModify(Buildings obj) {
        Optional<Buildings> old = findByIdOptional(obj.getId());
        if (old.isEmpty() || (old.get().getLast_update() >= obj.getLast_update()))
            return;
        persist(obj);
    }

//    public void addOrModify(JsonObject object) {
//        addOrModify(new Buildings(object));
//    }


//    public void addOrModify(JsonArray array) {
//        for (var object : array.asList()) {
//            addOrModify(object.getAsJsonObject());
//        }
//    }

//    public void addOrModify(JsonElement element) {
//        if (element.isJsonArray())
//            addOrModify(element.getAsJsonArray());
//        if (element.isJsonObject())
//            addOrModify(element.getAsJsonObject());
//    }

}