package jp.co.ambitious.sample.nba.services;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import jp.co.ambitious.sample.nba.beans.Player;
import jp.co.ambitious.sample.nba.beans.Team;
import jp.co.ambitious.sample.nba.forms.SearchForm;
import jp.co.ambitious.sample.nba.mappers.PlayerMapper;
import jp.co.ambitious.sample.nba.mappers.TeamMapper;

@Service
public class NbaService {
    
    @Autowired
    MessageSource messageSource;

    @Autowired
    PlayerMapper playerMapper;

    @Autowired
    TeamMapper teamMapper;

    // テーブルに選手情報を表示
    public List<Player> getPlayer(SearchForm searchForm) {
        return playerMapper.selectAll(searchForm);
    }

    // playerの主キー(playNo)を元にレコードを取得
    public Player selectByPK(int playerNo) {
        return playerMapper.selectByPK(playerNo);
    }

    // ドロップボックスにチーム名をセット
    public List<Team> getTeam() {
        return teamMapper.getTeam();
    }

    // 保存機能
    public void save(Player player) {
        
        int cnt = playerMapper.updateByPK(player);

        if (cnt == 0) {
            throw new OptimisticLockingFailureException(messageSource.getMessage("error.OptimisticLockingFailureException",null, Locale.JAPAN));
        }
        // なぜ注意文字が表示されないか分からないから元方ラグで内容確認しましよう
        if (cnt > 1) {
            throw new RuntimeException(messageSource.getMessage("error.RuntimeException", null, Locale.JAPAN));
        }
    }
    
}
