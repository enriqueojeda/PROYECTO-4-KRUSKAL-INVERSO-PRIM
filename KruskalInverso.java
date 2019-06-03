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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author newbie
 */
public class KruskalInverso {
    private Grafo grafo;
    
    private  List<Vertex> vertexes;
    private  List<Edge> edges;

    private LinkedHashMap<Edge, Float> edgesord;
    private LinkedHashMap<Edge, Float> t;//arbol
    private LinkedHashMap<String,ComponenteConectado> cc;//componente conectado

    
    public KruskalInverso(Grafo g)
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
       List<Entry<Edge,Float>> sortedEntries = new ArrayList<Entry<Edge,Float>>(edgesord.entrySet());
    Collections.sort(sortedEntries, 
            new Comparator<Entry<Edge,Float>>() {
                @Override
                public int compare(Entry<Edge,Float> e1, Entry<Edge,Float> e2) {
                    return e2.getValue().compareTo(e1.getValue());
                }
            }
    );
    
    Map<Edge, Float> sortedMap = new LinkedHashMap<Edge, Float>();
            for (Map.Entry<Edge, Float> entry : sortedEntries) {
                sortedMap.put(entry.getKey(), entry.getValue());
        }
            
    for(Edge e:sortedMap.keySet())
    {
            System.out.println(""+e.getWeight());
            t.put(e, e.getWeight());
    }
    
    for(Edge e:sortedMap.keySet())
    {
        //System.out.println("v1:"+e.getVertex1().getName()+" v2:"+e.getVertex2().getName());
        if(!disconnect(e))
        {
            //System.out.println("removing tSize:"+t.size()+" nEdges:"+edges.size());
            t.remove(e);
            removeEdge(e);
            //System.out.println("removed tSize:"+t.size()+" nEdges:"+edges.size());
        }
    }
    
        //System.out.println("**************CC*************");
        String dot="";
        grafo.setName(grafo.getName()+"KruskalInverso");
        dot+="graph "+grafo.getName()+"{ \n";
        for(Edge e:t.keySet())
        {
            System.out.println(""+e.getVertex1().getName()+"->"+e.getVertex2().getName());
            dot+="\n"+e.getVertex1().getName()+" -- "+e.getVertex2().getName()+" [label=\""+e.getWeight()+"\"];";
        }
        dot+="}";
        
    grafo.writeDotFile(dot);
    }
    
    private void removeEdge(Edge er)
    {
        ArrayList<Edge> tmp = new ArrayList<Edge>();
        for(Edge e:edges)
            if(e.getVertex1().getName().equals(er.getVertex1().getName()) &&
                    e.getVertex2().getName().equals(er.getVertex2().getName()))
                    tmp.add(e);
        for(Edge e:tmp)
            edges.remove(e);
    }
    private boolean disconnect(Edge e)
    {
        ArrayList<Edge> tmpe = new ArrayList<Edge>(edges);
        ArrayList<Vertex> tmpv = new ArrayList<Vertex>(vertexes);
        tmpe.remove(e);
        Grafo grafotmp = new Grafo(grafo.getName(),grafo.isSameVertexEdge());
        grafotmp.setEdges(tmpe);
        grafotmp.setVertexes(tmpv);
        
        DFS dfs = new DFS(grafotmp,e.getVertex1());
        int n = dfs.countEdgesDFSND(e.getVertex1());
        //System.out.println("ndfs:"+n+" == "+(vertexes.size()-1));
        if(n==(vertexes.size()-1))
            return false;
        return true;
        
    }
    
    
}
