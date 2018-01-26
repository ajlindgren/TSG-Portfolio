/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster.controller;

import com.sg.classroster.dao.ClassRosterDao;
import com.sg.classroster.dao.ClassRosterDaoFileImpl;
import com.sg.classroster.dto.Student;
import com.sg.classroster.ui.ClassRosterView;
import com.sg.classroster.ui.UserIO;
import com.sg.classroster.ui.UserIOConsoleImpl;
import java.util.List;

/**
 * Our strategy is to create a method that displays the menu, 
 * gets the user's menu choice, 
 * and then calls a method that performs an action based on the user's menu choice.
 * @author Alex
 */
public class ClassRosterController {
    
    ClassRosterDao dao = new ClassRosterDaoFileImpl();
    ClassRosterView view = new ClassRosterView();
    private UserIO io = new UserIOConsoleImpl();
    
    public void run(){
        boolean keepGoing = true;
        int menuSelection = 0;
        while (keepGoing){
           
            menuSelection = getMenuSelection();
            
            switch (menuSelection) {
                case 1:
                    listStudents();
                    break;
                case 2:
                    createStudent();
                    break;
                case 3:
                    viewStudent();
                    break;
                case 4:
                    io.print("REMOVE STUDENT");
                    break;
                case 5:
                    keepGoing = false;
                    break;
                default:
                    io.print("UNKNOWN COMMAND");
            }
            
        }
        io.print("GOOD BYE");
    } 
    
    private int getMenuSelection(){
        return view.printMenuAndGetSelection();
    }
    
    private void createStudent(){
       view.displayCreateStudentBanner();
       Student newStudent = view.getNewStudentInfo();
       dao.addStudent(newStudent.getStudentId(), newStudent);
       view.displayCreateSuccessBanner();
    }
    
    private void listStudents() {
        view.displayDisplayAllBanner();
        List<Student> studentList = dao.getAllStudents();
        view.displayStudentList(studentList);
    }
    
    private void viewStudent(){
        view.displayDIsplayStudentBanner();
        String studentId = view.getStudentIdChoice();
        Student student = dao.getStudent(studentId);
        view.displayStudent(student);
    }
}
