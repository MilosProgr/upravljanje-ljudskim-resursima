package rc.ac.singidunum.novisad.features.zaposlen;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import rc.ac.singidunum.novisad.features.adresa.AdresaDTO;
import rc.ac.singidunum.novisad.features.adresa.AdresaDTO.AdresaDTORecord;
import rc.ac.singidunum.novisad.features.korisnikHandler.KorisnikPravoPristupaDTO;
import rc.ac.singidunum.novisad.features.korisnikHandler.KorisnikPravoPristupaDTO.KorisnikPravoPristupaDTORecord;
import rc.ac.singidunum.novisad.features.odsek.OdsekDTO;
import rc.ac.singidunum.novisad.features.odsek.OdsekDTO.OdsekDTORecord;
import rc.ac.singidunum.novisad.features.zaduzenje.ZaduzenjeDTO;
import rc.ac.singidunum.novisad.features.zaduzenje.ZaduzenjeDTO.ZaduzenjeDTORecord;
import rc.ac.singidunum.novisad.types.Pol;
import rc.ac.singidunum.novisad.types.StatusZaposlenja;

public class ZaposleniDTO {
	private Long id;
	
	private String ime;
    private String prezime;
    private String email;
    private String telefon;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate datumRodjenja;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate datumZaposlenja;
    
    private Pol pol;
    
    private StatusZaposlenja statusZaposlenja;
    
    private AdresaDTO adresa;
//    private PozicijaDTO pozicija;
    private OdsekDTO odsek;
    
    private Set<KorisnikPravoPristupaDTO> pravoPristupa = new HashSet<>();
    private String korisnickoIme;
    private String lozinka;
    
    private Set<ZaduzenjeDTO> zaduzenja = new HashSet<>();
    
	public ZaposleniDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ZaposleniDTO(Long id, String ime,String prezime, String korisnickoIme,
			String lozinka ,String email, String telefon, LocalDate datumRodjenja,
			LocalDate datumZaposlenja, Pol pol, StatusZaposlenja statusZaposlenja, AdresaDTO adresa,
			OdsekDTO odsek) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.email = email;
		this.telefon = telefon;
		this.datumRodjenja = datumRodjenja;
		this.datumZaposlenja = datumZaposlenja;
		this.pol = pol;
		this.statusZaposlenja = statusZaposlenja;
		this.adresa = adresa;
//		this.pozicija = pozicija;
		this.odsek = odsek;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public LocalDate getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(LocalDate datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public LocalDate getDatumZaposlenja() {
		return datumZaposlenja;
	}

	public void setDatumZaposlenja(LocalDate datumZaposlenja) {
		this.datumZaposlenja = datumZaposlenja;
	}

	public Pol getPol() {
		return pol;
	}

	public void setPol(Pol pol) {
		this.pol = pol;
	}

	public StatusZaposlenja getStatusZaposlenja() {
		return statusZaposlenja;
	}

	public void setStatusZaposlenja(StatusZaposlenja statusZaposlenja) {
		this.statusZaposlenja = statusZaposlenja;
	}

	public AdresaDTO getAdresa() {
		return adresa;
	}

	public void setAdresa(AdresaDTO adresa) {
		this.adresa = adresa;
	}

//	public PozicijaDTO getPozicija() {
//		return pozicija;
//	}
//
//	public void setPozicija(PozicijaDTO pozicija) {
//		this.pozicija = pozicija;
//	}

	public OdsekDTO getOdsek() {
		return odsek;
	}

	public void setOdsek(OdsekDTO odsek) {
		this.odsek = odsek;
	}

	public Set<KorisnikPravoPristupaDTO> getPravoPristupa() {
		return pravoPristupa;
	}

	public void setPravoPristupa(Set<KorisnikPravoPristupaDTO> pravoPristupa) {
		this.pravoPristupa = pravoPristupa;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public Set<ZaduzenjeDTO> getZaduzenja() {
		return zaduzenja;
	}

	public void setZaduzenja(Set<ZaduzenjeDTO> zaduzenja) {
		this.zaduzenja = zaduzenja;
	}
	
	

	public record ZaposleniDTORecord(Long id, String ime,String prezime, String korisnickoIme,
			String lozinka ,String email, String telefon, 
		    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
			LocalDate datumRodjenja,
		    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
			LocalDate datumZaposlenja, Pol pol, StatusZaposlenja statusZaposlenja, AdresaDTORecord adresa,
			OdsekDTORecord odsek, Set<ZaduzenjeDTORecord> zaduzenja,
			Set<KorisnikPravoPristupaDTORecord> pravoPristupa) 
	{

		
		
	}
	
//	public void setZaduzenja(Set<ZaduzenjeDTORecord> zaduzenja) {
//		this.zaduzenja = zaduzenja;
//	}
	
	
	
	
    
    
	
	
}
