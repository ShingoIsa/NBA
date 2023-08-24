package jp.co.ambitious.sample.nba.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.ambitious.sample.nba.beans.Player;
import jp.co.ambitious.sample.nba.mappers.PlayerMapper;

@Service
public class NbaService {
    
    @Autowired
    PlayerMapper playerMapper;

    public List<Player> getPlayer() {
        return playerMapper.selectAll();
    }
}
