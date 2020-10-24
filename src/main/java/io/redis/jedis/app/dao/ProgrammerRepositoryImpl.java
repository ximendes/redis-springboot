package io.redis.jedis.app.dao;

import io.redis.jedis.app.models.Programmer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Repository
public class ProgrammerRepositoryImpl implements ProgrammerRepository {

    public static final String REDIS_LIST_KEY = "ProgrammerList";
    public static final String REDIS_SET_KEY = "ProgrammerSET";
    public static final String REDIS_HASH_KEY = "ProgrammerHash";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    @Qualifier("listOperations")
    private ListOperations<String, Programmer> listOperations;

    @Autowired
    @Qualifier("setOperations")
    private SetOperations<String, Programmer> setOperations;

    @Autowired
    @Qualifier("hashOperations")
    private HashOperations<String, Integer, Programmer> hashOperations;

    @Override
    public void setProgrammerAsString(String idKey, String programmer) {
        redisTemplate.opsForValue()
                     .set(idKey, programmer);
        redisTemplate.expire(idKey, 60, TimeUnit.SECONDS);

    }

    @Override
    public String getProgrammerAsString(String idKey) {
        return (String) redisTemplate.opsForValue()
                                     .get(idKey);
    }

    @Override
    public void addToProgrammerList(Programmer programmer) {
        listOperations.leftPush(REDIS_LIST_KEY, programmer);

    }

    @Override
    public List<Programmer> getProgrammerListMembers() {
        return listOperations.range(REDIS_LIST_KEY, 0, -1);
    }

    @Override
    public Long getProgrammersListCount() {
        return listOperations.size(REDIS_LIST_KEY);
    }

    @Override
    public void addToProgrammerSet(Programmer... programmers) {
        setOperations.add(REDIS_SET_KEY, programmers);
    }

    @Override
    public Set<Programmer> getProgrammerSetMembers() {
        return setOperations.members(REDIS_SET_KEY);
    }

    @Override
    public boolean isSetMember(Programmer programmer) {
        return setOperations.isMember(REDIS_SET_KEY, programmer);
    }

    @Override
    public void saveHash(Programmer programmer) {
        hashOperations.put(REDIS_HASH_KEY, programmer.getId(), programmer);
    }

    @Override
    public void updateHash(Programmer programmer) {
        hashOperations.put(REDIS_HASH_KEY, programmer.getId(), programmer);
    }

    @Override
    public Map<Integer, Programmer> findAllHash() {
        return hashOperations.entries(REDIS_HASH_KEY);
    }

    @Override
    public Programmer findHash(int id) {
        return hashOperations.get(REDIS_HASH_KEY, id);
    }

    @Override
    public void deleteHash(int id) {
        hashOperations.delete(REDIS_HASH_KEY, id);
    }
}
