/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainSource;

import java.util.ArrayList;

/**
 *
 * @author Luis Martinez
 */
public class KeyFinder {
    private ArrayList<String> variables;
    private ArrayList<String[]> relaciones;
    private String llave;
    
    public KeyFinder(ArrayList<String> variables, ArrayList<String[]> relaciones) {
        this.variables = variables;
        this.relaciones = relaciones;
    }
    
    public String getKey() {
        return llave;
    }
    
    private void findKey() {
        ArrayList<String[]> relacionesTmp = relaciones;
        for (String variable : variables) {
            if (esSalidaDeRelacion(variable)) {
                
            }
        }
    }
    
    private boolean esSalidaDeRelacion(String salida) {
        for (int i = 0; i < relaciones.size(); i++) 
            if (relaciones.get(i)[0].equals(salida))
                return true;
        return false;
    }
    
    private ArrayList<String[]> cambiarSalidaDeRelaciones(String A, String B, ArrayList<String[]> relacionesTmp) {
        for (int i = 0; i < relacionesTmp.size(); i++) 
            if (relacionesTmp.get(i)[0].equals(A))
                relacionesTmp.set(i, new String[] {B, relacionesTmp.get(i)[1]});
        return relacionesTmp;
    }
    
    private boolean sonIgualesLasSalidas(String salida, ArrayList<String[]> relacionesTmp) {
        for (int i = 0; i < relacionesTmp.size(); i++) 
            if (!relacionesTmp.get(i)[0].equals(salida))
                return false;
        return true;
    }
}