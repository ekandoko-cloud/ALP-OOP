# NUTRITALES — Dokumentasi Teknis

> Game RPG berbasis console bertema nutrisi (SDG 2 / Zero Hunger) yang dibangun dengan Java murni (no framework). Pemain mengelola party 4 karakter untuk menyelesaikan main quest, side quest, mini game, crafting, forging, dan gacha.

---

## Daftar Isi

1. [Arsitektur & Alur Utama](#1-arsitektur--alur-utama)
2. [Package `enums`](#2-package-enums)
3. [Package `main`](#3-package-main)
4. [Package `models.account`](#4-package-modelsaccount)
5. [Package `models.character`](#5-package-modelscharacter)
6. [Package `models.item`](#6-package-modelsitem)
7. [Package `models.location`](#7-package-modelslocation)
8. [Package `models.quest`](#8-package-modelsquest)
9. [Package `systems.battle`](#9-package-systemsbattle)
10. [Package `systems.classSystem`](#10-package-systemsclasssystem)
11. [Package `systems.craft`](#11-package-systemscraft)
12. [Package `systems.encyclopedia`](#12-package-systemsencyclopedia)
13. [Package `systems.gacha`](#13-package-systemsgacha)
14. [Package `systems.inventory`](#14-package-systemsinventory)
15. [Package `systems.map`](#15-package-systemsmap)
16. [Package `systems.music`](#16-package-systemsmusic)
17. [Package `systems.quest`](#17-package-systemsquest)
18. [Package `systems.save`](#18-package-systemssave)
19. [Package `systems.shop`](#19-package-systemsshop)
20. [Package `systems.skill`](#20-package-systemsskill)
21. [Package `systems.vault`](#21-package-systemsvault)
22. [Package `minigames`](#22-package-minigames)
23. [Package `DummyData`](#23-package-dummydata)

---

## 1. Arsitektur & Alur Utama

```
main.Main  ─►  main.App.startMenu()
                    │
                    ├── login() / register()
                    │       └── saveload.load() atau buat baru
                    │
                    └── mainMenu()
                            ├── Inventory (lihat/pakai/equip item)
                            ├── Shop (beli/jual)
                            ├── CraftingSystem (craft consumable baru)
                            ├── ForgeSystem (upgrade level equipment)
                            ├── SkillSystem (buka skill dengan gold)
                            ├── Encyclopedia (info item/monster/lokasi/quest)
                            ├── GachaSystem (gacha item)
                            ├── QuestTracker (ambil/klaim main+sub quest)
                            ├── AdventureSystem (explore, battle, puzzle, treasure)
                            ├── MapTraversal + WaypointSystem (pindah area)
                            ├── Vault (simpan/tarik item)
                            ├── MusicPlayer (playlist)
                            └── Save / Load
```

**Konsep OOP yang digunakan:**
- **Abstraction** — `GameCharacter`, `Item`, `Equipment`, `Quest` (abstract)
- **Inheritance** — `PlayerCharacter` & `Monster` extends `GameCharacter`; `Weapon`/`Armor`/`Accessory` extends `Equipment`; `MainQuest`/`SubQuest` extends `Quest`
- **Polymorphism** — `serang()`, `equip()`, `getRequiredClassType()`, `displayDetail()` di-override
- **Interface** — `Skill` (untuk skill), `IConsumable`, `IEquippable`
- **Encapsulation** — semua field atribut `private`/`protected` dengan getter/setter
- **Composition** — `AccountProfile` memiliki `QuestTracker`, `LinkedList<Item>`, `PlayerCharacter[]`
- **Aggregation** — `PlayerCharacter` punya `Equipment` reference (current weapon/armor/accessory)

---

## 2. Package `enums`

Package ini menyimpan semua enum yang digunakan untuk menggantikan magic-string dan meningkatkan type-safety.

### 2.1 `BattleResult`
- **Tipe:** enum
- **Nilai:** `VICTORY`, `DEFEAT`, `FLED`
- **Tujuan:** Outcome dari `BattleSystem.mulaiPertarungan()`.
- **Dipakai oleh:** `BattleSystem`, `AdventureSystem`.

### 2.2 `ClassType`
- **Tipe:** enum
- **Nilai:** `CLASSLESS, WARRIOR, ARCHER, MAGE, SUPPORT, KNIGHT, SWORDSMAN, BERSERKER, SCOUT, MARKSMAN, RANGER, WIZARD, WITCH, ARCHMAGE, SORCERER, SHIELDMAN, ANGEL, PALADIN, ARCHANGEL` (20 nilai)
- **Tujuan:** Mengkategorikan class/job karakter untuk membatasi equipment & menentukan bonus stat.
- **Dipakai oleh:** `Equipment` (required class check), `ClassSystem.applyClassToCharacter`, `BattleSystem.isHealClass`.

### 2.3 `EquipmentType`
- **Tipe:** enum
- **Nilai:** `WEAPON, ARMOR, ACCESSORY`
- **Tujuan:** Slot equipment.
- **Dipakai oleh:** `Equipment.equipmentType`, `SaveLoadSystem` parsing.

### 2.4 `ItemType`
- **Tipe:** enum
- **Nilai:** `CONSUMABLE, EQUIPMENT, INQREDIENT`
- **Tujuan:** Kategori item untuk filter inventory dan encyclopedia.
- **Dipakai oleh:** `Item.itemType`, `Inventory.displayInventoryByCategory`, `Encyclopedia`.

### 2.5 `StatusLokasi`
- **Tipe:** enum
- **Nilai:** `TERKUNCI, TERBUKA, TERTUTUP_SEMENTARA`
- **Tujuan:** Status pembukaan lokasi. Catatan: sebenarnya tracking buka/tutup lokasi dilakukan via `AccountProfile.statusLokasi` (List<String>); enum ini hanya dipakai sebagai return value dari helper.
- **Dipakai oleh:** `AccountProfile.getStatusLokasi(String)`.

### 2.6 `StatusQuest`
- **Tipe:** enum
- **Nilai:** `BELUM_DIAMBIL, ONGOING, COMPLETED, REWARDED`
- **Tujuan:** State lifecycle sebuah quest.
- **Dipakai oleh:** `Quest.statusQuest`, `QuestTracker`, `MainQuest`/`SubQuest` UI.

---

## 3. Package `main`

### 3.1 `Main`
- **Tipe:** final class
- **Tujuan:** Entry point program. Hanya berisi `public static void main(String[] args)` yang memanggil `new App().startMenu()`.
- **Logika:** Single-line bootstrapping. Tidak ada logika kompleks.

### 3.2 `AnsiColors` *(utilitas bersama, baru setelah refactor)*
- **Tipe:** final class dengan `private` constructor
- **Atribut konstan:** `RESET, BOLD, ITALIC, CYAN, GREEN, YELLOW, MAGENTA, RED, RED_BRIGHT, SOFT_TEAL, WARM_GOLD, SOFT_WHITE, SOFT_GREEN, DIM_GRAY`
- **Tujuan:** Sentralisasi semua ANSI escape code warna/teks agar mudah di-tweak dan tidak terduplikasi di 6 file (`App`, `MainQuest`, `SubQuest`, `Shop`, `Encyclopedia`, `GiziGame`).
- **Penggunaan:** `AnsiColors.RESET + "Hello" + AnsiColors.GREEN + " World" + AnsiColors.RESET`
- **Catatan refactor:** sebelumnya setiap kelas memiliki 13-14 deklarasi `private static final String ANSI_*` yang identik; sekarang hanya satu sumber.

### 3.3 `App`
- **Tipe:** class publik (orchestrator)
- **Atribut penting:**
  - `Scanner inpInt, inpStr` — input reader (dipisah int vs string agar tidak bentrok)
  - `HashMap<Integer, Item> ingredientAlamCatalog, ingredientMonsterCatalog, ingredientConsumablesCatalog, consumables, weaponCatalog, armorCatalog, accessoryCatalog` — katalog item dari `DummyData`
  - `HashMap<Integer, Location> kotaMap`, `HashMap<Integer, Monster> monster`, `HashMap<Integer, itemGacha> gachaItems`, `HashMap<Integer, craftingRecipe> craftingRecipes` — katalog dunia
  - `AccountProfile currentAccount` — akun yang sedang login
  - `ForgeSystem forgeSystem`, `CraftingSystem craftingSystem`, `PlayerCharacter[] party` — sistem & state party
  - `Map<String, Skill> classSkills` — skill default tiap class (damageSkill / healSkill)
  - `Vault vault`, `MusicPlayer musicPlayer`, `MapTraversal mapTraversal`, `WaypointSystem waypointSystem`, `GachaSystem gachaSystem`, `SaveLoadSystem saveload`
- **Method utama:**
  - `startMenu()` — menu awal Login/Register/Keluar
  - `register()` — input username/password, simpan ke `accounts.txt`
  - `login()` — verifikasi akun, load save atau buat party baru (4 karakter `CLASSLESS`)
  - `checkUsername()`, `saveAcc()`, `verifyLogin()` — manajemen akun file
  - `mainMenu()` — menu utama setelah login (inventory, shop, craft, forge, skill, encyclopedia, gacha, quest, adventure, map, vault, music, save)
  - `syncPartySkills()` — pasang `Skill` (damageSkill/healSkill) ke tiap `PlayerCharacter` di party sesuai `namaClass`
  - `ensureQuestTrackerCatalog(AccountProfile)` — attach katalog dari `DummyData` ke quest tracker
  - `findLocationByName(String)` — utilitas lookup
- **Flow login:** username/password → cek `accounts.txt` → jika valid → `saveload.load()` atau create new party → `syncPartySkills()` → init `forgeSystem` → `mapTraversal` & `waypointSystem` → masuk `mainMenu()`.

---

## 4. Package `models.account`

### 4.1 `AccountProfile`
- **Tipe:** class publik
- **Konstanta:**
  - `MAX_PARTY_SIZE = 4` (private static final)
  - `DEFAULT_MAX_INVENTORY_SLOTS = 200` (public static final)
- **Atribut:**
  - `String username, password`
  - `int totalGold, totalPlaytime`
  - `transient long sessionStartMillis` — untuk hitung playtime session aktif
  - `String areaName` — area terakhir dikunjungi
  - `PlayerCharacter[] party` — dibatasi max 4 via `limitPartySize()`
  - `LinkedList<Item> inventory` — slot item (LinkedList untuk efisiensi add/remove)
  - `QuestTracker questTracker` — referensi ke tracker
  - `int maxInventorySlots` — default 200
  - `ArrayList<String> unlockedSkillNames` — list nama skill yang sudah dibuka
  - `ArrayList<String> statusLokasi` — list nama lokasi yang sudah dikunjungi (disimpan lowercase)
- **Method penting:**
  - `limitPartySize(PlayerCharacter[])` — helper private, potong array jika > 4
  - `startPlaytime()`, `stopPlaytimeAndAccumulate()`, `getTotalPlaytimeFormatted()` — playtime tracking
  - `addItemToInventory(Item)`, `removeItemFromInventory(Item)` — bounded by `maxInventorySlots`
  - `setMaxInventorySlots(int)` — set + auto-trim
  - `trimInventoryToLimit()` — private helper, potong kelebihan dari belakang
  - `addUnlockedSkillName(String)`, `isSkillUnlocked(String)` — track unlocked skill
  - `kunjungiLokasi(String)`, `sudahMengunjungi(String)` — track visited location (lowercase, unique)
  - `getStatusLokasi(String)` — overload: return `StatusLokasi` enum (TERBUKA / TERKUNCI)
  - `getVisitedLocationNames()` — untuk serialisasi save
- **Konstruktor:** Inisialisasi party, null-kan inventory lalu `setInventory()` agar trimming berjalan.
- **Catatan OOP:** Field `sessionStartMillis` transient → tidak ikut diserialisasi jika pakai Java Serialization. `getStatusLokasi()` punya 2 overload (no-arg mengembalikan list, with-arg mengembalikan enum).

---

## 5. Package `models.character`

### 5.1 `GameCharacter` (abstract)
- **Tipe:** abstract class
- **Atribut (protected):** `nama, maxHp, currentHp, maxMp, currentMp, kekuatan, defense, defending`
- **Konstruktor:** `GameCharacter(nama, maxHp, currentHp, kekuatan, defense)` — `maxMp/currentMp` default 0; `defending = false`.
- **Method:**
  - Getter/setter untuk semua atribut
  - `isAlive()` — `currentHp > 0`
  - `getXpReward()` — return 0 (default, di-override oleh `Monster`/`BossMonster`)
  - `serang(GameCharacter target)` — damage = `max(1, kekuatan - target.defense)`, apply via `terimaDamage`
  - `defend()` — set `defending = true`
  - `terimaDamage(int damage)` — jika `defending` aktif, damage dibulatkan ke `ceil(damage*0.5)` minimal 1, lalu `defending = false`; clamp currentHp ≥ 0; return actualDamage
  - `modifikasiStat(hp, mp, atk, def)` — bump semua stat, clamp min (maxHp ≥ 1, mp ≥ 0, atk/def ≥ 0), heal/proc currentHp/currentMp
- **Peran:** Base class polymorphic untuk `PlayerCharacter` & `Monster`/`BossMonster`.

### 5.2 `PlayerCharacter`
- **Tipe:** class extends `GameCharacter`
- **Atribut tambahan (private):** `currentExp, maxExp, namaClass, statusTubuhNirlelah, level (protected), skill, currentWeapon, currentArmor, currentAccessory`
- **Konstruktor:** `PlayerCharacter(nama, maxHp, currentHp, maxMp, currentMp, kekuatan, defense, level, currentExp, maxExp, namaClass, statusTubuhNirlelah)` — inisialisasi semua + default equipment null.
- **Method khusus:**
  - `getEquipmentBySlot(String slot)` / `setEquipmentBySlot(String slot, Equipment e)` — get/setter by slot (WEAPON/ARMOR/ACCESSORY), menggunakan private `normalizeSlot()`
  - `normalizeSlot(String)` — private: trim, uppercase, validasi non-empty
  - `tambahExp(int exp)` — akumulasi EXP; jika `currentExp >= maxExp`, loop level-up
  - `levelUp()` — `level++`, `maxExp = level*100`, `maxHp++`, `currentHp = maxHp`, `maxMp++`, `currentMp = maxMp`, `kekuatan++`, `defense++`, print pesan
  - `gunakanSkillUnik(GameCharacter target)` — jika `skill != null` panggil `skill.gunakanSkill(this, target)`, else basic attack (1.5x kekuatan dikurangi defense)
- **Catatan:** Sama-sama punya `normalizeSlot` dengan `Equipment.normalizeSlot` (private, di-duplikasi karena beda scope). Disengaja tidak diekstrak ke utilitas karena ruang lingkupnya berbeda.

### 5.3 `Monster`
- **Tipe:** class extends `GameCharacter`
- **Atribut:** `triviaPenyakit, xpDiberikan` (default 10 jika tidak diisi)
- **Override:** `getXpReward()` return `xpDiberikan`
- **Konstruktor:** Overload (6-arg tanpa xpDiberikan → default 10; 7-arg dengan xpDiberikan custom)

### 5.4 `BossMonster`
- **Tipe:** class extends `GameCharacter` **implements `Skill`**
- **Atribut:** `triviaPenyakit, xpDiberikan` (default 50)
- **Override:** `getXpReward()` return `xpDiberikan`
- **Override Skill interface:** `gunakanSkill(source, target)` — damage = `max(1, source.kekuatan + source.defense/2 - target.defense)`, apply via `setCurrentHp`
- **Catatan:** `implements Skill` di sini adalah duplikasi dari `Monster` (tidak ada `implements Skill`). Konsolidasi bisa dilakukan dengan mengangkat skill ke `GameCharacter` sebagai default `void gunakanSkill(...)` (no-op), tapi mengubah struktur class. Disengaja dibiarkan.

### 5.5 `Skill` (interface)
- **Tipe:** interface
- **Method:** `void gunakanSkill(GameCharacter source, GameCharacter target)`
- **Implementor:** `BossMonster`, dan class anonim `damageSkill`/`healSkill` di `App` (static initializer block).

---

## 6. Package `models.item`

### 6.1 `Item` (abstract)
- **Tipe:** abstract class
- **Atribut (protected):** `idItem, namaItem, hargaJual, deskripsi, itemType`
- **Konstruktor:** protected, set semua atribut.
- **Method:** Getter/setter standar.

### 6.2 `IConsumable` (interface)
- **Tipe:** interface
- **Method:** `void consume(GameCharacter target)`
- **Implementor:** `ConsumableFood`

### 6.3 `IEquippable` (interface)
- **Tipe:** interface
- **Method:** `void equip(PlayerCharacter target)`, `void unequip(PlayerCharacter target)`
- **Implementor:** `Equipment` (abstract)

### 6.4 `Equipment` (abstract, extends `Item implements IEquippable`)
- **Atribut:** `equipmentType (EquipmentType), levelTempa (int)`
- **Konstruktor:** protected, set tipe + level tempa
- **Method abstract:** `getBonusKekuatan()`, `setBonusKekuatan(int)`, `getBonusDefense()`, `setBonusDefense(int)`
- **Method concrete:**
  - `getRequiredClassType()` — default `ClassType.CLASSLESS` (di-override `Weapon` & `Armor`)
  - `setTipeEquipment(EquipmentType)` / overload `setTipeEquipment(String)` — try parse enum, ignore if invalid
  - `equip(PlayerCharacter target)` / `equip(target, slot)` — equip ke slot; cek class compatibility; jika sudah ada equipment lain, kurangi stat dulu; tambah stat
  - `unequip(target)` / `unequip(target, slot)` — kebalikan equip; hanya unequip jika `equipped.idItem == this.idItem`
  - `slotName()` — return `equipmentType.name()` atau default `"WEAPON"`
  - `getPlayerClassType(target)` — parse `target.namaClass` ke enum, fallback `CLASSLESS`
  - `normalizeSlot(String)` — private, validasi slot harus WEAPON/ARMOR/ACCESSORY

### 6.5 `Weapon` (extends `Equipment`)
- **Atribut:** `bonusKekuatan, requiredClassType (default CLASSLESS)`
- **Override:** `getBonusKekuatan()/setBonusKekuatan`, `getRequiredClassType()`
- **No-op:** `getBonusDefense()` return 0; `setBonusDefense(int)` kosong (Weapon tidak menambah defense).

### 6.6 `Armor` (extends `Equipment`)
- **Atribut:** `bonusDefense, requiredClassType`
- **Override:** `getBonusDefense()/setBonusDefense`, `getRequiredClassType()`
- **No-op:** `getBonusKekuatan()` return 0; `setBonusKekuatan(int)` kosong.

### 6.7 `Accessory` (extends `Equipment`)
- **Atribut:** `bonusKekuatan, bonusDefense` (keduanya bisa non-zero — cincin/anting bisa dua-duanya)
- **Override:** `getBonusKekuatan/setBonusKekuatan`, `getBonusDefense/setBonusDefense`

### 6.8 `ConsumableFood` (extends `Item implements IConsumable`)
- **Atribut:** `healHpAmount, healMpAmount, strBuff, defBuff, infoGiziSDG`
- **Override interface:** `consume(GameCharacter target)` — restore HP/MP (clamp), tambahkan str/defBuff (permanent)
- **Catatan refactor:** dulunya ada `consume(target)` yang delegate ke `useItem(target)`. Digabung jadi satu method `consume` saja. Interface `IConsumable` otomatis terpenuhi. Pemanggil (`Inventory.useItem`, `BattleSystem.mulaiPertarungan` baris 183) di-update ke `consume`.

### 6.9 `Ingredients` (extends `Item`)
- **Tipe:** class konkret minimal, hanya konstruktor 5-argumen.
- **Tujuan:** Item material untuk crafting/forge.

---

## 7. Package `models.location`

### 7.1 `Location`
- **Tipe:** class publik
- **Atribut:** `namaLokasi, deskripsiLokasi`
- **Method:** Getter/setter standar.
- **Tujuan:** Representasi area/wilayah (Valerion, Asgard, Grandis, Lumina, Aldoria, dsb).

---

## 8. Package `models.quest`

### 8.1 `Quest` (abstract)
- **Tipe:** abstract class
- **Atribut (protected):** `idQuest, namaQuest, deskripsiQuest, objectiveQuest, objectiveTarget, objectiveProgress, hadiahKoin, statusQuest, riwayatObjective (ArrayList<String>)`
- **2 Konstruktor:**
  1. Lengkap: `Quest(id, nama, deskripsi, objectiveQuest, target, hadiahKoin)` — set semua
  2. Pendek: `Quest(id, nama, deskripsi, target, hadiahKoin)` — shortcut: `objectiveQuest = deskripsiQuest`
- **Method:**
  - Getter/setter standar
  - `setObjectiveProgress(int)` — set + auto `cekStatusPenyelesaian()`
  - `catatObjective(int progressTambahan, String catatan)` — increment progress (clamp 0..target), tambahkan ke `riwayatObjective` dengan format `"catatan (progress/target)"`
  - `cekStatusPenyelesaian()` — jika `progress >= target` → `statusQuest = COMPLETED`

### 8.2 `MainQuest` (extends `Quest`)
- **Tipe:** class konkret
- **Atribut tambahan:** `chapterTerbuka, wilayah, nomorQuest, hadiahUtama, lineUpMusuh (ArrayList<String>)`
- **2 Konstruktor:** 7-argumen (lengkap dengan `chapterTerbuka` saja) dan 11-argumen (lengkap dengan wilayah, nomor, hadiah, lineUpMusuh)
- **Method:**
  - `getLineUpMusuh()` — return `Collections.unmodifiableList(lineUpMusuh)` agar tidak bisa dimodifikasi langsung
  - `setLineUpMusuh(List<String>)` — clear + addAll
  - `getLineUpMusuhRingkas()` — return `String.join(", ", lineUpMusuh)`
  - `membutuhkanMusuh(String namaMusuh)` — cek apakah `lineUpMusuh` mengandung namaMusuh (case-insensitive)
  - `bisaDiambilPadaChapter(int chapterAktif)` — return `chapterAktif >= chapterTerbuka`
  - `catatObjectiveMainQuest(int, String)` — panggil super `catatObjective` dengan prefix `"[MainQuest] "`
  - `tambahProgress(int, String)` — alias dari `catatObjectiveMainQuest`
- **Method static (UI/Logic):**
  - `displayQuestTracker(QuestTracker)` — banner ASCII + list main quest ONGOING
  - `displayCompletedQuests(QuestTracker)` — list quest yang sudah selesai (Completed atau Rewarded)
  - `displayQuestBoard(QuestTracker)` — papan quest per wilayah (5 wilayah: Valerion, Asgard, Grandis, Lumina, Aldoria) dengan icon status
  - `displayQuestBoardForArea(QuestTracker, currentArea, Scanner)` — sub-board per area + input pilih quest (mengubah ke ONGOING)
  - `berikanHadiah(MainQuest, AccountProfile, Map... maps)` — saat klaim: set REWARDED, tambah gold (quest.hadiahKoin), EXP (50 + chapter*30 per anggota party), distribusi item hadiah (parse "Nx Item Name")
  - `cariItem(String nama, Map<Integer, Item>... maps)` — private varargs helper, cari item by nama (case-insensitive) di semua map
  - `tampilkanMusuhWilayah(String, List<Monster>)` — print enemy list per wilayah
  - `getNamaMusuhWilayah(String)` — switch-case, return list nama musuh per wilayah
  - `tampilkanSemuaQuest(List<MainQuest>)` — list semua quest (semua chapter)
- **Catatan refactor:** `butuhMusuh` & `siapDipakai` (alias method) dihapus karena duplikat dari `membutuhkanMusuh` & `bisaDiambilPadaChapter`. `QuestTracker.catatMusuhKalah` di-update ke `membutuhkanMusuh`.

### 8.3 `SubQuest` (extends `Quest`)
- **Tipe:** class konkret
- **Atribut tambahan:** `wilayah (final)` — wilayah spesifik side quest
- **Konstruktor:** 7-argumen (id, nama, deskripsi, objective, target, hadiah, wilayah)
- **Method:**
  - `bisaDiambilPadaArea(String areaSekarang)` — return true jika area cocok & status BELUM_DIAMBIL
- **Method static (UI/Logic):**
  - `displayQuestTracker(QuestTracker)` — banner ASCII + list sub quest
  - `displayQuestBoardForArea(QuestTracker, currentArea, Scanner)` — papan sub quest per area + input pilih; membuat instance `SubQuest` baru (deep copy) untuk player agar tidak memutasi katalog `DummyData`
  - `getAvailableSubQuests(QuestTracker, currentArea)` — return list sub quest BELUM_DIAMBIL di area tsb
  - `containsQuestId(List<SubQuest>, int idQuest)` — private helper

### 8.4 `Catatan tambahan Package quest`
- `MainQuest` & `SubQuest` masing-masing punya banyak static method yang berisi presentasi ASCII + logika UI quest. Ini menyebabkan "fat model" — alternatif ideal: pindahkan UI ke `App` atau layer `view` terpisah. Disengaja dibiarkan sesuai constraint "jangan ubah UI/menu".

---

## 9. Package `systems.battle`

### 9.1 `BattleLog`
- **Tipe:** class
- **Atribut:** `turnEntries (ArrayList<ArrayList<String>>)` — list of turns, each turn is list of string
- **Method:**
  - Konstruktor: inisialisasi 1 turn kosong
  - `nextTurn(int turn)` — set currentTurn + add ArrayList kosong jika kurang
  - `tambahEntri(String teks)` — tambahkan ke turn aktif
  - `tampilkanLog()` — print semua turn + entries
  - `bersihkan()` — clear + reset turn 1
  - `getHistoryLog()` / `setHistoryLog()` — flatten turnEntries ↔ list (untuk save/load)

### 9.2 `BattleEnemyFactory`
- **Tipe:** class dengan private constructor (utility/static)
- **Method static:**
  - `createPartyFromQuest(MainQuest, int chapter)` — ambil lineUpMusuh dari quest, buat party via createPartyFromNames
  - `createRandomPartyForChapter(int, Random)` — random pick main quest di chapter tsb
  - `createPartyFromNames(List<String>, int chapter)` — buat `GameCharacter[]` dari nama
  - `createEnemy(String name, int chapter)` — generate `Monster` atau `BossMonster` dengan stat scale by chapter (hp 36+18*tier, atk 8+4*tier, def 2+2*tier, xp=max(5, hp/10+atk*3)); boss punya multiplier 1.8/1.5/1.4 untuk hp/atk/def
  - `isBossEnemy(String)` — cek keyword ("boss", "blight-root", "goliath toad", "baron gluttony", "dr. mortis", "crimson chimera")
  - `triviaFor(String)` — private, return `"Encounter monster: " + name + "."`

### 9.3 `BattleSystem`
- **Tipe:** class
- **Atribut:** `partyPlayer (PlayerCharacter[]), partyEnemy (GameCharacter[]), battleLog (BattleLog), random (Random)`
- **Method utama `mulaiPertarungan(Scanner, LinkedList<Item>, QuestTracker)`:**
  - Loop turn-based: cek kondisi menang/kalah → tampilkan status → loop player actions → cek lagi → loop enemy actions → next turn
  - Aksi player: Serang / Bertahan / Skill / Pakai Item / Lewati / Lihat Log / Kabur
  - Heal class (Support/Shieldman/Angel/Paladin/Archangel) saat pakai skill → target ally; class lain → target enemy
  - Cek musuh kalah → trigger `QuestTracker.catatMusuhKalah` untuk update progress main quest
  - Return `BattleResult.VICTORY/DEFEAT/FLED`
  - Victory: `berikanXpHadiah()` — total XP dari musuh yang sudah kalah, distribusikan ke party yang masih hidup via `tambahExp`
- **Helper private:** `tampilkanStatusPertarungan, tampilkanOpsiAksi, pilihTargetMusuh, pilihTargetParty, pilihConsumable, pilihTargetPartyAcak, bacaPilihan, isHealClass, semuaMusuhDikalahkan, semuaPartyDikalahkan, cekMusuhKalah, berikanXpHadiah`
- **Catatan refactor:** Method `catatLog(String)` yang private & tidak pernah dipanggil → **dihapus**.

### 9.4 `AdventureSystem`
- **Tipe:** class
- **Konstanta:** `CHANCE_BATTLE = 55, CHANCE_TREASURE = 80` (probabilitas dalam %)
- **Method `jalankanEksplorasi(AccountProfile, MapTraversal, Scanner)`:**
  - Sinkron chapter via `QuestTracker.sinkronisasiChapterTerbuka`
  - Roll 0-99: <55 → battle random, <80 → treasure, sisanya → puzzle
  - Setelahnya: prompt "Lanjut explore / Balik ke kota"
  - Return `true` jika lanjut
- **Battle random:** `BattleEnemyFactory.createPartyFromQuest(seedQuest, chapter)` → jalankan battle
- **Treasure:** gold = 60 + chapter*35 + random(50)
- **Puzzle:** 5 puzzle berbeda per chapter dengan 4 opsi dan 1 jawaban benar (index 2)
- **Inner class `PuzzleData`** — value object untuk question/options/correctAnswer

---

## 10. Package `systems.classSystem`

### 10.1 `ClassNode`
- **Tipe:** class
- **Atribut:** `namaClass, deskripsi, syaratLevel, isUnlocked, tipeClass (ClassType), parent (ClassNode), children (ArrayList<ClassNode>)`
- **Konstruktor:** 7-argumen (semua atribut)
- **Method:**
  - Getter/setter standar
  - `unlock()` — set `isUnlocked = true`
  - `isAvailable()` — return `true` (default, bisa di-override untuk rule custom)
- **Catatan refactor:** Method `unlock()` yang kosong (no-op) → diisi dengan `this.isUnlocked = true` agar `SkillSystem.unlockSkill` style pattern bisa digunakan (meskipun saat ini yang dipakai untuk unlock skill adalah `SkillNode.unlock()`).

### 10.2 `ClassTree`
- **Tipe:** class
- **Atribut:** `root (ClassNode)`
- **Method:** Getter/setter untuk root. Wrapper minimal di atas ClassNode.

### 10.3 `ClassSystem`
- **Tipe:** class dengan method static (utility)
- **Method static:**
  - `getClassTreeRoot()` — return `classtree.generateClassTree()` (dari DummyData)
  - `getAvailableClassOptions(ClassNode root, PlayerCharacter chosen)` — filter children yang `chosen.level >= child.syaratLevel`
  - `applyClassToCharacter(ClassNode, PlayerCharacter)` — set `namaClass` + apply bonus stat sesuai archetype:
    - Warrior/Knight/Swordsman/Berserker: ATK+3, DEF+1, MaxHP+5, full heal
    - Archer/Scout/Marksman/Ranger: ATK+2, DEF+1, MaxMP+2, full mana
    - Mage/Wizard/Witch/Archmage: ATK+1, MaxMP+5, full mana
    - Support/Shieldman/Angel/Paladin/Archangel: DEF+3, MaxHP+3, full heal

---

## 11. Package `systems.craft`

### 11.1 `craftingRecipe`
- **Tipe:** class
- **Atribut:** `recipeName, resultItem (Item), requiredIngredients (ArrayList<IngredientReq>)`
- **Inner class `IngredientReq`:** `ingredient (Ingredients), amount (int)`
- **Method:** Getter/setter standar + getter `getRequiredIngredients()`

### 11.2 `forgeFormula`
- **Tipe:** class (value object)
- **Atribut:** `level, materialAmount, atkIncrease, defIncrease, materialName (String)`
- **Method:** Getter/setter standar

### 11.3 `CraftingSystem`
- **Tipe:** class
- **Atribut:** `daftarResep (ArrayList<craftingRecipe>)`
- **Konstruktor:** Jika list kosong → fallback ke `DummyData.craftingRecipe.getDummyRecipesArray()`
- **Method:**
  - `tampilkanResep()` — list semua resep + bahan
  - `craft(int index, AccountProfile)` — cek index, validasi inventory, consumeRequiredIngredients via temp copy, cek space, replace inventory, add result
  - `consumeRequiredIngredients(recipe, List<Item> snapshot)` — loop reqs, remove first match per amount; kumpulkan missing → rollback via caller
  - `removeFirstMatch(items, name)` — helper
  - `hasSpaceForCraftResult(int, Item, int)` — helper
  - `getDaftarResep/setDaftarResep`

### 11.4 `ForgeSystem`
- **Tipe:** class
- **Konstanta:** `DEFAULT_MAX_UPGRADE_LEVEL = 10` (private static final)
- **Atribut:** `currentAccount (AccountProfile), daftarFormula (ArrayList<forgeFormula>)`
- **Method:**
  - `tampilkanEquipment(AccountProfile)` — list semua Equipment yang bisa di-upgrade, format tabel
  - `upgrade(int index, AccountProfile)` — pilih equipment, cek level max, cari formula sesuai next level, hitung material tersedia, kurangi material dari inventory, naikkan level + apply bonus ATK/DEF
  - `collectEquipment(LinkedList<Item>)` — private helper, filter Equipment dari inventory
  - `countItemInInventory(LinkedList<Item>, String)` — private helper, hitung jumlah item by name (case-insensitive)
  - `findFormulaForLevel(int)` — private, linear search
  - `removeMaterials(inventory, name, amount)` — private, remove by name
  - `applyAttackBonus(Equipment, forgeFormula)` — private, apply ATK jika Weapon atau Accessory
  - `applyDefenseBonus(Equipment, forgeFormula)` — private, apply DEF jika Armor atau Accessory
  - `getLevelMaks()` — return `DEFAULT_MAX_UPGRADE_LEVEL`
  - `getDaftarFormula/setDaftarFormula`
- **Catatan refactor:** Method `setLevelMaks(int)` (kosong/no-op) → **dihapus** (memang tidak pernah dipanggil dan tidak punya efek).

---

## 12. Package `systems.encyclopedia`

### 12.1 `Encyclopedia`
- **Tipe:** class (penyimpan katalog + displayer)
- **Atribut (HashMap<String, Object> final):** `indexMonster, indexIngredientAlam, indexIngredientMonster, indexIngredientConsumables, indexConsumables, indexWeapon, indexArmor, indexAccessory, indexResep, indexLokasi, indexClassTree, indexSkillTree, indexUtama` — tiap map berisi entri dikelompokkan by kategori. `indexUtama` adalah aggregator untuk search.
- **Atribut tambahan:** `classTreeRoot (ClassNode), skillTreeList (List<SkillNode>)`
- **Method display (per sektor):**
  - `displayMonsterSector()` — Monster
  - `displayIngredientAlamSector()`, `displayIngredientMonsterSector()`, `displayIngredientConsumablesSector()` — Ingredients
  - `displayConsumablesSector()` — ConsumableFood
  - `displayWeaponSector()`, `displayArmorSector()`, `displayAccessorySector()` — Equipment
  - `displayLocationSector()` — Location
  - `displayRecipeSector()` — CraftingRecipe
  - `displayClassTreeSector()` — recursive tree print
  - `displaySkillTreeSector()` — SkillNode list
  - `displayDetail(Object)` — polymorphic detail print (switch on instanceof: Monster/Location/Recipe/ClassNode/SkillNode/Item with subtypes)
  - `searchEncyclopedia(Scanner)` — search by keyword (case-insensitive) di indexUtama
  - `descItem(Object)` — one-liner description per tipe
- **Helper private:** `printItemEntry(int, Item)`, `printEquipEntry(int, Item)`, `traverseAndAddClassNode(ClassNode)` — rekursif, `printClassTreeRecursive(ClassNode, int depth)`

---

## 13. Package `systems.gacha`

### 13.1 `itemGacha`
- **Tipe:** class (POJO)
- **Atribut:** `equipment (Equipment), probabilitas (int), rarity (String)`
- **Method:** Getter/setter standar.

### 13.2 `GachaSystem`
- **Tipe:** class
- **Konstanta:** `BIAYA_GACHA_1X = 50, BIAYA_GACHA_10X = 500, NAMA_HADIAH_MAX = 24`
- **Atribut:** `poolItem (itemGacha[]), random (Random)`
- **Method:**
  - `tampilkanDaftarHadiah()` — tabel daftar hadiah dengan rarity & probabilitas
  - `pull(AccountProfile)` — single pull, biaya 50 gold; jika inventory penuh / gold kurang → return null
  - `pullTen(AccountProfile)` — 10x pull, biaya 500 gold; jika 10 slot kosong tidak tersedia → return null
  - `pilihIndex()` — weighted random berdasarkan probabilitas kumulatif

---

## 14. Package `systems.inventory`

### 14.1 `Inventory`
- **Tipe:** class
- **Atribut:** `MAX_INVENTORY_SLOTS (int, default 200), currentAccount (AccountProfile), listBarang (LinkedList<Item>)`
- **Method:**
  - `syncInventory()` — copy dari `currentAccount.getInventory()` ke local `listBarang`
  - `getSortedInventory()` — return copy yang di-sort by nama
  - `displayInventory()` — tabel inventory
  - `displayInventoryByCategory(String category)` — filter by item type (substring match)
  - `cariItem(String keyword)` — search by name (case-insensitive)
  - `itemNameSearch(String)` — private, exact match
  - `useItem(int itemIndex, int targetIndex)` — pilih item consumable, target party member, panggil `food.consume(target)`, hapus dari listBarang + account inventory
  - `displayItemDetail(String itemName)` — tampilkan detail per tipe
  - `equipItem(int itemIndex, int targetIndex)` — equip Equipment ke target; unequip lama → masuk inventory, equip baru → apply stat, hapus dari inventory
  - `getListBarang/setListBarang`
  - `getMAX_INVENTORY_SLOTS/setMAX_INVENTORY_SLOTS` — delegate ke `currentAccount`
  - `sortByName(List<Item>)` — private helper
- **Catatan refactor:** Setelah perubahan, `useItem` di sini masih berupa method di `Inventory` (tidak konflik dengan `ConsumableFood.useItem` yang dihapus). Method ini menerima `itemIndex, targetIndex`, bukan dipanggil `food.useItem`. Tetap dipakai.

---

## 15. Package `systems.map`

### 15.1 `MapTraversal`
- **Tipe:** class
- **Atribut:** `riwayatArea (Stack<Location>)` — history area yang dikunjungi
- **Konstanta static:**
  - `LINEAR_LOCATIONS` = list dari `kota.getDummyKota()`
  - `AREA_QUEST_ID_RANGES` = `{{1,5}, {6,10}, {11,15}, {16,20}, {21,25}}` — range ID quest per area
- **Method:**
  - 2 konstruktor: default (start dari area 0) + `MapTraversal(String startArea)`
  - `initializeFromAreaName(String)` — push area dari awal hingga match
  - `pindahArea(Location)`, `goToNext()`, `goTo(String)` — linear traversal: hanya boleh maju 1 step atau mundur, lompat maju >1 ditolak
  - `kembali()` — pop stack (atau peek jika size 1)
  - `areaSaatIni()` — peek
  - `indexOf(String)` — private
  - `getQuestIdRangeForArea(String)` / `getQuestIdRangeForCurrentArea()` — return range ID quest
  - `countCompletedQuestsInRange(List<Quest>, int, int)` — static
  - `areAllQuestsInRangeCompleted(...)` — static
  - `isCurrentAreaCleared(List<Quest>)` — apakah semua quest di area saat ini sudah selesai

### 15.2 `WaypointSystem`
- **Tipe:** class
- **Atribut:** `lokasiTerbuka (ArrayList<Location>), lokasiSaatIni (Location)`
- **Method:**
  - 2 konstruktor: default (empty) + pre-populated
  - `tambahLokasi(Location)` — tambah jika belum ada (case-insensitive by name)
  - `tampilkanDaftar()` — list area terbuka dengan marker `[CURRENT]`
  - `teleport(Location)` — set lokasi saat ini jika ada di waypoint
  - Getter/setter standar

---

## 16. Package `systems.music`

### 16.1 `MusicPlayer`
- **Tipe:** class
- **Atribut (final):** `playlist (ArrayList<String>), filenames (ArrayList<String>)` — lagu & path file `.wav`
- **Atribut mutable:** `currentIndex (int, default -1), currentSong (String), isPlaying (boolean), clip (Clip)`
- **Konstruktor:** pre-populate 5 lagu (Pamungkas, Rex Orange County, Backstreet Boys, Taylor Swift, Gloria Gaynor)
- **Method:**
  - `getPlaylist`, `getCurrentSong`, `isPlaying`, `getCurrentIndex`, `size`
  - `play(int index)` — stop sebelumnya, set currentIndex & currentSong, startClip
  - `shuffle()` — random shuffle playlist & filenames, play lagu pertama
  - `stop()` — clip.stop() + close, reset state
  - `startClip(String filename)` — load dari `src/systems/music/songs/`, play dengan `LOOP_CONTINUOUSLY`
- **Dependencies:** `javax.sound.sampled` (JSE built-in audio API)

---

## 17. Package `systems.quest`

### 17.1 `QuestTracker`
- **Tipe:** class
- **Atribut:** `daftarMainQuestAktif (ArrayList<MainQuest>), daftarSubQuestAktif (ArrayList<SubQuest>), riwayatMisiSelesai (ArrayList<Quest>)`
- **Method:**
  - Getter/setter untuk ketiga list
  - `sinkronisasiChapterTerbuka(int chapterAktif)` — **saat ini no-op** (dibiarkan untuk stabilitas behavior; bisa diisi dengan logic auto-promote BELUM_DIAMBIL → ONGOING berdasarkan chapter)
  - `catatMusuhKalah(String namaMusuh)` — return list pesan log; loop mundur main quest ONGOING, jika butuh musuh → tambah progress; jika COMPLETED, pindahkan ke riwayatMisiSelesai (hapus dari aktif)

---

## 18. Package `systems.save`

### 18.1 `SaveLoadSystem`
- **Tipe:** class (file persistence)
- **Atribut konstan (public):** `SAVE_FOLDER = "src/saves/"`, `extension = ".txt"`, section markers: `basicInfo, party, inventory, quest, skillSection, locationSection`
- **Method `save(AccountProfile)`:**
  - Write section per kategori dengan format `key=value` (untuk scalar) atau `key=val1^val2^...` (untuk object):
    - `[BASIC INFO]` — username, totalGold, totalPlaytime, maxInventorySlots, areaName
    - `[PARTY INFO]` — `karakter=nama^class^level^...^weaponId^armorId^accessoryId`
    - `[INVENTORY INFO]` — per item: `inqredient=...` / `equipment=...` / `consumableFood=...`
    - `[QUEST INFO]` — `mainQuest=...`, `subQuest=...`, `questHistory=...` (multi-section)
    - `[SKILL INFO]` — `unlockedSkills=name1,name2,...`
    - `[LOCATION INFO]` — `visitedLocations=name1,name2,...`
- **Method `load(String username)`:**
  - Read line-by-line, track `currentSection`; parse per key prefix
  - Return `AccountProfile` baru; jika save corrupted → catch & return null
- **Helper private:**
  - `trySetEquipmentSlot(karakter, rawId, slot)` — parse id → lookup di `DummyData.weapon/armor/accessory` → set
  - `getEquipmentBySlotAndId(slot, id)` — switch by slot → DummyData map
  - `parseSlot(String rawSlot, int bonusStr, int bonusDef)` — fallback heuristic jika slot string invalid
  - `parseClassType(String)` — try parse enum, fallback CLASSLESS
  - `createEquipment(int id, String nama, int harga, String deskripsi, EquipmentType, int bonusStr, int bonusDef, int levelTempa, ClassType)` — factory switch: ARMOR/ACCESSORY/WEAPON

---

## 19. Package `systems.shop`

### 19.1 `Shop`
- **Tipe:** class
- **Atribut:** `daftarItem (ArrayList<Item>), shopName (String), currentAccount (AccountProfile)`
- **Method:**
  - `tampilkanItem()` — banner ASCII + tabel item (Nama | Tipe | Harga)
  - `beliItem(int itemIndex, int jumlah, AccountProfile)` — validasi index/jumlah, cek gold cukup, cek inventory space, kurangi gold, tambah item
  - `displayItemDetail(int itemIndex)` — detail per tipe (Equipment/Consumable)
  - `sellItem(int inventoryIndex, AccountProfile)` — sort inventory by name, validasi, remove, tambah gold
  - `getMaxInventorySlots/setMaxInventorySlots` — delegate ke account
  - `getCurrentAccount/setCurrentAccount` — get/set
- **Catatan refactor:** Field `linkedAccount` di-rename menjadi `currentAccount`. Method `setLinkedAccount` & `getLinkedAccount` dihapus (duplikat dari `setCurrentAccount/getCurrentAccount`). Pemanggil di `App.java` line 1743 sudah memakai `setCurrentAccount`, tidak ada perubahan call-site.

---

## 20. Package `systems.skill`

### 20.1 `SkillNode`
- **Tipe:** class
- **Atribut:** `namaSkill, deskripsi, biayaGold, isUnlocked, parent (SkillNode), children (ArrayList<SkillNode>)`
- **Konstruktor:** 6-argumen
- **Method:**
  - Getter/setter standar
  - `unlock()` — set `isUnlocked = true`
  - `isAvailable()` — return `parent == null || parent.isUnlocked` (skill hanya available jika parent sudah dibuka)

### 20.2 `SkillTree`
- **Tipe:** class (wrapper minimal)
- **Atribut:** `root (SkillNode)`
- **Method:** Getter/setter untuk root.

### 20.3 `SkillSystem`
- **Tipe:** class dengan method static
- **Method static:**
  - `getSkillTree()` — return `DummyData.skilltree.generateSkillTree()`
  - `getAvailableSkills(List<SkillNode>)` — filter `!isUnlocked && isAvailable`
  - `unlockSkill(AccountProfile, SkillNode)` — cek gold cukup, kurangi gold, `chosen.unlock()`, catat ke `account.unlockedSkillNames`, `applySkillEffect`
  - `applySavedUnlocks(List<SkillNode>, List<String> unlockedNames)` — sync state dari save
  - `applySkillEffect(SkillNode, AccountProfile)` — parse angka dari deskripsi (`extractFirstInt`), apply ke party sesuai keyword: "ATK" → +atk, "Max HP" → +maxHp, "Max MP" → +maxMp, "DEF" → +def, "Slot Inventory" → +maxInventorySlots
  - `extractFirstInt(String)` — private, extract digit pertama dari string

---

## 21. Package `systems.vault`

### 21.1 `Vault`
- **Tipe:** class
- **Atribut:** `items (ArrayList<Item>)`
- **Method:**
  - `deposit(AccountProfile, Item)` — cek item ada di inventory, hapus dari inventory, tambah ke vault
  - `withdraw(AccountProfile, Item)` — cek item ada di vault, cek inventory space, hapus dari vault, tambah ke inventory
  - `getItems/setItems`, `size()`

---

## 22. Package `minigames`

### 22.1 `MiniGame` (abstract)
- **Tipe:** abstract class
- **Atribut:** `namaGame, rewardKoin`
- **Method abstract:** `startGame(AccountProfile)`

### 22.2 `Quiz`
- **Tipe:** class (POJO soal)
- **Atribut:** `question, answerChoices (String[]), correctAnswer, explanation`
- **Method:** Konstruktor + getter

### 22.3 `QuizGame` (extends `MiniGame`)
- **Tipe:** class
- **Konstanta:** `JUMLAH_SOAL = 5`, `BANK_SOAL` — 20 soal nutrisi/stunting (array `Quiz[]`)
- **Method `startGame(AccountProfile)`:**
  - Pilih 5 soal random
  - Tampilkan soal, baca input (mendukung "a"/"b"/"c" atau jawaban langsung)
  - Hitung benar, reward = benar * 10 gold
- **Helper:** `tampilkanSoal`, `ambilSoalAcak`, `soal(...)` — factory static

### 22.4 `GiziGame` (extends `MiniGame`)
- **Tipe:** class
- **Konstanta budget/harga/poin:** `budgetBantuan = 1000`, `targetKenyang = targetGizi = 100`; tiap bahan (Gandum/Daging/Sayur) punya harga + poin kenyang + poin gizi
- **Method `startGame(AccountProfile)`:**
  - Print banner info (budget, target, harga pasar)
  - Loop: input qty Gandum/Daging/Sayur
  - Hitung total biaya → jika > budget → reject
  - Hitung totalKenyang & totalGizi
  - Evaluasi: jika keduanya ≥ target → MISI BERHASIL (reward); else berbagai mode MISI GAGAL

---

## 23. Package `DummyData`

Package ini menyimpan data statis yang digunakan sebagai katalog dunia game. Semua class-nya adalah "factory statik" yang return `List<...>` atau `Map<Integer, Item>`.

### 23.1 `monster`
- Method `getDummyMonstersMap()` — return `HashMap<Integer, Monster>` dari `DummyData.monster` (5 area × 4-6 monster).

### 23.2 `kota`
- Method `getDummyKota()` — return `List<Location>` (linear: Valerion, Asgard, Grandis, Lumina, Aldoria).
- Method `getDummyKotaMap()` — return `HashMap<Integer, Location>`.

### 23.3 `mainquest`
- Method `getDummyMainQuestByChapter(int chapter)` — return `List<MainQuest>`.
- Method `getDummyMainQuestMap()` — return `HashMap<Integer, MainQuest>` (semua 25 quest).

### 23.4 `subquest`
- Method `getDummySubQuestByWilayah(String)` — return `List<SubQuest>` per area.

### 23.5 `weapon`, `armor`, `accessory`
- `getDummyWeaponsMap()` / `getDummyArmorsMap()` / `getDummyAccessoriesMap()` — return `HashMap<Integer, Item>`.

### 23.6 `consumables`, `inqredients_alam`, `inqredients_monster`, `inqredients_consumables`
- `getDummyConsumablesMap()` / `getDummyIngredientsAlamMap()` / `getDummyIngredientsMonsterMap()` / `getDummyIngredientsConsumablesMap()` — return `HashMap<Integer, Item>`.

### 23.7 `craftingRecipe` (di package DummyData)
- `getDummyRecipesArray()` — return `craftingRecipe[]`.
- `getDummyRecipesMap()` — return `HashMap<Integer, systems.craft.craftingRecipe>`.

### 23.8 `forgeformula`
- `getDummyForgeFormulas()` — return `ArrayList<forgeFormula>` (formula per level +1 sampai +10).

### 23.9 `gacha`
- `getDummyGacha()` — return `itemGacha[]` (pool gacha dengan probabilitas).
- `getDummyGachaMap()` — return `HashMap<Integer, itemGacha>`.

### 23.10 `classtree`
- `generateClassTree()` — return `ClassNode` root (CLASSLESS → tier 1 → tier 2 → tier 3).

### 23.11 `skilltree`
- `generateSkillTree()` — return `List<SkillNode>` (linear dependency).

### 23.12 Catatan tentang DummyData
- Package ini adalah "pseudo-database" — semua data disimpan di source code (tidak ada DB/file).
- Penamaan tidak konsisten: `craftingRecipe` (camelCase, tapi di `systems.craft` juga ada), `inqredients_*` (typo "ingredients"), `classtree` (lowercase). Disengaja tidak diubah untuk konsistensi dengan kode pemanggil.

---

## Lampiran A: Alur File (Lifecycle)

1. **Main.main()** → `new App().startMenu()`
2. **App.startMenu()** — banner LOGIN, input choice
3. **App.login()** — `verifyLogin()` cek `accounts.txt` → `saveload.load()` → init party, waypoint, map
4. **App.mainMenu()** — switch case ke subsistem:
   - Inventory / Shop / Craft / Forge / Skill / Gacha / Encyclopedia / Quest / Adventure / Map / Vault / Music / Save
5. **App.save()** → `SaveLoadSystem.save(profile)` → tulis `src/saves/<username>.txt`
6. **App.exit()** → `saveload.save(currentAccount)` + `musicPlayer.stop()`

## Lampiran B: Refactor yang Dilakukan

| # | Lokasi | Sebelum | Sesudah | Alasan |
|---|--------|---------|---------|--------|
| 1 | `models.item.ConsumableFood` | `consume()` delegate ke `useItem()` | `consume()` berisi logic, `useItem()` dihapus | Hilangkan duplikat, interface `IConsumable` tetap terpenuhi |
| 2 | `models.quest.MainQuest` | `butuhMusuh()` + `siapDipakai()` alias method | Alias dihapus, hanya `membutuhkanMusuh()` & `bisaDiambilPadaChapter()` | Konsolidasi method duplikat |
| 3 | `systems.quest.QuestTracker` | `mq.butuhMusuh(...)` | `mq.membutuhkanMusuh(...)` | Update call-site ke method utama |
| 4 | `systems.shop.Shop` | Field `linkedAccount` + `setLinkedAccount` + `getLinkedAccount` + `setCurrentAccount` | Field di-rename `currentAccount`; hanya `setCurrentAccount` & `getCurrentAccount` | Hilangkan 2 method setter duplikat |
| 5 | `systems.craft.ForgeSystem` | `setLevelMaks(int)` no-op | Dihapus | Method kosong, tidak pernah dipanggil |
| 6 | `systems.battle.BattleSystem` | `catatLog(String)` private, tidak dipanggil | Dihapus | Dead code |
| 7 | `systems.classSystem.ClassNode` | `unlock()` no-op | Isi: `this.isUnlocked = true` | Method ini mungkin dipanggil di pattern unlock; isi dengan logic yang masuk akal |
| 8 | `main.AnsiColors` (baru) | 6 file masing-masing punya 13-14 konstanta ANSI duplikat | 1 class utilitas `AnsiColors` di `main`, semua file pakai `AnsiColors.X` | Sentralisasi 80+ baris duplikat |
| 9 | `models.item.ConsumableFood` (interface) | `class implements IConsumable` tapi logic ada di `useItem` | `@Override consume()` sebagai method utama | Sesuai kontrak interface |

## Lampiran C: Pattern & Best-Practice yang Diterapkan

1. **Single Source of Truth** — `AnsiColors` adalah satu-satunya tempat definisi warna.
2. **DRY** — Tidak ada lagi method alias yang mengerjakan hal yang sama.
3. **Interface Compliance** — `ConsumableFood.consume` diberi `@Override` agar jelas memenuhi kontrak `IConsumable`.
4. **Field Naming Consistency** — `Shop.currentAccount` (sebelumnya `linkedAccount`) — pilih nama yang lebih generik dan dipakai di `App` call-site.
5. **Defensive Trimming** — `AccountProfile.setInventory` & `setMaxInventorySlots` auto-trim inventory dari belakang jika slot dikurangi.
6. **Fail-safe parsing** — `parseSlot`, `parseClassType` di `SaveLoadSystem` punya fallback ke nilai default jika parsing gagal.
7. **Polymorphic display** — `Encyclopedia.displayDetail(Object)` switch on `instanceof` (Monster/Location/Recipe/ClassNode/SkillNode/Item), sehingga satu method bisa print banyak tipe.
8. **Polymorphic equip** — `Equipment.equip(target)` sudah validasi class requirement; subclass `Weapon`/`Armor` override `getRequiredClassType()`.
9. **Composition** — `AccountProfile` menyusun `LinkedList<Item>`, `PlayerCharacter[]`, `QuestTracker`; bukan inheritance.
10. **Encapsulation** — Semua field `private`/`protected`; akses via getter/setter; private helper (`normalizeSlot`, `slotName`, `getPlayerClassType`) disembunyikan dari caller.

---

**Dokumentasi ini mencakup seluruh 60+ kelas dalam package `src` proyek. Setiap kelas memiliki peran spesifik dalam arsitektur MVC-like: Models (data + business logic), Systems (orchestrator), Main (UI driver), DummyData (data source).**
