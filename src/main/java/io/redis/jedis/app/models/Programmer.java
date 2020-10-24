package io.redis.jedis.app.models;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class Programmer implements Serializable {

    private int id;
    private String company;
    private String name;

}
