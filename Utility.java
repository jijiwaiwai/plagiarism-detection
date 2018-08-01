/**
 * 1. use union find to find each word's parent
 * 2. replace each word in file1.txt with its parent, replace each word in file2.txt with its parent
 * 3. use a set to store tuples in file2.txt
 * 4. iterate each tuple in file1.txt and check if it is in the set, store it as count
 * 5. the result is countOfMatch / countOfTuples in file2 
 */
import java.util.*;

public class Utility {
    public double checkPlagiarism(List<List<String>> synonyms, String file1, String file2, int N) {
        int len = file1.split(" ").length;
        if(N > len) N = len;
    
        Map<String, String> parent = new HashMap<>();
        for(List<String> list : synonyms) {
            for(String s : list) {
                parent.put(s, s);
            }
        }
        
        for(List<String> list : synonyms) {
            String p = find(list.get(0), parent);
            for(int i = 1; i < list.size(); i++) {
                String curP = find(list.get(i), parent);
                parent.put(curP, p);
            }
        }
        
        for(String key : parent.keySet()) {
            String p = find(parent.get(key), parent);
            parent.put(key, p);
        }
        System.out.println(parent);
        System.out.println();

        String[] sArr1 = getReplacedInput(file1, parent);
        String[] sArr2 = getReplacedInput(file2, parent);
        
        //System.out.println(Arrays.toString(sArr1));
        //System.out.println(Arrays.toString(sArr2));
        
        Set<String> set = new HashSet<>();
        
        for(int i = 0; i <= sArr2.length - N; i++) {
            StringBuilder sb = new StringBuilder();
            for(int j = i; j < i + N; j++) {
                sb.append(sArr2[j]);
            }
            set.add(sb.toString());
        }
        //System.out.println(set);
        
        int count = 0;
        for(int i = 0; i <= sArr1.length - N; i++) {
            StringBuilder sb = new StringBuilder();
            for(int j = i; j < i + N; j++) {
                sb.append(sArr1[j]);
            }
            if(set.contains(sb.toString())) {
                count++;
            }
        }
        System.out.println("There are " + count + " tuples in file1 appear in file2");
        System.out.println();
        
        double res = count*1.0 / (sArr2.length - N + 1) * 100;
        res = Math.round(res * 100.0) / 100.0;
        System.out.println("Based on " + N + " tuples, the percent of tuples in file1 which appear in file2 is " + res + "%");
        
        return res;
    }
    
    private String[] getReplacedInput(String s, Map<String, String> parent) {
        String[] sArr = s.split(" ");
        for(int i = 0; i < sArr.length; i++) {
            if(parent.containsKey(sArr[i])) {
                sArr[i] = parent.get(sArr[i]);
            }
        }
        return sArr;
    }
    
    private String find(String s, Map<String, String> p) {
        while(s != p.get(s)) {
            p.put(s, p.get(s));
            s = p.get(s);
        }
        return s;
    }
        
}