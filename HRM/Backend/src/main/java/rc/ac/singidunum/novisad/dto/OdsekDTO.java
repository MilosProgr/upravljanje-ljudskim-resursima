package rc.ac.singidunum.novisad.dto;

import java.util.ArrayList;
import java.util.List;

public class OdsekDTO {
	private Long id;
	
	private String naziv;
	private String opis;
	
	private List<ZaposleniDTO> zaposleni = new ArrayList<>();

	public OdsekDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OdsekDTO(Long id, String naziv, String opis) {
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

	public List<ZaposleniDTO> getZaposleni() {
		return zaposleni;
	}

	public void setZaposleni(List<ZaposleniDTO> zaposleni) {
		this.zaposleni = zaposleni;
	}
	
}
