package jp.co.ambitious.sample.nba.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.ambitious.sample.nba.beans.Team;

/*
 * チーム名をドロップダウンにセットするやつ
 */
@Mapper
public interface TeamMapper {
    
    public List<Team> getTeam();
}
