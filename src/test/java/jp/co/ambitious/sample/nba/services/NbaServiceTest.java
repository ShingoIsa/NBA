package jp.co.ambitious.sample.nba.services;


import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jp.co.ambitious.sample.nba.beans.Player;
import jp.co.ambitious.sample.nba.forms.SearchForm;


@SpringBootTest
public class NbaServiceTest {
    
    @Autowired
    NbaService service;

    @DisplayName("チーム名のみ一致")
    @ParameterizedTest
    @CsvSource({"1, ゴールデンステート・ウォリアーズ", "2, ミルウォーキー・バックス", "3, ロサンゼルス・レイカーズ"})
    void test01(String id, String name) {
        SearchForm searchForm = new SearchForm();
        searchForm.setTeamId(id); // ウォリアーズ

        List<Player> players = service.getPlayer(searchForm);
        for (Player player : players) {
            assertThat(player.getTeam().getTeamName()).isEqualTo(name);
        }
    }

    @DisplayName("選手名のみ完全一致")
    @Test
    void test02() {
        SearchForm searchForm = new SearchForm();
        searchForm.setKeyword("ステフィン・カリー");

        List<Player> players = service.getPlayer(searchForm);
        for (Player player : players) {
            assertThat(player.getPlayerName()).isEqualTo("ステフィン・カリー");
            assertThat(player.getPlayerPosition()).isEqualTo("PG");
        }
    }

    @DisplayName("選手名・前方一致")
    @ParameterizedTest
    @CsvSource({"ステフィン, ステフィン・カリー", "ヤニス, ヤニス・アンデトクンポ"})
    void test03(String keyword, String name) {
        SearchForm searchForm = new SearchForm();
        searchForm.setKeyword(keyword);

        List<Player> players = service.getPlayer(searchForm);
        for (Player player : players) {
            assertThat(player.getPlayerName()).isEqualTo(name);
        }
    }
    
    @DisplayName("双方一致")
    @Test
    void test04() {
        SearchForm searchForm = new SearchForm();
        searchForm.setTeamId("1");
        searchForm.setKeyword("ステフィン・カリー");

        List<Player> players = service.getPlayer(searchForm);
        // assertThat(players.size()).isEqualTo(1);
        for (Player player : players) {
            assertThat(player.getPlayerName()).isEqualTo("ステフィン・カリー");
        }
    }
}
