package com.example.courses_backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.google.gson.Gson;

/**
 * CourseModel
 */
@Document(collection = "courses")
public class CourseModel {

  @Id
  private String id;
  private String title;
  private int courseCode;
  private String description;
  private int courseId;

  public CourseModel() {}

  public CourseModel(String id, String title, int courseCode, String description, int year, int sem,
      int courseId) {
    this.id = id;
    this.title = title;
    this.courseCode = courseCode;
    this.description = description;
  }

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

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return new Gson().toJson(this);
  }


  public int getCourseId() {
    return courseId;
  }

  public void setCourseId(int courseId) {
    this.courseId = courseId;
  }


}
