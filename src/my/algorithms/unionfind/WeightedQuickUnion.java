package my.algorithms.unionfind;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * ĳ����ĺ��壺�����±����ǰ�㣬�õ��ֵ�����Ե�ǰ����Ϊ�����±�õ���ֵ
 * ʵ�ַ����������������ͨ������ǰһ�����ֵ��ɺ�һ�����ֵ
 * ��ȴ�ͳ��Union-Find���¼�����һ����ʾ��ǰ�����ӽڵ������������sz
 * 
 * ʱ�临�Ӷ����1����
 * �ŵ㣺union�����ٶȽϿ죬ʱ�临�Ӷȣ�O(lg n)
 * ȱ�㣺find�����ٶȽϿ죬ʱ�临�Ӷȣ�O(lg n)
 * @author HJS
 * 
 * @date 2017��9��29��
 * 
 */
public class WeightedQuickUnion extends UF {
    
    private int[] sz;   // �������±�Ϊ�������������ڵ�����Ӧ�ķ����Ĵ�С

    public WeightedQuickUnion(int n) {
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
        
        if(sz[pRoot] >= sz[qRoot]) {    // p�ӽڵ��q���ӽڵ��
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
        while(p != id[p])   p = id[p];
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
