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
public class CargoServiceImpl implements CargoService {
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
    public int addCargo(String cargoName, String cargoDescription){
        Cargo c = new Cargo();
        c.setCargoName(cargoName);
        c.setCargoDescription(cargoDescription);
        c.setParent(-1);
        cm.insert(c);
        return c.getCargoID();
    }

    @Override
    public int addCargo(String cargoName, String cargoDescription, int parent){
        Cargo c = new Cargo();
        c.setCargoName(cargoName);
        c.setCargoDescription(cargoDescription);
        c.setParent(parent);
        cm.insert(c);
        return c.getCargoID();
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
