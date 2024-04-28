package com.studio314.autowaremanagesys.service.impl;

import com.studio314.autowaremanagesys.mapper.EmployeeMapper;
import com.studio314.autowaremanagesys.mapper.UserMapper;
import com.studio314.autowaremanagesys.pojo.Cargo;
import com.studio314.autowaremanagesys.pojo.Employee;
import com.studio314.autowaremanagesys.pojo.Stock;
import com.studio314.autowaremanagesys.service.EmployeeService;
import com.studio314.autowaremanagesys.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    UserMapper um;

    @Autowired
    EmployeeMapper em;

    @Override
    public Result getEmployee(int id) {
        List<Employee> employees = em.get(id);
        List<Map<String, Object>> res = new ArrayList<>();
        for(Employee e : employees){
            Map<String, Object> map = new HashMap<>();
            map.put("uID", e.getUID());
            String name = um.getUserNameById(e.getUID());
            map.put("name", name);
            map.put("role", e.getRole());
            res.add(map);
        }
        return Result.success(res);
    }

    @Override
    public Result addEmployee(int wareID, String email, String role){
        int uid=um.getIdByEmail(email);
        em.add(uid,role,wareID);
        return Result.success();
    }

    @Override
    public Result deleteEmployee(int wareID, int uID){
        em.del(uID,wareID);
        return Result.success();
    }
}
