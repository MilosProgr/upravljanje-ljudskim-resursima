package rc.ac.singidunum.novisad.controller.implementacija;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import rc.ac.singidunum.novisad.controller.deklaracija.GenericCrudController;
import rc.ac.singidunum.novisad.dto.PozicijaDTO;
import rc.ac.singidunum.novisad.model.Pozicija;
import rc.ac.singidunum.novisad.service.deklaracija.CrudService;
import rc.ac.singidunum.novisad.service.implementacija.PozicijaService;

@Controller
@RequestMapping("/api/pozicije")
public class PozicijaController extends GenericCrudController<PozicijaDTO, Pozicija, Long> {

	@Autowired
	private PozicijaService pozicijaService;
	
	@Override
	protected CrudService<PozicijaDTO, Pozicija, Long> getService() {
		return pozicijaService;
	}

}
