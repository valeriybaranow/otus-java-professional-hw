package ru.otus;

public enum Denominations {
    FIVE_THOUSAND(5000),
    ONE_THOUSAND(1000),
    FIVE_HUNDRED(500),
    TWO_HUNDRED(200),
    ONE_HUNDRED(100),
    FIFTY(50);

    private final int denomination;

    Denominations(int denomination) {
        this.denomination = denomination;
    }

    public int getCash() {
        return denomination;
    }
}

