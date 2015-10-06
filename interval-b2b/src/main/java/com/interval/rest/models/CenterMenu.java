package com.interval.rest.models;

import com.interval.dao.models.Category;
import com.interval.dao.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Muthuraj on 9/30/2015.
 */
public class CenterMenu {

    private List<Menu> menuList = new ArrayList<Menu>();

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public void tranformToMenu(Map<Category, List<Product>> categoryMap){
        for(Map.Entry<Category, List<Product>> entry : categoryMap.entrySet()){
            Category category = entry.getKey();
            Menu menu = new Menu(category.getId(), category.getName(), category.getDescription());
            for(Product product : entry.getValue()){
                Item item = new Item(product.getId(), product.getName(), product.getDescription(), product.getImage(),
                        product.getPrice());
                menu.getItems().add(item);
            }
            menuList.add(menu);
        }
    }
}

class Menu{
    private Integer id;
    private String name;
    private String description;
    private List<Item> items = new ArrayList<Item>();

    public Menu(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}

class Item {
    private Integer id;
    private String name;
    private String description;
    private String image;
    private double price;

    public Item(Integer id, String name, String description, String image, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}