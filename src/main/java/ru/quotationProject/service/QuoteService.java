package ru.quotationProject.service;

import ru.quotationProject.entity.Quote;

import java.util.List;

public interface QuoteService {

    List<Quote> findAll();
    Quote findById(long id);
    void save(Quote employee);
    void deleteById(long id);

}
