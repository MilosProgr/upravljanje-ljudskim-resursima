package rc.ac.singidunum.novisad.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import rc.ac.singidunum.novisad.model.base.BaseEntity;

@Entity
@Table(name = "odsek")
public class Odsek implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naziv;
    private String opis;
    
    @OneToMany(mappedBy = "odsek")
    private List<Zaposleni> zaposleni = new ArrayList<>();
    
	public Odsek() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Odsek(Long id, String naziv, String opis) {
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

	public List<Zaposleni> getZaposleni() {
		return zaposleni;
	}

	public void setZaposleni(List<Zaposleni> zaposleni) {
		this.zaposleni = zaposleni;
	}
	
	
    
}

