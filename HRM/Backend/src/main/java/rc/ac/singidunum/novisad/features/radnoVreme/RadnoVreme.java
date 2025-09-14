package rc.ac.singidunum.novisad.features.radnoVreme;

import java.time.LocalDate;
import java.time.LocalTime;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import rc.ac.singidunum.novisad.features.zaposlen.Zaposleni;
import rc.ac.singidunum.novisad.generics.base.BaseEntity;
import rc.ac.singidunum.novisad.types.TipRadnogVremena;

@Entity
@Table(name = "radno_vreme")
public class RadnoVreme implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "zaposleni_id")
    private Zaposleni zaposleni;

    private LocalDate datum;
    private LocalTime vremeDolaska;
    private LocalTime vremeOdlaska;
    
    @Enumerated(EnumType.STRING)
    private TipRadnogVremena tipRadnogVremena;
    
	public RadnoVreme() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RadnoVreme(Long id, Zaposleni zaposleni, LocalDate datum, LocalTime vremeDolaska, LocalTime vremeOdlaska,TipRadnogVremena tip) {
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

	public Zaposleni getZaposleni() {
		return zaposleni;
	}

	public void setZaposleni(Zaposleni zaposleni) {
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

