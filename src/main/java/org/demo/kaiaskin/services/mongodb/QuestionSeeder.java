package org.demo.kaiaskin.services.mongodb;

import org.demo.kaiaskin.dto.Option;
import org.demo.kaiaskin.models.mongodb.Question;
import org.demo.kaiaskin.models.mongodb.enums.CategoryQ;
import org.demo.kaiaskin.models.mongodb.enums.TypeQuestion;
import org.demo.kaiaskin.repositories.mongodb.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
//class test
@Component

public class QuestionSeeder  implements CommandLineRunner {
    @Autowired
    private QuestionRepository questionRepository;
    @Override
    public void run(String... args) {
        Question q = Question.builder()
                .label("Quelle est ta peau ?")
                .typeQuestion(TypeQuestion.CHOIX_UNIQUE)
                .obligatoire(true)
                .category(CategoryQ.TYPE_PEAU)
                .options(List.of(
                        new Option("Grasse", "grasse"),
                        new Option("Sèche", "seche")
                ))
                .build();

        questionRepository.save(q);
    }
}
