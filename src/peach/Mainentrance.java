package peach;

import java.util.Scanner;

public class Mainentrance {
	// �������ڼ�¼ÿ��ѡ������� ChoseArray;
	static int[][] ChoseArray = new int[3][4];

	// �����������������

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to Library Manage System of SUSTC\n=========================================");

		while (true) {
			System.out.printf("(1) Admin login (2) Reader login \n");
			System.out.println("Please Enter 1 or 2 to chose��");
			// �Ӽ��̻�ȡ�û�ѡ�������
			while (!input.hasNextInt()) {
				System.out.println("Error enter,please reinput.");
				input.nextLine();
			}
			ChoseArray[0][0] = input.nextInt();

			input.nextLine();
			// �����벻Ϊ1��2ʱ��һֱѭ��
			if (ChoseArray[0][0] == 2 || ChoseArray[0][0] == 1) {
				break;
			} else {
				System.out.println("Error enter.Please input the right number!");
			}
		}
		if (ChoseArray[0][0] == 1) {// ����Ϊ1�����й���Ա��¼
			Function.Adminlogin();// �������Ա��¼��void

		} else if (ChoseArray[0][0] == 2) {// ����Ϊ2�����ж��ߵ�¼
			Function.Readerlogin();// ������ߵ�¼��void

		}
	}

	public static void Firstlevel_1() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.println(">>>Admin login successfully ");
		Function.printhr();
		Mainentrance.ChoseArray[1][0] = 1;// ��ʼ��ChoseArray
		do {
			// ������ȷ����ѭ�������в���������һֱѭ����ʾ����1-4
			System.out.println(">>>(1) Manage Reader's information "
					+ "(2) Manage Book's information \n>>>(3) Manage borrow and return    (4) Change password\n"
					+ ">>>(0) Exit");
			System.out.println(">>>Please Enter num 0-4 to chose: ");
			Mainentrance.ChoseArray[1][0] = input.nextInt();// ��ȡ�����ߵ�ѡ��
			// ѡ��Ϊ0���˳�����
			if (ChoseArray[1][0] == 0)
				break;
			if (Mainentrance.ChoseArray[1][0] > 0 && Mainentrance.ChoseArray[1][0] < 5) {
				Function.printhr();
				switch (Mainentrance.ChoseArray[1][0]) {
				// ѡ��Ϊ1�����������Ϣ����void
				case 1:
					Mainvoid.adminreader();
					break;
				// ѡ��Ϊ2������ͼ����Ϣ����void
				case 2:
					Mainvoid.adminbook();
					break;
				// ѡ��Ϊ3������軹�����void
				case 3:
					Mainvoid.adminBandR();
					break;
				case 4:
					Mainvoid.adminpassword();
				}
			} else {
				System.out.println(">>>Error enter.Please input the right number!");
			}
		} while (true);
		Function.printhr();
		System.out.println(">>>Exit successfully");

	}

	public static void Firstlevel_2() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.println(">>>Reader login successfully ");
		Function.printhr();
		do {
			System.out.printf(">>>(1) Search Book (2) Query my borrowed book\n" + ">>>(0) Exit\n");
			System.out.println(">>>Please enter num 1-3 to chose: ");
			Mainentrance.ChoseArray[2][0] = input.nextInt();
			if (Mainentrance.ChoseArray[2][0] == 0)
				break;
			if (Mainentrance.ChoseArray[2][0] > 0 || Mainentrance.ChoseArray[2][0] < 3) {
				Function.printhr();
				switch (Mainentrance.ChoseArray[2][0]) {
				// �ؼ��ֲ�ѯ
				case 1:
					Function.queryBook();
					break;
				// ��ѯ�ѽ�����Ϣ
				case 2:
					Function.queryinfo();
					break;
				}
			} else {
				System.out.println(">>>Error enter.Please input the right number!");
			}
			Function.printhr();
		} while (true);
		Function.printhr();
		System.out.println(">>>Exit successfully");

	}
}
