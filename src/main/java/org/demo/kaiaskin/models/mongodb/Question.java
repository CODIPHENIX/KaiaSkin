package org.demo.kaiaskin.models.mongodb;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.demo.kaiaskin.dto.Option;
import org.demo.kaiaskin.models.mongodb.enums.CategoryQ;
import org.demo.kaiaskin.models.mongodb.enums.TypeQuestion;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Questions")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Question {
    @Id
    private String id;
    private int order;
    private String label;
    private boolean obligatoire;
    private TypeQuestion typeQuestion;
    private CategoryQ category;
    private List<Option> options;

}
