package engine.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTools {

    public DateTools() {

    }

    public static Integer reduce(Date date) {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	String sDate = sdf.format(date);
	String[] nums = sDate.split("/");
	Integer num = 0;
	for (String string : nums) {
	    num += Integer.parseInt(string);
	}

	return NumberTools.reduce(num);
    }
}
