package org.example.servlets;

public class UserAddGoldService  {

    private final ClanService clans;
    private final ClanRepository clanRepository;

    public UserAddGoldService(ClanService clans, ClanRepository clanRepository) {
        this.clans = clans;
        this.clanRepository = clanRepository;
    }

    public void addGoldToClan(long userId, long clanId, int gold) {
        Clan clan = clans.get(clanId);
        clan.addToGold(gold);
        clanRepository.updateClanGold(clanId, clan.getGold());
        // здесь можно добавить логику отслеживания изменений и сохранения
    }
}
