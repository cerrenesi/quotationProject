package ru.quotationProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.quotationProject.dao.QuoteRepository;
import ru.quotationProject.entity.Quote;

import java.util.List;

@Service
public class QuoteServiceImpl implements QuoteService {

    private QuoteRepository quoteRepository;

    @Autowired
    public QuoteServiceImpl(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    @Override
    public List<Quote> findAll() {
        return quoteRepository.findAll();
    }

    @Override
    public Quote findById(long id) {
        return quoteRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Quote quote) {
        quoteRepository.save(quote);

    }

    @Override
    public void deleteById(long id) {
        quoteRepository.deleteById(id);
    }
}
