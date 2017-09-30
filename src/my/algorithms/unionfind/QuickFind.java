package my.algorithms.unionfind;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * ĳ����ĺ��壺�����±����ǰ�㣬�õ��ֵ�����Ե�ǰ����Ϊ�����±�õ���ֵ
 * ʵ�ַ����������������ͨ������ǰһ�����ֵ��ɺ�һ�����ֵ
 * 
 * ʱ�临�Ӷ����1����
 * �ŵ㣺find�ٶȺܿ죬ʱ�临�Ӷȣ�O(1)
 * ȱ�㣺union�����ٶ�̫����ʱ�临�Ӷȣ�O(n)
 * 
 * @author HJS
 * 
 * @date 2017��9��28��
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
        // ��p��q�鲢����ͬ�ķ�����
        int pId = id[p];
        int qId = id[q];
        
        if(pId == qId) return;  // ����Ѿ���ͬһ�����У��򲻲���
        
        // ��p�ķ�����������Ϊq������
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
