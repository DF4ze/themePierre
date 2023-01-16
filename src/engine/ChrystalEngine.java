package engine;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import engine.exception.NotALetterException;
import engine.exception.NotAWordException;
import engine.tools.DateTools;
import engine.tools.NumberTools;
import engine.tools.StringTools;

public class ChrystalEngine  {

    StringTools concat;
    Date naiss;

    public ChrystalEngine(String noms, Date naiss) throws NotAWordException, IOException {
	this.naiss = naiss;
	this.concat = new StringTools(noms);


	String pwd = System.getProperty("user.dir");
	File ref = new File("RefPierres.tsv");

	if( !ref.exists() ) {
	    ref = new File(pwd + File.separator + "RefPierres.tsv");
	}

	ReadCSV reader = new ReadCSV(ref.getAbsoluteFile().toString());
	List<List<String>> content = reader.readContent("\t");

	Pierre.init(content); // a modifier
    }

    public List<Theme> compile() throws NotALetterException {
	Pierre base = getBase();
	Pierre sommet = getSommet();
	Pierre chemin = getChemin();
	Pierre transfo = getTransfo(base, sommet, chemin);

	Pierre appel = getAppel();
	Pierre indiv = getIndiv();
	Pierre expr = getExpression(appel, indiv);
	Pierre transmut = getTransmutation(appel, indiv, expr);

	Pierre essence = getEssence();
	Pierre synth = getSynthese();
	Pierre passage = getPassage(transfo, transmut);
	Pierre init = getInitiation(essence, synth, passage);

	Pierre rea = getRealisation(transfo, transmut, init);

	List<Theme> themes = new ArrayList<Theme>();
	themes.add(new Theme( "Base", base));
	themes.add(new Theme( "Sommet", sommet));
	themes.add(new Theme( "Chemin", chemin));
	themes.add(new Theme( "Transformation", transfo));

	themes.add(new Theme( "Appel", appel));
	themes.add(new Theme( "Individualité", indiv));
	themes.add(new Theme( "Expression", expr));
	themes.add(new Theme( "Transmutation", transmut));

	themes.add(new Theme( "Essence", essence));
	themes.add(new Theme( "Synthèse", synth));
	themes.add(new Theme( "Passage", passage));
	themes.add(new Theme( "Initiation", init));

	themes.add(new Theme( "Réalisation", rea));

	return themes;
    }

    private Pierre getBase() throws NotALetterException  {
	List<String> letters = concat.getAllFirstLetters();

	return Pierre.getPierreFromLettres(letters);
    }

    private Pierre getSommet() throws NotALetterException  {
	List<String> letters = concat.getAllLastLetters();

	return Pierre.getPierreFromLettres(letters);
    }

    private Pierre getChemin() {
	int num = DateTools.reduce(naiss);
	return Pierre.getPierreFromNumber(num);
    }

    private Pierre getTransfo(Pierre base, Pierre sommet, Pierre chemin) {
	return Pierre.getPierreFromPierres(base, sommet, chemin);
    }

    private Pierre getAppel() throws NotALetterException  {
	List<String> letters = concat.getVoyelles();

	return Pierre.getPierreFromLettres(letters);
    }

    private Pierre getIndiv() throws NotALetterException {
	List<String> letters = concat.getConsonnes();

	return Pierre.getPierreFromLettres(letters);
    }

    private Pierre getExpression(Pierre appel, Pierre indiv) {
	return Pierre.getPierreFromPierres(appel, indiv);
    }

    private Pierre getTransmutation(Pierre appel, Pierre indiv, Pierre expr) {
	return Pierre.getPierreFromPierres(appel, indiv, expr);
    }

    private Pierre getEssence() throws NotALetterException {
	List<String> lettres = concat.getAllFirstVoyelles();
	return Pierre.getPierreFromLettres(lettres);
    }

    private Pierre getSynthese() throws NotALetterException {
	List<String> list = new ArrayList<String>();
	list.add(concat.toString());
	return Pierre.getPierreFromLettres(list);
    }

    private Pierre getPassage(Pierre transfo, Pierre transmut) {
	int sum = transfo.getNumero() + transmut.getNumero();
	sum = NumberTools.reduce(sum);
	return Pierre.getPierreFromNumber(sum);
    }

    private Pierre getInitiation(Pierre essence, Pierre synthese, Pierre passage) {
	int sum = essence.getNumero() + synthese.getNumero() + passage.getNumero();
	sum = NumberTools.reduce(sum);
	return Pierre.getPierreFromNumber(sum);
    }

    private Pierre getRealisation(Pierre transfo, Pierre transmut, Pierre initiation) {
	int sum = transfo.getNumero() + transmut.getNumero() + initiation.getNumero();
	sum = NumberTools.reduce(sum);
	return Pierre.getPierreFromNumber(sum);
    }

}
