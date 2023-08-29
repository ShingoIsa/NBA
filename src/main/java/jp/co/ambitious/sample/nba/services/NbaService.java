package jp.co.ambitious.sample.nba.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.ambitious.sample.nba.beans.Player;
import jp.co.ambitious.sample.nba.beans.Team;
import jp.co.ambitious.sample.nba.forms.SearchForm;
import jp.co.ambitious.sample.nba.mappers.PlayerMapper;
import jp.co.ambitious.sample.nba.mappers.TeamMapper;

@Service
public class NbaService {
    
    @Autowired
    PlayerMapper playerMapper;

    @Autowired
    TeamMapper teamMapper;

    // テーブルに選手情報を表示
    public List<Player> getPlayer(SearchForm searchForm) {
        return playerMapper.selectAll(searchForm);
    }

    // ドロップボックスにチーム名をセット
    public List<Team> getTeam() {
        return teamMapper.getTeam();
    }
}
