package jp.co.ambitious.sample.nba.services;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jp.co.ambitious.sample.nba.beans.Player;

@SpringBootTest
public class NbaServiceTest {
    
    @Autowired
    NbaService nbaService;

    @Test
    void getPlayerRecord() {

        List<Player> players = nbaService.getPlayer();
        assertThat(players.size()).isEqualTo(2);
    }
}
