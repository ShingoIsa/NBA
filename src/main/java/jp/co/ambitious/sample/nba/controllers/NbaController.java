package jp.co.ambitious.sample.nba.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.ambitious.sample.nba.beans.Player;
import jp.co.ambitious.sample.nba.beans.Team;
import jp.co.ambitious.sample.nba.forms.NbaForm;
import jp.co.ambitious.sample.nba.forms.SearchForm;
import jp.co.ambitious.sample.nba.services.NbaService;
import lombok.extern.slf4j.Slf4j;


// 画面のtable表に値を表示できるか試作品作成中
@Controller
@Slf4j
public class NbaController {

    @Autowired
    MessageSource messageSource;

    @Autowired
    NbaService service;

    /**
     * 最初の画面
     * @param model
     * @return nba_list
     */
    @GetMapping("/nba")
    public String nba(@Validated SearchForm searchForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            // 入力チェックでエラーがある場合
            return "nba_list";
        }

        //チーム名をドロップダウンにセット
        this.setTeam(model);

        // 選手名をセット
        List<Player> players = new ArrayList<>();
        players = service.getPlayer(searchForm);
        model.addAttribute("players", players);
        
        return "nba_list";
    }

    /**
     * 更新画面の初期表時
     * @param nbaForm
     * @param playerNo
     * @param model
     * @return
     */
    @GetMapping("/nba/{playerNo}")
    public String initUpdate(@Validated NbaForm nbaForm, @PathVariable("playerNo") Integer playerNo, Model model) {

        // mapperとxmlファイルを利用して playerNoに紐づけられたレコードを取得し更新するながれでいいのか？
        Player players = service.selectByPK(playerNo);
        BeanUtils.copyProperties(players, nbaForm);
        model.addAttribute("nbaForm", nbaForm);

        //チーム名をドロップダウンにセット
        this.setTeam(model);

        return "nba";
    }

    @GetMapping("/nba/new")
    public String initNew() {
        return "nba";
    }

    @PostMapping("/nba/save")
    public String save(NbaForm form, BindingResult result, Model model) {

        // チーム名セット
        this.setTeam(model);

        if (result.hasErrors()) {
            return "nba";
        }

        try {
            // 詰め替え作業
            Player player = new Player();
            BeanUtils.copyProperties(form, player);

            // 更新作業
            service.save(player);

            // リダイレクト
            return "redirect:/nba";
        } catch(OptimisticLockingFailureException e) {
            result.addError(new ObjectError("global", e.getMessage()));
            
            return "nba";
        }
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