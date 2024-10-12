package ru.clevertec.dto.projection;

public interface ChannelFilterProjection {

    Long getId();

    String getName();

    Integer getSubscribersCount();

    String getMainLanguage();

    byte[] getAvatar();

    String getCategory();
}