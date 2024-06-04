package LastPractice;

import week12.SomeApplication;

import java.util.LinkedList;

public class WordList {
    LinkedList<String> list;

    public WordList() {
        list = new LinkedList<String>();
    }

    public void makeList(String s) {
        String[] stringArray = s.split(" ");
        addWordsToList(stringArray, 0);
    }

    // Recursive method to add words to the list
    private void addWordsToList(String[] words, int index) {
        if (index < words.length) {
            list.add(words[index]);
            addWordsToList(words, index + 1);
        }
    }

    public int getCount() {
        return list.size();
    }

    public void showList() {
        System.out.println("\n" + list);
    }

    public static void main(String[] args) {
        String input = "hi, this is a question list for final evaluation. do your best. good luck!";

        System.out.println("\n==[Q1]=================");

        WordList q1 = new WordList();
        q1.makeList(input);
        System.out.println("\nNumber of words =" + q1.getCount());
        q1.showList();
    }
}
