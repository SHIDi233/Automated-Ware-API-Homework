package com.studio314.autowaremanagesys.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cargo implements Serializable {
    private int cargoID;
    private String cargoName;
    private String cargoDescription;
    private int parent;
}
