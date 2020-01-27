package ru.quotationProject.service;

import org.springframework.validation.Errors;
import ru.quotationProject.entity.Quote;

import java.util.List;
import java.util.Map;

public interface QuoteService {

    List<Quote> findAll();
    Quote findById(long id);
    void save(Quote employee, Errors errors);

    Map<String, Double> findCurrentElvls();

    Map<String, Double>  findElvlByIsin(String isin);

}
