package com.bugjc.java.basics.data.structure;

import java.util.LinkedList;

/**
 * 有向无环图
 * 拓扑排序
 * @author aoki
 * @date 2021/1/15
 **/
public class DirectedAcyclicGraph {

    /**
     * 顶点的个数
     */
    private int v;

    /**
     * 邻接表
     */
    private LinkedList<Integer> adj[];

    public DirectedAcyclicGraph(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s, int t) {
        // s先于t，边s->t
        adj[s].add(t);
    }

    /**
     * Kahn 算法
     */
    public void topologicalSortingByKahn() {
        // 统计每个顶点的入度
        int[] inDegree = new int[v];
        for (int i = 0; i < v; ++i) {
            for (int j = 0; j < adj[i].size(); ++j) {
                // i->w
                int w = adj[i].get(j);
                inDegree[w]++;
            }
        }
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < v; ++i) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            int i = queue.remove();
            System.out.print("->" + i);
            for (int j = 0; j < adj[i].size(); ++j) {
                int k = adj[i].get(j);
                inDegree[k]--;
                if (inDegree[k] == 0) {
                    queue.add(k);
                }
            }
        }
    }

    /**
     * DFS 算法
     */
    public void topologicalSortingByDFS() {
        // 先构建逆邻接表，边s->t表示，s依赖于t，t先于s
        LinkedList<Integer> inverseAdj[] = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            // 申请空间
            inverseAdj[i] = new LinkedList<>();
        }
        for (int i = 0; i < v; ++i) {
            // 通过邻接表生成逆邻接表
            for (int j = 0; j < adj[i].size(); ++j) {
                // i->w
                int w = adj[i].get(j);
                // w->i
                inverseAdj[w].add(i);
            }
        }
        boolean[] visited = new boolean[v];
        for (int i = 0; i < v; ++i) {
            // 深度优先遍历图
            if (visited[i] == false) {
                visited[i] = true;
                dfs(i, inverseAdj, visited);
            }
        }
    }

    private void dfs(
            int vertex, LinkedList<Integer> inverseAdj[], boolean[] visited) {
        for (int i = 0; i < inverseAdj[vertex].size(); ++i) {
            int w = inverseAdj[vertex].get(i);
            if (visited[w] == true) {
                continue;
            }
            visited[w] = true;
            dfs(w, inverseAdj, visited);
        }
        // 先把vertex这个顶点可达的所有顶点都打印出来之后，再打印它自己
        System.out.print("->" + vertex);
    }
}
