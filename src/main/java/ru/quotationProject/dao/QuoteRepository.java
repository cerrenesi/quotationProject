package ru.quotationProject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.quotationProject.entity.Quote;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
}
