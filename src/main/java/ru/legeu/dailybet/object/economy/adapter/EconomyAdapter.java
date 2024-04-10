package ru.legeu.dailybet.object.economy.adapter;

public interface EconomyAdapter {
    boolean add(String accountName, double amount);

    boolean subtract(String accountName, double amount);

    double getBalance(String accountName);

    boolean setBalance(String accountName, double amount);
}
