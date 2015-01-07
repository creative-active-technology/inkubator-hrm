/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.sms.gateway.service.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Deni Husni FR
 */
public class IndexingLucen {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void indexAll() throws Exception {
        System.out.println(" Do Indexingggggg");
        try {
            Session session = sessionFactory.getCurrentSession();

            FullTextSession fullTextSession = Search.getFullTextSession(session);
            fullTextSession.createIndexer().startAndWait();
        } catch (HibernateException | InterruptedException e) {
            throw e;
        }
    }
}
