/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repozitorijum;

import java.util.List;

/**
 *
 * @author HP
 */
public interface Repozitorijum<T> {
    
    List<T> getAll(T param, String uslov) throws Exception;
    List<T> getAll(T param) throws Exception;
    void add(T param) throws Exception;
    void edit(T param) throws Exception;
    void delete(T param) throws Exception;
    List<T> getAll();
    T getObject(T param, String uslov) throws Exception;
    T getObject(T param) throws Exception;
    
}
