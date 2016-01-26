package frank;

import java.util.*;

public class Test {
	public static void main(String args[]) {
		Configuration config = new Configuration();
		ConnectionDB db = new ConnectionDB(config.getMysqlInfo());
		// @query
		String query = "select * from user";
		// Object resultset = db.executeQuerySingle(query, null);
		List<Object> resultset = db.excuteQuery(query, null);
		@SuppressWarnings("unchecked")
		HashMap<String, Object> map = (HashMap<String, Object>) resultset.get(0);//num = id -1;
		//System.out.println(((Boolean)map.get("gender")).booleanValue());
		System.out.println(map.get("password")==null);
		System.out.println(map);
		
		// @insert
		query = "insert into user(cardID,username,gender,studentID) values(11510491,\"÷‹÷«≤©\",0,11510491)";
		//System.out.println(db.executeUpdate(query, null));
		
		// @update 
		query = "update user set gender=0 where ID=2";
		//System.out.println(db.executeUpdate(query, null));
		
		// @search
		query = "select * from book where name LIKE '%One%'";
		resultset = db.excuteQuery(query, null);
		System.out.println(resultset.size());
		System.out.println(resultset.get(0));

	}
}