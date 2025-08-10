package rc.ac.singidunum.novisad.dto;

//import java.math.BigDecimal;

public class PozicijaDTO {
	private Long id;
	
	private String naziv;
	private String opis;
	private PlataDTO plata;
	
	public PozicijaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PozicijaDTO(Long id, String naziv, String opis) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.opis = opis;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public PlataDTO getPlata() {
		return plata;
	}

	public void setPlata(PlataDTO plata) {
		this.plata = plata;
	}
	
	
	
	
}
