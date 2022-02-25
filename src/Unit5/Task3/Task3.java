package Unit5.Task3;

import java.io.*;
import java.util.*;

public class Task3 {
    static String fileFilms = ".\\src\\Unit5\\Task3\\films.bin";
    static String fileActors = ".\\src\\Unit5\\Task3\\actors.bin";

    public static void main(String[] args) {
        HashMap<String, Film> films;
        HashMap<String, Actor> actors;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        HashSet<Actor> listActor = null;

        films = load(fileFilms);
        actors = load(fileActors);

        boolean inMainMenu = true;
        int mainMenuNum;
        String name, oldname;
        Film film;

        while(inMainMenu){
            mainMenu();
            try{
                mainMenuNum = Integer.parseInt(br.readLine());
            } catch (IOException e) {
                mainMenuNum = -1;
            }
            switch (mainMenuNum){
                case 1:
                    printFilm(films);
                    break;
                case 2:
                    try{
                        System.out.println("введите название фильма");
                        name = br.readLine();
                        if(films.containsKey(name)){
                            System.err.println("Такой фильм уже есть");
                            break;
                        }
                        listActor = getActors(actors);
                        film = new Film(name, listActor);
                        films.put(name, film);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    try{
                        System.out.println("введите название фильма для редактирования");
                        oldname = br.readLine();
                        if (!films.containsKey(oldname)){
                            System.err.println("Такого фильма в коллекции нет");
                            break;
                        }
                        System.out.println("введите новое название фильма");
                        name = br.readLine();
                        listActor = getActors(actors);
                        films.get(oldname).edit(name, listActor);
                        
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    System.out.println("введите название фильма для удаления");
                    try{
                        name = br.readLine();
                        films.remove(name);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 9:
                    save(films, fileFilms);
                    save(actors, fileActors);
                    inMainMenu = false;
                    break;
            }
        }

        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static HashMap load(String filename){
        File f = new File(filename);
        if (f.exists()){
            try(InputStream is = new FileInputStream(f);
                ObjectInputStream ois = new ObjectInputStream(is)) {
                return (HashMap) ois.readObject();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return new HashMap<>();
    }

    static void save(HashMap h, String filename){
        try(OutputStream is = new FileOutputStream(filename);
            ObjectOutputStream ois = new ObjectOutputStream(is)) {
            ois.writeObject(h);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void mainMenu() {
        System.out.println();
        System.out.println("Выберите действие");
        System.out.println("1 - Просмотр списка фильмов");
        System.out.println("2 - Добавление фильма");
        System.out.println("3 - Редактирование фильма");
        System.out.println("4 - Удаление фильма");
        System.out.println("9 - Выйти");
    }

    static HashSet<Actor> getActors(HashMap<String, Actor> actors) throws IOException {
        String actorsname;
        Actor actor;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashSet<Actor> listActor = null;

        System.out.println("введите актеров через запятую");
        actorsname = br.readLine();
        listActor = new HashSet<>();
        for (String actorname : actorsname.split(",")){
            if (actors.containsKey(actorsname)) {
                listActor.add(actors.get(actorsname));
            }
            else{
                actor = new Actor(actorname);
                listActor.add(actor);
                actors.put(actorname, actor);
            }
        }
        return listActor;
    }

    static void printFilm(HashMap<String, Film> films){
        for(Map.Entry f : films.entrySet()){
            System.out.println(f.getValue());
        }
    }
}
