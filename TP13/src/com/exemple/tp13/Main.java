package com.exemple.tp13;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		final String filePath = "employees.ser";

		List<Employee> staff = new ArrayList<>();
		staff.add(new Employee(1, "Ali", 3000.0, "pwdAli"));
		staff.add(new Employee(2, "Lina", 3500.5, "pwdLina"));
		staff.add(new Employee(3, "Amine", 2800.75, "pwdAmine"));

		try {
			SerializationUtil.serializeEmployees(filePath, staff);
			System.out.println("→ Sérialisation réussie dans " + filePath);
		} catch (IOException e) {
			System.err.println("Erreur de sérialisation : " + e.getMessage());
			return;
		}

		List<Employee> loaded;
		try {
			loaded = SerializationUtil.deserializeEmployees(filePath);
			System.out.println("→ Désérialisation réussie, objets restaurés :");
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("Erreur de désérialisation : " + e.getMessage());
			return;
		}

		for (Employee emp : loaded) {
			System.out.println("  " + emp);
		}

		System.out.println("\nNote : le champ 'password' est transient, il n'a pas été enregistré → null.");
	}
}
