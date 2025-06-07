package org.demo.kaiaskin.models.mongodb;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "QuestionnaireReponses")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionnaireReponse {

    @Id
    private String id;

    private String userId;

    private LocalDateTime date;

    private List<Reponse> reponses;

    private Boolean analyseEffectuee;
}
