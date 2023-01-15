package ru.otus;

import java.util.Stack;
import java.util.TreeMap;

public class Cells {
    private final TreeMap<Denominations, Stack<Banknote>> cells;

    public Cells() {
        this.cells = new TreeMap<>();
    }

    public Stack<Banknote> getDenominations(Denominations denomination) {
        return cells.get(denomination);
    }

    public TreeMap<Denominations, Stack<Banknote>> getCells() {
        return cells;
    }

    public void addCells(Denominations denominations, Stack<Banknote> banknote) {
        cells.put(denominations, banknote);
    }
}