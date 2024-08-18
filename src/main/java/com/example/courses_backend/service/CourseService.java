package com.example.courses_backend.service;

import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.courses_backend.model.CourseModel;
import com.example.courses_backend.repository.CourseRepository;

/**
 * CourseService
 */
@Service
public class CourseService {

  @Autowired
  private final CourseRepository courseRepository;

  public CourseService(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  public List<CourseModel> getCoursesService() {
    return courseRepository.findAll();
  }

  public CourseModel createCourseService(CourseModel course) {

    course.setCourseId(generateID());

    validateCourse(course.getTitle(), "Title");
    validateCourse(course.getDescription(), "Description");
    validateCourseInt(course.getCourseId(), "CourseID");
    validateCourseInt(course.getCourseCode(), "CourseCode");
    return courseRepository.save(course);
  }

  public void validateCourse(String course, String propName) {
    if (course == null || course.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "Course " + propName + " Cannot be empty!");
    }
    if (course.contains("restricted")) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "Course " + propName + " contains restricted words!");
    }
    if (propName == "Title" && !course.matches("^[a-zA-Z ]+$")) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "Course " + propName + " should be a string type!");
    }
    if (propName == "Description" && !course.matches("^[a-zA-Z ]+$")) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "Course " + propName + " should be a string type!");
    }
  }

  public void validateCourseInt(int course, String propName) {
    if (course <= 0) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "Course " + propName + " cannot be 0 or less than 0");
    }
    if (propName == "Sem" && course >= 10) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sem cannot be greater that 9!");
    }
    if (propName == "Sem" && String.valueOf(course).length() > 1) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Value of Sem!");
    }
    if (propName == "Year" && String.valueOf(course).length() > 4) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Value of Year!");
    }
    if (propName == "CourseCode" && courseRepository.existsByCourseCode(course)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "CourseCode " + course + " already exists!");
    }

  }

  public int generateID() {
    Random random = new Random();
    int id = 1000 + random.nextInt(9000);
    return id;
  }


  public CourseModel getCourseByIdService(int id) {
    if (courseRepository.findByCourseId(id) == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Id!");
    }
    return courseRepository.findByCourseId(id);
  }

  public void deleteCourseByIdService(int id) {
    if (courseRepository.findByCourseId(id) == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Id!");
    }
    courseRepository.delete(courseRepository.findByCourseId(id));
  }

}
