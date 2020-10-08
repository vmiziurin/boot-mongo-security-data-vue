package com.vmiziurin.gfootball.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document("players")
public class Player {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private int number;
}
