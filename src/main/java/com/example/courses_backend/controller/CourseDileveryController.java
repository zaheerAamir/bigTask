
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
  public List<Map<String, Object>> getInstancesController() {
    return courseDileveryService.getInstancesService();
  }

  @GetMapping("/instances/{year}/{sem}")
  public ResponseEntity<Map<String, Object>> getInstanceController(@PathVariable int year,
      @PathVariable int sem) {

    Map<String, Object> response = new HashMap<>();
    HttpStatus status;

    try {
      response = courseDileveryService.getInstanceService(year, sem);
      status = HttpStatus.OK;
    } catch (Exception e) {
      response.put("message", "An error occurred while fetching course details!");
      response.put("details", e.getMessage());
      status = HttpStatus.BAD_REQUEST;
    }


    return new ResponseEntity<>(response, status);
  }

  @GetMapping("/instances/{id}/{year}/{sem}")
  public ResponseEntity<Map<String, Object>> getDetailedInstanceController(@PathVariable int id,
      @PathVariable int year, @PathVariable int sem) {

    Map<String, Object> response = new HashMap<>();
    HttpStatus status;

    try {
      response = courseDileveryService.getDetailedInstanceService(id, year, sem);
      status = HttpStatus.OK;
    } catch (Exception e) {
      response.put("message", "An error occurred while fetching instance detail!");
      response.put("details", e.getMessage());
      status = HttpStatus.BAD_REQUEST;
    }


    return new ResponseEntity<>(response, status);
  }


  @DeleteMapping("/instances/{id}/{year}/{sem}")
  public ResponseEntity<Map<String, Object>> deleteInstanceController(@PathVariable int id,
      @PathVariable int year, @PathVariable int sem) {

    Map<String, Object> response = new HashMap<>();
    HttpStatus status;

    try {
      courseDileveryService.deleteInstanceService(id, year, sem);
      response.put("message", "Course instance deleted Successfully!");
      status = HttpStatus.NO_CONTENT;
    } catch (Exception e) {
      response.put("message", "An error occurred while fetching instance detail!");
      response.put("details", e.getMessage());
      status = HttpStatus.BAD_REQUEST;
    }


    return new ResponseEntity<>(response, status);
  }

}
