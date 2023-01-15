package ru.otus;

import ru.otus.ATMs.ATMVersion1;

public class Main {
    public static void main(String[] args) throws CashException {
        Cells cells = new Cells();
        ATM atm = new ATMVersion1(cells);
        atm.putCash(createCash());
        atm.showCashInCells();
        atm.putCash(createCash());
        atm.showCashInCells();
        Cash cash = atm.getCash(2000);
        System.out.println(cash.toString());
        System.out.printf("Остаток наличности в банкомате %d руб.", atm.getSumCash());
    }

    private static Cash createCash() {
        Cash cash = new Cash();
        cash.addBanknotes(Denominations.ONE_THOUSAND, 5);
        cash.addBanknotes(Denominations.ONE_HUNDRED, 2);
        cash.addBanknote(Denominations.FIFTY);
        cash.addBanknote(Denominations.FIVE_HUNDRED);
        System.out.printf("Сформирован кэш общей суммой %d руб. %n", cash.getSum());
        return cash;
    }
}