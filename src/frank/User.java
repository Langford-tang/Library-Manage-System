package frank;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import arthur.Book;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
	// initialize the mysql connection configuration ,needed only once before
	// changing mysql configration
	private static Configuration config = new Configuration();
	private static ConnectionDB db = new ConnectionDB(config.getMysqlInfo());
	// to store mysql fields as cache
	private int id;
	private int cardID;
	private String username;
	private boolean gender;
	private int studentID;
	private String password;
	private boolean exist = false;
	private Book[] booklist;
	// 1 for canborrow ,2 for enough books,3 for overdue
	private int canborrow = 1;

	// can only be accessed by subclass Admin
	protected void setExist(boolean exist) {
		this.exist = exist;
	}

	protected ConnectionDB getDb() {
		return db;
	}

	// constructor
	public User(String cardID) {
		if (cardID.equals("")) {
			this.exist = false;
		} else {
			String query = "select * from user where cardID = " + cardID;
			HashMap<String, Object> map = ConnectionDB.GetthefirstHashMap(db.excuteQuery(query, null));
			if (map != null) {
				// read the infor form the database
				id = Integer.parseInt(map.get("Id").toString());
				this.cardID = Integer.parseInt(map.get("cardID").toString());
				username = (String) map.get("username");
				gender = ((Boolean) map.get("gender")).booleanValue();
				studentID = Integer.parseInt(map.get("studentID").toString());
				checkCanborrow();
				// read end
				// for initializing what books the user has
				// end
				if (map.get("password") != null) {
					password = (String) map.get("password");
				} else {
					password = "";
				}
				exist = true;
			} else {
				exist = false;
			}
		}

	}

	// constructor for extending & new user
	public User() {

	}

	public int getCanborrow() {
		return canborrow;
	}

	// refresh the book the user borrowed & get the status
	public void checkCanborrow() {
		booklist = Book.queryborrowed(id);
		if (booklist == null) {
			canborrow = 1;
			return;
		}
		if (booklist.length > 2) {
			canborrow = 2;
			return;
		}
		for (Book book : booklist) {
			Date now = new Date();
			if (book.getDuedate().before(now)) {
				canborrow = 3;
				return;
			}
		}

	}

	// save a new user
	public boolean saveNew() {
		if (exist == false) {
			// confirm if there is the same cardID
			String query = null;
			query = "select * from user where cardID = " + cardID;
			if (db.excuteQuery(query, null).size() > 0)
				return false;

			query = String.format(
					"insert into user(cardID,username,gender,studentID,password) values(%d,'%s',%b,%d,'%s')", cardID,
					username, gender, studentID, password);
			// System.out.println(query);
			return db.executeUpdate(query, null) == 1;
		} else {
			return false;
		}
	}

	// update user infor
	public boolean updateInfor() {
		if (exist == true) {
			String query = String.format(
					"update user set cardID = %d , username ='%s' , gender = %b,studentID = %d ,password = '%s' WHERE Id = %d ",
					cardID, username, gender, studentID, password, id);
			return db.executeUpdate(query, null) == 1;
		} else {
			return false;
		}
	}

	// get all information
	public static List<Object> getAll() {
		String query = "select * from user";
		List<Object> resultset = db.excuteQuery(query, null);
		return resultset;
	}

	public Book[] getBooklist() {
		return booklist;
	}

	public int getId() {
		return id;
	}

	protected void setId(int id) {
		this.id = id;
	}

	public int getCardID() {
		return cardID;
	}

	public void setCardID(int cardID) {
		this.cardID = cardID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	public void setPassword(String password) {
		// to ensure the length of password is legal
		if (username.length() > 16) {
			this.password = "password";
		} else {
			this.password = password;
		}
	}

	public boolean isExist() {
		return exist;
	}

	public boolean checkPassword(String password) {
		// password = parseStrToMd5L32(password);
		return this.password.equals(password);
	}

	public static String parseStrToMd5L32(String str) {
		String reStr = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] bytes = md5.digest(str.getBytes());
			StringBuffer stringBuffer = new StringBuffer();
			for (byte b : bytes) {
				int bt = b & 0xff;
				if (bt < 16) {
					stringBuffer.append(0);
				}
				stringBuffer.append(Integer.toHexString(bt));
			}
			reStr = stringBuffer.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return reStr;
	}
}
