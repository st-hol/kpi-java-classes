package ua.training.exceptions;

public class CalculationException extends Exception {
    public CalculationException() {
    }

    public CalculationException(String m) {
        super(m);
    }
}