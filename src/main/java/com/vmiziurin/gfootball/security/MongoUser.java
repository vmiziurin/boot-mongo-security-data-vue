package com.vmiziurin.gfootball.security;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class MongoUser {
    @Id
    private ObjectId id;
    private String username;
    private String pass;
    private Role role;
}
