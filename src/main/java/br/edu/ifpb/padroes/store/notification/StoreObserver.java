package br.edu.ifpb.padroes.store.notification;

import br.edu.ifpb.padroes.music.Album;

public interface StoreObserver {
    void update(Album album);
}