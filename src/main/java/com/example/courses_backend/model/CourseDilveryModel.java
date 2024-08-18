package com.example.courses_backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.google.gson.Gson;

/**
 * CourseDilveryModel
 */
@Document(collection = "courseDilvery")
public class CourseDilveryModel {

  @Id
  private String id;
  private int sem;
  private int year;
  private int courseId;

  public CourseDilveryModel(int courseId, int sem, int year) {
    this.sem = sem;
    this.year = year;
  }

  public int getSem() {
    return sem;
  }

  public void setSem(int sem) {
    this.sem = sem;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public int getCourseId() {
    return courseId;
  }

  public void setCourseId(int courseId) {
    this.courseId = courseId;
  }

  @Override
  public String toString() {
    return new Gson().toJson(this);
  }

  public CourseDilveryModel() {}

}
