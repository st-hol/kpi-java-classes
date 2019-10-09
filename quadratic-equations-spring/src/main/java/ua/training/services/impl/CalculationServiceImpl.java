package ua.training.services.impl;


import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

import ua.training.exceptions.CalculationException;
import ua.training.services.CalculationService;


@Service
public class CalculationServiceImpl implements CalculationService {

    private static final int SCALE_FOR_RESULT = 3;
    private static final int SCALE_FOR_SQRT = 10001;
    private static final String NEGATIVE_VALUE_FOR_SQRT = "NEGATIVE_VALUE_FOR_SQRT";

    @Override
    public BigDecimal calcDiscriminant(BigDecimal b, BigDecimal a, BigDecimal c) {
        return b.pow(2)
                .subtract(BigDecimal.valueOf(4).multiply(a).multiply(c))
                .setScale(SCALE_FOR_RESULT, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal calcX1(BigDecimal b, BigDecimal a, BigDecimal d) throws CalculationException {
        return b.negate().add(sqrt(d))
                .divide(BigDecimal.valueOf(2).multiply(a), RoundingMode.HALF_UP)
                .setScale(SCALE_FOR_RESULT, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal calcX2(BigDecimal b, BigDecimal a, BigDecimal d) throws CalculationException {
        return b.negate().subtract(sqrt(d))
                .divide(BigDecimal.valueOf(2).multiply(a), RoundingMode.HALF_UP)
                .setScale(SCALE_FOR_RESULT, RoundingMode.HALF_UP);
    }

    /**
     * it’s from Stackoverflow :)
     *
     * @param number
     * @return square root
     * @throws CalculationException
     */
    private BigDecimal sqrt(BigDecimal number) throws CalculationException {

        BigDecimal first = BigDecimal.ZERO;
        BigDecimal second;
        try {
            second = new BigDecimal(Math.sqrt(number.doubleValue()));
        } catch (NumberFormatException e) {
            throw new CalculationException(NEGATIVE_VALUE_FOR_SQRT);
        }
        while (!first.equals(second)) {
            first = second;
            second = number.divide(first, SCALE_FOR_SQRT, RoundingMode.HALF_UP);
            second = second.add(first);
            second = second.divide(BigDecimal.valueOf(2), SCALE_FOR_SQRT, RoundingMode.HALF_UP);
        }
        return second;
    }
}