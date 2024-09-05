package project;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import project.entity.Projects;
import project.exception.DbException;
import project.service.ProjectService;


@SuppressWarnings("unused")
public class Project {
private Scanner scanner = new Scanner(System.in);
private ProjectService projectService = new ProjectService();

// @formatter:off
private List<String> operations = List.of(
		"1) Add a project"
		);
// @formatter:on

public static void main(String[] args) {
		new Project().displayMenu();
	}

private void displayMenu() {
	boolean done = false;
	while(!done) {
		try {
			int operation = getOperation();
			
			switch(operation) {
		case -1:
			done = exitMenu();
			break;
			
		case 1:
			addProject();
			break;
			
		default:
			System.out.println("\n" + operation + " is not valid. Try again.");
			break;
		}
	} catch(Exception e) {
		System.out.println("\nError: " + e.toString() + " Try again.");
	}
	}
}

private void addProject() {
	String projectName = getStringInput("Enter the project name");
	BigDecimal estimatedHours = getDecimalInput("Enter the estimated hours");
	BigDecimal actualHours = getDecimalInput("Enter the acutal hours");
	Integer difficulty = getIntInput("Enter the project difficulty (1-5)");
	String notes = getStringInput("Enter the project notes");
	
	
	
	Projects project = new Projects();
	
	project.setProjectName(projectName);
	project.setEstimatedHours(estimatedHours);
	project.setActualHours(actualHours);
	project.setDifficulty(difficulty);
	project.setNotes(notes);
	
	Projects dbProject = projectService.addProject(project);
	System.out.println("\nYou added this project:\n" + dbProject);
}

private BigDecimal getDecimalInput(String prompt) {
	String input = getStringInput(prompt);
	
	if(Objects.isNull(input)) {
		return null;
	} 
	try {
		return new BigDecimal(input).setScale(2);
	}
	catch(NumberFormatException e) {
		throw new DbException(input + " is not a valid decimal number.");
	}
}


private boolean exitMenu() {
	System.out.println("\nExiting the menu.");
	return true;
}

private int getOperation() {
	printOperations();
	Integer op = getIntInput("\nEnter a menu selection (press Enter to quit)");
	
	return Objects.isNull(op) ? -1 : op;
}

private void printOperations() {
	System.out.println();
	System.out.println("Here's what you can do:");
	
	operations.forEach(op -> System.out.println("    " + op));
}

private Integer getIntInput(String prompt) {
	String input = getStringInput(prompt);
	
	if(Objects.isNull(input)) {
		return null;
	} 
	try {
		return Integer.parseInt(input);
	}
	catch(NumberFormatException e) {
		throw new DbException(input + " is not a valid number.");
	}
}

private String getStringInput(String prompt) {
	System.out.print(prompt + ": ");
	String line = scanner.nextLine();
	
	return line.isBlank() ? null : line.trim();
}

}
