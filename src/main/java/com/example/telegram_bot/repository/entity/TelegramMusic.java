package com.example.telegram_bot.repository.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "tg_music")
public class TelegramMusic {
    @Id
    @Column(name = "id")
    private String Id;

    @Column(name = "path")
    private String active;

    @Column(name = "")
}
