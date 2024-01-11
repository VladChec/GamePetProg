package org.example.servlets;

public class Clan {
    private long id;     // id клана
    private String name; // имя кланаñ

    private  int gold;    // текущее количество золота в казне клана
                                //volatile для обеспечения видимости изменений в потоках



    public synchronized void addToGold(int amount) {
        int previousGold = this.gold;
        this.gold += amount;
        LoggingService loggingService = new LoggingService();
        loggingService.logGoldChanges(this.id, previousGold, this.gold,  "Added " + amount + " gold");
    }

    public synchronized void subtractFromGold(int amount) {
        int previousGold = this.gold;
        this.gold -= amount;
        LoggingService loggingService = new LoggingService();
        loggingService.logGoldChanges(this.id, previousGold, this.gold, "Subtracted " + amount + " gold");
    }

    public synchronized int getGold() {
        return this.gold;
    }


    public void saveToDatabase() {
        ClanRepository clanRepository = new ClanRepository();
        clanRepository.createClan(this.name);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
}