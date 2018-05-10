package hr.tvz.pios.domain.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class KorisnikFormModel {

	private Integer id;
	
	@NotNull(message = "Ne smije biti prazno.")
	@Size(min = 1, message = "Ne smije biti prazno.")
	private String korisnickoIme;
	
	@NotNull(message = "Ne smije biti prazno.")
	@Size(min = 1, message = "Ne smije biti prazno.")
	private String ime;
	
	@NotNull(message = "Ne smije biti prazno.")
	@Size(min = 1, message = "Ne smije biti prazno.")
	private String prezime;
	
	@NotNull(message = "Ne smije biti prazno.")
	@Min(value = 18, message = "Minimalni broj godina je 18.")
	private Integer godine;
	
	private String mjesto;
	private String opis;
	private String spol;
	private String trazeniSpol;
	
	@NotNull(message = "Ne smije biti prazno.")
	@Size(min = 8, message = "Lozinka mora imati najmanje 8 znakova.")
	private String lozinka;
	
	public KorisnikFormModel() {
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
	
}
