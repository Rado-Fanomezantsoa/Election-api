package org.election;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DateRetriever {

    //1
    long countAllVotes(){
        long count = 0;
        String sql = """
                select count(id) as total_votes 
                from vote;
                """;
        try (Connection connection = new DbConnection().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery();
        ){
            if  (resultSet.next()) {
                count = resultSet.getLong("total_votes");
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return count;
    }

    //2
    List<VoteTypeCount> countVotesByType(){
        List<VoteTypeCount> voteTypeCounts = new ArrayList<VoteTypeCount>();
        String sql = """
                Select vote_type, count(vote.id) as total_votes
                from vote
                group by vote_type;
                """;
        try(Connection connection = new DbConnection().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
        ){
            while(resultSet.next()) {
                VoteType voteType = VoteType.valueOf(resultSet.getString("vote_type"));
                int count = resultSet.getInt("total_votes");
                voteTypeCounts.add(new VoteTypeCount(voteType, count));
            }
            return voteTypeCounts;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    //3
    List<CandidateVoteCount> countValidVotesByCandidate(){
        List<CandidateVoteCount> candidateVoteCounts = new ArrayList<CandidateVoteCount>();
        String sql = """
                Select candidate.name as candidate_name, count(case when vote.vote_type = 'VALID'Then 1 END) as valid_vote
                from candidate
                left join vote on vote.candidate_id =  candidate.id
                group by candidate.id, candidate.name order by name;
        """;
        try(
                Connection connection = new DbConnection().getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                ){
            while(resultSet.next()) {
                String candidateName = resultSet.getString("candidate_name");
                int validVoteCount = resultSet.getInt("valid_vote");
                candidateVoteCounts.add(new CandidateVoteCount(candidateName, validVoteCount));
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        };
        return candidateVoteCounts;
    }

    //4
    public VoteSummary computeVoteSummary() {
        VoteSummary summary = null;
        String sql = """
        SELECT
            SUM(CASE WHEN vote_type = 'VALID' THEN 1 ELSE 0 END) AS valid_count,
            SUM(CASE WHEN vote_type = 'BLANK' THEN 1 ELSE 0 END) AS blank_count,
            SUM(CASE WHEN vote_type = 'NULL' THEN 1 ELSE 0 END) AS null_count
        FROM
            vote;
    """;
        try (Connection connection = new DbConnection().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                int validCount = resultSet.getInt("valid_count");
                int blankCount = resultSet.getInt("blank_count");
                int nullCount = resultSet.getInt("null_count");
                summary = new VoteSummary(validCount, blankCount, nullCount);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return summary;
    }

}
