package ambigramDetection.optimized;

public class Solution {

    public int[] parent;

    /*
    By the problem design on binarysearch.com, we have to work
    around the given method 'public boolean solve(String s, String[][] pairs)'.
    Even though the name 'solve' does not make a lot of sense, it is left as it is, 
    so that the code can be run directly on the website, without any modifications.
     */
    public boolean solve(String s, String[][] pairs) {
        initializeParent();
        findEquivalentCharacters(pairs);
        return isPalindrome(s);
    }

    public void initializeParent() {
        parent = new int[26];
        for (int i = 0; i < 26; i++) {
            parent[i] = i;
        }
    }

    public boolean isPalindrome(String s) {
        int leftSide = s.length() - 1;
        for (int rightSide = 0; rightSide < (s.length() / 2); rightSide++) {
            if (findParent(s.charAt(rightSide) - 'a') != findParent(s.charAt(leftSide - rightSide) - 'a')) {
                return false;
            }
        }
        return true;
    }

    public void findEquivalentCharacters(String[][] pairs) {
        for (String[] current : pairs) {
            union(current[0].charAt(0) - 'a', current[1].charAt(0) - 'a');
        }
    }

    public void union(int ch_01, int ch_02) {

        ch_01 = findParent(ch_01);
        ch_02 = findParent(ch_02);

        if (parent[ch_01] <= parent[ch_02]) {
            parent[ch_02] = ch_01;
        } else if (parent[ch_01] > parent[ch_02]) {
            parent[ch_01] = ch_02;
        }
    }

    public int findParent(int ch) {
        if (parent[ch] != ch) {
            parent[ch] = findParent(parent[ch]);
        }
        return parent[ch];
    }
}
