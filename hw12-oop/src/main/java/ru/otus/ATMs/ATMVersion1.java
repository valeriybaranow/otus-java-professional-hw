package ru.otus.ATMs;

import ru.otus.*;

import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

public class ATMVersion1 implements ATM {
    private final TreeMap<Denominations, Stack<Banknote>> cells;

    public ATMVersion1() {
        this.cells = new TreeMap<>();
    }

    public void putCash(Cash cash) {
        cash.getBanknotes().forEach((banknote) -> {
            var denomination = banknote.getDenomination();
            var oldBanknotes = cells.get(denomination);
            if(oldBanknotes == null) {
                var newBanknote = new Stack<Banknote>();
                newBanknote.add(banknote);
                cells.put(banknote.getDenomination(), newBanknote);
            }
            else {
                oldBanknotes.add(banknote);
                cells.put(banknote.getDenomination(), oldBanknotes);
            }
        });
        System.out.printf("Наличные общей суммой %d руб. загружены в ячейки банкомата.%n", cash.getSum());
    }

    public Cash getCash(int sumCash) throws CashException {
        System.out.printf("Запрошено снятие наличных общей суммой %d руб.%n", sumCash);
        if(sumCash > getSumCash()) {
            throw new CashException("В банкомате недостаточно средств. Невозможно снять указанную сумму", sumCash);
        }
        Cash cash = new Cash();
        for(Map.Entry<Denominations, Stack<Banknote>> cell : cells.entrySet()) {
            int cellCash = cell.getKey().getCash();
            if(sumCash >= cellCash) {
                int sumBanknotes = sumCash - sumCash%cellCash;
                int numBanknotes = Math.min(sumBanknotes / cellCash, cell.getValue().size());
                sumCash -= numBanknotes * cellCash;
                while(numBanknotes > 0 && !cell.getValue().empty()) {
                    Banknote banknote = cell.getValue().pop();
                    cash.addBanknote(banknote);
                    numBanknotes--;
                }
            }
        }
        if(sumCash != 0) {
            putCash(cash);
            throw new CashException("Невозможно снять указанную сумму. Банкноты возвращены в ячейки банкомата.", sumCash);
        }
        System.out.printf("Наличные общей суммой %d руб. сняты в банкомате.%n", cash.getSum());
        return cash;
    }

    public int getSumCash() {
        int sum = 0;
        for(Map.Entry<Denominations, Stack<Banknote>> cell : cells.entrySet()) {
            Denominations denomination = cell.getKey();
            Stack<Banknote> banknotes = cell.getValue();
            sum += banknotes.size()*denomination.getCash();
        }
        return sum;
    }

    public TreeMap<Denominations, Stack<Banknote>> getCells() {
        return cells;
    }

    public void showCashInCells() {
        System.out.println("-------------Наличные в ячейках-------------");
        for(Map.Entry<Denominations, Stack<Banknote>> cell : cells.entrySet()) {
            Denominations denomination = cell.getKey();
            Stack<Banknote> banknotes = cell.getValue();
            System.out.printf(
                    "Наличность в ячейкее %s (%dx%d руб.) => %d руб. ",
                    denomination,
                    banknotes.size(),
                    denomination.getCash(),
                    banknotes.size()*denomination.getCash()
            );
            System.out.println(denomination + " => " + banknotes);
        }
        System.out.println("--------------------------------------------");
        System.out.println("Количесто наличности в банкомате "+getSumCash()+" руб.");
    }
}
