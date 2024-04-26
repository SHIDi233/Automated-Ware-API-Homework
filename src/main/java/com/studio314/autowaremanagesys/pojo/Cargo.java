package com.studio314.autowaremanagesys.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cargo implements Serializable {
    private int cargoId;
    private String cargoName;
    private String cargoDescription;
}
