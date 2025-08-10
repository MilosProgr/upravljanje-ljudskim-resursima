package rc.ac.singidunum.novisad.service.implementacija;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import rc.ac.singidunum.novisad.dto.KorisnikPravoPristupaDTO;
import rc.ac.singidunum.novisad.mapper.deklaracija.Mapper;
import rc.ac.singidunum.novisad.model.KorisnikPravoPristupa;
import rc.ac.singidunum.novisad.service.deklaracija.GenericCrudService;

@Service
public class KorisnikPravoPristupaService extends GenericCrudService<KorisnikPravoPristupaDTO, KorisnikPravoPristupa, Long> {

	protected KorisnikPravoPristupaService(JpaRepository<KorisnikPravoPristupa, Long> repository,
			Mapper<KorisnikPravoPristupaDTO, KorisnikPravoPristupa> mapper) {
		super(repository, mapper);
	}

}
