package com.example.controller;

import com.example.model.Student;
import com.example.utils.StudentDbUtils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "StudentController", urlPatterns = "/StudentController")
public class StudentController extends HttpServlet {
    private DataSource dataSource;
    private StudentDbUtils studentDbUtils;

    // vòng đời servlet được gọi 1 lần khi có request đầu tiên đến servlet dùng để khởi tạo servlet
    @Override
    public void init() throws ServletException {
        super.init();
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/web_student_tracker");
            studentDbUtils = new StudentDbUtils(dataSource);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String readParameter = request.getParameter("command");

            if (readParameter == null) {
                readParameter = "LIST";
            }

            switch (readParameter) {
                case "LIST":
                    getStudentList(request, response);
                    break;
                case "ADD":
                    addStudents(request, response);
                    break;
                case "LOAD":
                    loadStudents(request, response);
                    break;
                case "UPDATE":
                    updateStudents(request, response);
                    break;
                case "DELETE":
                    deleteStudents(request, response);
                    break;
                default:
                    getStudentList(request, response);
            }

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void updateStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        int id = Integer.parseInt(request.getParameter("studentId"));

        Student student = new Student(id, firstName, lastName, email);
        studentDbUtils.updateStudent(student);
        getStudentList(request, response);
    }

    private void addStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // read parameter
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        Student student = new Student(firstName, lastName, email);
        // add data to database
        studentDbUtils.addStudent(student);
        getStudentList(request, response); // gửi lại đến trang chính student list
    }

    private void loadStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String studentId = request.getParameter("studentId");

        // load data from database
        Student student = studentDbUtils.loadStudent(studentId);

        request.setAttribute("THE_STUDENT", student);
        request.getRequestDispatcher("update.jsp").forward(request, response);
    }

    private void deleteStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String studentId = request.getParameter("studentId");

        studentDbUtils.deleteStudent(studentId);
        getStudentList(request, response);
    }

    private void getStudentList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Student> studentList = studentDbUtils.getStudents();
        request.setAttribute("STUDENT_LIST", studentList);
        request.getRequestDispatcher("/list-student.jsp").forward(request, response);
    }
}
