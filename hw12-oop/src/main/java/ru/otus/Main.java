package ru.otus;

import ru.otus.ATMs.ATMVersion1;

public class Main {
    public static void main(String[] args) throws CashException {
        ATM atm = new ATMVersion1();
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
        cash.createBanknotes(Denominations.ONE_THOUSAND, 5);
        cash.createBanknotes(Denominations.ONE_HUNDRED, 2);
        cash.createBanknote(Denominations.FIFTY);
        cash.createBanknote(Denominations.FIVE_HUNDRED);
        System.out.printf("Сформирован кэш общей суммой %d руб. %n", cash.getSum());
        return cash;
    }
}