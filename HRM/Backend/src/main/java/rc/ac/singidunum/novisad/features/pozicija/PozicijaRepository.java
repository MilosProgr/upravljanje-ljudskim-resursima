package rc.ac.singidunum.novisad.features.pozicija;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PozicijaRepository extends JpaRepository<Pozicija, Long> {

	Optional<Pozicija> findPozicijaByNaziv(String ime);

}
