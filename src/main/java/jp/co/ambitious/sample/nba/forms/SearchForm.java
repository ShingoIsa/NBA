package jp.co.ambitious.sample.nba.forms;

import lombok.Data;

@Data
public class SearchForm {
    
    // チームID
    private String teamId;

    // 選手名キーワード
    private String keyword;

}
