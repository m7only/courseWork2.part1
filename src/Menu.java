import Task.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

public class Menu {
    private final TaskService taskService;
    private final Print print;
    private final Scanner scanner;
    private boolean isExit; // по false - вываливаемся из цикла на выход
    private int currentMenu; // для выбора отображаемого меню, 0 - главное меню

    public Menu(TaskService taskService) {
        this.taskService = taskService;
        this.print = new Print();
        this.isExit = true;
        this.scanner = new Scanner(System.in);
        this.currentMenu = 0;
    }

    public void start() {
        while (isExit) { // цикл формирования меню
            String command;
            if (currentMenu == 0) { // Главное меню
                print.printMainMenu();
                command = scanner.nextLine();
                print.printMessage("%n");
                switch (command) {
                    case "0": // Выход
                        isExit = false;
                        break;
                    case "1": // Добавить задачу.
                        addTask();
                        break;
                    case "2": // Получить задачи на день.
                        showByDay();
                        break;
                    case "3": // Удалить задачу по id.
                        deleteTask();
                        break;
                    case "4": // Изменить заголовок задачи.
                        updateTitle();
                        break;
                    case "5": // Изменить описание задачи
                        updateDescription();
                        break;
                    case "6": // Сгруппировать задачи.
                        showAllGroupByDate();
                        break;
                    case "7": // Показать удаленные задачи
                        showDeleted();
                        break;
                    default: // Некорректный ввод
                        print.printMessage("Неверный выбор, попробуйте снова.%n");
                        break;
                }
            } else {
                currentMenu = 0;
            }
        }

    }

    private void showDeleted() {
        print.printDeleted(taskService.getRemovedTasks());
    }

    private void updateTitle() {
        showAllGroupByDate();
        print.printMessage("Введите ID редактируемой задачи: ");
        int updateId = getInt(0, taskService.getSize() - 1);
        print.printMessage("Текущий заголовок: " + taskService.getTaskById(updateId).getTitle() + "%n");
        Scanner updateTitle = new Scanner(System.in);
        print.printMessage("Введите новой заголовок: ");
        String title = updateTitle.nextLine();
        Task task = taskService.updateTitle(updateId, title);
        print.printMessage(String.format("Заголовок задачи #%d изменен на %s.%n", task.getId(), task.getTitle()));
    }

    private void updateDescription() {
        showAllGroupByDate();
        print.printMessage("Введите ID редактируемой задачи: ");
        int updateId = getInt(0, taskService.getSize() - 1);
        print.printMessage("Текущее описание: " + taskService.getTaskById(updateId).getDescription() + "%n");
        Scanner updateDescription = new Scanner(System.in);
        print.printMessage("Введите новое описание: ");
        String description = updateDescription.nextLine();
        Task task = taskService.updateDescription(updateId, description);
        print.printMessage(String.format("Описание задачи #%d изменено на %s.%n", task.getId(), task.getDescription()));
    }

    private void showAllGroupByDate() {
        print.printAllGroupByDate(taskService.getAllGroupByDate());
    }

    private void showByDay() {
        print.printMessage("Получение задач на день.%n");
        print.printMessage("Введите дату в формате ДД.ММ.ГГГГ: ");
        Scanner scannerByDay = new Scanner(System.in);
        String dateTime = scannerByDay.nextLine();
        LocalDate localDate = LocalDate.parse(dateTime, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        print.printByDay(taskService.getAllByDate(localDate), dateTime);
    }

    private void addTask() {
        Scanner scannerAdd = new Scanner(System.in);
        print.printMessage("Добавление задачи.%nВведите название: ");
        String title = scannerAdd.nextLine();
        print.printMessage("Введите описание: ");
        String description = scannerAdd.nextLine();
        print.printMessage("Введите дату и время в формате ДД.ММ.ГГГГ ЧЧ ММ : ");
        String dateTime = scannerAdd.nextLine();
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("dd.MM.yyyy H m"));
        print.printMessage("Выберите периодичность:%n");
        Arrays.stream(Frequency.values()).forEach(s -> print.printMessage((s.ordinal() + 1) + ". " + s.getFrequencyTitle() + "%n"));
        int frequencyIndex = getInt(1, Frequency.getLastIndex() + 1) - 1;
        Frequency frequency = Frequency.getFrequency(frequencyIndex);
        print.printMessage("Выберите статус:%n");
        Arrays.stream(Type.values()).forEach(s -> print.printMessage((s.ordinal() + 1) + ". " + s.getType() + "%n"));
        int typeIndex = getInt(1, Type.getLastIndex() + 1) - 1;
        Type type = Type.getType(typeIndex);
        switch (frequency) {
            case ONETIME:
                taskService.add(new OneTimeTask(title, description, localDateTime, type));
                break;
            case DAILY:
                taskService.add(new DailyTask(title, description, localDateTime, type));
                break;
            case WEEKLY:
                taskService.add(new WeeklyTask(title, description, localDateTime, type));
                break;
            case MONTHLY:
                taskService.add(new MonthlyTask(title, description, localDateTime, type));
                break;
            case YEARLY:
                taskService.add(new YearlyTask(title, description, localDateTime, type));
                break;
        }
    }

    private void deleteTask() {
        showAllGroupByDate();
        print.printMessage("Введие ID удаляемой задачи: ");
        int removeId = getInt(0, taskService.getSize() - 1);
        print.printMessage(String.format("Задача №%d удалена.%n", taskService.remove(removeId).getId()));
    }

    private int getInt() { // Проверка введенного в консоли значения на число. Зацикливаем, пока юзверь не введет число.
        Scanner scannerGetInt = new Scanner(System.in);
        boolean inputCheck = true;
        String input = scannerGetInt.nextLine();
        while (inputCheck) {
            if (isInteger(input) && Integer.parseInt(input) >= 0) {
                inputCheck = false;
            } else {
                print.printMessage("Не верный ввод, попробуйте снова. Введите число: ");
            }
        }
        return Integer.parseInt(input);
    }

    private int getInt(int min, int max) { // Проверка введенного в консоли значения на число. Зацикливаем, пока юзверь не введет число.
        int result = getInt();
        while (!(result >= min && result <= max)) {
            print.printMessage("Не верный ввод, попробуйте снова. Введите число в диапазоне от " + min + " до " + max + ": ");
            result = getInt();
        }
        return result;
    }

    private boolean isInteger(String string) { // Проверка введеной строки на целочисленное значение
        try {
            Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
