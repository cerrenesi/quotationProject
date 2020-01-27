package ru.quotationProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import ru.quotationProject.dao.EnergyLevelFinder;
import ru.quotationProject.dao.QuoteRepository;
import ru.quotationProject.entity.Quote;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuoteServiceImpl implements QuoteService {

    private QuoteRepository quoteRepository;
    private EnergyLevelFinder energyLevelFinder;
    private QuoteValidator quoteValidator;

    private Map<String, Double> elvlCash;

    @Autowired
    public QuoteServiceImpl(QuoteRepository quoteRepository, EnergyLevelFinder energyLevelFinder, QuoteValidator quoteValidator) {
        this.quoteRepository = quoteRepository;
        this.energyLevelFinder = energyLevelFinder;
        this.quoteValidator = quoteValidator;
        elvlCash = new HashMap<>();
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
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void save(Quote quote, Errors errors) {
        calculateNewEnergyLevel(quote);
        quoteValidator.validate(quote, errors);
        if (errors.hasErrors()) {
            throw new ValidationException("invalid quote");
        }
        quoteRepository.save(quote);
    }

    @Override
    public Map<String, Double> findCurrentElvls() {
        return energyLevelFinder.findAllElvls();
    }

    @Override
    public Map<String, Double>  findElvlByIsin(String isin) {
        return energyLevelFinder.findElvlByIsin(isin);
    }


    private void calculateNewEnergyLevel(Quote quote) {

        double currentElvl = elvlCash.getOrDefault(quote.getIsin(),
                findElvlByIsin(quote.getIsin()).getOrDefault(quote.getElvl(), Double.valueOf(0)));

        if (currentElvl == 0 || quote.getBid() >  currentElvl) {
            quote.setElvl(quote.getBid());
        }
        if (quote.getBid() == 0 || quote.getAsk() < currentElvl) {
            quote.setElvl(quote.getAsk());
        }

        elvlCash.put(quote.getIsin(), quote.getElvl());
    }

}
