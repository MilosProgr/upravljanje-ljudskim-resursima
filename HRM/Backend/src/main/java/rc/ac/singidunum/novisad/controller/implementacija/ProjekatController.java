package rc.ac.singidunum.novisad.controller.implementacija;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import rc.ac.singidunum.novisad.controller.deklaracija.GenericCrudController;
import rc.ac.singidunum.novisad.dto.ProjekatDTO;
import rc.ac.singidunum.novisad.model.Projekat;
import rc.ac.singidunum.novisad.service.deklaracija.CrudService;
import rc.ac.singidunum.novisad.service.implementacija.ProjekatService;

@Controller
@RequestMapping("/api/projekti")
public class ProjekatController extends GenericCrudController<ProjekatDTO, Projekat, Long> {

	@Autowired
	private ProjekatService projekatService;
	
	@Override
	protected CrudService<ProjekatDTO, Projekat, Long> getService() {
		return projekatService;
	}

}
