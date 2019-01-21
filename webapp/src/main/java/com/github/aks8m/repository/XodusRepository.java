package com.github.aks8m.service;

import jetbrains.exodus.entitystore.PersistentEntityStore;
import jetbrains.exodus.entitystore.PersistentEntityStores;
import jetbrains.exodus.entitystore.StoreTransaction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 2019-01-19
 * aks8m - https://github.com/aks8m
 */
public class XodusRepository {

    private final Path dataStorePath;
    private PersistentEntityStore persistentEntityStore;
    private StoreTransaction storeTransaction;

    public XodusRepository() {
        this.dataStorePath = Paths.get(this.getClass().getProtectionDomain().getCodeSource().getLocation().toString() + "/data");

        try {
            if (!dataStorePath.toFile().exists())
                Files.createDirectory(this.dataStorePath);
        }catch (IOException ioE){
            ioE.printStackTrace();
        }

        this.persistentEntityStore = PersistentEntityStores.newInstance(dataStorePath.toFile());
        this.storeTransaction = this.persistentEntityStore.beginTransaction();
    }

    public StoreTransaction getStoreTransaction() {
        return storeTransaction;
    }
}
