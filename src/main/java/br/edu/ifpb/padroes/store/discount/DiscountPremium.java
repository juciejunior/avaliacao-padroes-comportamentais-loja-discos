package br.edu.ifpb.padroes.store.discount;

import br.edu.ifpb.padroes.music.Album;
import br.edu.ifpb.padroes.music.MediaType;

public class DiscountPremium implements DiscountStrategy {
    @Override
    public double calculate(Album album) {
        double discount = album.getPrice() * 0.15;
        if (album.getType().equals(MediaType.VINYL) && album.getReleaseDate().getYear() < 1980) {
            discount += album.getPrice() * 0.10;
        }
        return discount;
    }
}