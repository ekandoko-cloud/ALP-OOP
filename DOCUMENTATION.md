# NUTRITALE — Dokumentasi Lengkap Aplikasi

> **Tema:** Edukasi Gizi & Anti Stunting (SDG 2: Zero Hunger)  
> **Platform:** Console Java (CLI)  
> **Struktur:** OOP dengan Package-based Architecture

---

## DAFTAR ISI

1. [Cara Kerja Aplikasi (Flow)](#1-cara-kerja-aplikasi-flow)
2. [Struktur Package](#2-struktur-package)
3. [Package `main`](#3-package-main)
4. [Package `enums`](#4-package-enums)
5. [Package `models.account`](#5-package-modelsaccount)
6. [Package `models.character`](#6-package-modelscharacter)
7. [Package `models.item`](#7-package-modelsitem)
8. [Package `models.location`](#8-package-modelslocation)
9. [Package `models.quest`](#9-package-modelsquest)
10. [Package `systems.battle`](#10-package-systemsbattle)
11. [Package `systems.classSystem`](#11-package-systemsclasssystem)
12. [Package `systems.skill`](#12-package-systemsskill)
13. [Package `systems.inventory`](#13-package-systemsinventory)
14. [Package `systems.shop`](#14-package-systemsshop)
15. [Package `systems.craft`](#15-package-systemscraft)
16. [Package `systems.quest`](#16-package-systemsquest)
17. [Package `systems.map`](#17-package-systemsmap)
18. [Package `systems.save`](#18-package-systemssave)
19. [Package `systems.gacha`](#19-package-systemsgacha)
20. [Package `systems.story`](#20-package-systemsstory)
21. [Package `systems.encyclopedia`](#21-package-systemsencyclopedia)
22. [Package `minigames`](#22-package-minigames)
23. [Package `DummyData`](#23-package-dummydata)
24. [Package `utils`](#24-package-utils)

---

## 1. Cara Kerja Aplikasi (Flow)

### Alur Utama

```
Main.main()
  └── App.startMenu()
        ├── Login  ──→ App.login()
        │               ├── Baca accounts.txt → verifikasi
        │               ├── SaveLoadSystem.load() → restore data
        │               │     ├── Jika save ditemukan → load party, inventory, quest, skill, lokasi
        │               │     └── Jika tidak → buat 4 karakter CLASSLESS baru
        │               ├── inisialisasi MapTraversal, WaypointSystem, ForgeSystem
        │               └── App.mainMenu()
        │
        ├── Register ──→ App.register()
        │               ├── Cek username unik
        │               └── Simpan ke accounts.txt
        │
        └── Keluar ──→ System.exit(0)
```

### Main Menu (16 fitur)

Setelah login, pemain masuk ke **Main Menu** yang berisi 16 opsi:

| No | Fitur | Method | Fungsi |
|----|-------|--------|--------|
| 1 | **Play** | `mapTraversalMenu()` | Navigasi area + eksplorasi + battle |
| 2 | **Quest Tracker** | via `MainQuest.displayQuestTracker()` | Lihat quest aktif & selesai |
| 3 | **Inventory** | `inventoryMenu()` | Lihat, cari, pakai, filter item |
| 4 | **Shop** | `shopMenu1()` | Beli & jual item |
| 5 | **Crafting** | `craftingMenu()` | Craft item dari resep |
| 6 | **Forge** | `forgeMenu()` | Upgrade equipment |
| 7 | **Quest Board** | `questBoardMenu()` | Ambil & klaim quest |
| 8 | **Mini Game** | `miniGameMenu()` | Quiz Nutrisi & Space Spam |
| 9 | **Encyclopedia** | `ensiklopediaMenu()` | Lihat database monster, item, resep, lokasi |
| 10 | **Skill Tree** | `skillTreeMenu()` | Beli/unlock skill pasif |
| 11 | **Class Tree** | `classTreeMenu()` | Evolusi class karakter |
| 12 | **Gacha** | `gachaMenu()` | Pull equipment random |
| 13 | **Waypoint** | `waypointMenu()` | Teleport antar area |
| 14 | **Profil Akun** | `accProfileMenu()` | Lihat/edit profil, party, ganti username |
| 15 | **Save Game** | save langsung | Simpan progress ke file |
| 16 | **Logout** | save + back to menu | Keluar ke start menu |

### Alur Eksplorasi (Play)

```
mapTraversalMenu()
  ├── Go to Next Area ──→ MapTraversal.goToNext()
  │                        ├── Cek semua quest area selesai?
  │                        ├── Jika ya → pindah area
  │                        └── Jika tidak → blokir
  ├── Go Back ──→ MapTraversal.kembali()
  ├── Explore ──→ AdventureSystem.jalankanEksplorasi()
  │                ├── 55% Battle ──→ BattleSystem.mulaiPertarungan()
  │                │                  ├── Turn-based: Serang, Bertahan, Skill, Item, Lewat, Log, Kabur
  │                │                  └── Result: VICTORY / DEFEAT / FLED
  │                ├── 25% Treasure ──→ dapat gold
  │                └── 20% Puzzle ──→ jawab pertanyaan nutrisi → gold
  ├── Show Path ──→ stack riwayat area
  └── Back to Main Menu
```

### Alur Battle (Turn-Based)

```
BattleSystem.mulaiPertarungan()
  └── Loop per turn:
        ├── Reset status defend semua karakter
        ├── Player's turn (per karakter hidup):
        │     1. Serang → damage = strength - target.defense
        │     2. Bertahan → damage received * 0.5
        │     3. Skill → damage/Heal (cek class heal/damage)
        │     4. Item → pakai consumable ke ally
        │     5. Lewat
        │     6. Lihat Log Battle
        │     7. Kabur → FLED
        └── Enemy's turn (random):
              ├── 50% defend
              └── 50% serang target acak
```

---

## 2. Struktur Package

```
src/
├── main/                  # Entry point & controller utama
│   ├── Main.java          # main() → jalankan App
│   └── App.java           # Semua menu & logika utama (2130 baris)
├── enums/                 # Enum class
├── models/                # Model data
│   ├── account/           # Profile akun pemain
│   ├── character/         # Karakter (player, monster, NPC)
│   ├── item/              # Item, equipment, consumable, ingredient
│   ├── location/          # Lokasi/area
│   └── quest/             # Quest, MainQuest, SubQuest
├── systems/               # Sistem/game mechanics
│   ├── battle/            # Battle, adventure, enemy factory
│   ├── classSystem/       # Class tree & evolution
│   ├── skill/             # Skill tree & pasif
│   ├── inventory/         # Manajemen inventory
│   ├── shop/              # Toko jual-beli
│   ├── craft/             # Crafting & forge
│   ├── quest/             # Quest tracker
│   ├── map/               # Traversal & waypoint
│   ├── save/              # Save/Load system
│   ├── gacha/             # Gacha system
│   ├── story/             # Story manager
│   └── encyclopedia/      # Encyclopedia database viewer
├── minigames/             # Mini games
├── DummyData/             # Data dummy (items, monsters, quests, etc.)
└── utils/                 # Utility (ANSI colors)
```

---

## 3. Package `main`

### Main.java

**Method:**
| Method | Deskripsi |
|--------|-----------|
| `main(String[] args)` | Entry point aplikasi. Membuat instance `App` dan memanggil `startMenu()`. |

### App.java — Class utama pengendali seluruh aplikasi.

**Fields penting:**
| Field | Tipe | Deskripsi |
|-------|------|-----------|
| `inpInt` | `Scanner` | Scanner untuk input integer |
| `inpStr` | `Scanner` | Scanner untuk input string |
| `inventory` | `Inventory` | Instance inventory (jarang dipakai langsung) |
| `currentAccount` | `AccountProfile` | Akun yang sedang login |
| `party` | `PlayerCharacter[4]` | Party karakter aktif (max 4) |
| `mapTraversal` | `MapTraversal` | Navigasi area |
| `waypointSystem` | `WaypointSystem` | Waypoint teleport |
| `forgeSystem` | `ForgeSystem` | Forge upgrade |
| `craftingSystem` | `CraftingSystem` | Crafting |
| `gachaSystem` | `GachaSystem` | Gacha |
| `shop1` | `Shop` | Toko |
| `QuizGame` | `QuizGame` | Mini game quiz |
| `SpaceGame` | `SpaceGame` | Mini game space spam |
| `ensiklopediaInstance` | `Encyclopedia` | Encyclopedia |

**Method utama:**
| Method | Deskripsi |
|--------|-----------|
| `startMenu()` | Tampilkan menu login/register/keluar dengan ASCII art |
| `register()` | Registrasi akun baru (simpan ke `accounts.txt`) |
| `login()` | Login: verifikasi + load save + inisialisasi komponen |
| `checkUsername(String)` | Cek apakah username sudah terdaftar |
| `saveAcc(String, String)` | Simpan username:password ke file |
| `verifyLogin(String, String)` | Verifikasi kecocokan username & password |
| `changeUsername(String, String)` | Ganti username di file accounts.txt + rename file save |
| `mainMenu()` | Tampilkan 16 fitur utama berdasarkan area saat ini |
| `displayMainMenuValerion()` | Menu spesifik area Valerion |
| `displayMenuAsgard()` | Menu spesifik area Asgard |
| `displayMenuGrandis()` | Menu spesifik area Grandis |
| `displayMenuLumina()` | Menu spesifik area Lumina |
| `displayMenuAldoria()` | Menu spesifik area Aldoria |
| `getActiveAreaName()` | Ambil nama area dari mapTraversal atau account |
| `displayMainMenuForCurrentArea()` | Pilih menu sesuai area aktif |
| `mapTraversalMenu()` | Navigasi area: next/back/explore/path (dengan gate quest) |
| `waypointMenu()` | Lihat daftar waypoint & teleport |
| `inventoryMenu()` | CRUD inventory: search, detail, use, filter |
| `shopMenu1()` | Beli/jual item di toko |
| `craftingMenu()` | Craft item dari resep |
| `forgeMenu()` | Upgrade equipment dengan material |
| `miniGameMenu()` | Pilih Quiz Game atau Space Spam |
| `questBoardMenu()` | Ambil main quest & sub quest + klaim reward |
| `acceptQuestRewards()` | Klaim hadiah quest yang completed |
| `ensiklopediaInit()` | Inisialisasi encyclopedia dari semua data dummy |
| `ensiklopediaMenu()` | Tampilkan encyclopedia (10 kategori + search) |
| `encyclopediaSubMenu(...)` | Sub-menu detail encyclopedia |
| `gachaMenu()` | Tampilkan daftar hadiah, pull 1x, pull 10x |
| `skillTreeMenu()` | Lihat skill tree, beli/unlock skill |
| `classTreeMenu()` | Pilih karakter → pilih class evolution |
| `accProfileMenu()` | Profil akun: edit username, detail karakter, ganti nama |
| `syncPartySkills()` | Sinkronisasi skill class ke party setelah load |
| `findLocationByName(String)` | Cari Location berdasarkan nama |
| `getIngredientById(int)` | Ambil ingredient dari catalog berdasarkan range ID (1-100 alam, 101-200 monster, 201+ consumables) |
| `ensureQuestTrackerCatalog(...)` | Inisialisasi QuestTracker dengan dummy quests |

**Static fields:**
| Field | Deskripsi |
|-------|-----------|
| `INVALID_INPUT_BOX` | String border merah untuk pesan error input |
| `classSkills` | HashMap mapping nama class → Skill (damage/heal) |

---

## 4. Package `enums`

### ClassType.java
Enum class karakter: `CLASSLESS, WARRIOR, ARCHER, MAGE, SUPPORT, KNIGHT, SWORDSMAN, BERSERKER, SCOUT, MARKSMAN, RANGER, WIZARD, WITCH, ARCHMAGE, SORCERER, SHIELDMAN, ANGEL, PALADIN, ARCHANGEL`

### ItemType.java
Enum tipe item: `CONSUMABLE, EQUIPMENT, INQREDIENT`

### EquipmentType.java
Enum slot equipment: `WEAPON, ARMOR, ACCESSORY`

### StatusLokasi.java
Enum status lokasi: `TERKUNCI, TERBUKA, TERTUTUP_SEMENTARA`

### StatusQuest.java
Enum status quest: `BELUM_DIAMBIL, ONGOING, COMPLETED, REWARDED`

---

## 5. Package `models.account`

### AccountProfile.java

Model data akun pemain. Disimpan dan di-load dari file.

**Fields:**
| Field | Tipe | Deskripsi |
|-------|------|-----------|
| `MAX_PARTY_SIZE` | `static final int` | 4 |
| `DEFAULT_MAX_INVENTORY_SLOTS` | `static final int` | 200 |
| `username` | `String` | Username akun |
| `password` | `String` | Password |
| `totalGold` | `int` | Jumlah gold |
| `totalPlaytime` | `int` | Total waktu bermain (menit) |
| `sessionStartMillis` | `transient long` | Waktu mulai session (tidak disimpan) |
| `areaName` | `String` | Nama area terakhir |
| `party` | `PlayerCharacter[]` | Array party (max 4) |
| `inventory` | `LinkedList<Item>` | Inventory item |
| `questTracker` | `QuestTracker` | Pelacak quest |
| `maxInventorySlots` | `int` | Kapasitas maks inventory |
| `unlockedSkillNames` | `Set<String>` | Nama skill yang sudah di-unlock |
| `statusLokasi` | `HashMap<String, StatusLokasi>` | Status kunjungan tiap lokasi |

**Method:**
| Method | Deskripsi |
|--------|-----------|
| `getParty()` / `setParty(...)` | Get/set party (dibatasi 4) |
| `limitPartySize(...)` | Private: potong array party ke max 4 |
| `getUsername()` / `setUsername(...)` | Get/set username |
| `getPassword()` / `setPassword(...)` | Get/set password |
| `getTotalGold()` / `setTotalGold(...)` | Get/set gold |
| `getInventory()` / `setInventory(...)` | Get/set inventory (trim to limit) |
| `getQuestTracker()` / `setQuestTracker(...)` | Get/set quest tracker |
| `getTotalPlaytime()` / `setTotalPlaytime(...)` | Get/set total playtime |
| `startPlaytime()` | Mulai hitung sesi (catat timestamp) |
| `stopPlaytimeAndAccumulate()` | Hentikan sesi + akumulasi menit ke totalPlaytime |
| `getTotalPlaytimeFormatted()` | Return string format "H:MM" |
| `getAreaName()` / `setAreaName(...)` | Get/set area name |
| `addItemToInventory(Item)` | Tambah item ke inventory (cek batas) |
| `removeItemFromInventory(Item)` | Hapus item dari inventory |
| `getMaxInventorySlots()` / `setMaxInventorySlots(...)` | Get/set kapasitas inventory (trim otomatis) |
| `trimInventoryToLimit()` | Private: hapus item terakhir jika overflow |
| `getUnlockedSkillNames()` / `setUnlockedSkillNames(...)` | Get/set set skill ter-unlock |
| `addUnlockedSkillName(String)` | Tambah skill ke set |
| `isSkillUnlocked(String)` | Cek apakah skill sudah di-unlock |
| `getStatusLokasi()` / `setStatusLokasi(...)` | Get/set status lokasi |
| `kunjungiLokasi(String)` | Tandai lokasi sebagai TERBUKA |
| `sudahMengunjungi(String)` | Cek apakah sudah pernah mengunjungi |
| `getStatusLokasi(String)` | Get status lokasi (default TERKUNCI) |
| `getVisitedLocationNames()` | Return daftar nama lokasi yang sudah dikunjungi (TERBUKA) |

---

## 6. Package `models.character`

### GameCharacter.java (abstract)

Base class untuk semua karakter.

**Fields:**
| Field | Tipe | Deskripsi |
|-------|------|-----------|
| `nama` | `String` | Nama karakter |
| `maxHp` | `int` | HP maksimum |
| `currentHp` | `int` | HP saat ini |
| `maxMp` | `int` | MP maksimum |
| `currentMp` | `int` | MP saat ini |
| `kekuatan` | `int` | Strength (ATK) |
| `defense` | `int` | Defense |
| `defending` | `boolean` | Status bertahan |

**Method:**
| Method | Deskripsi |
|--------|-----------|
| `getNama()` / `setNama(...)` | Get/set nama |
| `getMaxHp()` / `setMaxHp(...)` | Get/set max HP |
| `getCurrentHp()` / `setCurrentHp(...)` | Get/set current HP (clamped 0) |
| `getMaxMp()` / `setMaxMp(...)` | Get/set max MP |
| `getCurrentMp()` / `setCurrentMp(...)` | Get/set current MP |
| `getKekuatan()` / `setKekuatan(...)` | Get/set strength |
| `getDefense()` / `setDefense(...)` | Get/set defense |
| `isDefending()` / `setDefending(...)` | Get/set status defend |
| `isAlive()` | Return `currentHp > 0` |
| `getXpReward()` | Return XP reward (default 0, override by subclass) |
| `serang(GameCharacter)` | Hitung damage = max(1, strength - target.defense), panggil `terimaDamage()` |
| `defend()` | Set defending = true |
| `terimaDamage(int)` | Kurangi HP: jika defending, damage * 0.5 |
| `modifikasiStat(int hp, int mp, int atk, int def)` | Modifikasi semua stat sekaligus |

### PlayerCharacter.java (extends GameCharacter)

Karakter pemain dengan level, class, equipment, skill, EXP.

**Fields tambahan:**
| Field | Tipe | Deskripsi |
|-------|------|-----------|
| `currentExp` | `int` | EXP saat ini |
| `maxExp` | `int` | EXP maksimum (level * 100) |
| `namaClass` | `String` | Nama class (e.g. "Warrior") |
| `statusTubuhNirlelah` | `boolean` | Status kebugaran |
| `level` | `int` | Level karakter |
| `skill` | `Skill` | Skill unik class |
| `currentWeapon` | `Equipment` | Weapon yang dipakai |
| `currentArmor` | `Equipment` | Armor yang dipakai |
| `currentAccessory` | `Equipment` | Accessory yang dipakai |

**Method:**
| Method | Deskripsi |
|--------|-----------|
| `getCurrentExp()` / `setCurrentExp(...)` | Get/set current EXP |
| `getMaxExp()` / `setMaxExp(...)` | Get/set max EXP |
| `getNamaClass()` / `setNamaClass(...)` | Get/set nama class |
| `isStatusTubuhNirlelah()` / `setStatusTubuhNirlelah(...)` | Get/set status nirlelah |
| `getLevel()` / `setLevel(...)` | Get/set level |
| `getSkill()` / `setSkill(...)` | Get/set skill |
| `getCurrentWeapon()` / `setCurrentWeapon(...)` | Get/set weapon |
| `getCurrentArmor()` / `setCurrentArmor(...)` | Get/set armor |
| `getCurrentAccessory()` / `setCurrentAccessory(...)` | Get/set accessory |
| `getEquipmentBySlot(String)` | Ambil equipment berdasarkan slot ("WEAPON"/"ARMOR"/"ACCESSORY") |
| `setEquipmentBySlot(String, Equipment)` | Set equipment di slot tertentu |
| `tambahExp(int)` | Tambah EXP, jika >= maxExp → levelUp (loop) |
| `levelUp()` | Level++: reset HP/MP, naikkan STR/DEF, level * 100 maxExp |
| `gunakanSkillUnik(GameCharacter)` | Pakai skill jika ada, else basic attack * 1.5 |

### Warrior.java, Mage.java, Archer.java, Support.java

Subclass `PlayerCharacter` — semuanya hanya constructor yang memanggil `super()`. Tidak ada method tambahan. Digunakan untuk polymorphic type distinction jika diperlukan.

### Monster.java (extends GameCharacter)

Musuh biasa.

**Fields:**
| Field | Tipe | Deskripsi |
|-------|------|-----------|
| `triviaPenyakit` | `String` | Trivia tentang penyakit/monster |
| `xpDiberikan` | `int` | XP reward (default 10) |

**Method:** Getter/setter + override `getXpReward()`.

### BossMonster.java (extends GameCharacter implements Skill)

Musuh boss. Implement `Skill` untuk special attack.

**Fields:** Sama seperti Monster, default XP = 50.

**Method:** `gunakanSkill(GameCharacter, GameCharacter)` — damage = max(1, source.strength + source.defense/2 - target.defense).

### NPC.java (extends GameCharacter)

Karakter non-pemain dengan dialog.

**Fields:**
| Field | Tipe | Deskripsi |
|-------|------|-----------|
| `pangkat` | `String` | Pangkat/jabatan |
| `unitTaktis` | `String` | Unit taktis |
| `arrayDialog` | `String[]` | Array dialog |

### Skill.java (interface)

```java
void gunakanSkill(GameCharacter source, GameCharacter target);
```

---

## 7. Package `models.item`

### Item.java (abstract)

Base class untuk semua item.

**Fields:**
| Field | Tipe | Deskripsi |
|-------|------|-----------|
| `idItem` | `int` | ID unik item |
| `namaItem` | `String` | Nama item |
| `hargaJual` | `int` | Harga jual (gold) |
| `deskripsi` | `String` | Deskripsi |
| `itemType` | `ItemType` | Tipe item (CONSUMABLE/EQUIPMENT/INQREDIENT) |

### Equipment.java (abstract extends Item implements IEquippable)

Base class untuk equipment yang bisa dipasang.

**Fields tambahan:**
| Field | Tipe | Deskripsi |
|-------|------|-----------|
| `equipmentType` | `EquipmentType` | WEAPON/ARMOR/ACCESSORY |
| `levelTempa` | `int` | Level upgrade forge |

**Method:**
| Method | Deskripsi |
|--------|-----------|
| `getTipeEquipment()` / `setTipeEquipment(...)` | Get/set tipe equipment |
| `getLevelTempa()` / `setLevelTempa(...)` | Get/set level forge |
| `getBonusKekuatan()` | Abstract: bonus strength |
| `getBonusDefense()` | Abstract: bonus defense |
| `getRequiredClassType()` | Class requirement (default CLASSLESS) |
| `equip(PlayerCharacter)` / `equip(PlayerCharacter, String)` | Pasang equipment: cek class requirement, aplikasikan bonus stat |
| `unequip(PlayerCharacter)` / `unequip(PlayerCharacter, String)` | Lepas equipment: kurangi bonus stat |

### Weapon.java (extends Equipment)

**Fields:** `bonusKekuatan`, `requiredClassType`  
**`getBonusDefense()`** return 0.  
**`getRequiredClassType()`** return class requirement.

### Armor.java (extends Equipment)

**Fields:** `bonusDefense`, `requiredClassType`  
**`getBonusKekuatan()`** return 0.

### Accessory.java (extends Equipment)

**Fields:** `bonusKekuatan`, `bonusDefense`

### ConsumableFood.java (extends Item implements IConsumable)

Item yang bisa dikonsumsi untuk heal/buff.

**Fields:**
| Field | Tipe | Deskripsi |
|-------|------|-----------|
| `healHpAmount` | `int` | HP yang dipulihkan |
| `healMpAmount` | `int` | MP yang dipulihkan |
| `strBuff` | `int` | Buff strength permanen |
| `defBuff` | `int` | Buff defense permanen |
| `infoGiziSDG` | `String` | Informasi gizi (edukasi) |

**Method:**
| Method | Deskripsi |
|--------|-----------|
| `consume(GameCharacter)` | Panggil `useItem()` |
| `useItem(GameCharacter)` | Heal HP/MP + aplikasikan buff permanen |

### Ingredients.java (extends Item)

Item ingredient untuk crafting. Tidak punya method tambahan selain constructor.

### IEquippable.java (interface)

```java
void equip(PlayerCharacter target);
void unequip(PlayerCharacter target);
```

### IConsumable.java (interface)

```java
void consume(GameCharacter target);
```

---

## 8. Package `models.location`

### Location.java

**Fields:**
| Field | Tipe | Deskripsi |
|-------|------|-----------|
| `namaLokasi` | `String` | Nama lokasi |
| `deskripsiLokasi` | `String` | Deskripsi lokasi |

Method getter/setter standar. Digunakan oleh MapTraversal, WaypointSystem, Encyclopedia.

---

## 9. Package `models.quest`

### Quest.java (abstract)

Base class untuk quest.

**Fields:**
| Field | Tipe | Deskripsi |
|-------|------|-----------|
| `idQuest` | `int` | ID quest |
| `namaQuest` | `String` | Nama quest |
| `deskripsiQuest` | `String` | Deskripsi |
| `objectiveQuest` | `String` | Teks objective |
| `objectiveTarget` | `int` | Target (e.g. kalahkan 5 musuh) |
| `objectiveProgress` | `int` | Progress saat ini |
| `hadiahKoin` | `int` | Hadiah gold |
| `statusQuest` | `StatusQuest` | Status quest |
| `riwayatObjective` | `ArrayList<String>` | Riwayat progress |

**Method:**
| Method | Deskripsi |
|--------|-----------|
| `catatObjective(int, String)` | Tambah progress + catat riwayat |
| `cekStatusPenyelesaian()` | Jika progress >= target → COMPLETED |

### MainQuest.java (extends Quest)

Quest utama per wilayah (5 wilayah × 5 quest = 25 total).

**Fields tambahan:**
| Field | Tipe | Deskripsi |
|-------|------|-----------|
| `chapterTerbuka` | `int` | Chapter minimal untuk bisa ambil |
| `wilayah` | `String` | Wilayah quest (Valerion/Asgard/Grandis/Lumina/Aldoria) |
| `nomorQuest` | `int` | Nomor urut quest di wilayah |
| `hadiahUtama` | `String` | Hadiah item (format: "nama" atau "2x nama + 1x nama") |
| `lineUpMusuh` | `ArrayList<String>` | Daftar musuh yang harus dikalahkan |

**Static methods:**
| Method | Deskripsi |
|--------|-----------|
| `displayQuestTracker(QuestTracker)` | Tampilkan main quest ONGOING |
| `displayCompletedQuests(QuestTracker)` | Tampilkan quest selesai |
| `displayQuestBoard(QuestTracker)` | Tampilkan quest board semua wilayah |
| `displayQuestBoardForArea(QuestTracker, String, Scanner)` | Tampilkan quest board per area + ambil quest |
| `berikanHadiah(MainQuest, AccountProfile, Map, Map, Map)` | Beri gold + item hadiah |
| `tampilkanMusuhWilayah(String, List<Monster>)` | Tampilkan musuh di suatu wilayah |
| `getNamaMusuhWilayah(String)` | Return list nama musuh per wilayah |
| `tampilkanSemuaQuest(List<MainQuest>)` | Tampilkan semua quest |


### SubQuest.java (extends Quest)

Quest sampingan (side quest).

**Fields tambahan:**
| Field | Tipe | Deskripsi |
|-------|------|-----------|
| `wilayah` | `String` | Wilayah sub quest |

**Method:**
| Method | Deskripsi |
|--------|-----------|
| `bisaDiambilPadaArea(String)` | Cek apakah bisa diambil di area tertentu |
| `displayQuestTracker(QuestTracker)` (static) | Tampilkan sub quest aktif |
| `displayQuestBoardForArea(QuestTracker, String, Scanner)` (static) | Tampilkan + ambil sub quest (copy dari dummy agar tidak mutate global) |
| `getAvailableSubQuests(QuestTracker, String)` (static) | Filter sub quest yang tersedia |

---

## 10. Package `systems.battle`

### BattleSystem.java

Sistem pertarungan turn-based.

**Fields:**
| Field | Tipe | Deskripsi |
|-------|------|-----------|
| `partyPlayer` | `PlayerCharacter[]` | Party pemain |
| `partyEnemy` | `GameCharacter[]` | Party musuh |
| `battleLog` | `BattleLog` | Log pertarungan |
| `random` | `Random` | RNG |

**Method:**
| Method | Deskripsi |
|--------|-----------|
| `mulaiPertarungan(Scanner, LinkedList<Item>, QuestTracker)` | Loop utama battle, return `BattleResult` |
| `tampilkanStatusPertarungan()` | Tampilkan HP/MP semua karakter |
| `tampilkanOpsiAksi(...)` | Tampilkan menu aksi (1-7) |
| `pilihTargetMusuh(Scanner)` | Pilih target musuh yang masih hidup |
| `pilihTargetParty(Scanner, boolean)` | Pilih target party (ally) |
| `pilihConsumable(Scanner, LinkedList<Item>)` | Pilih item consumable |
| `bacaPilihan(Scanner, int, int)` | Baca input dengan validasi range |
| `isHealClass(String)` | Cek apakah class adalah healer |
| `pilihTargetPartyAcak()` | Musuh pilih target acak |
| `semuaMusuhDikalahkan()` | Cek semua musuh mati |
| `semuaPartyDikalahkan()` | Cek semua party mati |
| `cekMusuhKalah(int, QuestTracker)` | Jika musuh mati: log + update quest tracker |
| `berikanXpHadiah()` | Bagi XP ke party yang hidup |

### BattleResult.java (enum)

`VICTORY`, `DEFEAT`, `FLED`

### BattleLog.java

Menyimpan log pertarungan per turn.

**Fields:** `turnEntries` (ArrayList<ArrayList<String>>), `currentTurn`

**Method:**
| Method | Deskripsi |
|--------|-----------|
| `nextTurn(int)` | Siapkan slot untuk turn baru |
| `tambahEntri(String)` | Tambah entri ke turn saat ini |
| `tampilkanLog()` | Print semua log per turn |
| `bersihkan()` | Reset log |
| `getHistoryLog()` / `setHistoryLog(...)` | Flat list log |

### BattleEnemyFactory.java

Factory untuk membuat party musuh.

**Method:**
| Method | Deskripsi |
|--------|-----------|
| `createPartyFromQuest(MainQuest, int)` | Buat musuh dari lineup quest |
| `createRandomPartyForChapter(int, Random)` | Buat musuh random dari quest chapter |
| `createPartyFromNames(List<String>, int)` | Buat array musuh dari list nama |
| `createEnemy(String, int)` | Buat 1 musuh (Monster/BossMonster) dengan scaling chapter |
| `isBossEnemy(String)` | Cek apakah nama adalah boss |

### AdventureSystem.java

Sistem eksplorasi area (battle/treasure/puzzle).

**Method:**
| Method | Deskripsi |
|--------|-----------|
| `jalankanEksplorasi(AccountProfile, MapTraversal, Scanner)` | Eksplorasi: 55% battle, 25% treasure, 20% puzzle. Return apakah lanjut |
| `jalankanBattleRandom(AccountProfile, int, Scanner)` | Battle random + reward gold |
| `jalankanTreasure(AccountProfile, int)` | Dapat gold |
| `jalankanPuzzle(AccountProfile, int, Scanner)` | Puzzle nutrisi 4 opsi |
| `puzzleForChapter(int)` | Return soal puzzle per chapter |
| `chapterDariArea(Location)` | Tentukan chapter dari index lokasi |
| `hadiahGold(int, int, int, int)` | Hitung gold reward |

**Inner class `PuzzleData`:** `question`, `options[]`, `correctAnswer`

---

## 11. Package `systems.classSystem`

### ClassSystem.java

Sistem evolusi class.

**Method:**
| Method | Deskripsi |
|--------|-----------|
| `getClassTreeRoot()` | Ambil root ClassNode dari dummy data |
| `getAvailableClassOptions(ClassNode, PlayerCharacter)` | Filter class yang bisa dipilih (cek level) |
| `applyClassToCharacter(ClassNode, PlayerCharacter)` | Terapkan class: set nama + bonus stat sesuai branch |

### ClassNode.java

Node dalam tree class.

**Fields:** `namaClass`, `deskripsi`, `syaratLevel`, `isUnlocked`, `tipeClass` (ClassType), `parent`, `children` (ArrayList)

### ClassTree.java

Wrapper untuk root ClassNode.

---

## 12. Package `systems.skill`

### SkillSystem.java

Sistem skill tree.

**Method:**
| Method | Deskripsi |
|--------|-----------|
| `getSkillTree()` | Ambil skill tree dari dummy data |
| `getAvailableSkills(List<SkillNode>)` | Filter skill yang bisa dibeli (available & not unlocked) |
| `unlockSkill(AccountProfile, SkillNode)` | Kurangi gold + unlock + apply effect |
| `applySavedUnlocks(List<SkillNode>, Set<String>)` | Restore unlock status dari save |
| `applySkillEffect(SkillNode, AccountProfile)` | Aplikasikan efek skill ke party (ATK/DEF/HP/MP/Slot) |

### SkillNode.java

Node dalam skill tree.

**Fields:** `namaSkill`, `deskripsi`, `biayaGold`, `isUnlocked`, `parent`, `children`

**Method:** `unlock()` set true, `isAvailable()` cek parent sudah di-unlock.

### SkillTree.java

Wrapper untuk root SkillNode.

---

## 13. Package `systems.inventory`

### Inventory.java

Manajemen inventory player.

**Method:**
| Method | Deskripsi |
|--------|-----------|
| `syncInventory()` | Sinkronisasi dengan account |
| `getSortedInventory()` | Return inventory terurut nama |
| `displayInventory()` | Tampilkan semua item |
| `displayInventoryByCategory(String)` | Filter berdasarkan kategori |
| `cariItem(String)` | Cari item berdasarkan keyword |
| `useItem(int, int)` | Pakai consumable ke target party member |
| `displayItemDetail(String)` | Tampilkan detail item |

---

## 14. Package `systems.shop`

### Shop.java

Toko jual-beli item.

**Method:**
| Method | Deskripsi |
|--------|-----------|
| `tampilkanItem()` | Tampilkan daftar item toko |
| `beliItem(int, int, AccountProfile)` | Beli item (cek gold + slot) |
| `displayItemDetail(int)` | Detail item toko |
| `sellItem(int, AccountProfile)` | Jual item dari inventory |

---

## 15. Package `systems.craft`

### CraftingSystem.java

Sistem crafting item dari ingredient.

**Method:**
| Method | Deskripsi |
|--------|-----------|
| `tampilkanResep()` | Tampilkan semua resep |
| `craft(int, AccountProfile)` | Craft item: cek bahan, consume, hasilkan item |
| `consumeRequiredIngredients(...)` | Verifikasi & consume bahan |
| `removeFirstMatch(...)` | Hapus 1 item dari list snapshot |

### craftingRecipe.java

Model resep crafting.

**Fields:** `recipeName`, `resultItem`, `requiredIngredients` (ArrayList of IngredientReq)

**Inner class `IngredientReq`:** `ingredient` (Ingredients), `amount`

### ForgeSystem.java

Sistem upgrade equipment.

**Fields:** `DEFAULT_MAX_UPGRADE_LEVEL` (10), `currentAccount`, `daftarFormula`

**Method:**
| Method | Deskripsi |
|--------|-----------|
| `tampilkanEquipment(AccountProfile)` | Tampilkan equipment yang bisa di-upgrade |
| `upgrade(int, AccountProfile)` | Upgrade equipment: cek level, material, aplikasi bonus |
| `countItemInInventory(...)` | Hitung jumlah material di inventory |
| `collectEquipment(...)` | Kumpulkan semua equipment dari inventory |
| `findFormulaForLevel(int)` | Cari formula untuk level tertentu |
| `removeMaterials(...)` | Hapus material dari inventory |
| `applyAttackBonus(...)` | Tambah ATK bonus ke weapon/accessory |
| `applyDefenseBonus(...)` | Tambah DEF bonus ke armor/accessory |

### forgeFormula.java

Model formula forge per level.

**Fields:** `level`, `materialAmount`, `atkIncrease`, `defIncrease`, `materialName`

---

## 16. Package `systems.quest`

### QuestTracker.java

Pelacak quest player.

**Fields:**
| Field | Tipe | Deskripsi |
|-------|------|-----------|
| `daftarMainQuestAktif` | `ArrayList<MainQuest>` | Main quest aktif |
| `daftarSubQuestAktif` | `ArrayList<SubQuest>` | Sub quest aktif |
| `riwayatMisiSelesai` | `ArrayList<Quest>` | Quest yang sudah selesai |

**Method:**
| Method | Deskripsi |
|--------|-----------|
| `sinkronisasiChapterTerbuka(int)` | Sinkronisasi chapter (saat ini kosong) |
| `catatMusuhKalah(String)` | Catat musuh dikalahkan → update progress quest. Jika quest selesai, pindahkan ke riwayat |

---

## 17. Package `systems.map`

### MapTraversal.java

Navigasi linear antar area menggunakan Stack.

**Fields:**
| Field | Tipe | Deskripsi |
|-------|------|-----------|
| `riwayatArea` | `Stack<Location>` | Stack riwayat area |
| `LINEAR_LOCATIONS` | `List<Location>` | 5 area linear: Valerion, Asgard, Grandis, Lumina, Aldoria |
| `AREA_QUEST_ID_RANGES` | `int[][]` | Range ID quest per area: {1-5}, {6-10}, {11-15}, {16-20}, {21-25} |

**Method:**
| Method | Deskripsi |
|--------|-----------|
| `MapTraversal()` | Start di Valerion (index 0) |
| `MapTraversal(String)` | Start di area tertentu |
| `initializeFromAreaName(String)` | Push area sampai target |
| `pindahArea(Location)` | Push area ke stack |
| `goToNext()` | Pindah ke area berikutnya (linear) |
| `goTo(String)` | Pindah ke area tertentu (next atau back) |
| `kembali()` | Pop area (kembali) |
| `areaSaatIni()` | Peek area teratas |
| `getQuestIdRangeForArea(String)` (static) | Range quest ID untuk suatu area |
| `getQuestIdRangeForCurrentArea()` | Range quest ID untuk area saat ini |
| `countCompletedQuestsInRange(...)` (static) | Hitung quest selesai dalam range |
| `areAllQuestsInRangeCompleted(...)` (static) | Cek semua quest dalam range selesai |
| `isCurrentAreaCleared(List<Quest>)` | Cek area saat ini sudah clear |

### WaypointSystem.java

Sistem teleport antar area yang sudah dikunjungi.

**Fields:** `lokasiTerbuka` (ArrayList\<Location\>), `lokasiSaatIni` (Location)

**Method:**
| Method | Deskripsi |
|--------|-----------|
| `tambahLokasi(Location)` | Tambah area (jika belum ada) |
| `tampilkanDaftar()` | Tampilkan semua waypoint + status CURRENT |
| `teleport(Location)` | Teleport ke area (validasi) |

---

## 18. Package `systems.save`

### SaveLoadSystem.java

Simpan dan load game ke file teks.

**Fields:**
| Field | Nilai |
|-------|-------|
| `SAVE_FOLDER` | `"src/saves/"` |
| `extension` | `".txt"` |
| `basicInfo` | `"[BASIC INFO]"` |
| `party` | `"[PARTY INFO]"` |
| `inventory` | `"[INVENTORY INFO]"` |
| `quest` | `"[QUEST INFO]"` |
| `skillSection` | `"[SKILL INFO]"` |
| `locationSection` | `"[LOCATION INFO]"` |

**Method:**
| Method | Deskripsi |
|--------|-----------|
| `save(AccountProfile)` | Simpan semua data: basic info, party, inventory, quest, skill, location ke file |
| `load(String)` | Load semua data dari file, rebuild object AccountProfile |

**Format Save (contoh):**
```
[BASIC INFO]
username=player1
totalGold=500
totalPlaytime=1:30
maxInventorySlots=200
areaName=Asgard

[PARTY INFO]
karakter=Hero1^Warrior^5^100^100^50^50^15^8^0^100^false^0^0^0

[INVENTORY INFO]
consumableFood=1^Salad Wortel Segar^...
equipment=1^Rusted Iron Plate^...

[QUEST INFO]
mainQuest=1^The Starving Onslaught^...

[SKILL INFO]
unlockedSkills=ATK Boost 1,Max HP Boost 1

[LOCATION INFO]
visitedLocations=valerion,asgard
```

---

## 19. Package `systems.gacha`

### GachaSystem.java

Sistem gacha (pull random equipment).

**Constants:** `BIAYA_GACHA_1X = 50`, `BIAYA_GACHA_10X = 500`

**Method:**
| Method | Deskripsi |
|--------|-----------|
| `tampilkanDaftarHadiah()` | Tampilkan semua item gacha dengan rarity & probabilitas |
| `pull(AccountProfile)` | Pull 1x: kurangi 50 gold → dapat 1 item random (weighted probability) |
| `pullTen(AccountProfile)` | Pull 10x: kurangi 500 gold → dapat 10 item |
| `pilihIndex()` | Pilih index item berdasarkan cumulative probability |

### itemGacha.java

Model item dalam pool gacha.

**Fields:** `equipment` (Equipment), `probabilitas` (int), `rarity` (String: Common/Uncommon/Rare/Epic/Legendary)

---

## 20. Package `systems.story`

### StoryManager.java

Menyimpan chapter dan unit taktis aktif. Saat ini belum banyak digunakan.

**Fields:** `chapterSaatIni`, `unitTaktisAktif`

---

## 21. Package `systems.encyclopedia`

### Encyclopedia.java

Database informasi semua game content (monster, item, lokasi, resep).

**Fields:** 11 HashMap<String, Object> untuk masing-masing kategori + indexUtama gabungan.

**Method:**
| Method | Deskripsi |
|--------|-----------|
| `displayMonsterSector()` | Tampilkan semua monster |
| `displayIngredientAlamSector()` | Tampilkan ingredient alam |
| `displayIngredientMonsterSector()` | Tampilkan ingredient monster |
| `displayIngredientConsumablesSector()` | Tampilkan ingredient consumables |
| `displayConsumablesSector()` | Tampilkan consumables |
| `displayWeaponSector()` | Tampilkan weapon |
| `displayArmorSector()` | Tampilkan armor |
| `displayAccessorySector()` | Tampilkan accessory |
| `displayLocationSector()` | Tampilkan lokasi |
| `displayRecipeSector()` | Tampilkan crafting recipe |
| `displayDetail(Object)` | Tampilkan detail tergantung tipe objek |
| `searchEncyclopedia(Scanner)` | Cari berdasarkan keyword di indexUtama |

---

## 22. Package `minigames`

### MiniGame.java (abstract)

Base class minigame. Fields: `namaGame`, `rewardKoin`. Method: `startGame(AccountProfile)`

### Quiz.java

Model soal quiz nutrisi.

**Fields:** `question`, `answerChoices[]`, `correctAnswer`, `explanation`

**Method:** `cekJawaban(String)` — cek jawaban case-insensitive

### QuizGame.java (extends MiniGame)

Quiz edukasi nutrisi dengan 20 bank soal, ambil 5 acak per sesi.

**Method:**
| Method | Deskripsi |
|--------|-----------|
| `startGame(AccountProfile)` | Jalankan 5 soal acak, reward 10 gold/soal benar |
| `tampilkanSoal(int, Quiz)` | Print soal + opsi a/b/c |
| `ambilSoalAcak()` | Shuffle bank soal, ambil 5 |

### SpaceGame.java (extends MiniGame)

Minigame spam tombol spasi dalam 10 detik (GUI Swing).

**Method:**
| Method | Deskripsi |
|--------|-----------|
| `startGame(AccountProfile)` | Buka JFrame, hitung spasi dalam 10 detik |
| `reward(AccountProfile)` | Beri gold berdasarkan jumlah spasi: <20=0, <40=50, <60=75, <80=100, >=80=200 |

---

## 23. Package `DummyData`

Semua class di package ini menyediakan data awal (dummy/seed data) untuk game.

### Daftar Class DummyData:

| Class | Isi Data |
|-------|----------|
| `weapon.java` | 42 weapon (Common s/d Mythic) dengan variasi class requirement |
| `armor.java` | 70 armor (Common s/d Mythic) untuk Warrior/Archer/Mage/Support |
| `accessory.java` | 30 accessory (Common s/d Mythic) |
| `consumables.java` | 50 consumable food dengan efek heal & info gizi SDG |
| `inqredients_alam.java` | 102 ingredient alam (logam, batu, kristal, herbal, kayu, air, tanah, dll) |
| `inqredients_monster.java` | 147 ingredient monster (drop dari musuh) |
| `inqredients_consumables.java` | 54 ingredient consumables (bahan makanan) |
| `craftingRecipe.java` | 50 resep crafting (kombinasi ingredient → consumable) |
| `forgeformula.java` | 10 formula forge (level 1-10 dengan material: Copper s/d Obsidian) |
| `gacha.java` | 35 item gacha (Common 50%, Uncommon 30%, Rare 15%, Epic 4%, Legendary 1%) |
| `monster.java` | Data monster per wilayah (stat, trivia penyakit) |
| `kota.java` | 5 lokasi: Valerion, Asgard, Grandis, Lumina, Aldoria |
| `mainquest.java` | 25 main quest (5 per wilayah, quest tentang nutrisi & stunting) |
| `subquest.java` | 25 sub quest (5 per wilayah) |
| `skilltree.java` | 11 skill pasif (ATK, Max HP, Max MP, DEF, Slot Inventory) |
| `classtree.java` | Tree class dengan 4 branch: Warrior, Archer, Mage, Support (total 17 class) |

**Method umum per class DummyData:**
| Method | Deskripsi |
|--------|-----------|
| `getDummy...()` | Return `List<...>` |
| `getDummy...Array()` | Return array |
| `getDummy...Map()` | Return `HashMap<Integer, ...>` (key = ID) |

---

## 24. Package `utils`

### AnsiColors.java

Konstanta ANSI escape codes untuk pewarnaan terminal:

| Constant | Value | Warna |
|----------|-------|-------|
| `ANSI_RESET` | `\u001B[0m` | Reset |
| `ANSI_BOLD` | `\u001B[1m` | Bold |
| `ANSI_CYAN` | `\u001B[36m` | Cyan |
| `ANSI_GREEN` | `\u001B[32m` | Hijau |
| `ANSI_YELLOW` | `\u001B[33m` | Kuning |
| `ANSI_MAGENTA` | `\u001B[35m` | Magenta |
| `ANSI_RED` | `\u001B[31m` | Merah |
| `ANSI_RED_BRIGHT` | `\u001B[91m` | Merah terang |
| `SOFT_TEAL` | `\u001B[38;2;64;200;180m` | Teal lembut |
| `WARM_GOLD` | `\u001B[38;2;220;180;80m` | Emas hangat |
| `SOFT_WHITE` | `\u001B[38;2;220;230;240m` | Putih lembut |
| `SOFT_GREEN` | `\u001B[38;2;100;200;140m` | Hijau lembut |
| `DIM_GRAY` | `\u001B[38;2;130;145;160m` | Abu-abu redup |

---

## Ringkasan Arsitektur OOP

### Inheritance Hierarchy

```
GameCharacter (abstract)
├── PlayerCharacter
│   ├── Warrior
│   ├── Mage
│   ├── Archer
│   └── Support
├── Monster
├── BossMonster (implements Skill)
└── NPC

Item (abstract)
├── Equipment (abstract, implements IEquippable)
│   ├── Weapon
│   ├── Armor
│   └── Accessory
├── ConsumableFood (implements IConsumable)
└── Ingredients

Quest (abstract)
├── MainQuest
└── SubQuest

MiniGame (abstract)
├── QuizGame
└── SpaceGame
```

### Interface

```
Skill
  └── gunakanSkill(GameCharacter source, GameCharacter target)

IEquippable
  ├── equip(PlayerCharacter target)
  └── unequip(PlayerCharacter target)

IConsumable
  └── consume(GameCharacter target)
```

### Composition / Aggregation

```
App
├── AccountProfile (1)
│   ├── PlayerCharacter[4] (party)
│   ├── LinkedList<Item> (inventory)
│   ├── QuestTracker (1)
│   │   ├── ArrayList<MainQuest>
│   │   ├── ArrayList<SubQuest>
│   │   └── ArrayList<Quest> (riwayat)
│   └── Set<String> (unlocked skills)
├── MapTraversal (1)
│   └── Stack<Location> (history)
├── WaypointSystem (1)
│   └── ArrayList<Location> (unlocked)
├── BattleSystem (per battle)
│   ├── PlayerCharacter[] (party player)
│   ├── GameCharacter[] (party enemy)
│   └── BattleLog (1)
├── GachaSystem (1)
│   └── itemGacha[] (pool)
├── Encyclopedia (1)
│   └── HashMap<String, Object>[11] (indexes)
├── CraftingSystem (1)
│   └── ArrayList<craftingRecipe>
├── ForgeSystem (1)
│   └── ArrayList<forgeFormula>
├── Shop (1)
│   └── ArrayList<Item> (catalog)
├── QuizGame (1)
│   └── Quiz[] (bank soal)
└── SpaceGame (1)
```

---

## Cara Compile & Run

```powershell
# Compile ke folder bin/
javac -d bin "@build_sources.txt"

# Run
java -cp bin main.Main

# Atau pakai script
.\build.ps1 build   # compile
.\build.ps1 run     # run
.\build.ps1 clean   # hapus bin/

# CMD version
build.bat build
build.bat run
build.bat clean
```
