package com.vmiziurin.gfootball.model;

import lombok.Data;
import org.bson.types.ObjectId;

import java.util.List;

@Data
public class Team {

    private ObjectId id;
    private String name;
    private List<Player> players;
}
