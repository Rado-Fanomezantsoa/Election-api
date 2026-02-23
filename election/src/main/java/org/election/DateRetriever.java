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
}
