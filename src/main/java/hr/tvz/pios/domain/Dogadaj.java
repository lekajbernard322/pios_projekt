package hr.tvz.pios.domain;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "dogadaj")
public class Dogadaj {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@Size(min = 1, max = 50)
	@Column(length = 50, nullable = false)
	private String naziv;
	
	@NotNull
	@Size(min = 1, max = 200)
	@Column(length = 200, nullable = false)
	private String opis;
	
	@NotNull
	@Size(min = 1, max = 50)
	@Column(length = 50, nullable = false)
	private String mjesto;
	
	@NotNull
	private Timestamp datum;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	@JoinTable(
			name = "dogadaj_korisnik",
			joinColumns = { @JoinColumn(name = "dogadaj_id") },
			inverseJoinColumns = { @JoinColumn(name = "korisnik_id") })
	private Set<Korisnik> korisnici = new HashSet<>();

	public Dogadaj() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getMjesto() {
		return mjesto;
	}

	public void setMjesto(String mjesto) {
		this.mjesto = mjesto;
	}

	public Timestamp getDatum() {
		return datum;
	}

	public void setDatum(Timestamp datum) {
		this.datum = datum;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Dogadaj))
			return false;
		
		Dogadaj d = (Dogadaj) obj;
		return d.id == id;
	}

	public Set<Korisnik> getKorisnici() {
		return korisnici;
	}

	public void setKorisnici(Set<Korisnik> korisnici) {
		this.korisnici = korisnici;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, naziv);
	}

	@Override
	public String toString() {
		return "[id=" + id + ", naziv=" + naziv + ", broj korisnika: " + korisnici.size() + "]";
	}
	
}
