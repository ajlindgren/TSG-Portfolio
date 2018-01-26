/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster.ui;

import com.sg.classroster.dto.Student;
import java.util.List;

/**
 *
 * @author Alex
 */
public class ClassRosterView {
    
    UserIO io = new UserIOConsoleImpl();
    
    public int printMenuAndGetSelection(){
        io.print("Main Menu");
        io.print("1. List Student IDs");
        io.print("2. Create New Student");
	io.print("3. View a Student");
	io.print("4. Remove a Student");
	io.print("5. Exit");

	return io.readInt("Please select from the above choices.", 1, 5);
    }
    
    //this method prompts the user for Student ID, First Name, Last Name, and Cohort,
    //gathers this information, creates a new Student object, and returns it to the caller.
    public Student getNewStudentInfo(){
        String studentId = io.readString("Please enter Student ID");
        String firstName = io.readString("Please enter First Name");
        String lastName = io.readString("Please enter Last Name");
        String cohort = io.readString("Please enter Cohort");
        Student currentStudent = new Student(studentId);
        currentStudent.setFirstName(firstName);
        currentStudent.setLastName(lastName);
        currentStudent.setCohort(cohort);
        return currentStudent;
    }
    
    public void displayCreateStudentBanner(){
        io.print("=== Create Student ===");
    }
    
    public void displayCreateSuccessBanner(){
        io.readString("Student successfully created. Please hit enter to continue.");
    }
    
    /*
    a method that takes a list of Student objects as a parameter 
    and displays the information for each Student to the screen.  
    After the list has been displayed, the method will pause and wait for the user to hit the Enter key.
    */
    public void displayStudentList(List<Student> studentList) {
        for (Student currentStudent : studentList) {
            io.print(currentStudent.getStudentId() + ": "
                    + currentStudent.getFirstName() + " "
                    + currentStudent.getLastName());
        }
        io.readString("Please hit enter to continue.");
    }
    
    /*
    a method that shows the Display All Students banner
    */
    public void displayDisplayAllBanner() {
        io.print("=== Display All Students ===");
    }
    
    /*
    a method that shows the Display Single Student banner
    */
    public void displayDIsplayStudentBanner(){
        io.print("=== Display Student ===");
    }
    
    /*
    asks the user for the ID of the student they wish to display
    */
    public String getStudentIdChoice(){
        return io.readString("Please enter the Student ID.");
    }
    
    /*
    displays a student's information to the user
    */
    public void displayStudent(Student student){
        if (student != null){
            io.print(student.getStudentId());
            io.print(student.getFirstName() + " " + student.getLastName());
            io.print(student.getCohort());
            io.print("");
        } else {
            io.print("No such student.");
        }
        io.readString("Please hit enter to continue.");
    }
}
