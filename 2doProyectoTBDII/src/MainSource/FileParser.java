/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainSource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Luis Martinez
 */
public class FileParser {
    ArrayList<String> variables;
    ArrayList<String[]> relaciones;
    
    public FileParser() {
        variables = new ArrayList();
        relaciones = new ArrayList();
        loadData();
        encontrarTodasLasRelaciones();
        testData();
    }
    
    private boolean loadData() {
        try {
            String line, data;
            String[] vData, rData;
            BufferedReader reader = new BufferedReader(new FileReader(new File("./src/resources/dataTest.txt")));
            for (int lineIndex = 0; (line = reader.readLine()) != null; lineIndex++) {
                data = line.replaceAll(" ", "");
                if (lineIndex == 0) {
                    if ((data.charAt(0) == 'r' || data.charAt(0) == 'R') && (data.charAt(1) == '{' && data.charAt(data.length() - 1) == '}'))
                        variables.addAll(Arrays.asList(data.substring(2, data.length() - 1).split(",")));
                    else 
                        System.err.println("La sintaxis de la relacion no es correcta");
                } else {
                    if (data.contains("->"))
                        relaciones.add(data.split("->"));
                    else 
                        System.err.println("La sintaxis de la dependencia #" + lineIndex + " no es correcta");
                }  
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
    private void encontrarTodasLasRelaciones() {
        ArrayList<String[]> nuevasRelaciones = new ArrayList();
        String[] variablesSalida, variablesLlegada;
        String[] nRelacion1, nRelacion2; 
        for (String[] relacion : relaciones) {
            
            if (relacion[0].contains(",") && !relacion[1].contains(",")) {
                //A,B -> C
                variablesSalida = relacion[0].split(",");
                for (int index = 0; index < variablesSalida.length; index++) 
                    nuevasRelaciones.add(new String[] {variablesSalida[index], relacion[1]});
            } else if (relacion[0].contains(",") && relacion[1].contains(",")) {
                //A,B -> C,D
                variablesSalida = relacion[0].split(",");
                variablesLlegada = relacion[1].split(",");
                for (int indexSalida = 0; indexSalida < variablesSalida.length; indexSalida++) 
                    for (int indexLlegada = 0; indexLlegada < variablesLlegada.length; indexLlegada++) 
                        nuevasRelaciones.add(new String[] {variablesSalida[indexSalida], variablesLlegada[indexLlegada]});
            } else if (!relacion[0].contains(",") && relacion[1].contains(",")) {
                //A -> B,C
                variablesLlegada = relacion[1].split(",");
                for (int index = 0; index < variablesLlegada.length; index++) 
                    nuevasRelaciones.add(new String[] {relacion[0], variablesLlegada[index]});
            } else if (!relacion[0].contains(",") && !relacion[1].contains(",")) {
                //A -> B
                nuevasRelaciones.add(relacion);
            }
        }
        relaciones.clear();
        relaciones = nuevasRelaciones;
    }
    
    private boolean variablesEnRelaciones() {
        boolean falta = false;
        for (int i = 0; i < relaciones.size(); i++) 
            if (!variables.contains(relaciones.get(i)[0]) || !variables.contains(relaciones.get(i)[1])) {
                falta = true;
                break;
            }
        
        if (!falta)
            return true;
        return false;
    }
    
    private void testData() {
        printVariables();
        System.out.println("\n");
        printRelaciones();
    }
    
    public void printVariables() {
        System.out.println("Las variables son: ");
        for (int i = 0; i < variables.size(); i++) {
            System.out.print(variables.get(i) + " ");
        }
    }
    
    public void printRelaciones() {
        System.out.println("Las relaciones son: ");
        for (int i = 0; i < relaciones.size(); i++) 
            System.out.println(relaciones.get(i)[0] + " -> " + relaciones.get(i)[1]);
    }
    
}
