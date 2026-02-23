package org.election;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        DateRetriever dr = new DateRetriever();
        long totalVotes = dr.countAllVotes();
        System.out.println("Total votes: " + totalVotes);
    }
}