package br.edu.ifpb.padroes.store.discount;

import br.edu.ifpb.padroes.music.Album;

public class DiscountVIP implements DiscountStrategy {
    @Override
    public double calculate(Album album) {
        double discount = album.getPrice() * 0.20;
        if (album.getGenre().equalsIgnoreCase("Pop Punk")) {
            discount += album.getPrice() * 0.05;
        }
        return discount;
    }
}