package jp.co.ambitious.sample.nba.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jp.co.ambitious.sample.nba.beans.Player;
import jp.co.ambitious.sample.nba.services.NbaService;

// 画面のtable表に値を表示できるか試作品作成中
@Controller
public class NbaController {

    @Autowired
    NbaService service;

    @GetMapping("/nba")
    public String nba(Model model) {

        List<Player> players = new ArrayList<>();
        players = service.getPlayer();
        model.addAttribute("players", players);
        return "nba_list";
    }

    

}