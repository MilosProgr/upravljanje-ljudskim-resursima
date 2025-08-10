package rc.ac.singidunum.novisad.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ProjekatDTO {
	private Long id;
	
	private String naziv;
	private String opis;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")

	private LocalDate datumPocetka;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")

	private LocalDate datumKraja;
	
	public ProjekatDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProjekatDTO(Long id, String naziv, String opis, LocalDate datumPocetka, LocalDate datumKraja) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.opis = opis;
		this.datumPocetka = datumPocetka;
		this.datumKraja = datumKraja;
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

	public LocalDate getDatumPocetka() {
		return datumPocetka;
	}

	public void setDatumPocetka(LocalDate datumPocetka) {
		this.datumPocetka = datumPocetka;
	}

	public LocalDate getDatumKraja() {
		return datumKraja;
	}

	public void setDatumKraja(LocalDate datumKraja) {
		this.datumKraja = datumKraja;
	}
	
	
	
	
}
