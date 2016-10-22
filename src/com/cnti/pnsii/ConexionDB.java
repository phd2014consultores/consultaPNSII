/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnti.pnsii;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

/**
 *
 * @author phd2014
 */
public class ConexionDB {
    private static final Propiedades PROPIEDADES = new Propiedades();
    private static final String USUARIO = PROPIEDADES.getUsuario();
    private static final String CLAVE = PROPIEDADES.getClave();
    private static final String[] HOSTNAME = PROPIEDADES.getHostname();
    public static final String KEYSPACE = PROPIEDADES.getKeypace();
    public static final String COLUMNFAMILY = PROPIEDADES.getColumnfamily();
    public static final Cluster CLUSTER = Cluster.builder()
    .addContactPoints(HOSTNAME)
    .withCredentials(USUARIO, CLAVE)
    .build();
    public static final Session CSESSION = ConexionDB.CLUSTER.newSession();
}
