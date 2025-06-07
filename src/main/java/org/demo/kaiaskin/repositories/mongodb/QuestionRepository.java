package org.demo.kaiaskin.repositories.mongodb;

import org.demo.kaiaskin.models.mongodb.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface QuestionRepository extends MongoRepository<Question, String> {
}
