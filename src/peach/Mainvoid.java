package peach;

import java.util.Scanner;

import frank.Admin;
import frank.User;
import arthur.Book;

//�������Firstlevel_1���õ�static void
public class Mainvoid {

	// ���߹���
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
			Mainentrance.ChoseArray[1][1] = input.nextInt();// ��ȡ�����ߵ�ѡ��
			if (Mainentrance.ChoseArray[1][1] == 0)
				break;
			// �����벻Ϊ1��2ʱһֱѭ��
			if (Mainentrance.ChoseArray[1][1] > 0 && Mainentrance.ChoseArray[1][1] < 3) {
				Function.printhr();
				switch (Mainentrance.ChoseArray[1][1]) {
				// ѡ��Ϊ1���½�����
				case 1:
					Function.addnewreader();
					break;
				// ѡ��Ϊ2����ʾ���ж���
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

	// ͼ�����
	public static void adminbook() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.println("   >>>[The system of Book]");
		Mainentrance.ChoseArray[1][2] = 0; // ��ʼ��ChoseArray
		do {
			System.out.printf("   >>>(1) Add new Book                (2) Edit book's information"
					+ "\n   >>>(3) Show all Book's information (0) Back\n");
			System.out.println("   >>>Please enter num 0-3 to chose: ");
			while (!input.hasNextInt()) {
				System.out.println("   >>>Error enter,please reinput.");
				input.nextLine();
			}
			Mainentrance.ChoseArray[1][2] = input.nextInt();// ��ȡ�����ߵ�ѡ��
			input.nextLine();
			// �����벻Ϊ1,2,3ʱһֱѭ��
			if (Mainentrance.ChoseArray[1][2] == 0)
				break;
			if (Mainentrance.ChoseArray[1][2] > 0 && Mainentrance.ChoseArray[1][2] < 4) {
				Function.printhr();
				switch (Mainentrance.ChoseArray[1][2]) {
				// ѡ��Ϊ1����������
				case 1:
					Function.addnewbook();
					break;
				// ѡ��Ϊ2���༭��
				case 2:
					Function.editbook();
					break;
				// ѡ��Ϊ3����ʾ������
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

	// �軹�����
	public static void adminBandR() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.println("   >>>[The system of Borrow&Return]");
		Mainentrance.ChoseArray[1][3] = 0; // ��ʼ��ChoseArray
		do {
			System.out.printf("   >>>(1) Borrow book (2) Return book \n" + "   >>>(3) Renewal     (0) Back   \n  ");
			System.out.println("   >>>Please enter num 0-3 to chose: ");
			while (!input.hasNextInt()) {
				System.out.println("   >>>Error enter,please reinput.");
				input.nextLine();
			}
			Mainentrance.ChoseArray[1][3] = input.nextInt();// ��ȡ�����ߵ�ѡ��
			input.nextLine();
			// �����벻Ϊ0,1,2,3ʱһֱѭ��
			if (Mainentrance.ChoseArray[1][3] == 0)
				break;
			if (Mainentrance.ChoseArray[1][3] > 0 && Mainentrance.ChoseArray[1][3] < 4) {
				// ����
				Function.printhr();
				switch (Mainentrance.ChoseArray[1][3]) {
				// ѡ��Ϊ1������
				case 1:
					User user = null;
					Book book = null;// �����飬���ߵĶ��󣬳�ʼֵΪ��
					do {
						System.out.println("      >>>Please the reader's ID: ");
						String cardID = input.nextLine();// ��ȡ������ID
						System.out.println("      >>>Please the ID of the book you want to borrow: ");
						while (!input.hasNextInt()) {
							System.out.println("      >>>Error enter,please reinput.");
							input.nextLine();
						}
						int bookid = input.nextInt();// ��ȡҪ�����id
						input.nextLine();

						user = new User(cardID);
						book = new Book(bookid);// ����idΪ����id�Ķ�������Ķ���
						if (user.isExist() && book.isExist()) {
							break;// ����Ͷ��߶���������ѭ��
						} else {
							System.out.println("readerID or bookID wrong.");
							// ��������ĳһ���������һֱѭ��
						}
					} while (true);
					// �ж��Ƿ���Խ�
					// getCanborrow()��1Ϊ�ɽ裬2Ϊ���������3Ϊ��������û��
					// ��Ϊ1������Խ��飬2��3����
					switch (user.getCanborrow()) {
					case 1:// �޸����״̬�����>>�ڹ�
						if (Book.borrowBook(user, book)) {
							System.out.println("      >>>Borrow successfully.");
						} else {
							System.out.println("      >>>Fail to borrow.");
						}
						break;
					case 2:// ����������
						System.out.println("      >>>You have borrowed enough book.");
						break;
					case 3:// ��������û��
						System.out.println("      >>>You have book overdue.");
						break;

					}
					break;

				// ����
				case 2:
					User user2 = null;
					Book book2 = null;// �����飬���ߵĶ��󣬳�ʼֵΪ��
					do {
						System.out.println("      >>>Please the reader's ID: ");
						String cardID = input.nextLine();// ��ȡ������ID
						System.out.println("      >>>Please the ID of the book the reader want to return: ");
						while (!input.hasNextInt()) {
							System.out.println("      >>>Error enter,please reinput.");
							input.nextLine();
						}
						int bookid = input.nextInt();// ��ȡҪ�������ID
						input.nextLine();

						user2 = new User(cardID);
						book2 = new Book(bookid);// ����idΪ������ͻ������id�Ķ���
						if (user2.isExist() && book2.isExist()) {
							// ����Ͷ��߶���������ѭ��
							break;
						} else {// ��������ĳһ���������һֱѭ��
							System.out.println("      >>>readerID or bookID wrong.");
						}
					} while (true);
					// getCanborrow() == 3��ʾ��������û��
					if (user2.getCanborrow() == 3) {
						System.out.printf("      >>>You need to pay %d yuan for overdue.\n", Book.exceedDue(user2));
						// ��ʾ���轻�������Ŀ
					}
					if (Book.returnBook(user2, book2)) {// �޸����״̬���л���
						System.out.println("      >>>Return successfully");
					} else {
						System.out.println("      >>>Fail to borrow.");
					}
					break;

				// ����
				case 3:
					User user3 = null;
					Book book3 = null;// �����飬���ߵĶ��󣬳�ʼֵΪ��
					do {
						System.out.println("      >>>Please the reader's cardID: ");
						String cardID = input.nextLine();// ��ȡ������ID
						System.out.println("      >>>Please the ID of the book the reader want to renewal: ");
						while (!input.hasNextInt()) {
							System.out.println("      >>>Error enter,please reinput.");
							input.nextLine();
						}
						int bookid = input.nextInt();// ��ȡҪ��������ID
						input.nextLine();

						user3 = new User(cardID);
						book3 = new Book(bookid);// ����idΪ������ͻ������id�Ķ���
						if (user3.isExist() && book3.isExist()) {
							// ����Ͷ��߶���������ѭ��
							break;
						} else {// ��������ĳһ���������һֱѭ��
							System.out.println("      >>>readerID or bookID wrong.");
						}
					} while (true);
					if (Book.renewal(user3, book3)) {// ����ɹ����黹��������15��
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
