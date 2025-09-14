package rc.ac.singidunum.novisad.features.korisnikHandler;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import rc.ac.singidunum.novisad.features.korisnikHandler.KorisnikPravoPristupaDTO.KorisnikPravoPristupaDTORecord;
import rc.ac.singidunum.novisad.generics.mapper.Mapper;
import rc.ac.singidunum.novisad.generics.service.GenericCrudService;

@Service
public class KorisnikPravoPristupaService extends GenericCrudService<KorisnikPravoPristupaDTORecord, KorisnikPravoPristupa, Long> {

	protected KorisnikPravoPristupaService(JpaRepository<KorisnikPravoPristupa, Long> repository,
			Mapper<KorisnikPravoPristupaDTORecord, KorisnikPravoPristupa> mapper) {
		super(repository, mapper);
	}

}
