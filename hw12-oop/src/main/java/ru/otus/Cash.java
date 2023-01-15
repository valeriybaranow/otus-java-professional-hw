package ru.otus;

import java.util.ArrayList;
import java.util.Stack;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Cash {
    private final ArrayList <Banknote> banknotes;

    public Cash() {
       this.banknotes = new ArrayList<>();
    }

    public void addBanknote(Banknote banknote) {
        banknotes.add(banknote);
    }

    public void createBanknote(Denominations destination) {
        var banknote = new Banknote(destination);
        banknotes.add(banknote);
    }

    public void createBanknotes(Denominations destination, int count) {
        while(count > 0) {
            var banknote = new Banknote(destination);
            banknotes.add(banknote);
            count--;
        }
    }

    public ArrayList <Banknote> getBanknotes() {
        return banknotes;
    }

    public int getSum() {
        var sum = new AtomicInteger();
        banknotes.forEach((banknote) -> sum.addAndGet(banknote.getDenomination().getCash()));
        return sum.get();
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