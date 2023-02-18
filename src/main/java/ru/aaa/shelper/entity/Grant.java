package ru.aaa.shelper.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class Grant {
    private String id;
    private String name;
    private String type;
    private BigDecimal amount;
    private String region;
    private String scale;
}