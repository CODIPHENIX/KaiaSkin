package org.demo.kaiaskin.services.mongodb;

import org.demo.kaiaskin.repositories.mongodb.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService{
    @Autowired
    private QuestionRepository questionRepository;


}
