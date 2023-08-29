-- 選手詳細テーブル
INSERT INTO m_player (player_name, team_id, player_height, player_age, player_position, player_score, player_asist, player_rebound, version, ins_dt, up_dt) VALUE ('ステフィン・カリー', '1', 191, 35, 'PG', 35.6, 8.8, 6.9, 1, now(), null);
INSERT INTO m_player (player_name, team_id, player_height, player_age, player_position, player_score, player_asist, player_rebound, version, ins_dt, up_dt) VALUE ('ヤニス・アンデトクンポ', '2', 211, 28, 'PF', 31.1, 11.8, 5.7, 1, now(), null);
INSERT INTO m_player (player_name, team_id, player_height, player_age, player_position, player_score, player_asist, player_rebound, version, ins_dt, up_dt) VALUE ('レブロン・ジェームズ', '3', 203, 36, 'SF', 27.1, 12, 9, 1, now(), null);
INSERT INTO m_player (player_name, team_id, player_height, player_age, player_position, player_score, player_asist, player_rebound, version, ins_dt, up_dt) VALUE ('ジェイレン・ブラウン', '4', 201, 26, 'SG', 24.1, 6.5, 7, 1, now(), null);
INSERT INTO m_player (player_name, team_id, player_height, player_age, player_position, player_score, player_asist, player_rebound, version, ins_dt, up_dt) VALUE ('二コラ・ヨキッチ', '5', 211, 28, 'C', 28.1, 10.5, 10.9, 1, now(), null);
INSERT INTO m_player (player_name, team_id, player_height, player_age, player_position, player_score, player_asist, player_rebound, version, ins_dt, up_dt) VALUE ('ドリュー・ホリデー', '2', 195, 28, 'PG', 15.1, 10.0, 5, 1, now(), null);

-- チーム名テーブル
INSERT INTO m_team (team_id, team_name) VALUE ('1', 'ゴールデンステート・ウォリアーズ');
INSERT INTO m_team (team_id, team_name) VALUE ('2', 'ミルウォーキー・バックス');
INSERT INTO m_team (team_id, team_name) VALUE ('3', 'ロサンゼルス・レイカーズ');
INSERT INTO m_team (team_id, team_name) VALUE ('4', 'ボストン・セルティックス');
INSERT INTO m_team (team_id, team_name) VALUE ('5', 'デンバー・ゲッツ');