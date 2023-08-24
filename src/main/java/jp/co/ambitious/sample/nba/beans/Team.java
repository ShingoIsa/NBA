package jp.co.ambitious.sample.nba.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * チーム名簿
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    
    // チームID
    private String teamId;

    // チーム名
    private String teamName;
}
