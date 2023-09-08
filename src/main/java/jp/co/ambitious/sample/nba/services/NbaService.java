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

    /**
     * 参照機能
     * @param searchForm
     * @return
     */
    public List<Player> getPlayer(SearchForm searchForm) {
        return playerMapper.selectAll(searchForm);
    }

    /**
     * 参照機能(playerNoでフィルタリング)
     * @param playerNo
     * @return
     */
    public Player selectByPK(int playerNo) {
        return playerMapper.selectByPK(playerNo);
    }

    public int save(Player player) {
        if (player.getPlayerNo() == null) {
            System.out.println("登録実行されているよ");
            return this.initNew(player);
        }
        else {
            return this.update(player);
        }
        
    }

    /**
     * 登録機能
     * @param player
     */
    public int initNew(Player player) {

        int cnt = playerMapper.initNew(player);

        if (cnt == 0) {
            throw new RuntimeException(messageSource.getMessage("error.RuntimeException", new String[] {"登録に失敗しました。"}, Locale.JAPAN));
        }

        // 登録件数をコントローラに返す(デバッグの為)
        return cnt;
    }

    /**
     * 更新機能
     * @param player
     */
    public int update(Player player) {
        
        int cnt = playerMapper.updateByPK(player);

        if (cnt == 0) {
            throw new OptimisticLockingFailureException(messageSource.getMessage("error.OptimisticLockingFailureException",null, Locale.JAPAN));
        }
        // なぜ注意文字が表示されないか分からないから元方ラグで内容確認しましよう
        if (cnt > 1) {
            throw new RuntimeException(messageSource.getMessage("error.RuntimeException", new String[] {"SQL文が不適切な可能性があります。"}, Locale.JAPAN));
        }

        // 更新件数をコントローラに返す(デバッグの為)
        return cnt;
    }

    /**
     * 削除メソッド
     * @param player
     * @return
     */
    public int delete(Player player) {
        
        int cnt = playerMapper.delete(player);

        if (cnt == 0) {
            throw new OptimisticLockingFailureException(messageSource.getMessage("error.OptimisticLockingFailureException", null, Locale.JAPAN));
        }
        if (cnt == 2) {
            throw new RuntimeException(messageSource.getMessage("error.RuntimeException", new String[]{"2件以上削除されました"}, Locale.JAPAN));
        }

        return cnt;
    }
    
    // ドロップボックスにチーム名をセット
    public List<Team> getTeam() {
        return teamMapper.getTeam();
    }
}
