package jp.co.ambitious.sample.nba.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
@Slf4j
public class NbaController {

    @Autowired
    NbaService service;

    /**
     * 選手一覧を表示する
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
        log.info("form内容" + players);
        BeanUtils.copyProperties(players, nbaForm);
        model.addAttribute("nbaForm", nbaForm);

        //チーム名をドロップダウンにセット
        this.setTeam(model);

        return "nba";
    }

    /**
     * 登録画面の初期表示
     * @param form
     * @param model
     * @param result
     * @return
     */
    @GetMapping("/nba/new")
    public String initNew(NbaForm form, Model model, BindingResult result) {

        // チーム名セット
        this.setTeam(model);

        return "nba";
    }

    /**
     * 保存機能(新規登録・更新)
     * @param form
     * @param result
     * @param model
     * @return
     */
    @PostMapping("/nba/save")
    public String save(@Validated NbaForm form, BindingResult result, Model model) {
        
        this.setTeam(model);

        if (result.hasErrors()) {
            return "nba";
        }

        try {

            // 詰め替え作業
            Player player = new Player();
            BeanUtils.copyProperties(form, player);
            log.info("登録情報" + player);

            // 保存(変数に格納する理由はちゃんと更新されているか確かめる為)
            int cnt = service.save(player);
            log.info("保存件数" + cnt);

        } catch (Exception e) {
            result.addError(new ObjectError("global", e.getMessage()));
        }
        return "redirect:/nba";
        
    }

    /**
     * 削除機能
     * @param form
     * @param result
     * @param model
     * @return
     */
    @PostMapping("/nba/delete")
    public String delete(@Validated NbaForm form, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "nba";
        }

        try {
            // 詰め替え作業
            Player player = new Player();
            BeanUtils.copyProperties(form, player);
            
            // 削除
            int cnt = service.delete(player);
            log.info("削除件数" + cnt);

            // リダイレクト
            return "redirect:/nba";

        } catch (Exception e) {
            result.addError(new ObjectError("global", e.getMessage()));
            this.setTeam(model);
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