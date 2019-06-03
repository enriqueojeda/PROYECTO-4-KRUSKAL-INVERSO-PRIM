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

/**
 *
 * @author newbie
 */
public class Prim {
    private Grafo grafo;
    
    private  ArrayList<Vertex> vertexes;
    private  ArrayList<Edge> edges;
    private  ArrayList<Vertex> Q;
    private  ArrayList<Vertex> S;
    
    private LinkedHashMap<Edge, Float> edgesord;
    private LinkedHashMap<String, Edge> t;//arbol
    private LinkedHashMap<String,ComponenteConectado> cc;//componente conectado

    
    public Prim(Grafo g)
    {
        this.grafo = g;
        this.vertexes = g.getVertexes();
        this.edges = g.getEdges();
        Q = new ArrayList<Vertex>();
        S = new ArrayList<Vertex>();
        t = new LinkedHashMap<String, Edge>();
    }
    
    public void execute() throws IOException, InterruptedException
    {
        
        
        for(Vertex v:vertexes)
        {
            v.setRealWeight(Float.MAX_VALUE);
            Q.add(v);
            t.put(v.getName(),null);
        }
        
        grafo.setVertexes(Q);
        
        while(!Q.isEmpty())
        {
            Vertex u = getMin(Q);
            Vertex mintmp = new Vertex();
            Q.remove(u);
            Vertex v;
            S.add(u);
            
            for(Edge e:grafo.DegArrayListEdge(u))
            {
                if(e.getVertex1().getName().equals(u.getName()))//v1=u
                    v = e.getVertex2();
                else//v2=u
                    v = e.getVertex1();
//                mintmp=v;
                //System.out.println(""+u.getName()+" -> "+v.getName());
                //System.out.println(e.getWeight()+"<"+v.getRealWeight());
                if(!vIsInS(v) && e.getWeight()<v.getRealWeight())
                {
                        grafo.getVertex(v.getName()).setRealWeight(e.getWeight());
                        t.put(v.getName(), e);
                }
                
            }
//            System.out.println("min:"+u.getName()+" -> "+mintmp.getName());
        }    
        
    String dot="";
    grafo.setName(grafo.getName()+"Prim");
    dot+="graph "+grafo.getName()+"{ \n";
    for(String v:t.keySet())
    {
        if(t.get(v)!=null)
        {
            System.out.println(t.get(v).getVertex1().getName()+" -> "+t.get(v).getVertex2().getName()+" "+t.get(v).getWeight());
            dot+="\n"+t.get(v).getVertex1().getName()+" -- "+t.get(v).getVertex2().getName()+" [label=\""+t.get(v).getWeight()+"\"];";
            
        }
       
    }
    dot+="\n}";
    grafo.writeDotFile(dot);
    }
    
    private Vertex getMin(ArrayList<Vertex> q)
    {
        Vertex tmp;
        tmp = q.get(0);
        for(Vertex v:q)
            if(tmp.getRealWeight()>v.getRealWeight())
                tmp=v;
        return tmp;
    }
    
    private boolean vIsInS(Vertex vi)
    {
        for(Vertex v:S)
            if(v.getName().equals(vi.getName()))
                return true;
        
        return false;
    }
}
