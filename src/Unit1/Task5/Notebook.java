package Unit1.Task5;

/**
 * Блокнот, содержит массив записей
 *
 * @author Кузмич Павел
 * @version 1.0
 */
public class Notebook {
    private NotebookRecord[] record;
    private int recordPos = 0;

    /**
     * Создает блокнот с заданным количеством записей
     *
     * @param recordCount количество записей в блокноте
     */
    public Notebook(int recordCount) {
        record = new NotebookRecord[recordCount];
    }

    /**
     * Добавляет запись в блокнот.
     * Если блокнот переполнен, то выводится соответствующее сообщение
     *
     * @param nr запись для добавления
     */
    public void addRecord(NotebookRecord nr) {
        record[recordPos++] = nr;
    }

    /**
     * Заменяет запись с переданным номером, если такой записи не существует,
     * то выводится соответствующее сообщение
     *
     * @param nr номер заменяемой записи
     * @param recordNum новая запись
     */
    public void changeRecord(NotebookRecord nr, int recordNum) {
        record[recordNum] = nr;
    }

    /**
     * Выводит на экран все записи из блокнота
     */
    public void show() {
        for (int i = 0; i < recordPos; i++){
            System.out.println(record[i].getText());
        }
    }

    /**
     * Возвращает все записи
     *
     */
    public NotebookRecord[] getAllRecord(){
        return record;
    }

    /**
     * Возвращает запись по номеру
     *
     * @param nr номер заменяемой записи
     */
    public NotebookRecord getRecord(int nr){
        return record[nr];
    }
}