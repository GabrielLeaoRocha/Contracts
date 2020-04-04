package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entities.Departament;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkerLevel;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner (System.in);
		SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat ("MM/yyyy");
		
		System.out.print("Enter department's name: ");
		String depart = sc.nextLine();
		System.out.println("Enter worker data:");
		System.out.print("Name: ");
		String name = sc.nextLine();
		System.out.print("Level: ");
		String level = sc.nextLine();
		System.out.print("Base salary: ");
		double baseSalary = sc.nextDouble();
		Worker worker = new Worker(name, WorkerLevel.valueOf(level), baseSalary, new Departament(depart));
		
		System.out.println();
		System.out.print("How many contracts to this worker? ");
		int n = sc.nextInt();
		
		for(int x=1 ; x<=n ; x++) {
			System.out.println();
			System.out.println("Enter contract #"+ x +" data: ");
			System.out.print("Date (DD/MM/YYYY): ");
			Date date = sdf.parse(sc.next());
			System.out.print("Value per hour: ");
			double valuePerHour = sc.nextDouble();
			System.out.print("Duration (hours): ");
			int duration = sc.nextInt();
			
			worker.addContract(new HourContract(date, valuePerHour, duration));
		}
		
		System.out.println();
		System.out.print("Enter month and year to calculate income (MM/YYYY): ");
		Date date = sdf2.parse(sc.next());
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int date_year = cal.get(Calendar.YEAR);
		int date_month = 1 + cal.get(Calendar.MONTH);
		
		System.out.println();
		System.out.println(worker);
		System.out.println("Income for "+ sdf2.format(date) + ": " + String.format("%.2f",worker.income(date_year, date_month)));
		
		sc.close();
	}

}
