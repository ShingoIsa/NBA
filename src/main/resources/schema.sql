DROP TABLE IF EXISTS m_player;
CREATE TABLE m_player (
    player_no BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '選手番号',
    player_name VARCHAR(20) NOT NULL COMMENT '選手名',
    team_id VARCHAR(2) COMMENT 'チームID',
    player_height INT NOT NULL COMMENT '身長',
    player_age INT COMMENT '年齢',
    player_position VARCHAR(10) COMMENT 'ポジション',
    player_score DECIMAL(3,1) COMMENT '得点',
    player_asist DECIMAL(3,1) COMMENT 'リバウンド',
    player_rebound DECIMAL(3,1) COMMENT 'リバウンド',
    version INT COMMENT 'バージョン',
    ins_dt DATETIME COMMENT '登録日時',
    up_dt DATETIME COMMENT '更新日時'
) COMMENT '選手詳細テーブル';

DROP TABLE IF EXISTS m_team;
CREATE TABLE m_team (
    team_id VARCHAR(2) NOT NULL PRIMARY KEY COMMENT 'チームID',
    team_name VARCHAR(20) NOT NULL COMMENT 'チーム名'
) COMMENT 'チーム名テーブル';