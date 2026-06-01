package models.item;

import enums.ItemType;

public class Ingredients extends Item {

    public Ingredients(int idItem, String namaItem, int hargaJual, String deskripsi, ItemType itemType) {
        super(idItem, namaItem, hargaJual, deskripsi, itemType);
    }
}
