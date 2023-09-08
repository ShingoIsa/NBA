package jp.co.ambitious.sample.nba.forms;

import javax.validation.Valid;

import jp.co.ambitious.sample.nba.beans.Team;
import lombok.Data;

@Data
public class NbaForm {

    // 選手ID
    private Integer playerNo;

    // 選手名
    private String playerName;

    // チームID
    @Valid
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

}
