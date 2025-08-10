package rc.ac.singidunum.novisad.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import rc.ac.singidunum.novisad.model.Zaduzenje;

@Repository
public interface ZaduzenjeRepository extends JpaRepository<Zaduzenje, Long> {
	List<Zaduzenje> findByZaposleniId(Long id);
}
