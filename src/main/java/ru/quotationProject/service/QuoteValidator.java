package ru.quotationProject.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.quotationProject.entity.Quote;

@Service
public class QuoteValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Quote.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Quote quote = (Quote) o;

        if (quote.getBid() > quote.getAsk()) {
            errors.rejectValue("bid", "bid must be less than ask");
        }

    }
}
