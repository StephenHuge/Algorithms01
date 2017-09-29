package my.algorithms.unionfind;

public abstract class UF {
    
    protected int[] id;
    protected int count = 0;
    
    public UF(int n) {  //init the UF
        if (n < 0) throw new IllegalArgumentException();
        count = n;
        id = new int[n];
        for(int i = 0; i < id.length; i++) {
            id[i] = i;
        }
    }
    
    public abstract void union(int p, int q);
    
    public abstract int find(int p);
    
    public boolean connected(int p, int q) {
     return find(p) == find(q);   
    }
    
    public int count() {
        return count;
    }
}
