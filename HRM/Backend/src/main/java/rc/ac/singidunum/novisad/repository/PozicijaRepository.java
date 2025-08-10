package rc.ac.singidunum.novisad.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import rc.ac.singidunum.novisad.model.Pozicija;

@Repository
public interface PozicijaRepository extends JpaRepository<Pozicija, Long> {

	Optional<Pozicija> findPozicijaByNaziv(String ime);

}
