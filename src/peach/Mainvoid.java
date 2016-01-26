package peach;

import java.util.Scanner;

import frank.Admin;
import frank.User;
import arthur.Book;

//本类包含Firstlevel_1调用的static void
public class Mainvoid {

	// 读者管理
	public static void adminreader() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.println("   >>>[The system of Reader]");
		do {
			System.out.printf("   >>>(1) Add new reader (2) Show all readers' information  \n" + "   >>>(0) Back\n");
			System.out.println("   >>>Please enter num 0-2 to chose: ");
			while (!input.hasNextInt()) {
				System.out.println("   >>>Error enter,please reinput.");
				input.nextLine();
			}
			Mainentrance.ChoseArray[1][1] = input.nextInt();// 获取操作者的选择
			if (Mainentrance.ChoseArray[1][1] == 0)
				break;
			// 当输入不为1或2时一直循环
			if (Mainentrance.ChoseArray[1][1] > 0 && Mainentrance.ChoseArray[1][1] < 3) {
				Function.printhr();
				switch (Mainentrance.ChoseArray[1][1]) {
				// 选择为1，新建读者
				case 1:
					Function.addnewreader();
					break;
				// 选择为2，显示所有读者
				case 2:
					Function.showallreader();
					break;
				}
			} else {
				System.out.println("   >>>Error enter.Please input the right number!");
			}
			Function.printhr();
		} while (true);

	}

	// 图书管理
	public static void adminbook() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.println("   >>>[The system of Book]");
		Mainentrance.ChoseArray[1][2] = 0; // 初始化ChoseArray
		do {
			System.out.printf("   >>>(1) Add new Book                (2) Edit book's information"
					+ "\n   >>>(3) Show all Book's information (0) Back\n");
			System.out.println("   >>>Please enter num 0-3 to chose: ");
			while (!input.hasNextInt()) {
				System.out.println("   >>>Error enter,please reinput.");
				input.nextLine();
			}
			Mainentrance.ChoseArray[1][2] = input.nextInt();// 获取操作者的选择
			input.nextLine();
			// 当输入不为1,2,3时一直循环
			if (Mainentrance.ChoseArray[1][2] == 0)
				break;
			if (Mainentrance.ChoseArray[1][2] > 0 && Mainentrance.ChoseArray[1][2] < 4) {
				Function.printhr();
				switch (Mainentrance.ChoseArray[1][2]) {
				// 选择为1，加入新书
				case 1:
					Function.addnewbook();
					break;
				// 选择为2，编辑书
				case 2:
					Function.editbook();
					break;
				// 选择为3，显示所有书
				case 3:
					Function.showallBook();
					break;
				}
			} else {
				System.out.println("   >>>Error enter.Please input the right number!");
			}
			Function.printhr();
		} while (true);
	}

	// 借还书管理
	public static void adminBandR() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.println("   >>>[The system of Borrow&Return]");
		Mainentrance.ChoseArray[1][3] = 0; // 初始化ChoseArray
		do {
			System.out.printf("   >>>(1) Borrow book (2) Return book \n" + "   >>>(3) Renewal     (0) Back   \n  ");
			System.out.println("   >>>Please enter num 0-3 to chose: ");
			while (!input.hasNextInt()) {
				System.out.println("   >>>Error enter,please reinput.");
				input.nextLine();
			}
			Mainentrance.ChoseArray[1][3] = input.nextInt();// 获取操作者的选择
			input.nextLine();
			// 当输入不为0,1,2,3时一直循环
			if (Mainentrance.ChoseArray[1][3] == 0)
				break;
			if (Mainentrance.ChoseArray[1][3] > 0 && Mainentrance.ChoseArray[1][3] < 4) {
				// 借书
				Function.printhr();
				switch (Mainentrance.ChoseArray[1][3]) {
				// 选择为1，借书
				case 1:
					User user = null;
					Book book = null;// 建立书，读者的对象，初始值为空
					do {
						System.out.println("      >>>Please the reader's ID: ");
						String cardID = input.nextLine();// 获取借书者ID
						System.out.println("      >>>Please the ID of the book you want to borrow: ");
						while (!input.hasNextInt()) {
							System.out.println("      >>>Error enter,please reinput.");
							input.nextLine();
						}
						int bookid = input.nextInt();// 获取要借的书id
						input.nextLine();

						user = new User(cardID);
						book = new Book(bookid);// 建立id为输入id的读者与书的对象
						if (user.isExist() && book.isExist()) {
							break;// 当书和读者都存在跳出循环
						} else {
							System.out.println("readerID or bookID wrong.");
							// 当书或读者某一个输入错误，一直循环
						}
					} while (true);
					// 判断是否可以借
					// getCanborrow()是1为可借，2为借书量超额，3为有书逾期没还
					// 若为1，则可以借书，2，3不行
					switch (user.getCanborrow()) {
					case 1:// 修改书的状态：借出>>在馆
						if (Book.borrowBook(user, book)) {
							System.out.println("      >>>Borrow successfully.");
						} else {
							System.out.println("      >>>Fail to borrow.");
						}
						break;
					case 2:// 借书量超额
						System.out.println("      >>>You have borrowed enough book.");
						break;
					case 3:// 有书逾期没还
						System.out.println("      >>>You have book overdue.");
						break;

					}
					break;

				// 还书
				case 2:
					User user2 = null;
					Book book2 = null;// 建立书，读者的对象，初始值为空
					do {
						System.out.println("      >>>Please the reader's ID: ");
						String cardID = input.nextLine();// 获取还书者ID
						System.out.println("      >>>Please the ID of the book the reader want to return: ");
						while (!input.hasNextInt()) {
							System.out.println("      >>>Error enter,please reinput.");
							input.nextLine();
						}
						int bookid = input.nextInt();// 获取要还的书的ID
						input.nextLine();

						user2 = new User(cardID);
						book2 = new Book(bookid);// 建立id为还书这和还的书的id的对象
						if (user2.isExist() && book2.isExist()) {
							// 当书和读者都存在跳出循环
							break;
						} else {// 当书或读者某一个输入错误，一直循环
							System.out.println("      >>>readerID or bookID wrong.");
						}
					} while (true);
					// getCanborrow() == 3表示有书逾期没还
					if (user2.getCanborrow() == 3) {
						System.out.printf("      >>>You need to pay %d yuan for overdue.\n", Book.exceedDue(user2));
						// 提示所需交罚款的数目
					}
					if (Book.returnBook(user2, book2)) {// 修改书的状态进行还书
						System.out.println("      >>>Return successfully");
					} else {
						System.out.println("      >>>Fail to borrow.");
					}
					break;

				// 续借
				case 3:
					User user3 = null;
					Book book3 = null;// 建立书，读者的对象，初始值为空
					do {
						System.out.println("      >>>Please the reader's cardID: ");
						String cardID = input.nextLine();// 获取续借者ID
						System.out.println("      >>>Please the ID of the book the reader want to renewal: ");
						while (!input.hasNextInt()) {
							System.out.println("      >>>Error enter,please reinput.");
							input.nextLine();
						}
						int bookid = input.nextInt();// 获取要续借的书的ID
						input.nextLine();

						user3 = new User(cardID);
						book3 = new Book(bookid);// 建立id为还书这和还的书的id的对象
						if (user3.isExist() && book3.isExist()) {
							// 当书和读者都存在跳出循环
							break;
						} else {// 当书或读者某一个输入错误，一直循环
							System.out.println("      >>>readerID or bookID wrong.");
						}
					} while (true);
					if (Book.renewal(user3, book3)) {// 续借成功，归还日期增加15天
						System.out.println("      >>>Renew successfully.");
					} else {
						System.out.println("      >>>Fail to renew.");

					}
					break;
				}
			} else {
				System.out.println("   >>>Error enter.Please input the right number!");
			}
			Function.printhr();
		} while (true);

	}

	/**
	 * change the password
	 */
	public static void adminpassword() {
		// TODO Auto-generated method stub
		System.out.println("   >>>[Change the admin password]");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		String password, password_;
		do {
			System.out.println("   >>>Please enter new password: ");
			password = input.nextLine();
			System.out.println("   >>>Please enter new password again: ");
			password_ = input.nextLine();
			if (password.equals(password_)) {
				break;
			} else {
				System.out.println("   >>>The passwords don't match!");
			}
		} while (true);
		Admin admin = new Admin();
		admin.setPassword(password_);
		if (admin.updateInfor()) {
			System.out.println("   >>>The password is changed successfully.");
		} else {
			System.out.println("   >>>Filed to change the password.");
		}
	}
}
