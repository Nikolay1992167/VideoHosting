package ru.clevertec.dto.channel;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import ru.clevertec.enam.Language;

import java.util.Arrays;
import java.util.Objects;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ChannelFilterResponse(Long id,
                                    String title,
                                    Integer subscribersCount,
                                    Language mainLanguage,
                                    byte[] avatar,
                                    String category) {


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChannelFilterResponse that = (ChannelFilterResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.deepEquals(avatar, that.avatar) && category == that.category && mainLanguage == that.mainLanguage && Objects.equals(subscribersCount, that.subscribersCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, subscribersCount, mainLanguage, Arrays.hashCode(avatar), category);
    }

    @Override
    public String toString() {
        return "ChannelFilterResponse{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", subscribersCount=" + subscribersCount +
                ", mainLanguage=" + mainLanguage +
                ", avatar=" + Arrays.toString(avatar) +
                ", category=" + category +
                '}';
    }
}