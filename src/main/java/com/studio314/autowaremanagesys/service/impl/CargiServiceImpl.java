package com.studio314.autowaremanagesys.service.impl;

import com.studio314.autowaremanagesys.mapper.CargoMapper;
import com.studio314.autowaremanagesys.pojo.Cargo;
import com.studio314.autowaremanagesys.service.CargoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CargiServiceImpl implements CargoService {
    @Autowired
    CargoMapper cm;

    @Override
    public List<Cargo> getAllCargos(){
        return cm.selectAll();
    }

    @Override
    public Cargo getCargo(int id){
        return cm.select(id);
    }

    @Override
    public void addCargo(String cargoName, String cargoDescription){
        cm.insert(cargoName,cargoDescription,-1);
    }

    @Override
    public void addCargo(String cargoName, String cargoDescription, int parent){
        cm.insert(cargoName, cargoDescription, parent);
    }

    @Override
    public void updateCargo(int cargoID, String cargoName, String cargoDescription){
        cm.update(cargoID, cargoName, cargoDescription);
    }

    @Override
    public void deleteCargo(int cargoID){
        cm.delete(cargoID);
    }
}
