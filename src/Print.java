import Task.Task;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Print {

    public void printMainMenu() { // Вывод главного меню
        System.out.println();
        System.out.println("Главное меню.");
        System.out.println("1. Добавить задачу.");
        System.out.println("2. Получить задачи на день.");
        System.out.println("3. Удалить задачу по id.");
        System.out.println("4. Изменить заголовок задачи.");
        System.out.println("5. Изменить описание задачи.");
        System.out.println("6. Сгруппировать задачи по датам.");
        System.out.println("7. Показать удаленные задачи.");
        System.out.println("0. Выход.");
        System.out.print("Введите пункт меню: ");
    }
    public void printMessage(String message) { // Вывод в консоль - отделить модель от представления
        System.out.printf(message);
    }

    public void printAllGroupByDate(Map<LocalDate, LinkedList<Task>> groupByDate) {
        printMessage("Группировка задач по датам.%n");
        for (Map.Entry<LocalDate, LinkedList<Task>> entry : groupByDate.entrySet()) {
            printMessage(String.format("%nЗадачи на дату %td.%tm.%tY:%n", entry.getKey(), entry.getKey(), entry.getKey()));
            entry.getValue().forEach(task -> printMessage(formatTask(task)));
        }
    }

    public void printByDay(List<Task> taskList, String date) {
        printMessage("Задачи на " + date);
        if (taskList.size() != 0) {
            printMessage(":%n");
            taskList.forEach(task -> printMessage(formatTask(task)));
        } else {
            printMessage(" отсутствуют.%n");
        }
    }

    private String formatTask(Task task) {
        return String.format("    #%d %s, %s, %s%n        %s%n", task.getId(), task.getTitle(), task.getType().getType(), task.getFrequency().getFrequencyTitle(), task.getDescription());
    }

    public void printDeleted(List<Task> removedTasks) {
        if (removedTasks.size() != 0) {
            printMessage("Удаленные задачи:%n");
            removedTasks.forEach(t -> printMessage(formatTask(t)));
        } else {
            printMessage("Нет удаленных задач.%n");
        }
    }
}
