package com.punojsoft.solidprinciple.singleresponsibility.end;

import com.punojsoft.solidprinciple.singleresponsibility.Store;
import com.punojsoft.solidprinciple.singleresponsibility.User;

//A separate class for handling persistence
public class UserPersistenceService {

    private Store store = new Store();

    public void saveUser(User user) {
        store.store(user);
    }
}
