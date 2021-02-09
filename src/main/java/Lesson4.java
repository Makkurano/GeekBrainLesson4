import java.util.Random;
import java.util.Scanner;

public class Lesson4 {
    public static void main(String[] args) {

        game();

    }

    private static char[][] map;
    private static final int SIZE = 3;
    private static final int DOTS_TO_WIN = 3;
    private static final char DOT_EMPTY = '•';
    private static final char DOT_X = 'X';
    private static final char DOT_O = 'O';

    private static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    // Печать игрового поля
    private static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " "); // Выводим горизонтальную линейку
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " "); // Выводим пробелы
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " "); // Выводим матрицу точек DOT_EMPTY
            }
            System.out.println(); // Перенос строки
        }
        System.out.println(); // Перенос строки
    }

    // Проверка занятости ячейки
    private static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) { // Проверка на границы
            System.out.println("Координата вне поля");
            return false;
        }
        if (map[y][x] == DOT_EMPTY) {
            return true;
        }
        System.out.println("Координата уже занята");
        return false;
    }

    // Проверка наполненности
    public static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    // Ход компьютера
    private static void aiTurn() {
        Random rand = new Random();
        int x, y;
        do {
            x = rand.nextInt(SIZE); // Берем рандомное число до 2
            y = rand.nextInt(SIZE); // Берем рандомное число до 2
        } while (!isCellValid(x, y)); // Проверка на границы
        System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
        map[y][x] = DOT_O; // Если ячейка свободна, пишем 0 в указанной пользователем координате
    }

    // Ход игрока
    private static void humanTurn() {
        Scanner sc = new Scanner(System.in);
        int x, y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = sc.nextInt() - 1; // делаем поправку на ноль
            y = sc.nextInt() - 1; // делаем поправку на ноль
        } while (!isCellValid(x, y));
        map[y][x] = DOT_X; // Если ячейка свободна, пишем Х в указанной пользователем координате
    }

    // Основной метод программы
    private static void game() {
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (checkWin(DOT_X)) {
                System.out.println("Победил человек");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
            aiTurn();
            printMap();
            if (checkWin(DOT_O)) {
                System.out.println("Победил Искуственный Интеллект");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Игра закончена");
    }

    // Условие победы
    private static boolean checkWin(char symb) {
        int counterDiag = 0;
        int counterOposDiag = 0;
        int counterRow = 0;
        int counterColumn = 0;
            for (int x = 0; x < SIZE; x++) {
                if (map[x][x] == symb) { // Проверка левой диагонали
                    counterDiag++;
                }

                int lastY = ((SIZE - 1) - x); // В цикле вычисляю последний элемент J со сдвигом на предыдущий.
                if (map[x][lastY] == symb) { // Проверка правой диагонали
                    counterOposDiag++;
                }

                for (int y = 0; y < SIZE; y++) {
                    if (map[x][y] == symb && map[x][0] == map[x][y]) {
                        counterRow++;
                        System.out.println(counterRow);
                            }
                }

            }
        if (counterDiag == SIZE || counterOposDiag == SIZE || counterRow == SIZE) {
            return true;
        }
        return false;
    }

}
