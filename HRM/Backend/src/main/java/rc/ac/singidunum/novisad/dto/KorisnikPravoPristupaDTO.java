package rc.ac.singidunum.novisad.dto;

public class KorisnikPravoPristupaDTO {
	private Long id;
	
	private PozicijaDTO pozicija;
	
	private ZaposleniDTO zaposlen;

	public KorisnikPravoPristupaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public KorisnikPravoPristupaDTO(Long id, PozicijaDTO pozicija, ZaposleniDTO zaposlen) {
		super();
		this.id = id;
		this.pozicija = pozicija;
		this.zaposlen = zaposlen;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PozicijaDTO getPozicija() {
		return pozicija;
	}

	public void setPozicija(PozicijaDTO pozicija) {
		this.pozicija = pozicija;
	}

	public ZaposleniDTO getZaposlen() {
		return zaposlen;
	}

	public void setZaposlen(ZaposleniDTO zaposlen) {
		this.zaposlen = zaposlen;
	}
	
	
}
