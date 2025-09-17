package rc.ac.singidunum.novisad.features.projekat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import rc.ac.singidunum.novisad.features.projekat.ProjekatDTO.ProjekatDTORecord;
import rc.ac.singidunum.novisad.generics.controller.GenericCrudController;
import rc.ac.singidunum.novisad.generics.service.CrudService;

@Controller
@RequestMapping("/api/projekti")
public class ProjekatController extends GenericCrudController<ProjekatDTORecord, Projekat, Long> {

	@Autowired
	private ProjekatService projekatService;
	
	@Override
	protected CrudService<ProjekatDTORecord, Projekat, Long> getService() {
		return projekatService;
	}

	@Override
	protected String getBasePath() {
		// TODO Auto-generated method stub
		return "/api/projekti";
	}

	@Override
	protected Long getEntityId(ProjekatDTORecord dto) {
		// TODO Auto-generated method stub
		return dto.id();
	}

}
