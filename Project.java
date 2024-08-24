package project;

import project.dao.DbConnection;

@SuppressWarnings("unused")
public class Project {

	public static void main(String[] args) {
		DbConnection.getConnection();
	}

}
