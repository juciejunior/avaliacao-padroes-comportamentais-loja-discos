package br.edu.ifpb.padroes.store;

import br.edu.ifpb.padroes.music.Album;

public enum SearchType {
    TITLE {
        @Override
        public boolean matches(Album album, String term) {
            return album.getTitle().toLowerCase()
                    .contains(term.toLowerCase());
        }
    },

    ARTIST {
        @Override
        public boolean matches(Album album, String term) {
            return album.getArtist().toLowerCase()
                    .contains(term.toLowerCase());
        }
    },

    GENRE {
        @Override
        public boolean matches(Album album, String term) {
            return album.getGenre().toLowerCase()
                    .contains(term.toLowerCase());
        }
    },

    TYPE {
        @Override
        public boolean matches(Album album, String term) {
            return album.getType().name()
                    .equalsIgnoreCase(term);
        }
    };

    public abstract boolean matches(Album album, String term);
}
