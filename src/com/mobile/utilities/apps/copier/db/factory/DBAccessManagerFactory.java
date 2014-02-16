package com.mobile.utilities.apps.copier.db.factory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBAccessManagerFactory {

    private static final EntityManagerFactory emfInstance =
            Persistence.createEntityManagerFactory("transactions-optional");

        private DBAccessManagerFactory() {}

        public static EntityManagerFactory getEntityManagerFactory() {
            return emfInstance;
        }
}
