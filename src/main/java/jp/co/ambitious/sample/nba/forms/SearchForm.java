package jp.co.ambitious.sample.nba.forms;

import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class SearchForm {
    
    // チームID
    private String teamId;

    // 選手名キーワード
    @Size(min=2, max=14)
    private String keyword;

}
