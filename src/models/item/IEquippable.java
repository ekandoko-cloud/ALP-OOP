package models.item;
import models.character.PlayerCharacter;
public interface IEquippable {
    void equip(PlayerCharacter target);
    void unequip(PlayerCharacter target);
}


