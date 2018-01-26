/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classmodeling;

/**
 *
 * @author Alex
 */
public class School2 {
    
   private int enrollment;
   private int teachersEmployed;
   private String[] courseCatalog;
   private String mascot;
   private String[] studentClubs;
   private Student[] studentRoster;
   
   public void enrollStudent(Student student) {
       
   }
   
   public void unenrollStudent (Student student) {
       
   }

    public int getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(int enrollment) {
        this.enrollment = enrollment;
    }

    public int getTeachersEmployed() {
        return teachersEmployed;
    }

    public void setTeachersEmployed(int teachersEmployed) {
        this.teachersEmployed = teachersEmployed;
    }

    public String[] getCourseCatalog() {
        return courseCatalog;
    }

    public void setCourseCatalog(String[] courseCatalog) {
        this.courseCatalog = courseCatalog;
    }

    public String getMascot() {
        return mascot;
    }

    public void setMascot(String mascot) {
        this.mascot = mascot;
    }

    public String[] getStudentClubs() {
        return studentClubs;
    }

    public void setStudentClubs(String[] studentClubs) {
        this.studentClubs = studentClubs;
    }

    public Student[] getStudentRoster() {
        return studentRoster;
    }

    public void setStudentRoster(Student[] studentRoster) {
        this.studentRoster = studentRoster;
    }
    
}
