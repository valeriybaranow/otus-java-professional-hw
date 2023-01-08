package ru.otus;

class CashException extends Exception{
    public CashException(String message, int num){
        super("Ошибка формирования кэша из суммы " + num + " руб. " + message);
    }
}
