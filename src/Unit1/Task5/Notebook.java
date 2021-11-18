package Unit1.Task5;

/**
 * Блокнот, содержит массив записей
 *
 * @author Кузмич Павел
 * @version 1.0
 */
public class Notebook {
    private NotebookRecord record[];
    private int recordPos = 0;

    /**
     * Создает блокнот с заданным количеством записей
     *
     * @param recordCount количество записей в блокноте
     */
    Notebook(int recordCount) {
        record = new NotebookRecord[recordCount];
    }

    /**
     * Добавляет запись в блокнот.
     * Если блокнот переполнен, то выводится соответствующее сообщение
     *
     * @param nr запись для добавления
     */
    void addRecord(NotebookRecord nr) {
        if (recordPos == (record.length)) {
            System.out.println("Блокнот заполнен");
        }
        else {
            record[recordPos++] = nr;
        }
    }

    /**
     * Заменяет запись с переданным номером, если такой записи не существует,
     * то выводится соответствующее сообщение
     *
     * @param nr номер заменяемой записи
     * @param recordNum новая запись
     */
    void changeRecord(NotebookRecord nr, int recordNum) {
        if (recordNum >= 0 && recordNum < recordPos) {
            record[recordNum] = nr;
        }
        else {
            System.out.println("Записи №" + recordNum + " не существует");
        }
    }

    /**
     * Выводит на экран все записи из блокнота
     */
    void show() {
        for (int i = 0; i < recordPos; i++){
            System.out.println(record[i]);
        }
    }
}