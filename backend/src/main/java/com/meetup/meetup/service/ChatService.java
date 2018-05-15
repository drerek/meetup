package com.meetup.meetup.service;

import com.meetup.meetup.dao.ChatDao;
import com.meetup.meetup.exception.runtime.DeleteChatException;
import com.meetup.meetup.service.vm.ChatIds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import static com.meetup.meetup.keys.Key.EXCEPTION_CHAT_DELETE;

@Service
@PropertySource("classpath:strings.properties")
public class ChatService {

    private static Logger log = LoggerFactory.getLogger(EventService.class);

    @Autowired
    private Environment env;

    @Autowired
    private ChatDao chatDao;

    public ChatIds addChats(int eventId) {
        return new ChatIds(chatDao.createChatsByEventId(eventId));
    }

    public ChatIds getChatsIds(int eventId) {
        return new ChatIds(chatDao.findChatsIdsByEventId(eventId));
    }

    public void deleteChats(int eventId) {

        boolean success = chatDao.deleteChatsByEventId(eventId);

        if (!success) {
            throw new DeleteChatException(EXCEPTION_CHAT_DELETE);
        }

    }
}
