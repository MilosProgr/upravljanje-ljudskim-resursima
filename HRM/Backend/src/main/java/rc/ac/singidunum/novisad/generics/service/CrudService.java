package rc.ac.singidunum.novisad.generics.service;

import java.util.List;

import rc.ac.singidunum.novisad.generics.pagination.Page;


public interface CrudService<T,E,ID> {
	
	List<T> getAll();

    T getById(ID id);

    T save(E entity);

    T update(E entity);
    
    boolean delete(ID id);
    
    Page<T> getAllPaged(int page, int size);
}

