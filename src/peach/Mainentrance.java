package peach;

import java.util.Scanner;

public class Mainentrance {
	// 创建用于记录每次选择的数组 ChoseArray;
	static int[][] ChoseArray = new int[3][4];

	// 运行主界面的主函数

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to Library Manage System of SUSTC\n=========================================");

		while (true) {
			System.out.printf("(1) Admin login (2) Reader login \n");
			System.out.println("Please Enter 1 or 2 to chose：");
			// 从键盘获取用户选择的数字
			while (!input.hasNextInt()) {
				System.out.println("Error enter,please reinput.");
				input.nextLine();
			}
			ChoseArray[0][0] = input.nextInt();

			input.nextLine();
			// 当输入不为1或2时，一直循环
			if (ChoseArray[0][0] == 2 || ChoseArray[0][0] == 1) {
				break;
			} else {
				System.out.println("Error enter.Please input the right number!");
			}
		}
		if (ChoseArray[0][0] == 1) {// 输入为1，进行管理员登录
			Function.Adminlogin();// 进入管理员登录的void

		} else if (ChoseArray[0][0] == 2) {// 输入为2，进行读者登录
			Function.Readerlogin();// 进入读者登录的void

		}
	}

	public static void Firstlevel_1() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.println(">>>Admin login successfully ");
		Function.printhr();
		Mainentrance.ChoseArray[1][0] = 1;// 初始化ChoseArray
		do {
			// 输入正确跳出循环，进行操作。否则一直循环提示输入1-4
			System.out.println(">>>(1) Manage Reader's information "
					+ "(2) Manage Book's information \n>>>(3) Manage borrow and return    (4) Change password\n"
					+ ">>>(0) Exit");
			System.out.println(">>>Please Enter num 0-4 to chose: ");
			Mainentrance.ChoseArray[1][0] = input.nextInt();// 获取操作者的选择
			// 选择为0，退出程序
			if (ChoseArray[1][0] == 0)
				break;
			if (Mainentrance.ChoseArray[1][0] > 0 && Mainentrance.ChoseArray[1][0] < 5) {
				Function.printhr();
				switch (Mainentrance.ChoseArray[1][0]) {
				// 选择为1，进入读者信息管理void
				case 1:
					Mainvoid.adminreader();
					break;
				// 选择为2，进入图书信息管理void
				case 2:
					Mainvoid.adminbook();
					break;
				// 选择为3，进入借还书管理void
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
				// 关键字查询
				case 1:
					Function.queryBook();
					break;
				// 查询已借阅信息
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
