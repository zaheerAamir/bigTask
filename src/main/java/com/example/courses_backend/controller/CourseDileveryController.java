
package com.example.courses_backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.courses_backend.dto.CourseDto;
import com.example.courses_backend.model.CourseDilveryModel;
import com.example.courses_backend.service.CourseDileveryService;

/**
 * CourseDileveryController
 */
@RestController
@RequestMapping(path = "api/")
public class CourseDileveryController {

  private final CourseDileveryService courseDileveryService;

  @Autowired
  public CourseDileveryController(CourseDileveryService courseDileveryService) {
    this.courseDileveryService = courseDileveryService;
  }

  @PostMapping("/instances/{id}")
  public ResponseEntity<Map<String, Object>> createCourseController(
      @RequestBody CourseDto.createInstanceDto course, @PathVariable int id) {

    Map<String, Object> response = new HashMap<>();
    HttpStatus status = HttpStatus.CREATED;

    try {

      CourseDilveryModel courseDilveryModel = new CourseDilveryModel();
      courseDilveryModel.setSem(course.getSem());
      courseDilveryModel.setYear(course.getYear());
      courseDilveryModel.setCourseId(id);

      courseDileveryService.createCourseInstanceService(courseDilveryModel);

      response.put("message", "Course created Successfully!");
      response.put("status", HttpStatus.CREATED.value());

    } catch (Exception e) {
      response.put("message", "An error occurred while creating the course");
      response.put("details", e.getMessage());
      status = HttpStatus.BAD_REQUEST;
    }

    return new ResponseEntity<>(response, status);
  }


  @GetMapping("/instances")
  public List<Map<String, Object>> getInstanceController() {
    return courseDileveryService.getInstanceService();
  }

}
