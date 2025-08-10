package rc.ac.singidunum.novisad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

//import java.time.LocalDate;
//import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import rc.ac.singidunum.novisad.model.Plata;

@Repository
public interface PlataRepository extends JpaRepository<Plata, Long>{
	

}
	