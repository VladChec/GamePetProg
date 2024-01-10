package org.example;

import org.example.servlets.Clan;
import org.example.servlets.ClanRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClanRepositoryTest {

    @Test
    public void testCreateAndGetClan() {
        ClanRepository clanRepository = new ClanRepository();

        // Создаем клан
        clanRepository.createClan("Test Clan");

        // Получаем клан и проверяем, что он существует
        Clan clan = clanRepository.getClanById(1); // Предположим, что id первого созданного клана - 1
        Assertions.assertNotNull(clan);
        Assertions.assertEquals("Test Clan", clan.getName());
    }

    @Test
    public void testUpdateClanGold() {
        ClanRepository clanRepository = new ClanRepository();

        // Создаем клан
        clanRepository.createClan("Test Clan");

        // Обновляем количество золота у клана и проверяем изменение
        int newGoldAmount = 100;
        clanRepository.updateClanGold(1, newGoldAmount); // Предположим, что id первого созданного клана - 1
        Clan updatedClan = clanRepository.getClanById(1);
        Assertions.assertNotNull(updatedClan);
        Assertions.assertEquals(newGoldAmount, updatedClan.getGold());
    }
}
