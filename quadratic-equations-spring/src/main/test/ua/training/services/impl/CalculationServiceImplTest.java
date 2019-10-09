package ua.training.services.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.MockitoJUnitRunner;

import ua.training.exceptions.CalculationException;
import ua.training.services.CalculationService;

@RunWith(MockitoJUnitRunner.class)
public class CalculationServiceImplTest {

    private static final int SCALE_FOR_RESULT = 3;
    private static final String NEGATIVE_VALUE_FOR_SQRT = "NEGATIVE_VALUE_FOR_SQRT";

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private CalculationService serviceUnderTest = new CalculationServiceImpl();

//    @Captor
//    private ArgumentCaptor<BigDecimal> captor;

    @Before
    public void setUp() {
    }

    @Test
    public void givenZeroValues_whenCalcDiscriminant_thenDiscriminantIsZero() {
        BigDecimal expected = BigDecimal.ZERO.setScale(SCALE_FOR_RESULT, RoundingMode.HALF_UP);
        BigDecimal actual = serviceUnderTest.calcDiscriminant(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
        assertThat(actual, is(notNullValue()));
        assertThat(actual, is(expected));
        assertThat(actual.scale(), is(SCALE_FOR_RESULT));
    }

    @Test
    public void givenDamperGreaterThanDiminutive_whenCalcDiscriminant_thenDiscriminantIsNegative() {
        BigDecimal discriminant = serviceUnderTest.calcDiscriminant(BigDecimal.ZERO, BigDecimal.TEN, BigDecimal.TEN);
        assertThat(discriminant, is(notNullValue()));
        assertThat(discriminant, is(lessThan(BigDecimal.ZERO)));
        assertThat(discriminant.scale(), is(SCALE_FOR_RESULT));
    }

    @Test
    public void givenDamperLesserThanDiminutive_whenCalcDiscriminant_thenDiscriminantIsNegative() {
        BigDecimal discriminant = serviceUnderTest.calcDiscriminant(BigDecimal.TEN, BigDecimal.ZERO, BigDecimal.ZERO);
        assertThat(discriminant, is(notNullValue()));
        assertThat(discriminant, is(greaterThan(BigDecimal.ZERO)));
        assertThat(discriminant.scale(), is(SCALE_FOR_RESULT));
    }

    @Test
    public void givenValidValues_whenCalcX1_thenReturnAsExpected() throws CalculationException {
        BigDecimal paramA = BigDecimal.valueOf(-2);
        BigDecimal paramB = BigDecimal.valueOf(3);
        BigDecimal discriminant = BigDecimal.valueOf(25).setScale(SCALE_FOR_RESULT, RoundingMode.HALF_UP);

        BigDecimal actualX1 = serviceUnderTest.calcX1(paramB, paramA, discriminant);
        BigDecimal expectedX1 = BigDecimal.valueOf(-0.5).setScale(SCALE_FOR_RESULT, RoundingMode.HALF_UP);

        assertThat(discriminant, is(notNullValue()));
        assertThat(actualX1, is(expectedX1));
    }

    @Test
    public void givenValidValues_whenCalcX2_thenReturnAsExpected() throws CalculationException {
        BigDecimal paramA = BigDecimal.valueOf(-2);
        BigDecimal paramB = BigDecimal.valueOf(3);
        BigDecimal discriminant = BigDecimal.valueOf(25).setScale(SCALE_FOR_RESULT, RoundingMode.HALF_UP);

        BigDecimal actualX1 = serviceUnderTest.calcX2(paramB, paramA, discriminant);
        BigDecimal expectedX1 = BigDecimal.valueOf(2).setScale(SCALE_FOR_RESULT, RoundingMode.HALF_UP);

        assertThat(discriminant, is(notNullValue()));
        assertThat(actualX1, is(expectedX1));
    }

    @Test
    public void givenNegativeDiscriminant_whenCalcX1_thenThrowCalculationException() throws CalculationException {
        BigDecimal paramA = BigDecimal.valueOf(-2);
        BigDecimal paramB = BigDecimal.valueOf(3);
        BigDecimal discriminant = BigDecimal.valueOf(-1).setScale(SCALE_FOR_RESULT, RoundingMode.HALF_UP);

        exception.expect(CalculationException.class);
        exception.expectMessage(containsString(NEGATIVE_VALUE_FOR_SQRT));

        serviceUnderTest.calcX1(paramB, paramA, discriminant);
    }

}
