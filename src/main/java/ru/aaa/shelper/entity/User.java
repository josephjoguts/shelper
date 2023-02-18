package ru.aaa.shelper.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
    private boolean sendEmail;
    private long minimalGrantAmount;
    private long maximumGrantAmount;
    private String[] eventTypes;
    private String[] regions;
    private String[] scales;
}