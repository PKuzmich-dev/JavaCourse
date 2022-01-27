package Unit1.Task5;

/**
 * Запись. Может использоваться для заполнения блокнота
 *
 * @author Кузмич Павел
 * @version 1.0
 */
public class NotebookRecord {
    private String text;

    /**
     * Создает запись
     *
     * @param text Строка записи
     */
    public NotebookRecord(String text) {
        this.text = text;
    }

    /**
     * Возвращает строку записи
     *
     * @return строка записи
     */
    public String toString() {
        return text;
    }

    public String getText(){
        return text;
    }
}
