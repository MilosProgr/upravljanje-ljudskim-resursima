package rc.ac.singidunum.novisad.generics.controller;

import java.util.List;

//import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import rc.ac.singidunum.novisad.generics.Link.LinkFactory;
import rc.ac.singidunum.novisad.generics.Link.Resource;
import rc.ac.singidunum.novisad.generics.pagination.Page;
import rc.ac.singidunum.novisad.generics.service.CrudService;

public abstract class GenericCrudController<T,E,ID> implements CrudController<T, E, ID> {
	
	protected abstract CrudService<T, E, ID> getService();
    protected abstract String getBasePath(); // npr. "/api/adrese"

    @Override
    public ResponseEntity<Resource<List<T>>> getAll() {
        List<T> entities = getService().getAll();
        if (entities.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new Resource<>(entities, LinkFactory.createCrudLinks(getBasePath(), null)));
    }

    @Override
    public ResponseEntity<Resource<T>> getById(ID id) {
        T entity = getService().getById(id);
        if (entity == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new Resource<>(entity, LinkFactory.createCrudLinks(getBasePath(), id)));
    }
    
   
    @Override
    public ResponseEntity<Resource<T>> create(E entity) {
        T createdEntity = getService().save(entity);
        if (createdEntity == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new Resource<>(createdEntity, LinkFactory.createCrudLinks(getBasePath(), getEntityId(createdEntity))));
    }

    @Override
    public ResponseEntity<Resource<T>> update(ID id, E entity) {
        T updatedEntity = getService().update(entity);
        if (updatedEntity == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.ok(new Resource<>(updatedEntity, LinkFactory.createCrudLinks(getBasePath(), id)));
    }

    @Override
    public ResponseEntity<Resource<Void>> delete(ID id) {
        boolean deleted = getService().delete(id);
        if (!deleted) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new Resource<>(null, LinkFactory.createCrudLinks(getBasePath(), null)));
    }

    @Override
    public ResponseEntity<Resource<Page<T>>> getAllPaged(int page, int size) {
        Page<T> result = getService().getAllPaged(page, size);
        if (result.getContent().isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new Resource<>(result, LinkFactory.createCrudLinks(getBasePath(), null)));
    }

    // Ovo moraš implementirati u konkretnom kontroleru ili servisu da izvučeš ID iz DTO-a
    protected abstract ID getEntityId(T dto);
}