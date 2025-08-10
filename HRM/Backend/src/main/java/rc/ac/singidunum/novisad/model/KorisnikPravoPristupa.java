package rc.ac.singidunum.novisad.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import rc.ac.singidunum.novisad.model.base.BaseEntity;

@Entity
public class KorisnikPravoPristupa implements BaseEntity<Long> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id",nullable = false)
	private Long id;
	
	@ManyToOne(optional = false)
	private Pozicija pozicija;
	
	@ManyToOne(optional = false)
	private Zaposleni zaposlen;
	
	
	public KorisnikPravoPristupa() {
		super();
		// TODO Auto-generated constructor stub
	}

	public KorisnikPravoPristupa(Long id, Pozicija pozicija, Zaposleni zaposlen) {
		super();
		this.id = id;
		this.pozicija = pozicija;
		this.zaposlen = zaposlen;
	}
	
	public KorisnikPravoPristupa(Pozicija pozicija, Zaposleni zaposlen) {
		super();
		this.pozicija = pozicija;
		this.zaposlen = zaposlen;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pozicija getPozicija() {
		return pozicija;
	}

	public void setPozicija(Pozicija pozicija) {
		this.pozicija = pozicija;
	}

	public Zaposleni getZaposlen() {
		return zaposlen;
	}

	public void setZaposlen(Zaposleni zaposlen) {
		this.zaposlen = zaposlen;
	}
	
	
	
	
	
	
	
}
