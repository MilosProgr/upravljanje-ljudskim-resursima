package rc.ac.singidunum.novisad.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import rc.ac.singidunum.novisad.model.RadnoVreme;

@Repository
public interface RadnoVremeRepository extends JpaRepository<RadnoVreme, Long> {
    Optional<RadnoVreme> findByZaposleniIdAndDatum(Long zaposleniId, LocalDate datum);

    List<RadnoVreme> findByZaposleniId(Long zaposleniId);

}
