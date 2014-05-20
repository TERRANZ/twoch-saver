package ru.terra.twochsaver.twoch.model;

import ru.terra.twochsaver.twoch.dto.PostDTO;

/**
 * Date: 11.11.13
 * Time: 16:25
 */
public interface Model {
    public void createThread(Long id, long startMessage, String board);

    public boolean isThreadExists(Long num);

    public boolean isMessageExists(Long num);

    public void createMessage(PostDTO msg);

    public void close();
}
