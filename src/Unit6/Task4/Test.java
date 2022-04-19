package Unit6.Task4;

import org.junit.Before;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

import java.util.List;
import java.util.stream.Collectors;

public class Test {
    User user1 = new User("Василий");
    User user2 = new User("Пётр");
    User user3 = new User("Надежда");
    User user4 = new User("Наташа");
    User user5 = new User("Сергей");
    User user6 = new User("Наташа");
    User user7 = new User("Роман");
    User user8 = new User("Михаил");

    @Before
    public void setFriends() {
        user1.setFriends(asList(user2, user5, user7));
        user2.setFriends(asList(user3, user4));
        user5.setFriends(asList(user6));
        user7.setFriends(asList(user8));
        user6.setFriends(asList(user1)); // последний элемент содержит в друзьях первый элемент
    }

    @org.junit.Test
    public void searchForFriendsInWidthTest(){
        FriendSearch friendSearch = new FriendSearch();

        assertEquals(friendSearch.searchForFriendsInWidth(user1, "Наташа").size(), 2);
        assertTrue(friendSearch.searchForFriendsInWidth(user7, "Наташа").isEmpty());
        assertTrue(friendSearch.searchForFriendsInWidth(user8, "Наташа").isEmpty());
    }

    @org.junit.Test
    public void searchForFriendsInDepthTest(){
        FriendSearch friendSearch = new FriendSearch();

        assertEquals(friendSearch.searchForFriendsInDepth(user1, "Наташа").size(), 2);
        assertTrue(friendSearch.searchForFriendsInDepth(user7, "Наташа").isEmpty());
        assertTrue(friendSearch.searchForFriendsInDepth(user8, "Наташа").isEmpty());
    }
}
