package br.edu.ifpb.padroes.store.discount;

import br.edu.ifpb.padroes.music.Album;

public class DiscountRegular implements DiscountStrategy {
    @Override
    public double calculate(Album album) {
        return album.getPrice() * 0.05;
    }
}