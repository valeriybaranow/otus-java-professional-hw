package ru.otus;

import java.util.Stack;
import java.util.TreeMap;

public interface ATM {
    /**
     * Добавить наличность (банкноты) в банкомат
     */
    void putCash(Cash cash);

    /**
     * Выдать запрошенную сумму минимальным количеством банкнот
     */
    Cash getCash(int sumCash) throws CashException;

    /**
     * Сумма остатка денежных средств
     */
    int getSumCash();

    /**
     * Вврнуть ячейки банкомата
     */
    TreeMap<Denominations, Stack<Banknote>> getCells();

    /**
     * Отобразить информацию о наличности купюр в ячейках банкомата
     */
    void showCashInCells();
}
