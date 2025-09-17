package rc.ac.singidunum.novisad.features.zaposlen;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.transaction.Transactional;
import rc.ac.singidunum.novisad.features.korisnikHandler.KorisnikPravoPristupa;
import rc.ac.singidunum.novisad.features.korisnikHandler.KorisnikPravoPristupaService;
import rc.ac.singidunum.novisad.features.korisnikHandler.KorisnikPravoPristupaDTO.KorisnikPravoPristupaDTORecord;
import rc.ac.singidunum.novisad.features.plata.Plata;
import rc.ac.singidunum.novisad.features.pozicija.Pozicija;
import rc.ac.singidunum.novisad.features.pozicija.PozicijaService;
import rc.ac.singidunum.novisad.features.zaposlen.ZaposleniDTO.ZaposleniDTORecord;
import rc.ac.singidunum.novisad.generics.Link.Resource;
import rc.ac.singidunum.novisad.generics.controller.GenericCrudController;
import rc.ac.singidunum.novisad.generics.service.CrudService;
import rc.ac.singidunum.novisad.security.UserDetailsService;
import rc.ac.singidunum.novisad.utils.TokenUtils;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;


//@CrossOrigin(origins = "http://localhost:4200")
@Controller
@RequestMapping("/api/zaposleni")
public class ZaposleniController extends GenericCrudController<ZaposleniDTORecord, Zaposleni, Long> {

	@Autowired
	private ZaposleniService zaposlenService;
	
	@Autowired
	private ZaposleniMapper zaposleniMapper;
	
	@Autowired
	KorisnikPravoPristupaService korisnikpravoPristupaService;
	
	@Autowired
	PozicijaService pozicijaService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	TokenUtils tokenUtils;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Override
	protected CrudService<ZaposleniDTORecord, Zaposleni, Long> getService() {
		// TODO Auto-generated method stub
		return zaposlenService;
	}
	
//	@GetMapping("/me")
	
	
	@Secured("ADMINISTRATOR")
    public ResponseEntity<Resource<List<ZaposleniDTORecord>>> getAll() {
        return super.getAll();
    }
	
	@Secured("ADMINISTRATOR")
	@GetMapping("/pretraga")
	public ResponseEntity<List<ZaposleniDTORecord>> pretraziZaposlene(
	        @RequestParam(required = false) String ime,
	        @RequestParam(required = false) String prezime,
	        @RequestParam(required = false) String email,
	        @RequestParam(required = false) Long odsekId) {

	    List<ZaposleniDTORecord> rezultati = zaposlenService.pretrazi(ime, prezime, email, odsekId);
	    return new ResponseEntity<>(rezultati, HttpStatus.OK);
	}
	
	@RequestMapping(path = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody Zaposleni zaposlen) {
       try {
    	   System.out.println(userDetailsService.loadUserByUsername(zaposlen.getKorisnickoIme()));
           UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(zaposlen.getKorisnickoIme(),
        		   zaposlen.getLozinka());
           Authentication auth = authenticationManager.authenticate(token);
           SecurityContextHolder.getContext().setAuthentication(auth);

           String jwt = tokenUtils.generateToken(userDetailsService.loadUserByUsername(zaposlen.getKorisnickoIme()));
           System.out.println(jwt);

           return new ResponseEntity<String>(jwt, HttpStatus.OK);
    	   
       } catch (Exception e) {
    	// Log the error
           System.err.println("Error during login: " + e.getMessage());
           e.printStackTrace(); // This will print the full stack trace

           // Return an error response
           return new ResponseEntity<String>("Error during login: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    	   
       }
		
    }
	
	@PostMapping("/register/{role}")
    @Transactional
    public ResponseEntity<ZaposleniDTORecord> register(@PathVariable("role") String role, @RequestBody Zaposleni zaposlen) {
        //List<Permission> permissions = permissionService.findPermissionByName(role);
		Optional<Zaposleni> postojeciKorisnik = zaposlenService.findKorisnikByKorisnickoIme(zaposlen.getKorisnickoIme());
		if (postojeciKorisnik.isPresent()) {
            System.out.println("Korisnik: "+ postojeciKorisnik + "Vec postoji!");
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<Pozicija> permission = pozicijaService.findPozicijaByNaziv(role);
        if (!permission.isPresent()) {
        	System.out.println("Nije nadjena permisija za datu rolu: " + role);
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        Optional<Pozicija> pozicijaOpt = pozicijaService.findPozicijaByNaziv(role);
        if (!pozicijaOpt.isPresent()) {
            System.out.println("Nije nadjena pozicija za datu rolu: " + role);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Pozicija pozicija = pozicijaOpt.get();

        // Dobavljanje plate iz pozicije
        Plata plata = pozicija.getPlata();
        
        if (plata != null) {
            // Setovanje datuma isplate na danasnji datum
            plata.setDatumIsplate(LocalDate.now());
        } else {
            System.out.println("Pozicija nema povezanu platu.");
        }
        
        
        zaposlen.setLozinka(passwordEncoder.encode(zaposlen.getLozinka()));
        ZaposleniDTORecord savedKorisnik = zaposlenService.save(zaposlen);

        
        KorisnikPravoPristupaDTORecord uDto = korisnikpravoPristupaService.save(new KorisnikPravoPristupa(permission.get(),zaposlen));
        Set<KorisnikPravoPristupaDTORecord> skupPravaPristupa = new HashSet<>();
        skupPravaPristupa.add(uDto);
//        savedKorisnik.setPravoPristupa(skupPravaPristupa);
        savedKorisnik = new ZaposleniDTORecord(
        	    savedKorisnik.id(),
        	    savedKorisnik.ime(),
        	    savedKorisnik.prezime(),
        	    savedKorisnik.korisnickoIme(),
        	    savedKorisnik.lozinka(),
        	    savedKorisnik.email(),
        	    savedKorisnik.telefon(),
        	    savedKorisnik.datumRodjenja(),
        	    savedKorisnik.datumZaposlenja(),
        	    savedKorisnik.pol(),
        	    savedKorisnik.statusZaposlenja(),
        	    null,
        	    null,
        	    new HashSet<>(),
        	    skupPravaPristupa   // dodaj ovde prava
        	);
        System.out.println("Korisnik uspesno registrovan! " + savedKorisnik.id());
        return new ResponseEntity<>(savedKorisnik, HttpStatus.CREATED);
        
    }
	
	@RequestMapping("/me")
	public ResponseEntity<ZaposleniDTORecord> getLoggedInUserDetails() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (authentication != null) {
	        String username = authentication.getName(); // This gives the username of the logged-in user
	        Optional<Zaposleni> loggedInUser = zaposlenService.findKorisnikByKorisnickoIme(username);
	        if (loggedInUser.isPresent()) {
	            // Map the Zaposleni entity to a DTO
	        	ZaposleniDTORecord zaposleniDTO = zaposleniMapper.map(loggedInUser.get());
	            return new ResponseEntity<>(zaposleniDTO, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // User not found
	        }
	    }
	    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // User not authenticated
	}
	
	
	
	 @RequestMapping(path = "/{id}",method = RequestMethod.GET)
	    public ResponseEntity<Resource<ZaposleniDTORecord>> getById(Long id) {
	        return super.getById(id);
	 }
	 

	
	 

    @Override
    @Secured({"ADMINISTRATOR","RADNIK"})
    @RequestMapping(path = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Resource<ZaposleniDTORecord>> update(@PathVariable("id") Long id,@RequestBody Zaposleni korisnik) {
    	
    		
    		korisnik.setLozinka(passwordEncoder.encode(korisnik.getLozinka()));
    	

        return super.update(id,korisnik);
    }
    
   
    @Override
    @Secured("ADMINISTRATOR")
    @RequestMapping(path = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Resource<Void>> delete(Long id) {
        return super.delete(id);
    }

	@Override
	protected String getBasePath() {
		// TODO Auto-generated method stub
		return "/api/zaposleni";
	}

	@Override
	protected Long getEntityId(ZaposleniDTORecord dto) {
		// TODO Auto-generated method stub
		return dto.id();
	}
	

}
