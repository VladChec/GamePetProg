package org.example.servlets;

public class Main {

    public static void main(String[] args) {
        // Создаем новый клан и сохраняем его в базе данных
        Clan newClan = new Clan();
        newClan.setName("Example Clan");
        newClan.saveToDatabase();

        // Получаем информацию о клане из базы данных и выводим её
        ClanRepository clanRepository = new ClanRepository();
        Clan clanFromDB = clanRepository.getClanById(1); // Предположим, что id первого созданного клана - 1
        if (clanFromDB != null) {
            System.out.println("Clan ID: " + clanFromDB.getId());
            System.out.println("Clan Name: " + clanFromDB.getName());
            System.out.println("Clan Gold: " + clanFromDB.getGold());
        } else {
            System.out.println("Clan not found.");
        }

        // Обновляем количество золота у клана и сохраняем изменения в базе данных
        if (clanFromDB != null) {
            int newGoldAmount = 150;
            clanFromDB.setGold(newGoldAmount);
            clanRepository.updateClanGold(1, newGoldAmount); // Предположим, что id первого созданного клана - 1
            System.out.println("Clan gold updated.");
        }
    }
}
