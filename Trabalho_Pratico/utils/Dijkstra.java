package Trabalho_Pratico.utils;

import java.util.ArrayList;
import java.util.HashMap;

import Trabalho_Pratico.model.Aresta;
import Trabalho_Pratico.model.Grafo;
import Trabalho_Pratico.model.Vertice;

public class Dijkstra {
    public HashMap<Vertice, Integer> vertices = new HashMap<Vertice, Integer>();
    public Grafo grafo;
	public ArrayList<Vertice> grafoVerts;

    public Dijkstra(Grafo grafo) {
		this.grafo = grafo;
	}

    public int getCustoMinimo(Vertice init, Vertice end) {
        grafoVerts = grafo.getVertices();

        for (int i = 0; i < grafo.getSize(); i++) {
			vertices.put(grafoVerts.get(i), Integer.MAX_VALUE);
		}

        vertices.put(init, 0);
        int peso, pesoVertice;
		Vertice vert = null;
        HashMap<Vertice, Aresta> arestas = null;
		Object[] direcao;

        while(!graphEnded()){
            vert = getMenorVertice();
			vert.setVisitado(true);
			arestas = vert.getDirecao();
			direcao = arestas.keySet().toArray();
            pesoVertice = vertices.get(vert);
            for (int i = 0; i < arestas.size(); i++) {
				peso = arestas.get(direcao[i]).getPeso();
				if ((pesoVertice + peso) < vertices.get((Vertice)direcao[i])) {
					vertices.put((Vertice)direcao[i], pesoVertice + peso);
				}
			}
        }

        return vertices.get(end);
    }

    private Vertice getMenorVertice() {
        Integer menor = Integer.MAX_VALUE;
		Integer aux;
		Vertice vertMenor = null;

		for (int i = 0; i < grafo.getSize(); i++) {
			aux = vertices.get(grafoVerts.get(i));
			if (aux <= menor && !grafoVerts.get(i).visitado) {
				menor = aux;
				vertMenor = grafoVerts.get(i);
			}
		}

		return vertMenor;
    }

    private boolean graphEnded() {
        ArrayList<Vertice> visitados = new ArrayList<Vertice>();

		for (int i = 0; i < grafo.getSize(); i++) {
			if (!grafoVerts.get(i).visitado) {
				return false;
			}
		}
		return true;
	}
}
