/**
 * @classname = Test0.java
 * @description 
 * @author = Frank
 * @time = 2016年1月3日上午1:42:06
 * @version = 1.0
 */
package arthur;

import frank.User;

public class Test0 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Book book1 = new Book(2);
		User user1 = new User("11510484");
		// book1.setName("Harry Potty");
		// book1.setBookshelf("B2");
		// book1.setPrice(200);
		// book1.setKindid(3);
		//
		// System.out.println(book1.saveNew());
		System.out.println(Book.borrowBook(user1, book1));
	}

}
