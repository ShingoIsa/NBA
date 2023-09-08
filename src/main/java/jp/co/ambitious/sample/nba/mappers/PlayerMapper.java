package jp.co.ambitious.sample.nba.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import jp.co.ambitious.sample.nba.beans.Player;
import jp.co.ambitious.sample.nba.forms.SearchForm;

@Mapper
public interface PlayerMapper {
    
    /**
     * 選手情報を取得する
     * @return 選手情報リスト
     */
    public List<Player> selectAll(SearchForm searchForm);

    /**
     * 更新機能で引数を元にフィルタリングしたレコードを取得する
     * @param playerNo
     * @return
     */
    public Player selectByPK(int playerNo);

    /**
     * 本アプリの更新機能。xmlファイルに書かずMapperインターフェースに直接記載。
     * @param playerNo
     * @return
     */
    @Update("UPDATE m_player SET player_name = #{playerName}, team_id = #{team.teamId}, player_height = #{playerHeight}, player_age = #{playerAge}, player_position = #{playerPosition}, player_score = #{playerScore}, player_asist = #{playerAsist}, player_rebound = #{playerRebound}, version = 1 + version, up_dt = now() WHERE player_no = #{playerNo} AND version= #{version}")
    public int updateByPK(Player player);

    /**
     * 新規登録メソッド
     * @param player
     * @return
     */
    public int initNew(Player player);

    @Delete("DELETE FROM m_player WHERE player_no = #{playerNo} AND version = #{version}")
    public int delete(Player player);

}