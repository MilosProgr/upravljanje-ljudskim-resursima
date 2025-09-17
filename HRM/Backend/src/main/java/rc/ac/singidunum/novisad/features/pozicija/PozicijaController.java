package rc.ac.singidunum.novisad.features.pozicija;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import rc.ac.singidunum.novisad.features.pozicija.PozicijaDTO.PozicijaDTORecord;
import rc.ac.singidunum.novisad.generics.controller.GenericCrudController;
import rc.ac.singidunum.novisad.generics.service.CrudService;

@Controller
@RequestMapping("/api/pozicije")
public class PozicijaController extends GenericCrudController<PozicijaDTORecord, Pozicija, Long> {

	@Autowired
	private PozicijaService pozicijaService;
	
	@Override
	protected CrudService<PozicijaDTORecord, Pozicija, Long> getService() {
		return pozicijaService;
	}

	@Override
	protected String getBasePath() {
		// TODO Auto-generated method stub
		return "/api/pozicije";
	}

	@Override
	protected Long getEntityId(PozicijaDTORecord dto) {
		// TODO Auto-generated method stub
		return dto.id();
	}

}
