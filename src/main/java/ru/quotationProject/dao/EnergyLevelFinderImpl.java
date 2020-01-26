package ru.quotationProject.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EnergyLevelFinderImpl implements EnergyLevelFinder {

    private JdbcTemplate jdbc;

    @Autowired
    public EnergyLevelFinderImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Map<String, Double> findElvlByIsin(String isin) {
        return jdbc.query("SELECT ISIN, ELVL FROM QUOTE WHERE ISIN = ? order by ID desc limit 1", this::extractData, isin);

    }

    @Override
    public Map<String, Double> findAllElvls() {
        return jdbc.query("select ISIN, ELVL from QUOTE where ID in (select max(ID) from QUOTE group by ISIN)", this::extractData);
    }

    public Map extractData(ResultSet rs) throws SQLException, DataAccessException {
        HashMap<String,Double> mapRet= new HashMap<String,Double>();
        while(rs.next()){
            mapRet.put(rs.getString("isin"),rs.getDouble("elvl"));
        }
        return mapRet;
    }

}
