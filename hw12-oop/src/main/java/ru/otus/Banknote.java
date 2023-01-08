package ru.otus;

public class Banknote {
    public Denominations denomination;

    public Banknote(Denominations denomination) {
        this.denomination = denomination;
    }

    public Denominations getDenomination(){
        return this.denomination;
    }
}
