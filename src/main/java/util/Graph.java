package util;

import java.util.*;


/**
 * Clase que representa un grafo no dirigido mediante una lista de adyacencia.
 * @param <V> tipo de los vértices del grafo.
 */

public class Graph<V> {
    // Lista de adyacencia.
    private Map<V, Set<V>> adjacencyList = new HashMap<>();

    /**
     * Añade el vértice `v` al grafo.
     *
     * @param v vértice a añadir.
     * @return `true` si no estaba anteriormente y `false` en caso contrario.
     */

    public boolean addVertex(V v) {
        if (!adjacencyList.containsKey(v)) {
            adjacencyList.put(v, new HashSet<>());
            return true;
        }
        return false;
    }

    /**
     * Añade un arco entre los vértices `v1` y `v2` al grafo. En caso de que no exista alguno de los vértices, lo añade también.
     *
     * @param v1 el origen del arco.
     * @param v2 el destino del arco.
     * @return `true` si no existía el arco y `false` en caso contrario.
     */

    public boolean addEdge(V v1, V v2) {
        addVertex(v1);
        addVertex(v2);
        if (!adjacencyList.get(v1).contains(v2)) {
            adjacencyList.get(v1).add(v2);
            return true;
        }
        return false;
    }

     /**
     * Obtiene el conjunto de vértices adyacentes a ` v`.
     * @param v vértice del que se obtienen los adyacentes.
     *@return conjunto de vértices adyacentes. 
     */

     public Set<V> obtainAdjacents(V v) throws Exception{
        if(!adjacencyList.containsKey(v)){
            return null; 
        }
        return adjacencyList.get(v);
    }

    /**
     * Comprueba si el grafo contiene el vértice dado.
     *
     * @param v vértice para el que se realiza la comprobación.
     * @return `true` si `v` es un vértice del grafo.
     */

    public boolean containsVertex(V v) {
        return adjacencyList.containsKey(v);
    }

    /**
     * Método `toString()` reescrito para la clase `Graph`.
     * @return una cadena de caracteres con la lista de adyacencia.
     */

    @Override
    public String toString(){
        String texto = "";
        for (V v : adjacencyList.keySet()){
            texto += v.toString() + " -> ";
            for (V adj : adjacencyList.get(v)){
                texto += adj.toString() + " ";
            }
            texto += "\n";
        }
        return texto;
    }
     

    /**
     * Obtiene, en caso de que exista, el camino más corto entre `v1` y `v2`. En caso contrario, devuelve `null`.
     *
     * @param v1 el vértice origen.
     * @param v2 el vértice destino.
     * @return lista con la secuencia de vértices del camino más corto entre `v1` y `v2`.
     */

    public List<V> shortestPath(V v1, V v2){
    Queue<V> queue = new LinkedList<>();
    Map<V, V> previous = new HashMap<>();
    queue.add(v1);
    previous.put(v1, null);
    while (!queue.isEmpty()){
        V current = queue.poll();
        if (current.equals(v2)){
            List<V> path = new ArrayList<>();
            V vertex = v2;
            while (vertex != null){
                path.add(vertex);
                vertex = previous.get(vertex);
            }
            Collections.reverse(path);
            return path;    
        }
        for (V adj : adjacencyList.get(current)){
            if (!previous.containsKey(adj)){
                queue.add(adj);
                previous.put(adj, current);
            }
        }
    }
    return null;
}

}
