package engine.tools;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;

public class NumberTools {

    private static Map<String, Integer> referentiel ;

    private static void init()
    {
	referentiel = new HashMap<String, Integer>();
	String all = "abcdefghijklmnopqrstuvwxyz";

	for (int i = 0; i < all.length(); i++) {
	    referentiel.put(all.charAt(i)+"", (i)%9+1);

	}
    }

    private static Integer addition(Integer number) {
	String temp = number+"";
	Integer result = 0;

	for (int i = 0; i < temp.length(); i++) {
	    result += Integer.parseInt(temp.charAt(i)+"");

	}

	return result;
    }

    public static Integer reduce(Integer number) {
	Integer temp = number;
	while (temp > 33) {
	    temp = addition(temp);
	}
	return temp;
    }

    public static Integer letterToNumber(String letter) {
	if(referentiel == null) {
	    init();
	}
	letter = Normalizer.normalize(letter, Normalizer.Form.NFD);
	if( letter.length()>0 && !letter.equals(" ")  && !letter.equals("-") ) {
	    return  referentiel.get(letter.toLowerCase());
	} else {
	    return 0;
	}
    }

}
