package com.vgb.demo.secure.repository;

import com.vgb.demo.secure.data.UserAccount;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public class AccountRepository {
    final Map<Long, UserAccount> accountMap = new HashMap<>();
    final Set<String> userNames = new HashSet<>();
    final AtomicLong idKey = new AtomicLong(0L);

    public UserAccount create(UserAccount userAccount) {

        if (userNames.contains(userAccount.getUsername())) {
            throw new RepositoryException("userName already in use");
        }
        userAccount.setId(idKey.incrementAndGet());
        accountMap.put(userAccount.getId(), userAccount);
        return userAccount;
    }

    public UserAccount lookupById(Long id) {
        return accountMap.get(id);
    }


    public UserAccount lookupByUserName(String userName) {
        return accountMap.values().stream().filter(userAccount -> userAccount.getUsername().equals(userName)).findAny().orElse(null);
    }
    public UserAccount update(UserAccount userAccount) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    public UserAccount delete(Long id) {
        throw new UnsupportedOperationException("not yet implemented");
    }
}
