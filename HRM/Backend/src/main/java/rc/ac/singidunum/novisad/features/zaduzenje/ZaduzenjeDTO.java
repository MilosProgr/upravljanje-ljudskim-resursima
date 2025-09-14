package rc.ac.singidunum.novisad.features.zaduzenje;

import rc.ac.singidunum.novisad.features.projekat.ProjekatDTO;
import rc.ac.singidunum.novisad.features.projekat.ProjekatDTO.ProjekatDTORecord;
import rc.ac.singidunum.novisad.features.zaposlen.ZaposleniDTO;
import rc.ac.singidunum.novisad.features.zaposlen.ZaposleniDTO.ZaposleniDTORecord;

public class ZaduzenjeDTO {
	
	private Long id;
	
	private ZaposleniDTO zaposleni;
	
	private ProjekatDTO projekat;
	
	private String uloga;

	public ZaduzenjeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ZaduzenjeDTO(Long id, ZaposleniDTO zaposleni, ProjekatDTO projekat, String uloga) {
		super();
		this.id = id;
		this.zaposleni = zaposleni;
		this.projekat = projekat;
		this.uloga = uloga;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ZaposleniDTO getZaposleni() {
		return zaposleni;
	}

	public void setZaposleni(ZaposleniDTO zaposleni) {
		this.zaposleni = zaposleni;
	}

	public ProjekatDTO getProjekat() {
		return projekat;
	}

	public void setProjekat(ProjekatDTO projekat) {
		this.projekat = projekat;
	}

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}
	
	
	public record ZaduzenjeDTORecord(Long id,ZaposleniDTORecord zaposleni,ProjekatDTORecord projekat,String uloga) {}
	
	
	
	
	
	
}
