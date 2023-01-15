package ru.otus;

import java.util.*;

public class Cash {
    private final List <Banknote> banknotes;

    public Cash() {
       this.banknotes = new ArrayList<>();
    }

    public void addBanknote(Banknote banknote) {
        banknotes.add(banknote);
    }

    public void addBanknote(Denominations destination) {
        var banknote = new Banknote(destination);
        addBanknote(banknote);
    }

    public void addBanknotes(Denominations destination, int count) {
        while(count > 0) {
            var banknote = new Banknote(destination);
            banknotes.add(banknote);
            count--;
        }
    }

    public List<Banknote> getBanknotes() {
        return banknotes;
    }

    public int getSum() {
        return banknotes.stream().mapToInt(banknote -> banknote.getDenomination().getCash()).sum();
    }

    int getCountBanknote(Denominations denominations) {
        TreeMap<Denominations, Stack<Banknote>> banknotesCount = new TreeMap<>();
        banknotes.forEach((banknote) -> {
            var newBanknote = new Stack<Banknote>();
            newBanknote.add(banknote);
            banknotesCount.put(banknote.getDenomination(), newBanknote);
        });
        return banknotesCount.get(denominations).size();
    }

    @Override
    public String toString() {
        var ref = new Object() {
            String str = "Кэш:\n";
            int i = 1;
        };
        banknotes.forEach((banknote) -> {
            ref.str += "- банкнота номиналом " + banknote.getDenomination().getCash() + " руб.";
            if(ref.i != banknotes.size()) {
                ref.str += "\n";
            }
            ref.i++;
        });
        return ref.str;
    }
}