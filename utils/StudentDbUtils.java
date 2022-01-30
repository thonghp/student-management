package com.example.utils;

import com.example.model.Student;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDbUtils {
    private DataSource dataSource;

    public StudentDbUtils(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Student> getStudents() throws Exception {
        List<Student> studentList = new ArrayList<>();
        String sql = "select * from student order by last_name";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ppstm = conn.prepareStatement(sql)) {
            try (ResultSet rs = ppstm.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    String email = rs.getString("email");
                    Student student = new Student(id, firstName, lastName, email);
                    studentList.add(student);
                }
            }
        }
        return studentList;
    }

    public void addStudent(Student student) throws Exception {
        String sql = "insert into student " + "(first_name, last_name, email) " + "value(?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ppstm = conn.prepareStatement(sql)) {
            ppstm.setString(1, student.getFirstName());
            ppstm.setString(2, student.getLastName());
            ppstm.setString(3, student.getEmail());

            ppstm.execute();
        }
    }

    public Student loadStudent(String studentId) throws Exception {
        String sql = "select * from student where id = ?";
        Student student;
        int id = Integer.parseInt(studentId);
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ppstm = conn.prepareStatement(sql)) {
            ppstm.setInt(1, id);

            try (ResultSet rs = ppstm.executeQuery()) {
                if (rs.next()) {
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    String email = rs.getString("email");

                    student = new Student(id, firstName, lastName, email);
                } else {
                    throw new Exception("could not find student id: " + studentId);
                }
            }
        }

        return student;
    }

    public void updateStudent(Student student) throws Exception {
        String sql = "update student " + "set first_name = ?, last_name = ?, email = ? " + "where id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ppstm = conn.prepareStatement(sql)) {
            ppstm.setString(1, student.getFirstName());
            ppstm.setString(2, student.getLastName());
            ppstm.setString(3, student.getEmail());
            ppstm.setInt(4, student.getId());

            ppstm.execute();
        }
    }

    public void deleteStudent(String studentId) throws Exception {
        String sql = "delete from student where id = ?";
        int id = Integer.parseInt(studentId);

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ppstm = conn.prepareStatement(sql)) {
            ppstm.setInt(1, id);

            ppstm.execute();
        }
    }
}
