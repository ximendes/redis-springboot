package io.redis.jedis.app.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.redis.jedis.app.models.Programmer;
import io.redis.jedis.app.services.ProgrammerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@Api(value = "Programmer Rest Controller")
public class ProgrammerController {

    @Autowired
    private ProgrammerService service;

    @ApiOperation(value = "Add Programmer with Redis String Operation")
    @PostMapping("programmer-string")
    public void addTopic(@RequestBody Programmer programmer) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        service.setProgrammerAsString(String.valueOf(programmer.getId()), mapper.writeValueAsString(programmer));
    }

    @ApiOperation(value = "Find Programmer by id with Redis String Operation")
    @GetMapping("programmer-string/{id}")
    public String readString(@PathVariable String id){
        return service.getProgrammerAsString(id);
    }

    @ApiOperation(value = "Add Programmer with Redis List Operation")
    @PostMapping("/programmers-list")
    public void addToProgrammerList(@RequestBody Programmer programmer){
        service.addToProgrammerList(programmer);
    }

    @ApiOperation(value = "Find All Programmers with Redis List Operation")
    @GetMapping("/programmers-list")
    public List<Programmer> getProgrammersListMembers(){
        return service.getProgrammerListMembers();
    }

    @ApiOperation(value = "Count Programmers with Redis List Operation")
    @GetMapping("/programmers-list/count")
    public Long getProgrammersListCount(){
        return service.getProgrammersListCount();
    }

    @ApiOperation(value = "Add Programmer with Redis Set Operation")
    @PostMapping("/programmers-set")
    public void addToProgrammerSet(@RequestBody Programmer ... programmers){
        service.addToProgrammerSet(programmers);
    }

    @ApiOperation(value = "Find All Programmers with Redis Set Operation")
    @GetMapping("/programmers-set")
    public Set<Programmer> getProgrammersSetMembers(){
        return service.getProgrammerSetMembers();
    }

    @ApiOperation(value = "Check if Programmers is member of Set with Redis Set Operation")
    @PostMapping("/programmers-set/member")
    public boolean isSetMember(@RequestBody Programmer programmer){
        return  service.isSetMember(programmer);
    }

    @ApiOperation(value = "Save Programmer with Redis Hash Operation")
    @PostMapping("/programmers-hash")
    public void saveHash(@RequestBody Programmer programmer){
        service.saveHash(programmer);
    }

    @ApiOperation(value = "Update Programmer with Redis Hash Operation")
    @PutMapping("/programmers-hash")
    public void updateHash(@RequestBody Programmer programmer){
        service.updateHash(programmer);
    }

    @ApiOperation(value = "Find All Programmers with Redis Hash Operation")
    @GetMapping("/programmers-hash")
    public Map<Integer, Programmer> findAllHash(){
        return service.findAllHash();
    }

    @ApiOperation(value = "Find Programmer By id with Redis Hash Operation")
    @GetMapping("/programmers-hash/{id}")
    public Programmer findInHash(@PathVariable int id){
        return service.findHash(id);
    }

    @ApiOperation(value = "Delete Programmer By id with Redis Hash Operation")
    @DeleteMapping("/programmers-hash/{id}")
    public void deleteHash(@PathVariable int id){
        service.deleteHash(id);
    }
}
