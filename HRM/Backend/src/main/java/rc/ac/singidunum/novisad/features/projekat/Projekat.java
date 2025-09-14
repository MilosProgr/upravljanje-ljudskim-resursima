package rc.ac.singidunum.novisad.features.projekat;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import rc.ac.singidunum.novisad.generics.base.BaseEntity;

@Entity
@Table(name = "projekat")
public class Projekat implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naziv;
    private String opis;
    private LocalDate datumPocetka;
    private LocalDate datumKraja;
    
	public Projekat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Projekat(Long id, String naziv, String opis, LocalDate datumPocetka, LocalDate datumKraja) {
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
