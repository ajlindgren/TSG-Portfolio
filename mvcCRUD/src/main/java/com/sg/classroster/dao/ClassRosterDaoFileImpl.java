/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster.dao;

import com.sg.classroster.dto.Student;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Alex
 */
public class ClassRosterDaoFileImpl implements ClassRosterDao {
    
    private Map<String, Student> students = new HashMap<>();
    
    @Override
    public Student addStudent(String studentId, Student student) {
        Student newStudent = students.put(studentId, student);
        return newStudent;
    }

    /*
    This code gets all of the Student objects out of the students map as a collection by calling the values() method.  
    We pass that returned collection into the constructor for a new ArrayList.  
    One of the constructors for ArrayList take a collection as a parameter, which effectively allows us 
    to convert the collection of Student objects into an ArrayList of Student objects that we can return from the method.  
    Note that our method specifies that we’ll return a List<Student> but we create and return an ArrayList<Student>.  
    This is perfectly fine because ArrayList implements the List interface so it can be treated as a List.
    */
    @Override
    public List<Student> getAllStudents() {
        return new ArrayList<Student>(students.values());
    }

    @Override
    public Student getStudent(String studentId) {
        return students.get(studentId);
    }

    @Override
    public Student removeStudent(String studentId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
