package Unit2.Task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Task1 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        boolean b = true;
        int idEmployee;
        int menuNum;
        String name;
        HashMap<Integer, Employee> listEmployee = new HashMap<>();
        double cost;

        while(b){
            mainMenu();
            menuNum = Integer.parseInt(br.readLine());
            try {
                switch (menuNum) {
                    case 1:
                        idEmployee = inputEmployeeId();
                        if (listEmployee.containsKey(idEmployee)) {
                            System.out.println("Сотрудник с таким id уже заведен");
                            break;
                        }
                        System.out.println("Введите Фамилию сотрудника");
                        name = br.readLine();
                        listEmployee.put(idEmployee, new Employee(idEmployee, name));
                        break;
                    case 2:
                        idEmployee = inputEmployeeId();
                        checkEmployeeExists(listEmployee, idEmployee);
                        stationeryMenu();
                        menuNum = Integer.parseInt(br.readLine());
                        switch (menuNum) {
                            case 1:
                                listEmployee.get(idEmployee).addStationery(new Pen("ручка", 10));
                                break;
                            case 2:
                                listEmployee.get(idEmployee).addStationery(new Notepad("блокнот", 100));
                                break;
                            case 3:
                                listEmployee.get(idEmployee).addStationery(new Marker("маркер", 100, "черный"));
                                break;
                            default:
                                System.out.println("Введено неверное значение");
                        }
                        break;
                    case 3:
                        idEmployee = inputEmployeeId();
                        checkEmployeeExists(listEmployee, idEmployee);
                        System.out.println(listEmployee.get(idEmployee));
                        break;
                    case 4:
                        idEmployee = inputEmployeeId();
                        checkEmployeeExists(listEmployee, idEmployee);
                        System.out.println("Общая стоимость канцелярки - " + listEmployee.get(idEmployee).getCost());
                        break;
                    case 5:
                        idEmployee = inputEmployeeId();
                        checkEmployeeExists(listEmployee, idEmployee);
                        stationeryMenu();
                        menuNum = Integer.parseInt(br.readLine());
                        System.out.println("Введите новую стоимость");
                        cost = Double.parseDouble(br.readLine());
                        listEmployee.get(idEmployee).setStationeryCost(menuNum, cost);
                        break;
                    case 6:
                        System.out.println(listEmployee);
                        break;
                    case 7:
                        testEquals();
                        break;
                    case 9:
                        b = false;
                        break;
                }
            }
            catch(RuntimeException e){
                System.out.println("Произошла ошибка: " + e);
            }
        }
        br.close();
    }

    static void mainMenu() {
        System.out.println();
        System.out.println("Выберите необходимое действие");
        System.out.println("1 - добавить нового сотрудника");
        System.out.println("2 - выдать канцелярку сотруднику");
        System.out.println("3 - вывести информацию по сотруднику");
        System.out.println("4 - вывести стоимость канцелярки для сотрудника");
        System.out.println("5 - изменить стоимость канцелярки");
        System.out.println("6 - вывести информацию по всем сотрудникам");
        System.out.println("7 - проверка equals и hashcode");
        System.out.println("9 - выйти");
    }

    static void stationeryMenu() {
        System.out.println("Выберите канцелярский товар");
        System.out.println("1 - ручка");
        System.out.println("2 - блокнот");
        System.out.println("3 - маркер");
    }

    static void checkEmployeeExists(HashMap<Integer, Employee> list, int key){
        if (!list.containsKey(key)){
            throw new RuntimeException("Сотрудник с таким id не существует");
        }
    }

    static int inputEmployeeId() throws IOException {
        System.out.println("Введите Id сотрудника(числовое значение)");
        return Integer.parseInt(br.readLine());
    }

    static void testEquals(){
        Pen p1 = new Pen("канцелярка", 10);
        Pen p2 = new Pen("канцелярка", 10);
        Notepad n1 = new Notepad("канцелярка", 10);
        Notepad n2 = new Notepad("блокнот", 10);
        System.out.println("p1 эквивалентно p2 " + p1.equals(p2));
        System.out.println("p1 эквивалентно n1 " + p1.equals(n1));
        System.out.println("n1 эквивалентно n2 " + n1.equals(n2));

        System.out.println();
        Employee e1 = new Employee(38, "Петров");
        Employee e2 = new Employee(38, "Петров");
        Employee e3 = new Employee(38, "ПетрОв");
        System.out.println("хешкоды у e1 и e2 равны - " + (e1.hashCode() == e2.hashCode()));
        System.out.println("e1 эквивалентно e2 - " + e1.equals(e2));
        System.out.println("хешкоды у e1 и e3 равны - " + (e1.hashCode() == e3.hashCode()));
        System.out.println("e1 эквивалентно e3 - " + e1.equals(e3));

    }
}
