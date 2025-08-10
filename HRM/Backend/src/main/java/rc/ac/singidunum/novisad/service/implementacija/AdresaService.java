package rc.ac.singidunum.novisad.service.implementacija;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import rc.ac.singidunum.novisad.dto.AdresaDTO;
import rc.ac.singidunum.novisad.mapper.deklaracija.Mapper;
import rc.ac.singidunum.novisad.model.Adresa;
import rc.ac.singidunum.novisad.service.deklaracija.GenericCrudService;

@Service
public class AdresaService extends GenericCrudService<AdresaDTO, Adresa, Long> {

	protected AdresaService(JpaRepository<Adresa, Long> repository, Mapper<AdresaDTO, Adresa> mapper) {
		super(repository, mapper);
	}

}
