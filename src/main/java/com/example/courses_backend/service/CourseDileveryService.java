package com.example.courses_backend.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.courses_backend.model.CourseDilveryModel;
import com.example.courses_backend.model.CourseModel;
import com.example.courses_backend.repository.CourseDileveryRepository;
import com.example.courses_backend.repository.CourseRepository;

/**
 * CourseDileveryService
 */
@Service
public class CourseDileveryService {

  @Autowired
  private final CourseDileveryRepository courseDileveryRepository;
  private final CourseRepository courseRepository;

  public CourseDileveryService(CourseDileveryRepository courseDileveryRepository,
      CourseRepository courseRepository) {
    this.courseDileveryRepository = courseDileveryRepository;
    this.courseRepository = courseRepository;
  }


  public CourseDilveryModel createCourseInstanceService(CourseDilveryModel course) {

    validateCourseInt(course.getSem(), "Sem");
    validateCourseInt(course.getYear(), "Year");
    validateCourseInt(course.getCourseId(), "CourseID");
    return courseDileveryRepository.save(course);
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

  }

  public List<Map<String, Object>> getInstanceService() {

    List<CourseDilveryModel> courseDilverys = courseDileveryRepository.findAll();

    List<Map<String, Object>> responseList = new ArrayList<>();


    courseDilverys.forEach(courseDilvery -> {

      Map<String, Object> response = new HashMap<>();

      CourseModel course = courseRepository.findByCourseId(courseDilvery.getCourseId());

      response.put("title", course.getTitle());
      response.put("Year", courseDilvery.getYear());
      response.put("Sem", courseDilvery.getSem());
      response.put("CourseCode", course.getCourseCode());

      responseList.add(response);

    });

    return responseList;

  }

}
