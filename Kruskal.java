/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import static jdk.nashorn.internal.objects.NativeArray.map;
import static jdk.nashorn.internal.objects.NativeDebug.map;

/**
 *
 * @author newbie
 */
public class Kruskal extends Grafo{
    private Grafo grafo;
    
    private  List<Vertex> vertexes;
    private  List<Edge> edges;

    private LinkedHashMap<Edge, Float> edgesord;
    private LinkedHashMap<Edge, Float> t;//arbol
    private LinkedHashMap<String,ComponenteConectado> cc;//componente conectado

    
    public Kruskal(Grafo g)
    {
        this.grafo = g;
        this.vertexes = g.getVertexes();
        this.edges = g.getEdges();
        this.cc = new LinkedHashMap<String,ComponenteConectado>();
    }
    
    
    public void execute() throws IOException, InterruptedException
    {
        
        t = new LinkedHashMap<Edge, Float>();
        edgesord = new LinkedHashMap<Edge, Float>();

       
        for(Edge e:edges)
            edgesord.put(e, e.getWeight());
        
        List<Map.Entry<Edge, Float>> entries =  new ArrayList<Map.Entry<Edge, Float>>(edgesord.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<Edge,Float>>() 
        {
        public int compare(Map.Entry<Edge, Float> a, Map.Entry<Edge, Float> b){
            return a.getValue().compareTo(b.getValue());
        }
        });

        Map<Edge, Float> sortedMap = new LinkedHashMap<Edge, Float>();
            for (Map.Entry<Edge, Float> entry : entries) {
                sortedMap.put(entry.getKey(), entry.getValue());
        }
    
    
    
    
    //inicializa componente conectado
    for(Vertex v:vertexes)
    {
        System.out.println(v.getName()+" added to CC");
        cc.put(v.getName(),new ComponenteConectado(v));
    }

    for(Edge e:sortedMap.keySet())
    {
        System.out.println("u:"+e.getVertex1().getName()+" v:"+e.getVertex2().getName()+" w:"+e.getWeight());
        
        if(!isSameSet(e.getVertex1(),e.getVertex2()))
        {
           
                System.out.println("u:");
                printSet(cc.get(e.getVertex1().getName()).getCc());
                System.out.println("V:");
                printSet(cc.get(e.getVertex2().getName()).getCc());
                
                if(t.size()<vertexes.size()-1)
                    t.put(e, e.getWeight());
                
                ArrayList<Vertex> vxsu = new ArrayList<Vertex>(cc.get(e.getVertex1().getName()).getCc());
                ArrayList<Vertex> vxsv = new ArrayList<Vertex>(cc.get(e.getVertex2().getName()).getCc());
                cc.get(e.getVertex1().getName()).addVertexes(vxsv);
                cc.get(e.getVertex2().getName()).addVertexes(vxsu);
            
        }
    }
    
    String dot="";
    grafo.setName(grafo.getName()+"Kruskal");
    dot+="graph "+grafo.getName()+"{ \n";
    for(Edge e:t.keySet())
    {
            System.out.println(e.getVertex1().getName()+"->"+e.getVertex2().getName()+" "+e.getWeight());
            dot+="\n"+e.getVertex1().getName()+" -- "+e.getVertex2().getName()+" [label=\""+e.getWeight()+"\"];";
    }
    dot+="}";
        
    grafo.writeDotFile(dot);
    }
    
    
    private boolean isSameSet(Vertex u, Vertex v)
    {        
        if(cc.get(u.getName().trim()).containsV(v) || cc.get(v.getName().trim()).containsV(u))
            return true;
        return false;
    }
    
    private void printSet(ArrayList<Vertex> vtxs)
    {
        for(Vertex v: vtxs)
            System.out.println(""+v.getName());
    }
    
    
}
