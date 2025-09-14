package rc.ac.singidunum.novisad.features.adresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import rc.ac.singidunum.novisad.features.adresa.AdresaDTO.AdresaDTORecord;
import rc.ac.singidunum.novisad.generics.controller.GenericCrudController;
import rc.ac.singidunum.novisad.generics.service.CrudService;

@Controller
@RequestMapping("api/adrese")
public class AdresaController extends GenericCrudController<AdresaDTORecord, Adresa, Long> {

	@Autowired
	private AdresaService adresaService;
	
	@Override
	protected CrudService<AdresaDTORecord, Adresa, Long> getService() {
		// TODO Auto-generated method stub
		return adresaService;
	}

}
