package com.acggov.telegram.service;

import com.acggov.telegram.dto.Result;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.InputStream;


/**
 * Created: Nekoer
 * Desc: 接收器，分发流程
 * Date: 2020/7/25 21:07
 */
public interface PublicService {



     void sendAmazingPic(Update update);

     InputStream getPicBytes(String uri);
}
