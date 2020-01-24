# Viikkoraportti 2
perjantai 24.1.2020


## Mitä olen tehnyt tällä viikolla?
Tällä viikolla työ pääsi kunnolla vauhtiin. Kirjoitin ensimmäisen luolastogeneraattorin kokonaisuudessaan samoin kuin sen metodien yksikkötestit. Ohjaajalta saamani palautteen perusteella työssäni on vielä melko vähän sisältöä, joten suunnittelen työn laajentamista vielä toiseenkin luolasto-/tyrmägeneraattoriin.

Itse luolastogeneraattorin kirjoittaminen sujui kohtuullisen mukavasti, mutta yksikkötestaus sen sijaan tuotti harmaita hiuksia. Suurimman osan työhön viikon aikana käyttämästäni ajasta menikin yksikkötestaukseen perehtyessä - joko lukien tai testimetodeja kirjoittaen. Testiluokan pystyttämiseen liittyi myös hieman epävarmuutta - toimiko esimerkkimateriaaleista kopioimani Gradle-määritykset riittävän hyvin?


## Miten ohjelma on edistynyt?

Ohjelma on mielestäni edistynyt aikataulussa. Olen nyt huomattavasti luottavaisempi projektin suhteen kuin aloitusviikolla. Kun on jo jotain tehtynä, on paljon helpompi jatkaa eteenpäin. Toki tämän ensimmäisenkin luolastogeneraattorin kanssa saa vielä tehdä paljon töitä, sillä se ei ole todellakaan täydellinen.

## Mitä opin tällä viikolla / tänään?

Yksikkötestauksen lisäksi viikon suurin oppi liittyy itse algoritmiin eli soluautomaatteihin. Olin jopa hieman häkeltynyt siitä, kuinka hyvin se tuottaa luolastoja. Yksikkötestaukseen tutustuminen oli ammatillisessa mielessä ehkä se kaikista keskeisin sisältö tämän viikon osalta, ja sen työn parissa saan ehdottomasti viettää vielä runsaasti aikaa ennen kuin siihen liittyvät prosessit alkavat tulla ulkomuistista.


## Mikä jäi epäselväksi tai on tuottanut vaikeuksia? KYSYMYS OHJAAJALLE

### KYSYMYS 1

Yksikkötestauksen kanssa alkuun pääseminen oli kovin hankalaa. Vaikein kohta oli testata metodia wallOrSpace, joka perustuu satunnaiseen todennäköisyyteen. Jos Random-luokalla luotu satunnaisluku (0-100) on pienempi kuin parametrina annettu seinän todennäköisyys, tulee metodin palauttaa luku 1 ( = seinämä) ja muuten luku 0 ( = vapaa ruutu). En oikein keksinyt, kuinka tätä metodia pitäisi yksikkötestata. Tuntuu, että testiluokkaan TestDungeon kirjoittamani yksikkötesti testWallOrSpace on vain alkuperäinen luokka kirjoitettuna uudelleen ja verrattuna itseään vasten. Toisin sanoen: yksikkötesti testaa Random-luokan metodia nextInt eikä Dungeon-luokan wallOrSpacea. Miten tämä oikein tulisi hoitaa?

### KYSYMYS 2

Olen ottanut Gradlen käyttöön projektissa ja projektikansiooni generoituu testien tuloksia. Kaikki tulokset ovat kuitenkin oletusarvoisesti piilotettu .gitignorella gitHubista. Mitä kurssin aikataulussa mainitulta "testikattavuuden seurannalla" tarkoitetaan? Onko ideana laittaa kuvia testikattavuudesta viikkoraportteihin?

Alla vielä kuva testiraportista.

![Vko2 unit tests][kuva]

[kuva]: https://github.com/lauriap/random-dungeons/tree/master/documentation/vko2_unit_tests.png "Vko2 unit tests"


## Mitä teen seuraavaksi? KYSYMYS OHJAAJALLE

Koska saamani LabTool-palautteen perusteella yksi luolastogeneraattori on aiheena suppea, ajattelin laajentaa sitä vielä toiseen "rauniogeneraattoriin." Tämä perustuu myöskin satunnaislukuihin ja ajatuksena on luoda suorakulmion muotoisia, osin pirstaloituneita rakennelmia kartalle. Kartat ovat ulkoasultaan samantyylisiä kuin tässä ensimmäisessä generaattorissa, mutta "kaupunkimaisempia". Lähestymistapa on täysin erilainen kuin soluautomaateissa, joten kaikki koodi on siten uutta. Täyttääkö projekti tällä laajennuksella kurssille asetetut tavoitteet? Arvioin itse työmäärän noin puolitoistakertaistuvan tämän lisäyksen johdosta (GUI on yhteinen molemmille, muuten työmäärä olisi kaksinkertainen).

Ensi viikolla ajattelin aloittaa käyttöliittymän kirjoittamisen odottaessani vastausta yllä esitettyyn kysymykseen projektin laajentamisesta. Tavoitteena on saada jonkinlainen graafinen käyttöliittymä valmiiksi, jotta pääsen toteuttamaan luolastojen "printtauksen" järkevällä tavalla yksikkötesteineen. Jos aikaa jää, aion myös yrittää saada testaukseen mukaan suoritustestejä. Ja jos vielä aikaa jää ja vastaus yo. kysymykseen on tullut, aloitan rauniogeneraattorin suunnittelua. Myös dokumentaatiota pitää päivittää tämän mahdollisen laajennuksen osalta.


## Tuntikirjanpito

la 18.1.2020
* 5h ensimmäisen luolastokoodin suunnittelu ja kirjoitus

ke 22.1.2020
* 1h perehtymistä yksikkötestaukseen

to 23.1.2020
* 2h perehtymistä yksikkötestaukseen

pe 24.1.2020
* 4h yksikkötestien kirjoittaminen Dungeon-luokalle
* 2h viikkoraportin kirjoittaminen ja koodikorjaukset

**VIIKKO YHT. 14 h**

**KOKO PROJEKTI YHT. 21 h** 
