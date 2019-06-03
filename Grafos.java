/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author newbi
 */
public class Grafos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException, CloneNotSupportedException {
        // TODO code application logic here
//      EL CODIGO SE COMIENZA A EJECUTAR POR BLOQUE EN CADA TIPO DE GRAFO        
        /***************************ErdosRenyi***************************/
//        ErdosRenyi er = new ErdosRenyi(5,8,false);//vertexes, edges, sameVertexEdge
//        er.grafoNoDirigido();
//        er.exportGraphVizND();
//        
//        System.out.println("PRIM");
//        Prim p = new Prim(er.grafond);
//        p.execute();
        
//        System.out.println("Kruskal");
//        Kruskal k = new Kruskal(er.grafond);
//        k.execute();
//        
//        System.out.println("Kruskal Inverso");
//        KruskalInverso ki = new KruskalInverso(er.grafond);
//        ki.execute();


        
        /****************************BarabasiAlbert**************************/
//        BarabasiAlbert ba = new BarabasiAlbert(5,8,false);//vertexes, d, sameVertexEdge
//        ba.grafoNoDirigido();
//        ba.exportGraphVizND();
//        
//        System.out.println("Prim");
//        Prim p = new Prim(ba.grafond);
//        p.execute();
        
//        System.out.println("Kruskal");
//        Kruskal k = new Kruskal(ba.grafond);
//        k.execute();
//        
//        System.out.println("Kruskal Inverso");
//        KruskalInverso ki = new KruskalInverso(ba.grafond);
//        ki.execute();
        
        /***************************Geografico***************************/
        Geografico g = new Geografico(5,0.6,false);//vertexes, d, sameVertexEdge
        g.grafoNoDirigido();
        g.exportGraphVizND();
        
        System.out.println("Prim");
        Prim p = new Prim(g.grafond);
        p.execute();
        
//        System.out.println("Kruskal");
//        Kruskal k = new Kruskal(g.grafond);
//        k.execute();
//        
//        System.out.println("Kruskal Inverso");
//        KruskalInverso ki = new KruskalInverso(g.grafond);
//        ki.execute();
        
        /***************************Gilbert***************************/
//        Gilbert gi = new Gilbert(5,0.6,false);//vertexes, d, sameVertexEdge
//        gi.grafoNoDirigido();
//        gi.exportGraphVizND();
//
//        System.out.println("Prim");
//        Prim p = new Prim(gi.grafond);
//        p.execute();
        
//        System.out.println("Kruskal");
//        Kruskal k = new Kruskal(gi.grafond);
//        k.execute();
        
//        System.out.println("Kruskal Inverso");
//        KruskalInverso ki = new KruskalInverso(gi.grafond);
//        ki.execute();
    }
    
}
