package it.techgap.challenge.java.senior;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * This test uses an in-memory <a href="http://www.h2database.com/">H2 database</a>
 */
public class Challenge04SSQL {

    /**
     * TODO: Implement this
     */
    public static class ManagerWithCount {
    	
    	public String cf;
    	public int count;
    	
        public ManagerWithCount(String cf, int count) {
        	this.cf = cf;
        	this.count = count;
        }
    }

    /**
     * Gets the CF of all employees aged more than minAge
     *
     * @param connection A JDBC Connection
     * @param minAge     Minimum age to consider
     * @return A set of employees's CF
     * @throws SQLException If anything goes wrong
     */
    public static Collection<String> selectCFOfEmployeesAgedMoreThan(Connection connection, int minAge) throws SQLException {
    	Statement s = null;
    	String query = "select CF from EMPLOYEE where AGE > " + Integer.toString(minAge);
    	ArrayList<String> a = new ArrayList<String>();
    	try {
    		s = connection.createStatement();
    		ResultSet results = s.executeQuery(query);
    		while (results.next()) {
    			a.add(results.getString("CF"));
    		}
    	} catch (SQLException se) {
    		System.out.println(se.getMessage());
    	}
        return a;
    }

    /**
     * Gets the CF of all employees whose manager has a salary of more than minSalary euros
     *
     * @param connection A JDBC Connection
     * @param minSalary  Minimum salary to consider
     * @return A set of employees' CF
     * @throws SQLException If anything goes wrong
     */
    public static Collection<String> selectCFOfEmployeesWhoseManagerHasASalaryofMoreThan(
            Connection connection,
            int minSalary
    ) throws SQLException {
    	Statement s = null;
    	String query = "select A.CF from EMPLOYEE A " +
    					"inner join EMPLOYEE B on A.MANAGER_CF = B.CF " +
    					"where B.SALARY > " + Integer.toString(minSalary);
    	ArrayList<String> a = new ArrayList<String>();
    	try {
    		s = connection.createStatement();
    		ResultSet results = s.executeQuery(query);
    		while (results.next()) {
    			a.add(results.getString("CF"));
    		}
    	} catch (SQLException se) {
    		System.out.println(se.getMessage());
    	}
        return a;
    }

    /**
     * Retrieves all the managers with their respective subordinates count if and only if the manager has more than
     * minSubordinates subordinates
     *
     * @param connection      A JDBC Connection
     * @param minSubordinates Minimum number of subordinates to consider
     * @return A well-built collection of {@link ManagerWithCount}
     * @throws SQLException If anything goes wrong
     */
    public static Collection<ManagerWithCount> getManagersWithMoreThanXSubordinates(
            Connection connection,
            int minSubordinates
    ) throws SQLException {
    	Statement s = null;
    	String query = "select B.CF, count(*) as SUB from EMPLOYEE A " +
    					"inner join EMPLOYEE B on A.MANAGER_CF = B.CF " +
    					"group by B.CF";
    	ArrayList<ManagerWithCount> a = new ArrayList<ManagerWithCount>();
    	try {
    		s = connection.createStatement();
    		ResultSet results = s.executeQuery(query);
    		while (results.next()) {
    			int count = results.getInt("SUB");
    			String boss = results.getString("CF");
    			if (count > minSubordinates) {
    				a.add(new ManagerWithCount(boss, count));
    			}
    		}
    	} catch (SQLException se) {
    		System.out.println(se.getMessage());
    	}
    	final Challenge04SSQL.ManagerWithCount[] expected = {
                new Challenge04SSQL.ManagerWithCount("XXXXX00X00X001M", 5)};
//    	if (a.size() > 0) {
//    		if (a.get(0).cf.equals(expected[0].cf)) System.out.println("cf same");
//    		if (a.get(0).count == (expected[0].count)) System.out.println("value same");
//    	}
        return a;
    }

}
