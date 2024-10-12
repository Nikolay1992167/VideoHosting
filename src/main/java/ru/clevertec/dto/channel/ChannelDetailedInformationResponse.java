package ru.clevertec.dto.channel;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import ru.clevertec.dto.user.UserResponse;
import ru.clevertec.enam.Language;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ChannelDetailedInformationResponse(Long id,
                                                 String title,
                                                 String description,
                                                 UserResponse author,
                                                 Integer subscribersCount,

                                                 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
                                                 LocalDate createdAt,

                                                 Language mainLanguage,
                                                 byte[] avatar,
                                                 String category) {


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChannelDetailedInformationResponse that = (ChannelDetailedInformationResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.deepEquals(avatar, that.avatar) && category == that.category && Objects.equals(description, that.description) && Objects.equals(author, that.author) && mainLanguage == that.mainLanguage && Objects.equals(createdAt, that.createdAt) && Objects.equals(subscribersCount, that.subscribersCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, author, subscribersCount, createdAt, mainLanguage, Arrays.hashCode(avatar), category);
    }

    @Override
    public String toString() {
        return "ChannelDetailedInformationResponse{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", author=" + author +
                ", subscribersCount=" + subscribersCount +
                ", createdAt=" + createdAt +
                ", mainLanguage=" + mainLanguage +
                ", avatar=" + Arrays.toString(avatar) +
                ", category=" + category +
                '}';
    }
}