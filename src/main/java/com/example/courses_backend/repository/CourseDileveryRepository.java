
package com.example.courses_backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.courses_backend.model.CourseDilveryModel;

public interface CourseDileveryRepository extends MongoRepository<CourseDilveryModel, String> {

  CourseDilveryModel findByYearAndSem(int year, int sem);

  CourseDilveryModel findByCourseIdAndYearAndSem(int courseId, int year, int sem);

}
