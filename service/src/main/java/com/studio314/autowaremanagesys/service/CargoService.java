package com.studio314.autowaremanagesys.service;

import com.studio314.autowaremanagesys.pojo.Cargo;

import java.util.List;

public interface CargoService {
    public List<Cargo> getAllCargos();

    public Cargo getCargo(int id);

    public int addCargo(String CargoName, String CargoDescription);

    public int addCargo(String CargoName, String CargoDescription, int parent);

    public void updateCargo(int cargoID, String CargoName, String CargoDescription);

    public void deleteCargo(int id);
}
