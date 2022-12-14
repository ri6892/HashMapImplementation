import java.util.*;

public class Main {
    public static void main(String[] args) {
        HashMapImplementation.Hashmap<String,Integer> map=new HashMapImplementation.Hashmap<>();
        map.put("Ritiksha",6892);
        map.put("DAmini",6875);
    }

}
class HashMapImplementation{
    static  class Hashmap<k,v>{
        private class Node{
            k key;
            v value;
            public Node(k Key,v Value){
                this.key=Key;
                this.value=Value;
            }
        }
        private int n=0;
        private int N;
        private  LinkedList<Node> bucket[];

        // to store the data we need to create an empty ll to directly store the data
        public Hashmap(){
            this.N=4;
            this.bucket=new LinkedList[4];
            for(int i=0;i<4;i++){
                this.bucket[i]=new LinkedList<>();
            }
        }
        private int hashfunction(k key){//bi btw 0 to n
            int bi=key.hashCode();
            return Math.abs(bi)%N;
        }
        private int searchInLL(k key ,int bi){
           LinkedList<Node> ll=bucket[bi];
           for(int i=0;i<ll.size();i++){
             if(ll.get(i).key==key)return i;
           }
            return -1;
        }
        private void rehash(){
            LinkedList<Node> oldbucket[]=bucket;
            bucket=new LinkedList[N*2];
            for(int i=0;i<bucket.length;i++){
                bucket[i]=new LinkedList<>();
            }
            for(int i=0;i<oldbucket.length;i++){
                LinkedList<Node>ll=oldbucket[i];
                for(int j=0;j<ll.size();j++){
                    Node node=ll.get(j);// provide index
                    put(node.key,node.value);
                }
            }
        }
        public void put(k key,v value){
            int bi=hashfunction(key);
            int di=searchInLL(key,bi);// search in bucket index bi ,for the value
            if(di== -1){// key does not exist
                bucket[bi].add(new Node(key,value));
                n++;
            }
            else{//key exist
                Node data=bucket[bi].get(di);
                data.value=value;
            }
            double lambda=(double) n/N;
            if(lambda>2.0){
                //rehashing
                rehash();
            }
        }
    public boolean containsKey(k key){
        int bi=hashfunction(key);
        int di=searchInLL(key,bi);
        if(di==-1){
            return false;
        }
        else return true;
     }
      public v get(k key){
          int bi=hashfunction(key);
          int di=searchInLL(key,bi);
          if(di==-1){
              return null;
          }
          else {
              Node node=bucket[bi].get(di);
              return node.value;
          }
      }
      public Node remove(k key){
          int bi=hashfunction(key);
          int di=searchInLL(key,bi);
          if(di==-1){
              return  null;
          }
          else{
              Node node=bucket[bi].remove(di);
              return node;
          }
      }
      public boolean IsEmpty(){
            return n==0;
      }
      public ArrayList<k> keyset(){
            ArrayList<k> keys=new ArrayList<>();
            for(int i=0;i< bucket.length;i++){//bucket list
                LinkedList<Node>ll= bucket[i];
                for(int j=0;j<ll.size();j++){// data list
                    keys.add(ll.get(j).key);
                }
            }
            return keys;
      }
    }
}