package rc.ac.singidunum.novisad.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import rc.ac.singidunum.novisad.tipovi.TipRadnogVremena;

public class RadnoVremeDTO {
	private Long id;
	
	private ZaposleniDTO zaposleni;
	
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate datum;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	private LocalTime vremeDolaska;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	private LocalTime vremeOdlaska;
	
	private TipRadnogVremena tipRadnogVremena;
	
	public RadnoVremeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RadnoVremeDTO(Long id, ZaposleniDTO zaposleni, LocalDate datum, LocalTime vremeDolaska,
			LocalTime vremeOdlaska,TipRadnogVremena tip) {
		super();
		this.id = id;
		this.zaposleni = zaposleni;
		this.datum = datum;
		this.vremeDolaska = vremeDolaska;
		this.vremeOdlaska = vremeOdlaska;
		this.tipRadnogVremena = tip;
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

	public LocalDate getDatum() {
		return datum;
	}

	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}

	public LocalTime getVremeDolaska() {
		return vremeDolaska;
	}

	public void setVremeDolaska(LocalTime vremeDolaska) {
		this.vremeDolaska = vremeDolaska;
	}

	public LocalTime getVremeOdlaska() {
		return vremeOdlaska;
	}

	public void setVremeOdlaska(LocalTime vremeOdlaska) {
		this.vremeOdlaska = vremeOdlaska;
	}

	public TipRadnogVremena getTipRadnogVremena() {
		return tipRadnogVremena;
	}

	public void setTipRadnogVremena(TipRadnogVremena tipRadnogVremena) {
		this.tipRadnogVremena = tipRadnogVremena;
	}
	
	
	
	
	
	
	
}
