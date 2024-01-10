package org.example.servlets;

public class TaskService {

    private final ClanService clans;

    public TaskService(ClanService clans) {
        this.clans = clans;
    }

    void completeTask(long clanId, long taskId, int gold) {
        Clan clan = clans.get(clanId);
        clan.addToGold(gold);
        // здесь можно добавить логику отслеживания изменений и сохранения
    }
}