package br.edu.ifpb.padroes.store;

import br.edu.ifpb.padroes.customer.Customer;
import br.edu.ifpb.padroes.customer.CustomerType;
import br.edu.ifpb.padroes.music.AgeRestriction;
import br.edu.ifpb.padroes.music.Album;
import br.edu.ifpb.padroes.music.MediaType;
import br.edu.ifpb.padroes.store.validation.*;
import br.edu.ifpb.padroes.store.discount.*;
import br.edu.ifpb.padroes.store.notification.StoreObserver;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MusicStore {

    private List<Album> inventory = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private List<StoreObserver> observers = new ArrayList<>();

    public void addMusic(Album album) {
        inventory.add(album);
        System.out.println("Added: " + album.getTitle());
    }

    public void addObserver(StoreObserver observer) {
        this.observers.add(observer);
    }

    public void removeObserver(StoreObserver observer) {
        this.observers.remove(observer);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
        addObserver(customer); // Registra automaticamente
    }

    public List<Album> searchMusic(SearchType searchType, String searchTerm) {
        List<Album> results = new ArrayList<>();

        if (searchType.equals(SearchType.TITLE)) {
            for (Album album : inventory) {
                if (album.getTitle().toLowerCase().contains(searchTerm.toLowerCase())) {
                    results.add(album);
                }
            }
        } else if (searchType.equals(SearchType.ARTIST)) {
            for (Album album : inventory) {
                if (album.getArtist().toLowerCase().contains(searchTerm.toLowerCase())) {
                    results.add(album);
                }
            }
        } else if (searchType.equals(SearchType.GENRE)) {
            for (Album album : inventory) {
                if (album.getGenre().toLowerCase().contains(searchTerm.toLowerCase())) {
                    results.add(album);
                }
            }
        } else if (searchType.equals(SearchType.TYPE)) {
            for (Album album : inventory) {
                if (album.getType().name().equalsIgnoreCase(searchTerm)) {
                    results.add(album);
                }
            }
        }

        return results;
    }

    public double calculateDiscount(Album album, CustomerType customerType) {
        DiscountStrategy strategy;

        switch (customerType) {
            case VIP:
                strategy = new DiscountVIP();
                break;
            case PREMIUM:
                strategy = new DiscountPremium();
                break;
            case REGULAR:
            default:
                strategy = new DiscountRegular();
                break;
        }

        return strategy.calculate(album);
    }

    public void purchaseMusic(Customer customer, Album album) {
        if (validatePurchase(customer, album)) {
            double discount = calculateDiscount(album, customer.getType());
            double finalPrice = album.getPrice() - discount;

            System.out.println("Purchase: " + album.getFormattedName() + " by " + customer.getName());
            System.out.println("Original price: $" + album.getPrice());
            System.out.println("Discount: $" + discount);
            System.out.println("Final price: $" + finalPrice);

            album.decreaseStock();
            customer.addPurchase(album);

            // Notifica os observadores
            notifyObservers(album, customer);
        } else {
            System.out.println("Out of stock!");
        }
    }

    private void notifyObservers(Album album, Customer buyer) {
        for (StoreObserver observer : observers) {
            // Não notifica quem comprou
            if (!observer.equals(buyer)) {
                observer.update(album);
            }
        }
    }

    public boolean validatePurchase(Customer customer, Album album) {
        PurchaseHandler chain = new StockHandler();

        chain.linkWith(new CreditHandler())
                .linkWith(new AgeHandler());

        return chain.validate(customer, album);
    }


    public List<Album> getInventory() {
        return inventory;
    }

}
