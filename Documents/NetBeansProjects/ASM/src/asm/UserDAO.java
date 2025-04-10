/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asm;

import java.util.*;

/**
 *
 * @author Tung
 */
public class UserDAO {
    List<User> ls = new ArrayList<>();

    public UserDAO() {
        ls.add(new User("daotao","1234",1));
        ls.add(new User("giaovien","1234",2));
    }
    
    public int checkLogin(String user,String pw){
        for (User item : ls) {
            if (item.getUsername().equalsIgnoreCase(user) && item.getPassword().equals(pw)) {
                return item.getRole();
            }
        }
        return 0;
    }
    
}
