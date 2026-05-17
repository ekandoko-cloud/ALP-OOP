package models.item;
import java.util.*;
public abstract class Item {
    protected int idItem;
    protected String namaItem;
    protected int hargaJual;
    protected String deskripsi;

    protected Item(int idItem, String namaItem, int hargaJual, String deskripsi) {
        this.idItem = idItem;
        this.namaItem = namaItem;
        this.hargaJual = hargaJual;
        this.deskripsi = deskripsi;
    }


    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public String getNamaItem() {
        return namaItem;
    }

    public void setNamaItem(String namaItem) {
        this.namaItem = namaItem;
    }

    public int getHargaJual() {
        return hargaJual;
    }

    public void setHargaJual(int hargaJual) {
        this.hargaJual = hargaJual;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public void tampilkanInfoItem() {
    }
}


