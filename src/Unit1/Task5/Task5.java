package Unit1.Task5;

public class Task5 {
    public static void main(String[] args) {
        NotebookRecord record;
        Notebook notebook = new Notebook(38);

        record = new NotebookRecord("Первая запись");
        notebook.addRecord(record);

        record = new NotebookRecord("Вторая запись");
        notebook.addRecord(record);

        System.out.println("Содержимое блокнота:");
        notebook.show();

        System.out.println();
        record = new NotebookRecord("Измененная первая запись");
        notebook.changeRecord(record, 0);

        System.out.println("Измененное содержимое блокнота:");
        notebook.show();

        /* для создания документации использовал команду
        javadoc.exe C:\JavaCourse\src\Unit1\Task5\N*.java -d C:\JavaCourse\src\Unit1\Task5\
        */
    }
}
