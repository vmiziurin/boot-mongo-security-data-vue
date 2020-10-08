package com.vmiziurin.gfootball.repos;

import com.vmiziurin.gfootball.model.Player;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlayerRepo extends MongoRepository<Player, String> {

    Player findById(ObjectId id);
}
