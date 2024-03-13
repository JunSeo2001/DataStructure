package week2;

public class Search {

    public int sequential1(int[] input, int val){
        for (int i = 0; i<=input.length; i++){
            if(input[i]==val)
                return i;
        }
        return -1;
    }

    public int sequential2(int[] input, int val){
        int index=0;

        while(input[index] <= val){
            if(input[index]==val)
                return index;
            else
                index ++;
        }
        return -1;
    }

    public int Binary(int[] input, int val, int s, int e){
        if(s>e)
            return -1;

        int mid = (s+e)/2;

        if(input[mid]==val)
            return mid;
        else if(input[mid]>val)
            return Binary(input, val, s, mid-1);
            else
                return Binary(input, val, mid+1, e);
    }

    public static void main(String[] arg){
        int[] rdata = {10, 40, 20, 15, 50, 25};
        int[] sdata = {10, 15, 20, 25, 40, 50};

        Search s = new Search();

        System.out.println(s.sequential1(rdata, 25));
        System.out.println(s.sequential2(sdata, 25));

        System.out.println(s.Binary(sdata, 25, 0, sdata.length-1));


    }
}
