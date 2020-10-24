package io.redis.jedis.app.services;

import io.redis.jedis.app.models.Programmer;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ProgrammerService {

    void setProgrammerAsString(String idKey, String programmer);

    String getProgrammerAsString(String idKey);

    void addToProgrammerList(Programmer programmer);

    List<Programmer> getProgrammerListMembers();

    Long getProgrammersListCount();

    void addToProgrammerSet(Programmer ... programmers);

    Set<Programmer> getProgrammerSetMembers();

    boolean isSetMember(Programmer programmer);

    void saveHash(Programmer programmer);

    void updateHash(Programmer programmer);

    Map<Integer, Programmer> findAllHash();

    Programmer findHash(int id);

    void deleteHash(int id);
}
