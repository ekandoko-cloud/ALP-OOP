package models.item;
import enums.ItemType;
public abstract class Item {
    protected int idItem;
    protected String namaItem;
    protected int hargaJual;
    protected String deskripsi;
    protected ItemType itemType;

    protected Item(int idItem, String namaItem, int hargaJual, String deskripsi,  ItemType itemType) {
        this.idItem = idItem;
        this.namaItem = namaItem;
        this.hargaJual = hargaJual;
        this.deskripsi = deskripsi;
        this.itemType = itemType;
    }

    public int getIdItem() {
        return idItem;
    }

    public String getNamaItem() {
        return namaItem;
    }

    public int getHargaJual() {
        return hargaJual;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public ItemType getItemType() {
        return itemType;
    }
}


