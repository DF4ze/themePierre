package engine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.exception.NotALetterException;
import engine.tools.NumberTools;

public class Pierre {

	private Integer numero;
	private String nom;
	private String keyWords;
	private String shortDescr;
	private String description;
	private static Map<Integer, Pierre> referentiel;
	
	public static void init(){
		referentiel = new HashMap<>();
		for (int i = 0; i < 33; i++) {
			Pierre p = new Pierre(i, "Nom"+i, "Keywords"+i, "shortDescr"+i, "description"+i);
			referentiel.put(i, p);
		} 

	}
	
	public static void init(List<List<String>> csv){
		referentiel = new HashMap<>();
		int i=0;
		for ( List<String> line : csv ) {
			if( i == 0 ) {
				i++;
			}else {
				Integer num = 			line.size() >= 1 ? Integer.parseInt( line.get(0)) : null;
				String nom = 			line.size() >= 2 ?  line.get(1) : null;
				String key = 			line.size() >= 3 ?  line.get(2) : null;
				String shortDescr = 	line.size() >= 4 ?  line.get(3) : null;
				String descr = 			line.size() >= 5 ?  line.get(4) : null;
				
				Pierre p = new Pierre(
						num,
						nom,
						key,
						shortDescr,
						descr);
				referentiel.put(i++, p);
			}
		} 

	}
	
	
	public Pierre(Integer numero, String nom, String keyWords, String shortDescr, String description) {
		super();
		
		this.numero = numero;
		this.nom = nom;
		this.keyWords = keyWords;
		this.shortDescr = shortDescr;
		this.description = description;
	}

	
	public static Pierre getPierreFromNumber(Integer num) {
		return referentiel.get(num);
	}
	
	public static Pierre getPierreFromLettres(List<String> lettres) throws NotALetterException {
		Integer num = 0;
		for (String letter : lettres) {
			for( int i=0; i < letter.length(); i++ ) {
				num += NumberTools.letterToNumber(letter.charAt(i)+"");
			}
		}
		num = NumberTools.reduce(num);

		Pierre p = Pierre.getPierreFromNumber(num);
		return p;
	}
	
	public static Pierre getPierreFromPierres( Pierre ... pierres ) {
		int sum = 0;
		for (Pierre pierre : pierres) {
			sum += pierre != null ? pierre.getNumero() : 0;
		}
		sum = NumberTools.reduce(sum);
		return getPierreFromNumber(sum);
	}

	
	public Integer getNumero() {
		return numero == null ? 0 : numero;
	}
	
	public String getNom() {
		return nom;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getKeyWords() {
		return keyWords;
	}

	public String getShortDescr() {
		return shortDescr;
	}

	@Override
	public String toString() {
		String texte = "N° "+numero;
		if( nom != null )
			texte += " " + nom;
		if( keyWords != null )
			texte += " - "+keyWords;
		if( shortDescr != null ) {
			
			texte += "\n"+shortDescr.replaceAll("\\. ", ".\n");
			
		}
		if( description != null )
			texte += "\n\n"+description;
		
		return texte;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
