import java.sql.*;

public class HealthDB {

	private Connection conn;
	private Statement stmt;

	public HealthDB(String connectStr, String dbUser, String dbPass) {
		try {
                        Class.forName("oracle.jdbc.OracleDriver");
                } catch (Exception e) {
                        System.out.println("Error importing JDBC OracleDriver. Is the jar file added to classpath?");
                        System.exit(-1);
                }
		conn = DriverManager.getConnection("jdbc:oracle:thin:@aloe.cs.arizona.edu:1521:oracle", dbUser, dbPass);
                stmt = conn.createStatement();
	}

	public ResultSet executeQuery(String query) {
		return stmt.executeQuery(query);
	}

	public void insertPatient(String name, String birthday, String type, int insurance) {
		try { 
			if(insurance == -1) {
				stmt.executeQuery("INSERT INTO jacobchambers.patient(name, birthday, type) values(\'" + name + "\', DATE \'" + birthday + "\', \'" + type + "\')");
			} else {
				stmt.executeQuery("INSERT INTO jacobchambers.patient(name, birthday, type) values(\'" + name + "\', DATE \'" + birthday + "\', \'" + type + "\', " + insurance + ")");
			} 
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertCareProvider(String name, String serviceType) {
		try {
			stmt.executeQuery("INSERT INTO jacobchambers.CareProvider(name, providedServiceType) values(" + name + ", " + serviceType + ")");
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertAppointment(int patientID, int careProviderID, String beginTime, String endTime, int isWalkin) {
                try {
                        stmt.executeQuery("INSERT INTO jacobchambers.Appointment(patientID, careProviderID, begin, end, isWalkin) values(" + patientID + ", " + careProviderID + ", timestamp \'" + beginTime + "\', timestamp " + endTime + "\', " + isWalkin + ")");
                } catch(SQLException e) {
                        e.printStackTrace();
                }		
	}

	public void updatePatient(int patientID, String attribute, Object value) {
		try {
			if(value instanceof String) {
				value = "\'" + value + "\'"
			}
			stmt.executeQuery("UPDATE jacobchambers.Patient SET " + attribute + " = " + value + " WHERE patientID = " + patientID);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public void update


	protected void finalize() {
		stmt.close();
		conn.close();
	}
}
