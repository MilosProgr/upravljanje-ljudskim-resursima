package rc.ac.singidunum.novisad.service.implementacija;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import rc.ac.singidunum.novisad.dto.OdsekDTO;
import rc.ac.singidunum.novisad.mapper.deklaracija.Mapper;
import rc.ac.singidunum.novisad.model.Odsek;
import rc.ac.singidunum.novisad.service.deklaracija.GenericCrudService;

@Service
public class OdsekService extends GenericCrudService<OdsekDTO, Odsek,Long> {

	protected OdsekService(JpaRepository<Odsek, Long> repository, Mapper<OdsekDTO, Odsek> mapper) {
		super(repository, mapper);
	}

}
