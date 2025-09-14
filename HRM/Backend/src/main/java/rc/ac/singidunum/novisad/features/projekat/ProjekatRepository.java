package rc.ac.singidunum.novisad.features.projekat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjekatRepository extends JpaRepository<Projekat, Long> {

}
