package com.vmiziurin.gfootball.repos;

import com.vmiziurin.gfootball.security.MongoUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<MongoUser, String> {
    MongoUser findByUsername(String username);
}
