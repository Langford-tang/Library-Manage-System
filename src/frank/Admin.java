package frank;

import java.util.HashMap;

public class Admin extends User {
	public Admin() {
		super();
		// admin account ID is 1 defaultly
		String query = "select * from user where ID = 1 ";
		HashMap<String, Object> map = ConnectionDB.GetthefirstHashMap(super.getDb().excuteQuery(query, null));
		if (map.size() > 0) {
			// read the data of admin
			this.setId(Integer.parseInt(map.get("Id").toString()));
			this.setCardID(Integer.parseInt(map.get("cardID").toString()));
			this.setUsername((String) map.get("username"));
			this.setGender(((Boolean) map.get("gender")).booleanValue());
			this.setStudentID(Integer.parseInt(map.get("studentID").toString()));
			if (map.get("password") != null) {
				this.setPassword((String) map.get("password"));
			} else {
				this.setPassword("");
			}
			this.setExist(true);
			// System.out.print(this.getPassword());
		} else {
			this.setExist(false);
		}
	}

	// check if the adminname is right
	public boolean checkadminname(String admin) {
		return this.getUsername().equals(admin);
	}//end check

}
