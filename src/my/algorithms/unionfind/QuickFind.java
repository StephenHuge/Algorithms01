package my.algorithms.unionfind;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 某个点的含义：数组下标代表当前点，该点的值就是以当前点作为数组下标得到的值
 * 实现方法是如果两个点连通，就让前一个点的值变成后一个点的值
 * 
 * 时间复杂度针对1个点
 * 优点：find速度很快，时间复杂度：O(1)
 * 缺点：union方法速度太慢，时间复杂度：O(n)
 * 
 * @author HJS
 * 
 * @date 2017年9月28日
 * 
 */
public class QuickFind extends UF {

    public QuickFind(int n) {
        super(n);
    }

    @Override
    public void union(int p, int q) {
        if(p < 0 || p > id.length - 1 || q < 0 || q > id.length - 1)  
            throw new java.lang.ArrayIndexOutOfBoundsException();
        // 将p和q归并到相同的分量中
        int pId = id[p];
        int qId = id[q];
        
        if(pId == qId) return;  // 如果已经在同一分量中，则不操作
        
        // 将p的分量重新命名为q的名称
        for(int i = 0; i < id.length; i++) {
            if(id[i] == pId) id[i] = qId;
        }
        count--;
    }

    @Override
    public int find(int p) {
        if(p < 0 || p > id.length - 1)  throw new java.lang.ArrayIndexOutOfBoundsException();
        return id[p];
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
        UF uf = new QuickFind(N);
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
            
            System.out.print("The array is ");
            for(int i : uf.id)
                System.out.print(i + " ");
           System.out.println("There are " + uf.count() + " components left.");
        }
        in.close();
        System.out.println("execution stop...");
    }

}
