package org.demo.kaiaskin.models.mongodb;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "DiagnosticsM")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiagnosticM {
    @Id
    private String id;
    private String userId;
    private String questionnaireId;

    private String zone;

    private String typePeau;
    private List<String> problemes;
    private Boolean sensibilites;

    private String typeRoutinePreferee;
    private Boolean produitsBio;
    private String budget;

    private List<String> produitsRecommandes;

    private LocalDateTime createdAt;
}
