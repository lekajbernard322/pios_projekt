package hr.tvz.pios.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "korisnik")
public class Korisnik implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@Size(min = 1, max = 50)
	@Column(length = 50, nullable = false)
	private String korisnickoIme;
	
	@NotNull
	@Size(min = 1, max = 50)
	@Column(length = 50, nullable = false)
	private String ime;
	
	@NotNull
	@Size(min = 1, max = 50)
	@Column(length = 50, nullable = false)
	private String prezime;
	
	@Min(18)
	private Integer godine;
	
	@Size(max = 50)
	private String mjesto;
	
	@Size(max = 100)
	private String profilnaSlikaUrl;
	
	@Size(max = 500)
	private String opis;
	
	@Size(max = 20)
	private String spol;
	
	@Size(max = 20)
	private String trazeniSpol;
	
	@Size(max = 120)
	@NotNull
	private String lozinka;
	
	@NotNull
	private Boolean aktivan = Boolean.TRUE;
	
	@ElementCollection
	@CollectionTable(
			name = "slike", 
			joinColumns = @JoinColumn(name="korisnik_id"))
	@Column(name = "slika_url")
	private Set<String> slikeUrl = new HashSet<>();
	
	@ElementCollection
	@CollectionTable(
			name = "korisnik_uloga", 
			joinColumns = @JoinColumn(
					name="korisnik", 
					referencedColumnName="korisnickoIme"))
	@Column(name = "uloga")
	private Set<String> uloge = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	@JoinTable(
			name = "dogadaj_korisnik",
			joinColumns = { @JoinColumn(name = "korisnik_id") },
			inverseJoinColumns = { @JoinColumn(name = "dogadaj_id") })
	private Set<Dogadaj> dogadaji = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	@JoinTable(
			name = "korisnik_like",
			joinColumns = { @JoinColumn(name = "korisnik_id") },
			inverseJoinColumns = { @JoinColumn(name = "korisnik_id_liked") })
	private Set<Korisnik> korisniciLike = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	@JoinTable(
			name = "korisnik_dislike",
			joinColumns = { @JoinColumn(name = "korisnik_id") },
			inverseJoinColumns = { @JoinColumn(name = "korisnik_id_disliked") })
	private Set<Korisnik> korisniciDislike = new HashSet<>();

	public Korisnik() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
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

	public Integer getGodine() {
		return godine;
	}

	public void setGodine(Integer godine) {
		this.godine = godine;
	}

	public String getMjesto() {
		return mjesto;
	}

	public void setMjesto(String mjesto) {
		this.mjesto = mjesto;
	}

	public String getProfilnaSlikaUrl() {
		return profilnaSlikaUrl;
	}

	public void setProfilnaSlikaUrl(String profilnaSlikaUrl) {
		this.profilnaSlikaUrl = profilnaSlikaUrl;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getSpol() {
		return spol;
	}

	public void setSpol(String spol) {
		this.spol = spol;
	}

	public String getTrazeniSpol() {
		return trazeniSpol;
	}

	public void setTrazeniSpol(String trazeniSpol) {
		this.trazeniSpol = trazeniSpol;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public Boolean getAktivan() {
		return aktivan;
	}

	public void setAktivan(Boolean aktivan) {
		this.aktivan = aktivan;
	}

	public Set<String> getSlikeUrl() {
		return slikeUrl;
	}

	public void setSlikeUrl(Set<String> slikeUrl) {
		this.slikeUrl = slikeUrl;
	}

	public Set<Dogadaj> getDogadaji() {
		return dogadaji;
	}

	public void setDogadaji(Set<Dogadaj> dogadaji) {
		this.dogadaji = dogadaji;
	}

	public Set<Korisnik> getKorisniciLike() {
		return korisniciLike;
	}

	public void setKorisniciLike(Set<Korisnik> korisniciLike) {
		this.korisniciLike = korisniciLike;
	}

	public Set<Korisnik> getKorisniciDislike() {
		return korisniciDislike;
	}

	public void setKorisniciDislike(Set<Korisnik> korisniciDislike) {
		this.korisniciDislike = korisniciDislike;
	}

	public Set<String> getUloge() {
		return uloge;
	}

	public void setUloge(Set<String> uloge) {
		this.uloge = uloge;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Korisnik))
			return false;
		
		Korisnik k = (Korisnik) obj;
		return k.id == id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, korisnickoIme);
	}

	@Override
	public String toString() {
		return "[id=" + id + ", korisnicko ime=" + korisnickoIme + ", uloge=" + uloge.stream().map(Object::toString).collect(Collectors.joining(",")) + "]";
	}
	
}