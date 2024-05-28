package week12;

import java.util.ArrayList;

public class SomeApplication {


    ArrayList<Integer> group = new ArrayList<>();
    ArrayList<BinarySearchTree> member = new ArrayList<>();

    public void insert(int gID, int mID) {
        int index;
        if (group.contains(gID)) {
            index = group.indexOf(gID);
        } else {
            group.add(gID);
            index = group.size() - 1;
            member.add(new BinarySearchTree());
        }
        BinarySearchTree t = member.get(index);
        t.insert(mID);
        // == member.get(index).insert(mID);
    }

    public static void main(String[] args) {
        SomeApplication sa = new SomeApplication();

        sa.insert(1, 101);  // group ID, member ID

    }
}
