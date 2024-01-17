import java.util.Arrays;
import java.util.Scanner;

public class Sum {
    public static void main(String[] args) {
        Sum s = new Sum();
        s.test();
    }
    
    protected void test() {
        Scanner sc=new Scanner(System.in);
        int target = sc.nextInt();

        int sz = sc.nextInt();
        int[] in = new int[sz];
        for(int i = 0; i < sz; i ++) {
            in[i] = sc.nextInt();
        }
        System.out.println(this.impl(in, target));
    }

    protected boolean impl(int[] a, int s) {
        //TODO
        int i=0;
        int j=a.length-1;
        if(i==j && a[i] == s) return true; 
        
        while(i!=j){
            if(a[i]+a[j]>s){
                j--;
            }
            else if(a[i]+a[j]<s){
                i++;
            }
            else return true;
        }
        return false;
    }
}