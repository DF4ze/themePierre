package engine.tools;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import engine.exception.NotAWordException;

public class StringTools {

    private String word;
    private List<String> vowels;

    public StringTools(String word) throws NotAWordException {
	String normalized = Normalizer.normalize(word, Normalizer.Form.NFD);
	String accentRemoved = normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	this.word = accentRemoved;

	vowels = new ArrayList<String>();
	vowels.add("a");
	vowels.add("e");
	vowels.add("i");
	vowels.add("o");
	vowels.add("u");
	if(!isAlpha(this.word)) {
	    throw new NotAWordException("Undesirable character.s : "+this.word);
	}
    }
    public boolean isAlpha(String name) {
	return name.matches("[a-zA-Z -]+");
    }

    public String getFirstLetter() {
	return word.charAt(0)+"";
    }
    public String getFirstLetter(String word) {
	return word.length() > 0 ? word.charAt(0)+"" : "";
    }

    public List<String> getAllFirstLetters(){
	List<String> liste = new ArrayList<String>();

	String[] split = word.split(" ");
	for (String string : split) {
	    liste.add(getFirstLetter(string));
	}

	return liste;
    }

    public String getLastLetter() {
	return word.charAt(word.length()-1)+"";
    }
    public String getLastLetter(String word) {
	return word.charAt(word.length()-1)+"";
    }

    public List<String> getAllLastLetters(){
	List<String> liste = new ArrayList<String>();

	String[] split = word.split(" ");
	for (String string : split) {
	    liste.add(getLastLetter(string));
	}

	return liste;
    }
    public List<String> getAllFirstVoyelles() {
	String buff = word.toLowerCase();
	String[] split = buff.split(" ");

	List<String> result = new ArrayList<String>();
	for (String string : split) {
	    for( int i=0; i < string.length(); i++ ) {
		String letter = string.charAt(i)+"";
		if( vowels.contains(letter+"") ) {
		    result.add(letter);
		    i = string.length();
		}
	    }
	}

	return result;
    }

    public List<String> getVoyelles() {
	String buff = word.toLowerCase();

	List<String> result = new ArrayList<String>();
	for( int i=0; i < buff.length(); i++ ) {
	    String letter = buff.charAt(i)+"";
	    if( vowels.contains(letter+"") ) {
		result.add(letter);
	    }
	}

	return result;
    }

    public List<String>getConsonnes() {
	String buff = word.toLowerCase();

	List<String> result = new ArrayList<String>();
	for( int i=0; i < buff.length(); i++ ) {
	    String letter = buff.charAt(i)+"";
	    if( !vowels.contains(letter+"") ) {
		result.add(letter);
	    }
	}

	return result;
    }

    @Override
    public String toString() {
	return word;
    }
}
