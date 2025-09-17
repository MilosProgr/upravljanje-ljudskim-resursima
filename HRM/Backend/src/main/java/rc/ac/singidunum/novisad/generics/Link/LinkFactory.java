package rc.ac.singidunum.novisad.generics.Link;

import java.util.ArrayList;
import java.util.List;

public class LinkFactory {

    public static List<Link.LinkDto> createCrudLinks(String basePath, Object id) {
        List<Link.LinkDto> links = new ArrayList<>();

        links.add(new Link.LinkDto(basePath, "all", "GET"));
        links.add(new Link.LinkDto(basePath + "/paged", "paged", "GET"));
        links.add(new Link.LinkDto(basePath, "create", "POST"));

        if (id != null) {
            links.add(new Link.LinkDto(basePath + "/" + id, "self", "GET"));
            links.add(new Link.LinkDto(basePath + "/" + id, "update", "PUT"));
            links.add(new Link.LinkDto(basePath + "/" + id, "delete", "DELETE"));
        }

        return links;
    }
}
