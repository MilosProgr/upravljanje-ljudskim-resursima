package rc.ac.singidunum.novisad.controller.implementacija;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import rc.ac.singidunum.novisad.controller.deklaracija.GenericCrudController;
import rc.ac.singidunum.novisad.dto.AdresaDTO;
import rc.ac.singidunum.novisad.model.Adresa;
import rc.ac.singidunum.novisad.service.deklaracija.CrudService;
import rc.ac.singidunum.novisad.service.implementacija.AdresaService;

@Controller
@RequestMapping("api/adrese")
public class AdresaController extends GenericCrudController<AdresaDTO, Adresa, Long> {

	@Autowired
	private AdresaService adresaService;
	
	@Override
	protected CrudService<AdresaDTO, Adresa, Long> getService() {
		// TODO Auto-generated method stub
		return adresaService;
	}

}
