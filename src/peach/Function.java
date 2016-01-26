package peach;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import arthur.Book;
import frank.Admin;
import frank.User;

//�������Mainvoid���õ�����static void
public class Function {

	// ����Ա��¼
	public static void Adminlogin() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		Admin admin = new Admin();// �����µ�admin�������ڵ�������ĺ���

		String adminname = null, password = null;// ��������Ա�û������룬��ʼֵΪ��
		do {
			System.out.println(">>>Please enter adminname:");
			adminname = input.nextLine();// ��ȡ����Ĺ���Ա�û���
			System.out.println(">>>Please enter admin Passord:");
			// get password method_01
			// java.io.Console console = System.console();
			// password = new String(console.readPassword());
			// get password method_02
			password = input.nextLine();
			// Ȼ���ж�Id Password �Ƿ���ȷ
			if (admin.checkPassword(password) && admin.checkadminname(adminname)) {
				break;// ���û�������ȫ��ȷʱ����ѭ��

			} else {// ����һֱѭ��
				System.out.println(">>>Id or Passord wrong.");
			}
		} while (true);
		// �û���������ȷ���������Ա����ϵͳ
		Mainentrance.Firstlevel_1();
	}

	static String cardID;// ȫ�ֱ�������Ϊ�����cardID����һ��Ϊ���cardID

	// ���ߵ�¼
	public static void Readerlogin() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		do {
			System.out.println("Please enter cardID:");
			cardID = input.nextLine();// ��ȡ����Ķ����˺�
			System.out.println("Please enter passord:");
			String password = input.nextLine();// ��ȡ����Ķ�������
			User user = new User(cardID);
			// Ȼ���ж�Id Password �Ƿ���ȷ
			if (user.isExist() && user.checkPassword(password)) {
				break;// ���û�������ȫ��ȷʱ����ѭ��
			} else {// ����һֱѭ��
				System.out.println("Id or Passord wrong.");
			}
		} while (true);
		// �û�������ȫ����ȷ���������ϵͳ
		Mainentrance.Firstlevel_2();

	}

	// ����¶���
	public static void addnewreader() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		User newReader = new User();// �����µ�User���󣬽��з�������
		// ����ÿһ�����붼��������set��Reader
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
			// ��saveNew()����ֵΪtrue�������ɹ�
			System.out.println("   >>>Create new reader successfully.");
		} else {
			System.out.println("   >>>Failed to create new reader.");
		}

	}

	// ��ʾ���ж�����Ϣ
	public static void showallreader() {
		// ��ʾ���ж�����Ϣ��
		List<Object> resultset = User.getAll();// ���ݿ����Ϊall����
		// ��ͷ
		System.out.printf("%s\t%s\t%15s\t%s\t%s\t\n", "ID", "cardID", "username", "gender", "studentID");
		for (int i = 0; i < resultset.size(); i++) {
			// ѭ�����ÿ�����ߵ�����
			@SuppressWarnings("unchecked")
			HashMap<String, Object> map = (HashMap<String, Object>) resultset.get(i);// num=id-1;
			System.out.printf("%s\t", map.get("Id"));
			System.out.printf("%s\t", map.get("cardID"));
			System.out.printf("%s\t", map.get("username"));
			System.out.printf("%s\t", (boolean) (map.get("gender")) ? "female" : "male");
			// 1����Ů��0������
			System.out.printf("%s\t\n", map.get("studentID"));
		}
	}

	// �ؼ��ֲ�ѯ��
	public static void queryBook() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.println("   >>>Please enter the keyword: ");
		String keyword = input.nextLine();// ��ȡ�ؼ���
		// ��ͷ
		System.out.printf("%s\t%s\t%35%s\t%s\t%s\t%s\t\n", "ID", "Kindid", "Name", "Bookshelf", "Status", "Due_date");
		for (int i = 0; i < Book.searchname(keyword).size(); i++) {
			// ѭ��������Ϲؼ��ֵ���ĸ�������
			@SuppressWarnings({ "unchecked" })
			HashMap<String, Object> map = (HashMap<String, Object>) Book.searchname(keyword).get(i);
			// ����Ϊ���ϵĵ�i��
			System.out.printf("%s\t", map.get("Id"));
			System.out.printf("%s\t", map.get("kindID"));
			System.out.printf("%35s\t", map.get("name"));
			System.out.printf("%s\t\t", map.get("bookshelf"));
			System.out.printf("%s\t", Integer.parseInt(map.get("status").toString()) == 0 ? "InLibrary" : "Borrowed");
			System.out.printf("%s\t\n", map.get("due_date"));
		}

	}

	// ��ѯ�Լ�ͼ��ݵ���Ϣ
	public static void queryinfo() {

		User user = new User(cardID);// �����µ�User����
		Book.queryborrowed(user.getId());// ����queryborrowed������ʹ���߽��ÿ����
		// ���һ������Book[]
		Book[] booklist = user.getBooklist();
		if (booklist == null) {
			System.out.println("No book borrowed");
		} else {
			System.out.printf("%s\t%s\t%35s\t%s\t%s\t%s\t\n", "ID", "Kindid", "Name", "Bookshelf", "Out_date",
					"Due_date");
			for (Book book : booklist) {
				// �н����ʱ��ѭ�����ÿ���������
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
		Book book = new Book();// �����¶���
		// ����ÿһ��������ֵset��book
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
		if (book.saveNew()) {// save�ɹ�������ֵΪtrue
			System.out.println("   >>>Create new book successfully.");
		} else {// δ�ɹ�������ֵΪfalse
			System.out.println("   >>>Failed to create new book.");
		}

	}

	public static void showallBook() {
		// ��ͷ
		System.out.printf("%s\t%s\t%35s\t%s\t%s\t%s\t%s\t%s\t%s\t\n", "ID", "Kindid", "Name", "Price",
				"Collection_date", "Bookshelf", "Status", "Out_date", "Due_date");
		for (int i = 0; i < Book.getAll().size(); i++) {
			// ѭ����ʾ������ĸ�������
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
			int id = input.nextInt();// ���book��id
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
		// �����𲽽�������set��book
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
		// ��������Ը���
		if (book.update()) {
			System.out.println("      >>>Update successfully.");

		} else {
			System.out.println("      >>>Failed to update.");

		}
	}

}
