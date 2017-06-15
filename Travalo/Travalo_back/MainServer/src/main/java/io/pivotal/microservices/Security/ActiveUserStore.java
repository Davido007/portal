package io.pivotal.microservices.Security;

/**
 * Created by DPLICHTA on 4/13/2017.
 */

import java.util.ArrayList;
import java.util.List;

public class ActiveUserStore {

    public List<String> users;

    public ActiveUserStore() {
        users = new ArrayList<String>();
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }
}