package com.github.aks8m;

import jetbrains.exodus.entitystore.Entity;
import jetbrains.exodus.entitystore.PersistentEntityStore;
import jetbrains.exodus.entitystore.PersistentEntityStores;
import jetbrains.exodus.entitystore.StoreTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * aks8m
 */
@SpringBootApplication
public class WebApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(WebApp.class, args);
    }

    private void test(){

    }
}
