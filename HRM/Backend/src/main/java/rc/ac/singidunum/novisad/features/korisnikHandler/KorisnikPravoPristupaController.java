package rc.ac.singidunum.novisad.features.korisnikHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import rc.ac.singidunum.novisad.features.korisnikHandler.KorisnikPravoPristupaDTO.KorisnikPravoPristupaDTORecord;
import rc.ac.singidunum.novisad.generics.controller.GenericCrudController;
import rc.ac.singidunum.novisad.generics.service.CrudService;

@Controller
@RequestMapping("api/pravaPristupa")
public class KorisnikPravoPristupaController extends GenericCrudController<KorisnikPravoPristupaDTORecord, KorisnikPravoPristupa, Long> {

	@Autowired
	private KorisnikPravoPristupaService kpService;
	
	@Override
	protected CrudService<KorisnikPravoPristupaDTORecord, KorisnikPravoPristupa, Long> getService() {
		// TODO Auto-generated method stub
		return kpService;
	}

	@Override
	protected String getBasePath() {
		// TODO Auto-generated method stub
		return "/api/pravaPristupa";
	}

	@Override
	protected Long getEntityId(KorisnikPravoPristupaDTORecord dto) {
		// TODO Auto-generated method stub
		return dto.id();
	}

}
