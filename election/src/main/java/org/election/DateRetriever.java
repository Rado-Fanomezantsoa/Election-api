package org.election;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DateRetriever {

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
}
