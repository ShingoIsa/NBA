package jp.co.ambitious.sample.nba.beans;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 選手
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    
    // 選手ID
    private Integer playerNo;

    // 選手名
    private String playerName;

    // チームID
    private Team team;

    // 身長
    private Integer playerHeight;

    // 年齢
    private Integer playerAge;

    // ポジション
    private String playerPosition;

    // 得点数
    private Integer playerScore;

    // アシスト数
    private Integer playerAsist;

    // リバウンド数
    private Integer playerRebound;

    // バージョン
    private Integer version;

    // 登録日時
    private LocalDateTime insDt;

    // 更新日時
    private LocalDateTime upDt;
}
