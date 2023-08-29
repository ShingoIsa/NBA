package jp.co.ambitious.sample.nba.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.ambitious.sample.nba.beans.Player;
import jp.co.ambitious.sample.nba.forms.SearchForm;

@Mapper
public interface PlayerMapper {
    
    /**
     * 選手情報を取得する
     * @return 選手情報リスト
     */
    public List<Player> selectAll(SearchForm searchForm);

}
