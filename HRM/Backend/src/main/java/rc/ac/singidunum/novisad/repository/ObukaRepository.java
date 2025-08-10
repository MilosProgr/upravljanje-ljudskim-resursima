package rc.ac.singidunum.novisad.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import rc.ac.singidunum.novisad.model.Obuka;

@Repository
public interface ObukaRepository extends JpaRepository<Obuka, Long> {
	List<Obuka> findByZaposleniId(Long zaposleniId);
}
