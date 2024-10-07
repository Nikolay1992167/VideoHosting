package ru.clevertec.dto.channel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import ru.clevertec.enam.Language;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ChannelResponse(Long id,
                              String title,
                              String description,
                              Long authorId,

                              @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
                              LocalDate createdAt,

                              Language mainLanguage,
                              byte[] avatar,
                              String category) {


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChannelResponse that = (ChannelResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(authorId, that.authorId) && Objects.deepEquals(avatar, that.avatar) && category == that.category && Objects.equals(description, that.description) && Objects.equals(createdAt, that.createdAt) && mainLanguage == that.mainLanguage;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, authorId, createdAt, mainLanguage, Arrays.hashCode(avatar), category);
    }

    @Override
    public String toString() {
        return "ChannelResponse{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", authorId=" + authorId +
                ", createdAt=" + createdAt +
                ", mainLanguage=" + mainLanguage +
                ", avatar=" + Arrays.toString(avatar) +
                ", category=" + category +
                '}';
    }
}