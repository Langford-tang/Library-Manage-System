package arthur;

import java.util.*;
import java.text.SimpleDateFormat;

import frank.Configuration;
import frank.ConnectionDB;
import frank.User;

public class Book {
	// initialize the mysql connection configuration ,needed only once before
	// changing mysql configration
	private static Configuration config = new Configuration();
	private static ConnectionDB db = new ConnectionDB(config.getMysqlInfo());
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private int id = 0;
	private int kindid;
	private String name;
	private double price;
	private boolean borrowStatus = false;// false means available
	private int borrowid = 0;
	private Date collectiondate;
	private Date outdate;
	private Date duedate;
	private String bookshelf;
	private boolean exist = false;
	private boolean renewaled = false;

	// constructor
	public Book(int id) {
		if (id == 0) {
			this.exist = false;
		} else {
			// query from mysql database
			String query = "select * from book where Id=" + id;
			HashMap<String, Object> map = ConnectionDB.GetthefirstHashMap(db.excuteQuery(query, null));

			if (map != null) {
				// store the data
				this.id = id;
				this.kindid = Integer.parseInt(map.get("kindID").toString());
				this.name = (String) map.get("name");
				this.price = Double.parseDouble(map.get("price").toString());
				this.borrowid = Integer.parseInt(map.get("status").toString());
				this.borrowStatus = borrowid == 0 ? false : true;
				this.bookshelf = (String) map.get("bookshelf");
				this.renewaled = Boolean.parseBoolean(map.get("renewaled").toString());
				// store date infor
				// this.collectiondate = (Date) map.get("collection_date");
				// this.borrowdate = (Date) map.get("out_date");
				this.duedate = (Date) map.get("due_date");
				this.outdate = (Date) map.get("out_date");
				this.collectiondate = (Date) map.get("collection_date");
				this.exist = true;
			}
		}
	}

	public Book() {

	}

	// get all the book information
	public static List<Object> getAll() {
		String query = "select * from book";
		List<Object> resultset = db.excuteQuery(query, null);
		return resultset;
	}

	// search for book
	public static List<Object> searchname(String keyword) {
		String query = String.format("select * from book WHERE name LIKE '%%%s%%'", keyword);
		List<Object> resultset = db.excuteQuery(query, null);
		return resultset;
	}

	// get the books certain user borrowed
	public static Book[] queryborrowed(int user_ID) {
		String query = "select Id FROM book WHERE status =" + user_ID;
		List<Object> resultset = db.excuteQuery(query, null);
		if (resultset.size() > 0) {
			Book[] book = new Book[resultset.size()];
			int i = 0;
			for (Object object : resultset) {
				@SuppressWarnings("unchecked")
				HashMap<String, Object> map = (HashMap<String, Object>) object;
				book[i] = new Book(Integer.parseInt(map.get("Id").toString()));
				i++;
			}
			return book;
		} else {
			return null;
		}
	}

	// save a new book
	public boolean saveNew() {
		if (name.equals("") || exist || kindid < 0)
			return false;
		bookshelf = bookshelf.equals("") ? "A1" : bookshelf;
		Date now = new Date();
		String query = String.format(
				"insert into book(name,price,collection_date,bookshelf,status,kindID,out_date,due_date) values('%s',%.2f,'%s','%s',0,%d,NOW(),NOW())",
				name, price, df.format(now), bookshelf, kindid);
		if (db.executeUpdate(query, null) == 1) {
			exist = true;
			return true;
		} else {
			return false;
		}

	}// end saveNew

	// edit the book information
	public boolean update() {
		if (exist == false)
			return false;
		String query = String.format(
				"UPDATE book SET name = '%s',kindID=%d ,bookshelf ='%s',collection_date = '%s',price = %.2f,status = %d WHERE Id=%d",
				name, kindid, bookshelf, df.format(collectiondate), price, borrowid, id);
		return db.executeUpdate(query, null) == 1 ? true : false;
	}

	// borrow a book sattic
	public static boolean borrowBook(User user, Book book) {
		Date now = new Date();
		// check existence
		if (user.isExist() == false || book.exist == false || book.borrowStatus == true)
			return false;
		// check if the user can still borrow books
		if (user.getCanborrow() == 1) {
			// calculate the duedate
			Calendar cal = Calendar.getInstance();
			cal.setTime(now);
			// one month
			cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 30);
			Date due = cal.getTime();
			cal = null;
			// end calcualte
			String query = null;
			// 更改状态/借书时间/应还书时间
			query = String.format("UPDATE book SET status=%d, out_date='%s',due_date='%s'  where Id=%d", user.getId(),
					df.format(now), df.format(due), book.id);
			if (db.executeUpdate(query, null) != 1)
				// change the status
				user.checkCanborrow();
			book.borrowStatus = true;
			return true;
		} else {
			return false;
		}
	}

	// return the book
	public static boolean returnBook(User user, Book book) {
		// confirm the access
		if (user.isExist() == false || book.exist == false || book.borrowStatus == false
				|| book.borrowid != user.getId())
			return false;
		// modify the status
		String query = String.format("update book set status=0, renewaled = 0 where Id=%d", book.id);
		if (db.executeUpdate(query, null) == 1) {
			// change the status
			user.checkCanborrow();
			book.borrowStatus = false;
			return true;
		} else {
			return false;
		}
	}

	// call this before return and borrow
	public static int exceedDue(User user) {
		// confirm the access
		if (user.isExist() == false || user.getBooklist() == null)
			return 0;
		Date now = new Date();
		if (user.getCanborrow() != 3)
			return 0;
		int delta = 0;
		for (Book book : user.getBooklist()) {
			delta += daysBetween(book.duedate, now);
		}
		return delta;
	}

	// calculate the days between two Date Object
	public static int daysBetween(Date smdate, Date bdate) {

		try {
			smdate = df.parse(df.format(smdate));
		} catch (java.text.ParseException e) {

			e.printStackTrace();
		}
		try {
			bdate = df.parse(df.format(bdate));
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	// renewal the book,15days
	public static boolean renewal(User user, Book book) {
		// confirm the access
		if (user.isExist() == false || book.exist == false || book.borrowStatus == false
				|| book.borrowid != user.getId() || book.renewaled == true)
			return false;
		String query = String.format(
				"UPDATE book SET due_date = DATE_ADD(due_date,INTERVAL 15 DAY),renewaled = 1 WHERE Id = %d", book.id);
		if (db.executeUpdate(query, null) == 1) {
			user.checkCanborrow();
			return true;
		} else {
			return false;
		}

	}

	public Date getDuedate() {
		return duedate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isBorrowStatus() {
		return borrowStatus;
	}

	// public void setBorrowStatus(boolean borrowStatus) {
	// this.borrowStatus = borrowStatus;
	// }

	public int getKindid() {
		return kindid;
	}

	public void setKindid(int kindid) {
		this.kindid = kindid;
	}

	public String getBookshelf() {
		return bookshelf;
	}

	public void setBookshelf(String bookshelf) {
		this.bookshelf = bookshelf;
	}

	public int getId() {
		return id;
	}

	public boolean isExist() {
		return exist;
	}

	public int getBorrowid() {
		return borrowid;
	}

	public void setBorrowid(int borrowid) {
		this.borrowid = borrowid;
	}

	public static SimpleDateFormat getDf() {
		return df;
	}

	public Date getCollectiondate() {
		return collectiondate;
	}

	public void setCollectiondate(Date collectiondate) {
		this.collectiondate = collectiondate;
	}

	public Date getOutdate() {
		return outdate;
	}

}
