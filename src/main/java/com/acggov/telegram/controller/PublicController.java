package com.acggov.telegram.controller;


import com.acggov.telegram.service.PublicService;
import com.acggov.telegram.service.impl.PublicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Created: Nekoer
 * Desc: 电报接收
 * Date: 2020/7/25 21:06
 */
@RestController
@RequestMapping("public")
public class PublicController {
    @Autowired
    private PublicServiceImpl publicService;

    @RequestMapping(method = RequestMethod.POST, value = "msg", produces = MediaType.APPLICATION_JSON_VALUE)
    public BotApiMethod getMsg(@RequestBody Update update) {
        return publicService.onWebhookUpdateReceived(update);
    }

}
