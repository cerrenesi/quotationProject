package ru.quotationProject.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.quotationProject.entity.Quote;
import ru.quotationProject.service.QuoteService;

import java.util.List;
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

    @PostMapping("/quotes")
    public Quote addQuote(@RequestBody Quote quote) {
        quote.setId(0);
        quoteService.save(quote);
        return quote;
    }

    @PutMapping("/quotes")
    public Quote updateQuote(@RequestBody Quote quote) {
        quoteService.save(quote);
        return quote;
    }

    @DeleteMapping("/quotes/{quoteId}")
    public String deleteQuote(@PathVariable int quoteId) {
        Quote tempQuote = quoteService.findById(quoteId);
        if (Objects.isNull(tempQuote)) {
            throw new RuntimeException("quote id not found - " + quoteId);
        }
        quoteService.deleteById(quoteId);
        return "Deleted quote id - " + quoteId;

    }


}
