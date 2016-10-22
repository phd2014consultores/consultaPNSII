/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnti.pnsii;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 *
 * @author phd2014
 */
public class Propiedades {
private String[] hostname;
private String usuario;
private String clave;
private String keyspace;
private String columnfamily;
public Propiedades(){
Properties prop = new Properties();
InputStream input = null;
try {
input = new FileInputStream("/home/fase1/Apps/apache-tomcat-8.0.27/conf/ConexionDB.properties");
// Cargo el archivo de Propiedades
prop.load(input);
// Se obtienen las propiedades de la base de datos
hostname = prop.getProperty("HOSTNAME").split(",");
usuario=prop.getProperty("USUARIO");
clave=prop.getProperty("CLAVE");
keyspace=prop.getProperty("KEYSPACE");
columnfamily=prop.getProperty("COLUMNFAMILY");
} catch (IOException ex) {
ex.printStackTrace();
} finally {
if (input != null) {
try {
input.close();
} catch (IOException e) {
e.printStackTrace();
}
}
}
}

public String[] getHostname() {
return hostname;
}
public String getUsuario() {
return usuario;
}
public String getClave() {
return clave;
}
public String getKeypace() {
return keyspace;
}
public String getColumnfamily() {
return columnfamily;
}
public void setHostname(String[] hostname) {
this.hostname = hostname;
}
public void setUsuario(String usuario) {
this.usuario = usuario;
}
public void setClave(String clave) {
this.clave = clave;
}
public void setKeyspace(String keyspace) {
this.keyspace = keyspace;
}
public void setColumnfamily(String columnfamily) {
this.columnfamily = columnfamily;
}
}