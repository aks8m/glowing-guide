package com.github.aks8m.repository;

import com.github.aks8m.model.Analysis;
import jetbrains.exodus.entitystore.*;

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
    private Entity currentEntity;
    private Entity currentParent;

    public XodusRepository() {
        this.dataStorePath = Paths.get(this.getClass().getProtectionDomain().getCodeSource()
                .getLocation().toString() + "/data");
        try {
            if (!dataStorePath.toFile().exists())
                Files.createDirectory(this.dataStorePath);
        }catch (IOException ioE){
            ioE.printStackTrace();
        }
        this.persistentEntityStore = PersistentEntityStores.newInstance(dataStorePath.toFile());
    }

    public void beginTransactions(Analysis analysis) {
        this.storeTransaction = this.persistentEntityStore.beginTransaction();
        this.currentParent = this.storeTransaction.newEntity("Analysis");
        this.currentParent.setProperty("name", analysis.getName());
        this.currentParent.setProperty("localdatettime", analysis.getLocalDateTime().toString());
    }

    public void endTransactions(){
        this.storeTransaction.commit();
    }

    public XodusRepository addChild(String name) {
        this.currentEntity = this.storeTransaction.newEntity(name);
        this.currentParent.setLink("contains", this.currentEntity);
        return this;
    }

    public XodusRepository addAdult(String name) {
        this.currentEntity = this.storeTransaction.newEntity(name);
        this.currentParent.setLink("contains", this.currentEntity);
        this.currentParent = this.currentEntity;
        return this;
    }

    public XodusRepository addProperty(String name, String value){
        this.currentEntity.setProperty(name, value);
        return this;
    }

    public long getCurrentEntityID(){
        return this.currentEntity.getId().getLocalId();
    }


}
