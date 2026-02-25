package org.election;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        DateRetriever dr = new DateRetriever();
        /*long totalVotes = dr.countAllVotes();
        System.out.println("Total votes: " + totalVotes);

        List<VoteTypeCount> voteTypeCounts = dr.countVotesByType();
        System.out.println(voteTypeCounts);

        List<CandidateVoteCount> validVoteCounts = dr.countValidVotesByCandidate();
        System.out.println(validVoteCounts);*/
        VoteSummary voteSummary = dr.computeVoteSummary();
        System.out.println(voteSummary);

        double computerVoter = dr.computeTurnoutRate();
        System.out.println(computerVoter);
    }
}