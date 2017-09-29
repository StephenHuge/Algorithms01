package my.algorithms.unionfind;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WeightedQUWithPC extends UF {

    private int[] sz;   // 以数组下标为索引，各个根节点所对应的分量的大小

    public WeightedQUWithPC(int n) {
        super(n);
        // init the size array
        sz = new int[n];
        for(int i = 0; i < n; i++)  sz[i] = 1;
    }

    @Override
    public void union(int p, int q) {
        if(p < 0 || p > id.length - 1 || q < 0 || q > id.length - 1)  
            throw new java.lang.ArrayIndexOutOfBoundsException();
        int pRoot = find(p);
        int qRoot = find(q);
        
        if(sz[pRoot] >= sz[qRoot]) {    // p子节点比q的子节点多
            id[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        } else {
            id[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } 
        count--;
    }

    @Override
    public int find(int p) {
        if(p < 0 || p > id.length - 1)  throw new java.lang.ArrayIndexOutOfBoundsException();
        while(p != id[p]) p = id[p];
        return p;
    }
    
    private int root(int i) {
        while(i != id[i]) {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    public static void main(String[] args) {
        Scanner in = null;
        try {
            in = new Scanner(new File("src/largeUF.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        String s = in.nextLine();
        int N = Integer.parseInt(s);
        UF uf = new WeightedQuickUnion(N);
        int p, q;
        
        while(true) {
            String nextLine = in.nextLine();
            System.out.println(nextLine);
            if (nextLine == null || nextLine.trim().length() == 0) {
                System .out.println("blank line");
                break;   
            }
            String[] strs = nextLine.split(" ");
            p = Integer.parseInt(strs[0]);
            q = Integer.parseInt(strs[1]);
            if(uf.connected(p, q)) {
                System.out.println(p + " and " +q + " is connected!");
                continue;
            }   
            uf.union(p, q);
            
//            System.out.print("The array is ");
//            for(int i : uf.id)
//                System.out.print(i + " ");
           System.out.println("There are " + uf.count() + " components left.");
        }
        in.close();
        System.out.println("execution stop...");
    }

}
