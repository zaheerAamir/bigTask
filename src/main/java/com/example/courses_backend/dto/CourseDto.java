package com.example.courses_backend.dto;

import java.io.Serializable;

/**
 * CourseDto
 */
public class CourseDto {

  public static class createCourseDto implements Serializable {

    private String title;
    private int courseCode;
    private String description;

    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public int getCourseCode() {
      return courseCode;
    }

    public void setCourseCode(int courseCode) {
      this.courseCode = courseCode;
    }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }

  }

}
