package it.techgap.challenge.java.senior;

import java.util.ArrayList;

import it.techgap.challenge.java.senior.beans.Employee;

public class Challenge03SimpleProgram {

    public static class Technician implements Employee {
    	
    	private String name;
    	private double startingSalary;
    	
        public Technician(String name, double startingSalary) {
        	this.name = name;
        	this.startingSalary = startingSalary;
        }

		@Override
		public double getSalary() {
			return startingSalary;
		}

		@Override
		public String getName() {
			return name;
		}
    }
    
    public static class Salesman implements Employee {
    	
    	private String name;
    	private double startingSalary;
    	private double monthlySales;
    	private static final double COMMISSION_PERCENTAGE = .1;
    	
        public Salesman(String name, double startingSalary, double monthlySales) {
        	this.name = name;
        	this.startingSalary = startingSalary;
        	this.monthlySales = monthlySales;
        }

		@Override
		public double getSalary() {
			return startingSalary+(COMMISSION_PERCENTAGE*monthlySales);
		}

		@Override
		public String getName() {
			return name;
		}
    }
    
    public static class Manager implements Employee {
    	
    	private String name;
    	private double startingSalary;
    	private ArrayList<Employee> underlings;
    	private double bonus;
    	private static final double BONUS_PERCENTAGE = .005;
    	
        public Manager(String name, double startingSalary, Employee... directSubordinates) {
        	this.name = name;
        	this.startingSalary = startingSalary;
        	underlings = new ArrayList<Employee>();
        	for (Employee e : directSubordinates) {
        		underlings.add(e);
        	}
        }

		@Override
		public double getSalary() {
        	bonus = 0;
			calculateBonus(this);
			return startingSalary+(BONUS_PERCENTAGE*bonus);
		}
		
		private void calculateBonus(Manager m) {
			for (int i = 0; i < m.underlings.size(); i++) {
				Employee e = m.underlings.get(i); 
				bonus += e.getSalary();
				if (e instanceof Manager) calculateBonus((Manager)e);
			}
		}

		@Override
		public String getName() {
			return name;
		}
    }
    /**
     * A Technician only gets his base salary each month
     *
     * @param name           The name of the {@link Employee}
     * @param startingSalary The starting salary of the {@link Employee}
     * @return A well-built {@link Employee}
     */
    public static Employee getTechnician(String name, int startingSalary) {
        return new Technician(name, startingSalary);
    }

    /**
     * A Salesman gets his base salary plus 10% of his monthly sales
     *
     * @param name           The name of the {@link Employee}
     * @param startingSalary The starting salary of the {@link Employee}
     * @param monthlySales   The monthly sales of that {@link Employee}
     * @return A well-built {@link Employee}
     */
    public static Employee getSalesman(String name, int startingSalary, int monthlySales) {
        return new Salesman(name, startingSalary, monthlySales);
    }

    /**
     * A Manager gets his base salary plus 0,5% of the total salary of all managed members below him (at all levels)
     * E.g. If A manages B that manages C, C's salary will count towards both A and B salaries
     *
     * @param name               The name of the {@link Employee}
     * @param startingSalary     The starting salary of the {@link Employee}
     * @param directSubordinates The direct subordinates of the {@link Employee}. <br />
     *                           In the given example, this is B for the manager A
     * @return A well-built {@link Employee}
     */
    public static Employee getManager(String name, int startingSalary, Employee... directSubordinates) {
    	Employee emp = new Manager(name, startingSalary, directSubordinates);
        return new Manager(name, startingSalary, directSubordinates);
    }
}
