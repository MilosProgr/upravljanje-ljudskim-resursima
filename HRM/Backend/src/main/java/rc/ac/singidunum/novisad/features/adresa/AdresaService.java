package rc.ac.singidunum.novisad.features.adresa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import rc.ac.singidunum.novisad.features.adresa.AdresaDTO.AdresaDTORecord;
import rc.ac.singidunum.novisad.generics.mapper.Mapper;
import rc.ac.singidunum.novisad.generics.service.GenericCrudService;

@Service
public class AdresaService extends GenericCrudService<AdresaDTORecord, Adresa, Long> {

	protected AdresaService(JpaRepository<Adresa, Long> repository, Mapper<AdresaDTORecord, Adresa> mapper) {
		super(repository, mapper);
	}

}
