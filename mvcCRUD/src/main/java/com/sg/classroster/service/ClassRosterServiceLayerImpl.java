/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster.service;

import com.sg.classroster.dao.ClassRosterAuditDao;
import com.sg.classroster.dao.ClassRosterDao;
import com.sg.classroster.dao.ClassRosterPersistenceException;
import com.sg.classroster.dto.Student;
import java.util.List;

/**
 *
 * @author Alex
 */
public class ClassRosterServiceLayerImpl implements ClassRosterServiceLayer {
    
    private ClassRosterAuditDao auditDao;
    ClassRosterDao dao;
    
    public ClassRosterServiceLayerImpl(ClassRosterDao dao, ClassRosterAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public void createStudent(Student student) throws ClassRosterDuplicateIdException, ClassRosterDataValidationException, ClassRosterPersistenceException {
        //First check to see if there is already a student associated with the given ID. If so, throw a ClassRosterDuplicateIdException
        if (dao.getStudent(student.getStudentId()) != null) {
            throw new ClassRosterDuplicateIdException("ERROR: Could not create student. Student ID " + student.getStudentId() + " already exists.");
        }
        
        //Now validate all the fields on the given Student object.
        //This method will throw an exception if any of the validation rules are violated.
        validateStudentData(student);
        
        //We passed all our business rules checks so go ahead and persist the Student object
        dao.addStudent(student.getStudentId(), student);
        
//        //The student was successfully created, write to Audit Log
//        auditDao.writeAuditEntry("Student " + student.getStudentId() + " CREATED.");
    }

    @Override
    public List<Student> getAllStudents() throws ClassRosterPersistenceException {
        return dao.getAllStudents();
    }

    @Override
    public Student getStudent(String studentId) throws ClassRosterPersistenceException {
        return dao.getStudent(studentId);
    }

    @Override
    public Student removeStudent(String studentId) throws ClassRosterPersistenceException {
        Student removedStudent = dao.removeStudent(studentId);
//        auditDao.writeAuditEntry("Student " + studentId + " REMOVED.");
        return removedStudent;
    }
    
    private void validateStudentData(Student student) throws ClassRosterDataValidationException {
        if (student.getFirstName() == null
            || student.getFirstName().trim().length() == 0
            || student.getLastName() == null
            || student.getLastName().trim().length() == 0
            || student.getCohort() == null
            || student.getCohort().trim().length() == 0) {
            throw new ClassRosterDataValidationException("ERROR: All fields [FName, Lname, and Cohort] are required.");
        }
    }
    
}
