package org.example.shop;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private int id;
    private int customer_id;
    private List<Pair<Product,Integer>> list = new ArrayList<>();

    public Cart(int id, int customer_id) {
        this.id = id;
        this.customer_id = customer_id;
    }

    public void add(Product product, int count){
        list.add(new Pair<Product,Integer>(product,count));
    }
    public int price(){
        int sum = 0;
        for(Pair<Product,Integer> i: list){
            sum += i.first().price()*i.second();
        }
        return sum;
    }
}
