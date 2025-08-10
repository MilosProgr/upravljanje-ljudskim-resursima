package rc.ac.singidunum.novisad.controller.implementacija;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import rc.ac.singidunum.novisad.controller.deklaracija.GenericCrudController;
import rc.ac.singidunum.novisad.dto.KorisnikPravoPristupaDTO;
import rc.ac.singidunum.novisad.model.KorisnikPravoPristupa;
import rc.ac.singidunum.novisad.service.deklaracija.CrudService;
import rc.ac.singidunum.novisad.service.implementacija.KorisnikPravoPristupaService;

@Controller
@RequestMapping("api/pravaPristupa")
public class KorisnikPravoPristupaController extends GenericCrudController<KorisnikPravoPristupaDTO, KorisnikPravoPristupa, Long> {

	@Autowired
	private KorisnikPravoPristupaService kpService;
	
	@Override
	protected CrudService<KorisnikPravoPristupaDTO, KorisnikPravoPristupa, Long> getService() {
		// TODO Auto-generated method stub
		return kpService;
	}

}
