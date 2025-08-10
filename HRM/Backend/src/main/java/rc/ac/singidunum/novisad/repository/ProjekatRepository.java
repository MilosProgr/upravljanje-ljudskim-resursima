package rc.ac.singidunum.novisad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import rc.ac.singidunum.novisad.model.Projekat;

@Repository
public interface ProjekatRepository extends JpaRepository<Projekat, Long> {

}
