package rc.ac.singidunum.novisad.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import rc.ac.singidunum.novisad.model.base.BaseEntity;

@Entity
@Table(name = "adresa")
public class Adresa implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ulica;
    private String broj;
    private String grad;
    private String postanskiBroj;
    private String drzava;
    
	public Adresa() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Adresa(Long id, String ulica, String broj, String grad, String postanskiBroj, String drzava) {
		super();
		this.id = id;
		this.ulica = ulica;
		this.broj = broj;
		this.grad = grad;
		this.postanskiBroj = postanskiBroj;
		this.drzava = drzava;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUlica() {
		return ulica;
	}

	public void setUlica(String ulica) {
		this.ulica = ulica;
	}

	public String getBroj() {
		return broj;
	}

	public void setBroj(String broj) {
		this.broj = broj;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getPostanskiBroj() {
		return postanskiBroj;
	}

	public void setPostanskiBroj(String postanskiBroj) {
		this.postanskiBroj = postanskiBroj;
	}

	public String getDrzava() {
		return drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}
	
	
    
    
}