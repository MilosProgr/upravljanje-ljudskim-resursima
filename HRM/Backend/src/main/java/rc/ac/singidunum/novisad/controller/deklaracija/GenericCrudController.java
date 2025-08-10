package rc.ac.singidunum.novisad.controller.deklaracija;

import java.util.List;

//import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import rc.ac.singidunum.novisad.service.deklaracija.CrudService;



public abstract class GenericCrudController<T,E,ID> implements CrudController<T, E, ID> {
	
	protected abstract CrudService<T, E, ID> getService();

    @Override
    public ResponseEntity<List<T>> getAll() {
        List<T> entities = getService().getAll();
        return entities.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(entities);
    }

    @Override
    public ResponseEntity<T> getById(ID id) {
        T entity = getService().getById(id);
        return entity != null ? ResponseEntity.ok(entity) : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<T> create(E entity) {
        T createdEntity = getService().save(entity);
        return createdEntity != null ? ResponseEntity.status(HttpStatus.CREATED).body(createdEntity) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @Override
    public ResponseEntity<T> update(ID id, E entity) {
        T updatedEntity = getService().update(entity);
        return updatedEntity != null ? ResponseEntity.ok(updatedEntity) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @Override
    public ResponseEntity<T> delete(ID id) {
        boolean deleted = getService().delete(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
    
    

}
