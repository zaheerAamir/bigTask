package com.example.courses_backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.courses_backend.dto.CourseDto;
import com.example.courses_backend.model.CourseModel;
import com.example.courses_backend.service.CourseService;

/**
 * CourseController
 */
@RestController
@RequestMapping(path = "api/")
public class CourseController {


  private final CourseService courseService;

  @Autowired
  public CourseController(CourseService courseService) {
    this.courseService = courseService;
  }

  @GetMapping("/courses")
  public List<CourseModel> getCoursesController() {
    return courseService.getCoursesService();
  }

  @PostMapping("/courses")
  public ResponseEntity<Map<String, Object>> createCourseController(
      @RequestBody CourseDto.createCourseDto course) {

    Map<String, Object> response = new HashMap<>();
    HttpStatus status = HttpStatus.CREATED;

    try {

      CourseModel courseModel = new CourseModel();
      courseModel.setTitle(course.getTitle());
      courseModel.setDescription(course.getDescription());
      courseModel.setCourseCode(course.getCourseCode());
      courseModel.setCourseId(0);

      courseService.createCourseService(courseModel);

      response.put("message", "Course created Successfully!");
      response.put("status", HttpStatus.CREATED.value());

    } catch (Exception e) {
      response.put("message", "An error occurred while creating the course");
      response.put("details", e.getMessage());
      status = HttpStatus.BAD_REQUEST;
    }

    return new ResponseEntity<>(response, status);
  }


  @GetMapping("/courses/{id}")
  public ResponseEntity<Map<String, Object>> getCourseByIdController(@PathVariable("id") int id) {

    Map<String, Object> response = new HashMap<>();
    HttpStatus status = HttpStatus.OK;

    try {
      CourseModel course = courseService.getCourseByIdService(id);
      response.put("message", "Course Found Successfully!");
      response.put("data", course);

    } catch (Exception e) {
      response.put("message", "An error occurred while creating the course");
      response.put("details", e.getMessage());
      status = HttpStatus.BAD_REQUEST;
    }

    return new ResponseEntity<>(response, status);

  }



  @DeleteMapping("/courses/{id}")
  public ResponseEntity<Map<String, Object>> deleteCourseByIdController(
      @PathVariable("id") int id) {

    Map<String, Object> response = new HashMap<>();
    HttpStatus status = HttpStatus.OK;

    try {
      courseService.deleteCourseByIdService(id);
      response.put("message", "Course deleted Successfully!");

    } catch (Exception e) {
      response.put("message", "An error occurred while creating the course");
      response.put("details", e.getMessage());
      status = HttpStatus.BAD_REQUEST;
    }

    return new ResponseEntity<>(response, status);

  }

}
