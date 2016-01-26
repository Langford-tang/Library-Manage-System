/**
 * @classname = Configuration.class
 * @description 
 * @author = Frank
 * @time = 2015年12月16日下午2:37:26
 * @version = 1.0
 */
package frank;

import java.io.File;
import java.util.regex.Pattern;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class Configuration {
	// enumeration for the mysql configration
	private static enum MYSQLINFOR {
		DBHOST("dbhost", ""), DBPORT("dbport", "3306"), DBNAME("dbname", "libraryMS"), DBUSER("dbuser",
				"root"), DBPASSWORD("dbpassword", "root");
		private final String configname;
		private String configvalue;

		private MYSQLINFOR(String configname, String configvalue) {
			this.configname = configname;
			this.configvalue = configvalue;
		}

	}

	// constructor
	public Configuration() {
		// automatically load the configuration
		try {
			this.readConfig();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// read the Configuration Document from XML file & store it into the
	// enumeration class
	public void readConfig() throws DocumentException {
		SAXReader reader = new SAXReader();
		File file = new File("config.xml");
		Document document = reader.read(file);
		Node node = document.selectSingleNode("//config/mysql");
		for (MYSQLINFOR mysqlinfor : MYSQLINFOR.values()) {
			mysqlinfor.configvalue = node.valueOf(mysqlinfor.configname);
		}
		// ensure DBPORT is of a form of number String
		MYSQLINFOR.DBPORT.configvalue = isNumeric(MYSQLINFOR.DBPORT.configvalue) ? MYSQLINFOR.DBPORT.configvalue
				: "3306";

		// System.out.println(MYSQLINFOR.DBHOST.configvalue);
	}

	// return the mysql information
	public String[] getMysqlInfo() {
		String[] mysqlInforArray = new String[5];
		int i = 0;
		for (MYSQLINFOR mysqlinfor : MYSQLINFOR.values()) {
			mysqlInforArray[i] = mysqlinfor.configvalue;
			i++;
		}
		return mysqlInforArray;
	}

	// // test
	// public static void main(String[] args) {
	// Configuration config = new Configuration();
	// System.out.println(config.getMysqlInfo()[4]);
	// }

	// to judge if a String is a number
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}
}