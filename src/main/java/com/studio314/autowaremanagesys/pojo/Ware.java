package com.studio314.autowaremanagesys.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ware implements Serializable {
    private int wareID;
    private String wareName;
    private int creator;
}
