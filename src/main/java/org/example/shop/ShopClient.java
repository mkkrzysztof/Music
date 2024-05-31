package org.example.shop;

import org.example.site.auth.Account;

import java.util.ArrayList;
import java.util.List;

public class ShopClient extends Account {
    List<Cart> list = new ArrayList<>();
    public ShopClient(String username, int id) {
        super(username, id);
    }
    public List<Cart> getCarts(){
        return list;
    }
}
