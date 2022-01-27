package Unit3;

import Unit1.Task5.Notebook;
import Unit1.Task5.NotebookRecord;
import static org.junit.Assert.*;

import Unit2.Task1.Employee;
import org.junit.*;

public class NotebookTest {

    @Test
    public void addRecord(){
        System.out.println("проверка добавления записей в блокнот");
        NotebookRecord[] expected = {new NotebookRecord("первая запись в блокноте"),
                new NotebookRecord("вторая запись в блокноте"),
                new NotebookRecord("третья запись в блокноте")};

        Notebook nb = new Notebook(3);
        for(NotebookRecord r : expected){
            nb.addRecord(r);
        }

        assertArrayEquals(expected, nb.getAllRecord());
    }

    @Test
    public void editRecord(){
        System.out.println("проверка изменения записи в блокноте");

        Notebook nb = new Notebook(2);
        nb.addRecord(new NotebookRecord("первая запись в блокноте"));
        nb.addRecord(new NotebookRecord("вторая запись в блокноте"));

        NotebookRecord expected = new NotebookRecord("измененная запись");
        nb.changeRecord(expected, 1);

        assertSame(expected, nb.getRecord(1));
    }

    @Test
    public void show(){
        System.out.println("проверка вывода содержимого блокнота");
        Notebook nb = new Notebook(5);

        for (int i=0; i<5; i++){
            nb.addRecord(new NotebookRecord("запись №" + i));
        }

        nb.show();
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void notebookOverflow(){
        System.out.println("проверка ошибки при переполнении блокнота");
        Notebook nb = new Notebook(2);
        nb.addRecord(new NotebookRecord("первая запись в блокноте"));
        nb.addRecord(new NotebookRecord("вторая запись в блокноте"));
        nb.addRecord(new NotebookRecord("третья запись в блокноте"));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void editNotExistingRecord(){
        System.out.println("проверка ошибки редактировании несуществующей строки");
        int size = 2;
        Notebook nb = new Notebook(size);
        nb.changeRecord(new NotebookRecord("test"), size);
    }

}
