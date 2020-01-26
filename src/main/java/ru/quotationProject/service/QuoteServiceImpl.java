package ru.quotationProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.quotationProject.dao.EnergyLevelFinder;
import ru.quotationProject.dao.QuoteRepository;
import ru.quotationProject.entity.Quote;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuoteServiceImpl implements QuoteService {

    private QuoteRepository quoteRepository;
    private EnergyLevelFinder energyLevelFinder;

    private Map<String, Double> elvlCash;

    @Autowired
    public QuoteServiceImpl(QuoteRepository quoteRepository, EnergyLevelFinder energyLevelFinder) {
        this.quoteRepository = quoteRepository;
        this.energyLevelFinder = energyLevelFinder;
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
    public void save(Quote quote) {
        calculateNewEnergyLevel(quote);
        quoteRepository.save(quote);

    }

    @Override
    public void deleteById(long id) {
        quoteRepository.deleteById(id);
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

        double currentElvl = findElvlByIsin(quote.getIsin()).getOrDefault(quote.getElvl(), Double.valueOf(0));//elvlCash.get(quote.getIsin());

        if (currentElvl == 0 || quote.getBid() >  currentElvl) {
            quote.setElvl(quote.getBid());
        }
        if (quote.getBid() == 0 || quote.getAsk() < currentElvl) {
            quote.setElvl(quote.getAsk());
        }

        elvlCash.put(quote.getIsin(), quote.getElvl());
    }

}
