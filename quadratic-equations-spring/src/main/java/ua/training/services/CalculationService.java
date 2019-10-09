package ua.training.services;


import java.math.BigDecimal;

import ua.training.exceptions.CalculationException;

public interface CalculationService {

    /**
     * D=B^2-4*A*C
     * @return D
     */
    BigDecimal calcDiscriminant(BigDecimal b, BigDecimal a, BigDecimal c);

    /**
     * x1=(-В+D^(1/2))/2А
     * @return x1
     */
    BigDecimal calcX1(BigDecimal b, BigDecimal a, BigDecimal d) throws CalculationException;

    /**
     * x1=(-В+D^(1/2))/2А
     * @return x2
     */
    BigDecimal calcX2(BigDecimal b, BigDecimal a, BigDecimal d) throws CalculationException;
}