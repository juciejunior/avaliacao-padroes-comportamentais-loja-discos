package br.edu.ifpb.padroes.store.validation;

import br.edu.ifpb.padroes.customer.Customer;
import br.edu.ifpb.padroes.music.Album;

public abstract class PurchaseHandler {
    protected PurchaseHandler next;

    public PurchaseHandler linkWith(PurchaseHandler next) {
        this.next = next;
        return next;
    }

    public abstract boolean validate(Customer customer, Album album);

    protected boolean checkNext(Customer customer, Album album) {
        if (next == null) {
            return true;
        }
        return next.validate(customer, album);
    }
}