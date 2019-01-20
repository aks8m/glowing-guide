package com.github.aks8m.service;

import jetbrains.exodus.entitystore.*;

/**
 * 2019-01-19
 * aks8m - https://github.com/aks8m
 */
public class XodusRepository {

    public void write(){

        PersistentEntityStore entityStore = PersistentEntityStores.newInstance(this.getClass().getProtectionDomain().getCodeSource().getLocation().toString());
        final StoreTransaction storeTransaction = entityStore.beginTransaction();

        storeTransaction.newEntity("User");
        storeTransaction.commit();
    }

    public String read(){
        PersistentEntityStore entityStore = PersistentEntityStores.newInstance(this.getClass().getClassLoader().getResource("data").toString());
        final StoreTransaction storeTransaction = entityStore.beginReadonlyTransaction();
        StringBuilder stringBuilder = new StringBuilder();

        final EntityIterable users = storeTransaction.getAll("User");
        for (Entity entity : users){
            stringBuilder.append(entity.getId() + "\r");
        }

        storeTransaction.abort();

        return stringBuilder.toString();
    }
}
