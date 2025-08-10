package rc.ac.singidunum.novisad.service.implementacija;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import rc.ac.singidunum.novisad.dto.ProjekatDTO;
import rc.ac.singidunum.novisad.mapper.deklaracija.Mapper;
import rc.ac.singidunum.novisad.model.Projekat;
import rc.ac.singidunum.novisad.service.deklaracija.GenericCrudService;

@Service
public class ProjekatService extends GenericCrudService<ProjekatDTO, Projekat, Long> {

	protected ProjekatService(JpaRepository<Projekat, Long> repository, Mapper<ProjekatDTO, Projekat> mapper) {
		super(repository, mapper);
	}

}
