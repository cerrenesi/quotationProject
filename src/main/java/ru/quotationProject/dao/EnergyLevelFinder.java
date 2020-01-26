package ru.quotationProject.dao;


import java.util.Map;

public interface EnergyLevelFinder {

    Map<String, Double> findElvlByIsin(String isin);

    Map<String, Double> findAllElvls();


}
