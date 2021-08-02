package graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**UnionFind
 *
 * Implements Union Find with weight consider
 * Guarantee 'find' execute in logN
 *
 * @author pms
 *
 * */
public class UnionFind {
    int count;
    int[] components;
    int[] sizes;

    /*constructor*/
    public UnionFind(int count) {
        this.count = count;
        this.components = new int[count];
        this.sizes = new int[count];

        for(int i=0; i<count; i++) {
            components[i] = i;
        }
        for(int i=0; i<count; i++) {
            sizes[i] = 1;
        }
    }

    /*find root*/
    public int find(int p) {
        while(components[p] != p) p = components[p];
        return p;
    }

    /*return is connected*/
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /*union if not already connected*/
    public int union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if(pRoot == qRoot) return -1;

        if(sizes[pRoot] > sizes[qRoot]) {
            components[qRoot] = pRoot;
            sizes[pRoot] += sizes[qRoot];
            return pRoot;
        }
        else {
            components[pRoot] = qRoot;
            sizes[qRoot] += sizes[pRoot];
            return qRoot;
        }
    }

    public static void main(String[] args) {
        Scanner sc = null;
        UnionFind uf;
        try {
            sc = new Scanner(new File("src/graph/tinyUf.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int count = sc.nextInt();
        uf = new UnionFind(count);

        while(sc.hasNextLine()) {
            int p = sc.nextInt();
            int q = sc.nextInt();

            int result = uf.union(p, q);
            if(result == -1) {
                System.out.println(p + ", " + q + " is already connected");
            }
            else {
                System.out.println(p + ", " + q + "is connecting with " + result);
            }
        }
        sc.close();
    }
}
