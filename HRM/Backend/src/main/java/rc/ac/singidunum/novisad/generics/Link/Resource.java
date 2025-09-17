package rc.ac.singidunum.novisad.generics.Link;

import java.util.List;

public class Resource<T> {
    private final T data;
    private final List<Link.LinkDto> links;

    public Resource(T data, List<Link.LinkDto> links) {
        this.data = data;
        this.links = links;
    }

    public T getData() {
        return data;
    }

    public List<Link.LinkDto> getLinks() {
        return links;
    }
}