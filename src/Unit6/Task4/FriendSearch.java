package Unit6.Task4;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FriendSearch implements SearchService {
    @Override
    public List<User> searchForFriendsInWidth(User me, String name) {
        if (me.getFriends() == null)
            return new ArrayList<>();
        ArrayDeque<User> ad = new ArrayDeque<>(me.getFriends()); // добавили всех друзей
        Set<User> result = new HashSet<>();
        User firstelement;
        // друзья, которых мы уже рассматривали
        Set<User> checkedUser = new HashSet<>();

        // пока очередь не пустая
        while (!ad.isEmpty()){
            // вытаскиваем первого друга из очереди
            firstelement = ad.pollFirst();
            // добавляем друга в список просмотренных
            checkedUser.add(firstelement);
            // если совпадает, то добавляем к результату
            if (firstelement.getName().equals(name))
                result.add(firstelement);
            // если есть друзья, то надо поместить их в очередь
            if (firstelement.getFriends() != null)
                // в очередь помещаем тольке тех, кого еще не проверяли
                ad.addAll(firstelement.getFriends()
                              .stream()
                              .filter(e -> !checkedUser.contains(e))
                              .collect(Collectors.toList()));
        }
        return new ArrayList<>(result);
    }

    @Override
    public List<User> searchForFriendsInDepth(User me, String name) {
        if (me.getFriends() == null)
            return new ArrayList<>();
        // создаем HashMap, чтобы хранить количество отработанных друзей
        HashMap<User, Integer> checkedUser = new HashMap<>();
        // создаем очередь
        ArrayDeque<User> ad = new ArrayDeque<>();
        // результат
        Set<User> result = new HashSet<>();
        User lastElement;
        int checkedFriendsCount;

        ad.add(me); // в вершину добавляем юзера, с которого начинается поиск
        checkedUser.put(me,0);

        // пока в первом элементе есть необработанные друзья
        while(!ad.isEmpty()) {
            // берем из очереди последний элемент
            lastElement = ad.getLast();
            // получаем кол-во уже проверенных юзеров
            checkedFriendsCount = checkedUser.get(lastElement);
            // если у этого элемента мы просмотрели всех друзей
            if (lastElement.getFriends() == null || checkedFriendsCount == lastElement.getFriends().size()) {
                // проверяем подходит ли он нам
                if (lastElement.getName().equals(name))
                    result.add(lastElement);
                ad.removeLast();
            }
            // иначе добавляем в очередь  следующего друга
            else{
                // увеличиваем кол-во просмотренных друзей
                checkedUser.put(lastElement, checkedFriendsCount + 1);
                // получаем следующего друга
                lastElement = lastElement.getFriends().get(checkedFriendsCount);
                // добавляем его в очередь, если его там еще не было
                if (!checkedUser.containsKey(lastElement)) {
                    ad.add(lastElement);
                    checkedUser.put(lastElement, 0);
                }
            }
        }
        return new ArrayList<>(result);
    }
}
