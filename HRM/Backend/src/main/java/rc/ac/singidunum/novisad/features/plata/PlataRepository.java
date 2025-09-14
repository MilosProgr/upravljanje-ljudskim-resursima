package rc.ac.singidunum.novisad.features.plata;

import org.springframework.data.jpa.repository.JpaRepository;

//import java.time.LocalDate;
//import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public interface PlataRepository extends JpaRepository<Plata, Long>{
	

}
	