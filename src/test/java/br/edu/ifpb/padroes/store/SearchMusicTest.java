package br.edu.ifpb.padroes.store;

import br.edu.ifpb.padroes.music.AgeRestriction;
import br.edu.ifpb.padroes.music.Album;
import br.edu.ifpb.padroes.music.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchMusicTest {

    private MusicStore store;

    @BeforeEach
    void setUp(){
        store = new MusicStore();

        store.getInventory().add(new Album(
                "Master of Puppets",
                "Metallica",
                MediaType.CD,
                99.90,
                LocalDate.of(1986, 3, 3),
                AgeRestriction.GENERAL,
                "Metal",
                10
        ));

        store.getInventory().add(new Album(
                "Abbey Road",
                "The Beatles",
                MediaType.VINYL,
                120.00,
                LocalDate.of(1969, 9, 26),
                AgeRestriction.PARENTAL_ADVISORY,
                "Rock",
                5
        ));

        store.getInventory().add(new Album(
                "Back in Black",
                "AC/DC",
                MediaType.CD,
                110.00,
                LocalDate.of(1980, 7, 25),
                AgeRestriction.GENERAL,
                "Rock",
                8
        ));

        store.getInventory().add(new Album(
                "Fearless",
                "Taylor Swift",
                MediaType.VINYL,
                60.00,
                LocalDate.of(2008, 11, 11),
                AgeRestriction.GENERAL,
                "Country",
                12
        ));
    }

    @Test
    void deveBuscarPorTitulo() {
        List<Album> results = store.searchMusic(SearchType.TITLE, "Master");

        assertEquals(1, results.size());
        assertEquals("Master of Puppets", results.get(0).getTitle());
    }

    @Test
    void deveBuscarPorArtista() {
        List<Album> results = store.searchMusic(SearchType.ARTIST, "Metallica");

        assertEquals(1, results.size());
        assertEquals("Metallica", results.get(0).getArtist());
    }

    @Test
    void deveBuscarPorGenero() {
        List<Album> results = store.searchMusic(SearchType.GENRE, "Rock");

        assertEquals(2, results.size());
    }

    @Test
    void deveBuscarPorTipo() {
        List<Album> results = store.searchMusic(SearchType.TYPE, "CD");

        assertEquals(2, results.size());
    }

}
