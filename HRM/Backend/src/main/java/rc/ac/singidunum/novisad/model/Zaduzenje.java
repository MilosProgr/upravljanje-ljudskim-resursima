package rc.ac.singidunum.novisad.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import rc.ac.singidunum.novisad.model.base.BaseEntity;

@Entity
@Table(name = "zaduzenje")
public class Zaduzenje implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "zaposleni_id")
    private Zaposleni zaposleni;

    @ManyToOne
    @JoinColumn(name = "projekat_id")
    private Projekat projekat;

    private String uloga;

	public Zaduzenje() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Zaduzenje(Long id, Zaposleni zaposleni, Projekat projekat, String uloga) {
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

	public Zaposleni getZaposleni() {
		return zaposleni;
	}

	public void setZaposleni(Zaposleni zaposleni) {
		this.zaposleni = zaposleni;
	}

	public Projekat getProjekat() {
		return projekat;
	}

	public void setProjekat(Projekat projekat) {
		this.projekat = projekat;
	}

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}
    
    
}

