package asus.example.com.exercise5;


import java.util.List;

public class FilmsResponse {

    List<Group> groups;

    class Group{
        List<Item> items;
    }


    class Item{
        Film film;
    }

}
