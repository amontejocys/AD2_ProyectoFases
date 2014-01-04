/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

/**
 *
 * @author mayaka
 */
public class Item {
    
    public String id_producto;
    public int cantidad;
    public Double subtotal;
    public Item() {
    
    }
    
    public Item(String id_producto, int cantidad, Double subtotal) {
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }
    
}
