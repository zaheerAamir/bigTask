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
  private int year;
  private int sem;
  private int courseId;

  public CourseModel() {}

  public CourseModel(String id, String title, int courseCode, String description, int year, int sem,
      int courseId) {
    this.id = id;
    this.title = title;
    this.courseCode = courseCode;
    this.description = description;
    this.year = year;
    this.sem = sem;
    this.courseId = courseId;
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


  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public int getSem() {
    return sem;
  }

  public void setSem(int sem) {
    this.sem = sem;
  }

  public int getCourseId() {
    return courseId;
  }

  public void setCourseId(int courseId) {
    this.courseId = courseId;
  }

  public CourseModel(int year, int sem, int courseId) {
    this.year = year;
    this.sem = sem;
    this.courseId = courseId;
  }


}
