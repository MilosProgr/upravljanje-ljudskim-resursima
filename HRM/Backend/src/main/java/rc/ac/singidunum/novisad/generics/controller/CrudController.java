package rc.ac.singidunum.novisad.generics.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import rc.ac.singidunum.novisad.generics.pagination.Page;
import rc.ac.singidunum.novisad.generics.Link.Resource;

import java.util.List;

public interface CrudController<T,E,ID> {

    @GetMapping
    ResponseEntity<Resource<List<T>>> getAll();
    

    @GetMapping("/{id}")
    ResponseEntity<Resource<T>> getById(@PathVariable ID id);

    @PostMapping
    ResponseEntity<Resource<T>> create(@RequestBody E entity);
    

    @PutMapping("/{id}")
    ResponseEntity<Resource<T>> update(@PathVariable ID id, @RequestBody E entity);

    @DeleteMapping("/{id}")
    ResponseEntity<Resource<Void>> delete(@PathVariable ID id);

    @GetMapping("/paged")
    ResponseEntity<Resource<Page<T>>> getAllPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    );

}
