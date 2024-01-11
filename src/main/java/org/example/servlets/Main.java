package org.example.servlets;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        // Создаем несколько потоков для добавления золота
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        // Создаем новый клан и сохраняем его в базе данных
        LoggingService loggingService = new LoggingService(); // Предположим, что у вас есть соответствующий объект LoggingService
        Clan newClan = new Clan();
//        newClan.setName("Example Clan");
//        newClan.saveToDatabase();

        // Получаем информацию о клане из базы данных и выводим её
        ClanRepository clanRepository = new ClanRepository();
        Clan clanFromDB = clanRepository.get(1); // Предположим, что id первого созданного клана - 1
        if (clanFromDB != null) {
            System.out.println("Clan ID: " + clanFromDB.getId());
            System.out.println("Clan Name: " + clanFromDB.getName());
            System.out.println("Clan Gold: " + clanFromDB.getGold());
        } else {
            System.out.println("Clan not found.");
        }

        // Обновляем количество золота у клана и сохраняем изменения в базе данных
        if (clanFromDB != null) {
            Runnable addGoldTask = () -> {
                UserAddGoldService addGoldService = new UserAddGoldService(clanRepository, clanRepository);
                addGoldService.addGoldToClan(1, clanFromDB.getId(), 10); // Предположим, что userId=1
            };

            // Запускаем несколько потоков одновременно
            for (int i = 0; i < 10; i++) {
                executorService.submit(addGoldTask);
            }

            // Завершаем выполнение всех потоков после их завершения
            executorService.shutdown();
        }
    }
}
