package com.studio314.autowaremanagesys.service;

import com.studio314.autowaremanagesys.utils.Result;

public interface EmployeeService {
    Result addEmployee(int wareID, String email, String role);

    Result getEmployee(int wareID);

    Result deleteEmployee(int wareID, int uID);
}
