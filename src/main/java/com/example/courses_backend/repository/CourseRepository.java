package com.example.courses_backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.courses_backend.model.CourseModel;

public interface CourseRepository extends MongoRepository<CourseModel, String> {

  CourseModel findByCourseId(int id);

  boolean existsByCourseCode(int id);

}
