package com.example.redis_hhh.repository;

import com.example.redis_hhh.model.Employee;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {

    private HashOperations hashOperations;//crud hash
    private ListOperations listOperations;//crud list
    private SetOperations setOperations;//crud set
    private RedisTemplate redisTemplate;

    public EmployeeRepository(RedisTemplate redisTemplate) {

       this.hashOperations = redisTemplate.opsForHash();
        //this.listOperations = redisTemplate.opsForList();
        //this.setOperations = (SetOperations) redisTemplate.opsForZSet();
        this.redisTemplate = redisTemplate;

    }

    public void saveEmployee(Employee employee){

        hashOperations.put("EMPLOYEE", employee.getId(), employee);
        //listOperations.leftPush("EMPLOYEE", employee);
        //setOperations.add("EMPLOYEE", employee);

    }
    public List<Employee> findAll(){

        return hashOperations.values("EMPLOYEE");
        //		return listOperations.range("EMPLOYEE", 0, listOperations.size("EMPLOYEE"));
        //return setOperations.members("EMPLOYEE");
    }
    public Employee findById(Integer id){

        return (Employee) hashOperations.get("EMPLOYEE", id);
    }

    public void update(Employee employee){
        saveEmployee(employee);
    }
    public void delete(Integer id){

        hashOperations.delete("EMPLOYEE", id);
        //listOperations.rightPopAndLeftPush("EMPLOYEE", id);
//        Employee e = this.findById(id);
//        setOperations.remove("EMPLOYEE", e);
    }
}
