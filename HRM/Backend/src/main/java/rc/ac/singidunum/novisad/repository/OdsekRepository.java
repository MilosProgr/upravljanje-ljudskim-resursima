package rc.ac.singidunum.novisad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import rc.ac.singidunum.novisad.model.Odsek;

@Repository
public interface OdsekRepository extends JpaRepository<Odsek, Long>{

}
