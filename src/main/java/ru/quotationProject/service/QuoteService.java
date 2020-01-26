package ru.quotationProject.service;

import ru.quotationProject.entity.Quote;

import java.util.List;
import java.util.Map;

public interface QuoteService {

    List<Quote> findAll();
    Quote findById(long id);
    void save(Quote employee);
    void deleteById(long id);

    Map<String, Double> findCurrentElvls();

    Map<String, Double>  findElvlByIsin(String isin);

}
