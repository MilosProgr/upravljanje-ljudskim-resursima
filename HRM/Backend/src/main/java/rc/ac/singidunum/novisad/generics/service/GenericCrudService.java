package rc.ac.singidunum.novisad.generics.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.persistence.EntityNotFoundException;
import rc.ac.singidunum.novisad.generics.base.BaseEntity;
import rc.ac.singidunum.novisad.generics.mapper.Mapper;
import rc.ac.singidunum.novisad.generics.pagination.Page;

public abstract class GenericCrudService<DTO, ENTITY extends BaseEntity<ID>, ID>
        implements CrudService<DTO, ENTITY, ID> {

    private final JpaRepository<ENTITY, ID> repository;
    private final Mapper<DTO, ENTITY> mapper;

    protected GenericCrudService(JpaRepository<ENTITY, ID> repository, Mapper<DTO, ENTITY> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Page<DTO> getAllPaged(int page, int size) {
        org.springframework.data.domain.Page<ENTITY> entityPage = repository.findAll(PageRequest.of(page, size));
        List<DTO> dtoList = mapper.map(entityPage.getContent());

        return new Page<>(
                dtoList,
                entityPage.getNumber(),        
                entityPage.getSize(),          
                entityPage.getTotalElements()
                
        );
    }


    @Override
    public List<DTO> getAll() {
        Iterable<ENTITY> entiteti = repository.findAll();
        List<ENTITY> entityList = new ArrayList<>();
        for (ENTITY e : entiteti) {
            entityList.add(e);
        }
        return mapper.map(entityList);
    }

    @Override
    public DTO getById(ID id) {
        ENTITY entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entitet sa ID: " + id + " nije pronađen!"));
        return mapper.map(entity);
    }

    @Override
    public DTO save(ENTITY entity) {
        ENTITY savedEntity = repository.save(entity);
        return mapper.map(savedEntity);
    }

    @Override
    public DTO update(ENTITY entity) {
        if (repository.existsById(entity.getId())) {
            ENTITY updatedEntity = repository.save(entity);
            return mapper.map(updatedEntity);
        }
        return null;
    }

    @Override
    public boolean delete(ID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
