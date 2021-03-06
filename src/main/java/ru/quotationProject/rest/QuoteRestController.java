package ru.quotationProject.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.quotationProject.entity.Quote;
import ru.quotationProject.service.QuoteService;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class QuoteRestController {

    private QuoteService quoteService;

    @Autowired
    public QuoteRestController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping("/quotes")
    public List<Quote> findAll() {
        return quoteService.findAll();
    }

    @GetMapping("/quotes/{quoteId}")
    public Quote getQuote(@PathVariable int quoteId) {
        Quote quote = quoteService.findById(quoteId);
        if (Objects.isNull(quote)) {
            throw new RuntimeException("quote id not found - " + quoteId);
        }
        return quote;
    }

    @GetMapping("/elvls")
    public Map<String, Double> getElvls() {
        return quoteService.findCurrentElvls();
    }

    @GetMapping("/elvls/{quoteIsin}")
    public Map<String, Double>  getElvls(@PathVariable String quoteIsin) {
        return quoteService.findElvlByIsin(quoteIsin);
    }


    @PostMapping("/quotes")
    public Quote addQuote(@RequestBody Quote quote, Errors errors) {
        quote.setId(0);
        quoteService.save(quote, errors);
        return quote;
    }
}
