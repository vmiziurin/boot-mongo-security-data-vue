package com.vmiziurin.gfootball.repos;

import com.vmiziurin.gfootball.model.Match;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MatchRepo extends MongoRepository<Match, String> {

    Match findById(ObjectId id);
}
