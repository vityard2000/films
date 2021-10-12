package com.films.data.network
import com.films.data.network.model.FilmDto
import com.films.data.network.model.LinkDto
import com.films.data.network.model.MultimediaDto
import com.films.domain.interfaces.FilmsRepository
import com.films.domain.entities.Film
import com.films.domain.entities.Link
import com.films.domain.entities.Multimedia
import retrofit2.Retrofit

class NetworkFilmsRepositoryService(private val retrofit: Retrofit): FilmsRepository{
    override suspend fun loadFilms(page: Int): List<Film> {
        val offset = (page-1)*20

        val films = retrofit.create(FilmsService::class.java).getFilms(offset).body()?.let{ filmsResponse ->
            filmsResponse.results?.map{it.toFilm()}
            }

        return films?:listOf() //обработать все случаи
    }

    companion object {
        //mapping
        fun FilmDto.toFilm(): Film {
            return Film(
                displayTitle = displayTitle,
                link = link?.toLink(),
                multimedia = multimedia?.toMultimedia(),
                dateUpdated = dateUpdated,
                publicationDate = publicationDate,
                summaryShort = summaryShort,
                criticsPick = criticsPick,
                byline = byline,
                headline = headline,
                mpaaRating = mpaaRating,
                openingDate = openingDate
            )
        }
        fun LinkDto.toLink(): Link {
            return Link(
                suggestedLinkText = suggestedLinkText,
                type = type,
                url = url
            )
        }
        fun MultimediaDto.toMultimedia(): Multimedia {
            return Multimedia(
                src = src,
                type = type,
                width = width,
                height = height
            )
        }
    }




}
