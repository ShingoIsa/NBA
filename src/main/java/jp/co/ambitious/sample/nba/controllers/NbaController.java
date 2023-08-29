package jp.co.ambitious.sample.nba.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jp.co.ambitious.sample.nba.beans.Player;
import jp.co.ambitious.sample.nba.beans.Team;
import jp.co.ambitious.sample.nba.forms.SearchForm;
import jp.co.ambitious.sample.nba.services.NbaService;
import lombok.extern.slf4j.Slf4j;

// 画面のtable表に値を表示できるか試作品作成中
@Controller
@Slf4j
public class NbaController {

    @Autowired
    NbaService service;

    /**
     * 最初の画面
     * @param model
     * @return nba_list
     */
    @GetMapping("/nba")
    public String nba(SearchForm searchForm, Model model) {

        //チーム名をドロップダウンにセット,コントローラに問題があるのではないか
        this.setTeam(model);
        log.info("searchform :{}" + searchForm);

        // 選手名のみの検索だとフィルタリングをかけることができない。なぜ？
        List<Player> players = new ArrayList<>();
        players = service.getPlayer(searchForm);
        model.addAttribute("players", players);
        
        return "nba_list";
    }

    /**
     * 検索条件リセットボタン用
     * @return nba_list
     */
    @GetMapping("/nba/reset")
    public String reset(SearchForm searchForm, Model model) {
        this.setTeam(model);
        searchForm = new SearchForm();
        return "nba_list";
    }
    
    /** チーム名をドロップダウンにセット */
    private void setTeam(Model model) {

        List<Team> teams = new ArrayList<>();
        teams = service.getTeam();
        model.addAttribute("teams", teams);
    }
    

}