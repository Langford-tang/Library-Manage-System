package peach;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import arthur.Book;
import frank.Admin;
import frank.User;

//本类包含Mainvoid调用的其中static void
public class Function {

	// 管理员登录
	public static void Adminlogin() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		Admin admin = new Admin();// 创建新的admin对象，用于调用里面的函数

		String adminname = null, password = null;// 创建管理员用户名密码，初始值为空
		do {
			System.out.println(">>>Please enter adminname:");
			adminname = input.nextLine();// 获取输入的管理员用户名
			System.out.println(">>>Please enter admin Passord:");
			// get password method_01
			// java.io.Console console = System.console();
			// password = new String(console.readPassword());
			// get password method_02
			password = input.nextLine();
			// 然后判断Id Password 是否正确
			if (admin.checkPassword(password) && admin.checkadminname(adminname)) {
				break;// 当用户名密码全正确时跳出循环

			} else {// 否则一直循环
				System.out.println(">>>Id or Passord wrong.");
			}
		} while (true);
		// 用户名密码正确，进入管理员管理系统
		Mainentrance.Firstlevel_1();
	}

	static String cardID;// 全局变量，作为输入的cardID，不一定为真的cardID

	// 读者登录
	public static void Readerlogin() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		do {
			System.out.println("Please enter cardID:");
			cardID = input.nextLine();// 获取输入的读者账号
			System.out.println("Please enter passord:");
			String password = input.nextLine();// 获取输入的读者密码
			User user = new User(cardID);
			// 然后判断Id Password 是否正确
			if (user.isExist() && user.checkPassword(password)) {
				break;// 当用户名密码全正确时跳出循环
			} else {// 否则一直循环
				System.out.println("Id or Passord wrong.");
			}
		} while (true);
		// 用户名密码全部正确，进入读者系统
		Mainentrance.Firstlevel_2();

	}

	// 添加新读者
	public static void addnewreader() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		User newReader = new User();// 创建新的User对象，进行方法调用
		// 以下每一步输入都将输入结果set进Reader
		System.out.println("      >>>If you want to cancel,please just give null to username or password.");
		System.out.println("      >>>Please enter new reader's cardID:");
		while (!input.hasNextInt()) {
			System.out.println("      >>>Error enter,please reinput.");
			input.nextLine();
		}
		newReader.setCardID(input.nextInt());
		input.nextLine();
		System.out.println("      >>>Please enter new reader's username:");
		newReader.setUsername(input.nextLine());
		if (newReader.getUsername().equals("")) {
			System.out.println("      >>>Operation canceled.");
			return;
		}
		System.out.println("      >>>Please enter new reader's gender ( 0 for male and 1 for female ):");
		while (!input.hasNextInt()) {
			System.out.println("      >>>Error enter,please reinput.");
			input.nextLine();
		}
		newReader.setGender(input.nextInt() == 1);
		input.nextLine();
		System.out.println("      >>>Please enter new reader's studentID:");
		while (!input.hasNextInt()) {
			System.out.println("      >>>Error enter,please reinput.");
			input.nextLine();
		}
		newReader.setStudentID(input.nextInt());
		input.nextLine();
		System.out.println("      >>>Please enter new reader's password:");
		String password = input.nextLine();
		if (password.equals("")) {
			System.out.println("      >>>Operation canceled.");
			return;
		}
		newReader.setPassword(password);
		input = null;
		if (newReader.saveNew()) {
			// 当saveNew()返回值为true，创建成功
			System.out.println("   >>>Create new reader successfully.");
		} else {
			System.out.println("   >>>Failed to create new reader.");
		}

	}

	// 显示所有读者信息
	public static void showallreader() {
		// 显示所有读者信息。
		List<Object> resultset = User.getAll();// 数据库对象为all读者
		// 表头
		System.out.printf("%s\t%s\t%15s\t%s\t%s\t\n", "ID", "cardID", "username", "gender", "studentID");
		for (int i = 0; i < resultset.size(); i++) {
			// 循环输出每个读者的属性
			@SuppressWarnings("unchecked")
			HashMap<String, Object> map = (HashMap<String, Object>) resultset.get(i);// num=id-1;
			System.out.printf("%s\t", map.get("Id"));
			System.out.printf("%s\t", map.get("cardID"));
			System.out.printf("%s\t", map.get("username"));
			System.out.printf("%s\t", (boolean) (map.get("gender")) ? "female" : "male");
			// 1代表女，0代表男
			System.out.printf("%s\t\n", map.get("studentID"));
		}
	}

	// 关键字查询书
	public static void queryBook() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.println("   >>>Please enter the keyword: ");
		String keyword = input.nextLine();// 获取关键字
		// 表头
		System.out.printf("%s\t%s\t%35%s\t%s\t%s\t%s\t\n", "ID", "Kindid", "Name", "Bookshelf", "Status", "Due_date");
		for (int i = 0; i < Book.searchname(keyword).size(); i++) {
			// 循环输出符合关键字的书的各项属性
			@SuppressWarnings({ "unchecked" })
			HashMap<String, Object> map = (HashMap<String, Object>) Book.searchname(keyword).get(i);
			// 对象为符合的第i个
			System.out.printf("%s\t", map.get("Id"));
			System.out.printf("%s\t", map.get("kindID"));
			System.out.printf("%35s\t", map.get("name"));
			System.out.printf("%s\t\t", map.get("bookshelf"));
			System.out.printf("%s\t", Integer.parseInt(map.get("status").toString()) == 0 ? "InLibrary" : "Borrowed");
			System.out.printf("%s\t\n", map.get("due_date"));
		}

	}

	// 查询自己图书馆的信息
	public static void queryinfo() {

		User user = new User(cardID);// 创建新的User对象
		Book.queryborrowed(user.getId());// 运行queryborrowed方法，使读者借的每本书
		// 组成一个数组Book[]
		Book[] booklist = user.getBooklist();
		if (booklist == null) {
			System.out.println("No book borrowed");
		} else {
			System.out.printf("%s\t%s\t%35s\t%s\t%s\t%s\t\n", "ID", "Kindid", "Name", "Bookshelf", "Out_date",
					"Due_date");
			for (Book book : booklist) {
				// 有借的书时，循环输出每个书的属性
				System.out.printf("%s\t", book.getId());
				System.out.printf("%s\t", book.getKindid());
				System.out.printf("%35s\t", book.getName());
				System.out.printf("%s\t\t", book.getBookshelf());
				System.out.printf("%s\t", book.getOutdate());
				System.out.printf("%s\t\n", book.getDuedate());

			}
		}

	}

	// add a new book
	public static void addnewbook() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		Book book = new Book();// 创建新对象
		// 以下每一步将输入值set进book
		System.out.println("      >>>If you want to cancel,please just give null to bookname.");
		System.out.println("      >>>Please enter new book's name: ");
		book.setName(input.nextLine());
		if (book.getName().equals("")) {
			System.out.println("      >>>Operation canceled.");
			return;
		}
		System.out.println("      >>>Please enter new book's price: ");
		book.setPrice(input.nextDouble());
		input.nextLine();
		System.out.println("      >>>Please enter new book's bookshelf: ");
		book.setBookshelf(input.nextLine());
		System.out.println("      >>>Please enter new book's kindID: ");
		while (!input.hasNextInt()) {
			System.out.println("      >>>Error enter,please reinput.");
			input.nextLine();
		}
		book.setKindid(input.nextInt());
		input = null;
		if (book.saveNew()) {// save成功，返回值为true
			System.out.println("   >>>Create new book successfully.");
		} else {// 未成功，返回值为false
			System.out.println("   >>>Failed to create new book.");
		}

	}

	public static void showallBook() {
		// 表头
		System.out.printf("%s\t%s\t%35s\t%s\t%s\t%s\t%s\t%s\t%s\t\n", "ID", "Kindid", "Name", "Price",
				"Collection_date", "Bookshelf", "Status", "Out_date", "Due_date");
		for (int i = 0; i < Book.getAll().size(); i++) {
			// 循环显示所有书的各项属性
			@SuppressWarnings({ "unchecked" })
			HashMap<String, Object> map = (HashMap<String, Object>) Book.getAll().get(i);
			System.out.printf("%s\t", map.get("Id"));
			System.out.printf("%d\t", map.get("kindID"));
			System.out.printf("%35s\t", map.get("name"));
			System.out.printf("%s\t", map.get("price"));
			System.out.printf("%s\t", map.get("collection_date"));
			System.out.printf("%s\t\t", map.get("bookshelf"));
			System.out.printf("%s\t", map.get("status"));
			System.out.printf("%s\t", map.get("out_date"));
			System.out.printf("%s\t\n", map.get("due_date"));
		}
	}

	public static void printhr() {
		System.out.printf(
				"---------------------------------------------------------------------------------------------------------------------------------\n");
	}

	public static void editbook() {

		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		Book book = null;
		do {
			System.out.println("      >>>Please enter book's id: ");
			while (!input.hasNextInt()) {
				System.out.println("      >>>Error enter,please reinput.");
				input.nextLine();
			}
			int id = input.nextInt();// 获得book的id
			input.nextLine();
			book = new Book(id);
			if (book.isExist())
				break;
			else
				System.out.println("      >>>The book is not found,please reinput.");
		} while (true);

		System.out.printf("The data of the book now is following:\n%s\t%s\t%35s\t%s\t%s\t%s\t%s\t\n", "ID", "Kindid",
				"Name", "Price", "Collection_date", "Bookshelf", "Status");
		System.out.printf("%d\t", book.getId());
		System.out.printf("%d\t", book.getKindid());
		System.out.printf("%35s\t", book.getName());
		System.out.printf("%s\t", book.getPrice());
		System.out.printf("%s\t", book.getCollectiondate());
		System.out.printf("%s\t\t", book.getBookshelf());
		System.out.printf("%s\t\n", book.getBorrowid());
		// 以下逐步将新属性set进book
		System.out.println("      >>>If you want to cancel,please just give null to bookname.");
		System.out.println("      >>>Please enter new book's kindID: ");
		while (!input.hasNextInt()) {
			System.out.println("      >>>Error enter,please reinput.");
			input.nextLine();
		}
		book.setKindid(input.nextInt());
		input.nextLine();
		System.out.println("      >>>Please enter new book's name: ");
		book.setName(input.nextLine());
		if (book.getName().equals("")) {
			System.out.println("      >>>Operation canceled.");
			return;
		}
		System.out.println("      >>>Please enter new book's price: ");
		book.setPrice(input.nextDouble());
		input.nextLine();
		System.out.println("      >>>Please enter new book's collection_date: ");
		try {
			book.setCollectiondate(Book.getDf().parse(input.nextLine()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("      >>>Error formating date!");
		}
		System.out.println("      >>>Please enter new book's bookshelf: ");
		book.setBookshelf(input.nextLine());
		System.out.println("      >>>Please enter new book's status: ");
		while (!input.hasNextInt()) {
			System.out.println("      >>>Error enter,please reinput.");
			input.nextLine();
		}
		book.setBorrowid(input.nextInt());
		input.nextLine();
		// 将书的属性更新
		if (book.update()) {
			System.out.println("      >>>Update successfully.");

		} else {
			System.out.println("      >>>Failed to update.");

		}
	}

}
