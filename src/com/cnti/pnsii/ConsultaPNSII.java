/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnti.pnsii;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.exceptions.NoHostAvailableException;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import java.util.Map.Entry;

/**
 *
 * @author phd2014
 */
public class ConsultaPNSII {
    public static String consultaPorPropiedad(String clave, String institucion,String propiedad) {

    String key = new String();
String valor = new String();
if (null != clave && null != propiedad && null != institucion) {
if (!clave.isEmpty() && !institucion.isEmpty() && !propiedad.isEmpty()){
ResultSet results = null;
try{
Session session = ConexionDB.CSESSION;
results = session.execute(new QueryBuilder(session.getCluster())
.select()
.all()
.from(ConexionDB.KEYSPACE, ConexionDB.COLUMNFAMILY)
.where(QueryBuilder
.eq("clave", clave))
.and(QueryBuilder
.eq("institucion", institucion))
.and(QueryBuilder
.eq("propiedad", propiedad)));

if (results.isExhausted()) {
valor = "INFO: NO existe Información para el Sujeto";
}else {
for (Entry<String, String> campos : (results.one().getMap("valores",
String.class, String.class)).entrySet()) {
key = campos.getKey();
if (key.equals(propiedad)){
valor = campos.getValue();
}
}
if (valor.isEmpty()){
    valor = "INFO: NO existe la Propiedad para el Sujeto";
}
}
}catch (NoHostAvailableException e) {
e.getStackTrace();
return "ERROR 101: imposible conectar con la base de datos";
}
}else{
valor = "ERROR 200: La clave, institucion o propiedad no pueden ser null";
}
}else{
valor = "ERROR 201: La clave, institucion o propiedad no pueden estar vacias";
}
return valor;
}

/**
* @param clave
* @param institucion
* @return
* @funcion Dado un campo clave para una institución se devuelven todos los valores
* que estan almacenados para esa clave en la institución especifica, el valor de retorno
* es un ResultSet
*/
public static String consultaPorSujeto(String clave, String institucion) {
String valores = "{";
if(clave != null && institucion != null){
if (!clave.isEmpty() && !institucion.isEmpty()){
   ResultSet results = null;
try{
Session session = ConexionDB.CSESSION;
results = session.execute(new QueryBuilder(session.getCluster())
.select()
.all()
.from(ConexionDB.KEYSPACE, ConexionDB.COLUMNFAMILY)
.where(QueryBuilder
.eq("clave", clave))
.and(QueryBuilder
.eq("institucion", institucion)));
if (results.isExhausted()) {
valores = "INFO: NO existe Información para el Sujeto";
}else {
String key = "";
String valor = "";
for (Entry<String, String> campos : (results.one().getMap("valores",
String.class, String.class)).entrySet()) {
    key = campos.getKey();
valor = campos.getValue();
valores = valores + "\""+ key + "\":\"" + valor+"\",";
}
valores = valores.substring(0, valores.length()-1);
valores = valores +"}";
}
}catch (NoHostAvailableException e) {
e.getStackTrace();
return "ERROR 101: imposible conectar con la base de datos";
}
}else{
valores = "ERROR 200: La clave, institucion o propiedad no pueden ser null";

}
}else{
valores = "ERROR 201: La clave, institucion o propiedad no pueden estar vacias";
}
if (valores.equals("{")) {
valores = "ERROR 999: inesperado";
}
return valores;
}
}