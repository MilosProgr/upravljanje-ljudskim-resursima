package rc.ac.singidunum.novisad.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ObukaDTO {
	private Long id;
	
	private String naziv;
	private String opis;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate datumOdrzavanja;
	
	private ZaposleniDTO zaposleni;

	public ObukaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ObukaDTO(Long id, String naziv, String opis, LocalDate datumOdrzavanja, ZaposleniDTO zaposleni) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.opis = opis;
		this.datumOdrzavanja = datumOdrzavanja;
		this.zaposleni = zaposleni;
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

	public LocalDate getDatumOdrzavanja() {
		return datumOdrzavanja;
	}

	public void setDatumOdrzavanja(LocalDate datumOdrzavanja) {
		this.datumOdrzavanja = datumOdrzavanja;
	}

	public ZaposleniDTO getZaposleni() {
		return zaposleni;
	}

	public void setZaposleni(ZaposleniDTO zaposleni) {
		this.zaposleni = zaposleni;
	}
	
	
	
	
}
