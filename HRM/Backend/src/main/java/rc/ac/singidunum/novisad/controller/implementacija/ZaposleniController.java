package rc.ac.singidunum.novisad.controller.implementacija;

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
import rc.ac.singidunum.novisad.controller.deklaracija.GenericCrudController;
import rc.ac.singidunum.novisad.dto.KorisnikPravoPristupaDTO;
import rc.ac.singidunum.novisad.dto.ZaposleniDTO;
import rc.ac.singidunum.novisad.mapper.implementacija.ZaposleniMapper;
import rc.ac.singidunum.novisad.model.KorisnikPravoPristupa;
import rc.ac.singidunum.novisad.model.Plata;
import rc.ac.singidunum.novisad.model.Pozicija;
import rc.ac.singidunum.novisad.model.Zaposleni;
import rc.ac.singidunum.novisad.service.deklaracija.CrudService;
import rc.ac.singidunum.novisad.service.implementacija.KorisnikPravoPristupaService;
import rc.ac.singidunum.novisad.service.implementacija.PozicijaService;
import rc.ac.singidunum.novisad.service.implementacija.UserDetailsService;
import rc.ac.singidunum.novisad.service.implementacija.ZaposleniService;
import rc.ac.singidunum.novisad.utils.TokenUtils;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;


//@CrossOrigin(origins = "http://localhost:4200")
@Controller
@RequestMapping("/api/zaposleni")
public class ZaposleniController extends GenericCrudController<ZaposleniDTO, Zaposleni, Long> {

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
	protected CrudService<ZaposleniDTO, Zaposleni, Long> getService() {
		// TODO Auto-generated method stub
		return zaposlenService;
	}
	
//	@GetMapping("/me")
	
	
	@Secured("ADMINISTRATOR")
    public ResponseEntity<List<ZaposleniDTO>> getAll() {
        return super.getAll();
    }
	
	@Secured("ADMINISTRATOR")
	@GetMapping("/pretraga")
	public ResponseEntity<List<ZaposleniDTO>> pretraziZaposlene(
	        @RequestParam(required = false) String ime,
	        @RequestParam(required = false) String prezime,
	        @RequestParam(required = false) String email,
	        @RequestParam(required = false) Long odsekId) {

	    List<ZaposleniDTO> rezultati = zaposlenService.pretrazi(ime, prezime, email, odsekId);
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
    public ResponseEntity<ZaposleniDTO> register(@PathVariable("role") String role, @RequestBody Zaposleni zaposlen) {
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
        ZaposleniDTO savedKorisnik = zaposlenService.save(zaposlen);

        
        KorisnikPravoPristupaDTO uDto = korisnikpravoPristupaService.save(new KorisnikPravoPristupa(permission.get(),zaposlen));
        Set<KorisnikPravoPristupaDTO> skupPravaPristupa = new HashSet<>();
        skupPravaPristupa.add(uDto);
        savedKorisnik.setPravoPristupa(skupPravaPristupa);
        System.out.println("Korisnik uspesno registrovan! " + savedKorisnik.getId());
        return new ResponseEntity<>(savedKorisnik, HttpStatus.CREATED);
        
    }
	
	@RequestMapping("/me")
	public ResponseEntity<ZaposleniDTO> getLoggedInUserDetails() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (authentication != null) {
	        String username = authentication.getName(); // This gives the username of the logged-in user
	        Optional<Zaposleni> loggedInUser = zaposlenService.findKorisnikByKorisnickoIme(username);
	        if (loggedInUser.isPresent()) {
	            // Map the Zaposleni entity to a DTO
	            ZaposleniDTO zaposleniDTO = zaposleniMapper.map(loggedInUser.get());
	            return new ResponseEntity<>(zaposleniDTO, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // User not found
	        }
	    }
	    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // User not authenticated
	}
	
	
	
	 @RequestMapping(path = "/{id}",method = RequestMethod.GET)
	    public ResponseEntity<ZaposleniDTO> getById(Long id) {
	        return super.getById(id);
	 }
	 

	
	 @Override
	 @Secured("ADMINISTRATOR")
	 @RequestMapping(path = "",method = RequestMethod.POST)
	 public ResponseEntity<ZaposleniDTO> create(@RequestBody Zaposleni zaposlen) {
//	     zaposlen.setLozinka(passwordEncoder.encode(zaposlen.getLozinka()));
//	     return super.create(zaposlen);
		 return register("RADNIK", zaposlen);
	 }

    @Override
    @Secured({"ADMINISTRATOR","RADNIK"})
    @RequestMapping(path = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity<ZaposleniDTO> update(@PathVariable("id") Long id,@RequestBody Zaposleni korisnik) {
    	
    		
    		korisnik.setLozinka(passwordEncoder.encode(korisnik.getLozinka()));
    	

        return super.update(id,korisnik);
    }
    
   
    @Override
    @Secured("ADMINISTRATOR")
    @RequestMapping(path = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<ZaposleniDTO> delete(Long id) {
        return super.delete(id);
    }
	

}
