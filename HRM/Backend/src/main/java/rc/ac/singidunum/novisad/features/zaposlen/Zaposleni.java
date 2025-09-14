package rc.ac.singidunum.novisad.features.zaposlen;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import rc.ac.singidunum.novisad.features.adresa.Adresa;
import rc.ac.singidunum.novisad.features.korisnikHandler.KorisnikPravoPristupa;
import rc.ac.singidunum.novisad.features.odsek.Odsek;
import rc.ac.singidunum.novisad.features.zaduzenje.Zaduzenje;
import rc.ac.singidunum.novisad.generics.base.BaseEntity;
import rc.ac.singidunum.novisad.types.Pol;
import rc.ac.singidunum.novisad.types.StatusZaposlenja;

@Entity
@Table(name = "zaposleni")
public class Zaposleni implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
    private Long id;

    private String ime;
    private String prezime;
    private String email;
    private String telefon;
    
    private LocalDate datumRodjenja;
    private LocalDate datumZaposlenja;

    @Enumerated(EnumType.STRING)
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    private Pol pol;

    @Enumerated(EnumType.STRING)
    private StatusZaposlenja statusZaposlenja;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adresa_id")
    private Adresa adresa;

//    @ManyToOne
//    @JoinColumn(name = "pozicija_id")
//    private Pozicija pozicija;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "odsek_id")
    private Odsek odsek;
    
    @OneToMany(mappedBy = "zaposlen",orphanRemoval = true,fetch = FetchType.EAGER)
    private Set<KorisnikPravoPristupa> pravoPristupa = new HashSet<>();
    
    @Column(nullable = false,unique = true)
    private String korisnickoIme;		

    @Column(nullable = false)
    private String lozinka;
    
    @OneToMany(mappedBy = "zaposleni", fetch = FetchType.LAZY)
    private Set<Zaduzenje> zaduzenja = new HashSet<>();
    

	public Zaposleni() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Zaposleni(Long id, String ime,String korisnickoIme,String lozinka, String prezime, String email, String telefon, LocalDate datumRodjenja,
			LocalDate datumZaposlenja, Pol pol, StatusZaposlenja statusZaposlenja, Adresa adresa,
			Odsek odsek) {
		super();
		this.id = id;
		this.ime = ime;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.prezime = prezime;
		this.email = email;
		this.telefon = telefon;
		this.datumRodjenja = datumRodjenja;
		this.datumZaposlenja = datumZaposlenja;
		this.pol = pol;
		this.statusZaposlenja = statusZaposlenja;
		this.adresa = adresa;
		this.odsek = odsek;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public LocalDate getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(LocalDate datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public LocalDate getDatumZaposlenja() {
		return datumZaposlenja;
	}

	public void setDatumZaposlenja(LocalDate datumZaposlenja) {
		this.datumZaposlenja = datumZaposlenja;
	}

	public Pol getPol() {
		return pol;
	}

	public void setPol(Pol pol) {
		this.pol = pol;
	}

	public StatusZaposlenja getStatusZaposlenja() {
		return statusZaposlenja;
	}

	public void setStatusZaposlenja(StatusZaposlenja statusZaposlenja) {
		this.statusZaposlenja = statusZaposlenja;
	}

	public Adresa getAdresa() {
		return adresa;
	}

	public void setAdresa(Adresa adresa) {
		this.adresa = adresa;
	}

//	public Pozicija getPozicija() {
//		return pozicija;
//	}
//
//	public void setPozicija(Pozicija pozicija) {
//		this.pozicija = pozicija;
//	}

	public Odsek getOdsek() {
		return odsek;
	}

	public void setOdsek(Odsek odsek) {
		this.odsek = odsek;
	}

	public Set<KorisnikPravoPristupa> getPravoPristupa() {
		return pravoPristupa;
	}

	public void setPravoPristupa(Set<KorisnikPravoPristupa> pravoPristupa) {
		this.pravoPristupa = pravoPristupa;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public Set<Zaduzenje> getZaduzenja() {
		return zaduzenja;
	}

	public void setZaduzenja(Set<Zaduzenje> zaduzenja) {
		this.zaduzenja = zaduzenja;
	}
	
	
	
	
    
    
}

