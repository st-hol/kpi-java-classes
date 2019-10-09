package ua.training.entities;


import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Calculation {
    private BigDecimal paramA;
    private BigDecimal paramB;
    private BigDecimal paramC;

    private BigDecimal discriminant;
    private BigDecimal x1;
    private BigDecimal x2;
}
