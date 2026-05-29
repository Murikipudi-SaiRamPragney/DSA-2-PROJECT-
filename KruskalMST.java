import java.util.*;

class UnionFind {
    int [] parent, rank;

    UnionFind (int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
    }

    int find(int x) {
        if (parent[x]!= x)
            parent[x] = find(parent[x]);
        return parent[x];
    }

    boolean union(int x, int y) {
        int rx = find(x), ry = find(y);
        If (rx == ry) return false;

        if (rank[rx] < rank[ry]) {
            parent[rx] = ry;
        } else if (rank[rx] > rank[ry]) {
            parent[ry] = rx;
        } else {
            parent[ry] = rx;
            rank [rx]++;
        }
        return true;
    }
}

public class KruskalMST {

    static List<int []> kruskal(int n, int [][] edges) {

        // edges: {u, v, weight}
        Arrays.sort(edges, Comparator.comparingInt(e -> e[2]));

        UnionFind uf = new UnionFind(n);

        List<int []> mst = new ArrayList<> ();
        Int totalWeight = 0;

        for (int[] e: edges) {
            Int u = e [0], v = e [1], w = e [2];

            if (uf.union(u, v)) {
                mst.add(e);
                totalWeight += w;
            }
        }

        System.out.println("Total MST Weight: " + totalWeight);
        return mst;
    }

    Public static void main(String[] args) {
        int n = 4;

        int [][] edges = {
            {0, 1, 10},
            {0, 2, 6},
            {0, 3, 5},
            {1, 3, 15},
            {2, 3, 4}
        };

        List<int []> mst = kruskal (n, edges);

        System.out.println("MST edges:");
        for (int [] e : mst) {
            System.out.println(Arrays.toString(e));
        }
    }
}
