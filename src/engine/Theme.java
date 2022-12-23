package engine;

public class Theme {

	Integer place;
	String nom;
	Pierre pierre;
	
	public Theme() {
		// TODO Auto-generated constructor stub
	}


	public Theme(int place, String nom, Pierre pierre) {
		super();
		this.place = place;
		this.nom = nom;
		this.pierre = pierre;
	}


	public Theme(String nom, Pierre pierre) {
		this.nom = nom;
		this.pierre = pierre;
	}


	public Integer getPlace() {
		return place;
	}

	public void setPlace(Integer place) {
		this.place = place;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Pierre getPierre() {
		return pierre;
	}

	public void setPierre(Pierre pierre) {
		this.pierre = pierre;
	}

	
}
