package ru.quotationProject.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Quote")
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private long id;

    @Column(name = "isin")
    private String isin;

    @Column(name = "bid")
    private double bid;

    @Column(name = "ask")
    private double ask;


}
