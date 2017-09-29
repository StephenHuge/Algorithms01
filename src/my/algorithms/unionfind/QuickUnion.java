package my.algorithms.unionfind;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 某个点的含义：数组下标代表当前点，该点的值就是以当前点作为数组下标得到的值
 * 实现方法是如果两个点连通，就让前一个点的值变成后一个点的值
 * 
 * 时间复杂度针对1个点
 * 优点：union方法速度很快，时间复杂度：O(1)
 * 缺点：find方法速度太慢，时间复杂度：O(n)，原因是树有可能“太高”
 * 
 * @author HJS
 * 
 * @date 2017年9月29日
 * 
 */
public class QuickUnion extends UF {

    public QuickUnion(int n) {
        super(n);
    }

    @Override
    public void union(int p, int q) {
        if(p < 0 || p > id.length - 1 || q < 0 || q > id.length - 1)  
            throw new java.lang.ArrayIndexOutOfBoundsException();
        int pRoot = find(p);
        int qRoot = find(q);
        
        if(pRoot == qRoot) return;
        id[pRoot] = qRoot;
        count--;
    }

    @Override
    public int find(int p) {
        if(p < 0 || p > id.length - 1) throw new java.lang.ArrayIndexOutOfBoundsException();
        while(id[p] != p) p = id[p];
        return p;
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
        UF uf = new QuickUnion(N);
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
