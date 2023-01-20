import Task.*;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        TaskService taskService = new TaskService();

        taskService.add(new DailyTask("Дайли", "Нормально так отдохнуть", LocalDateTime.now(), Type.PERSONAL));
        taskService.add(new MonthlyTask("Монтли", "Чу-чуть поработать над работой", LocalDateTime.now().plusDays(1), Type.WORK));
        taskService.add(new WeeklyTask("Викли", "Вообще не работать над работой сидеть пялить паблики", LocalDateTime.now().plusDays(1), Type.WORK));
        taskService.add(new OneTimeTask("ОнеТиме", "Взять кисти и краски и исполнить давнюю мечту написать на заборе большими буквами слово", LocalDateTime.now().plusDays(3), Type.WORK));
        taskService.add(new YearlyTask("Ырле", "Уехать на пляж пляжный к морю морскому", LocalDateTime.now().plusDays(3), Type.WORK));

        Menu menu = new Menu(taskService);
        menu.start();
    }
}