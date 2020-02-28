# Viikkoraportti 7
perjantai 28.02.2020


## Mitä olen tehnyt tällä viikolla?

Tämän viikon työnä oli dokumentaation parantelu sekä käyttöliittymään liittyvien ongelmien ratkaiseminen. Dokumentaatioon lisäsin suositusten mukaisesti "nopeustestin", jotta algoritmien tehokkuudesta saa jonkinlaisen kuvan.

## Miten ohjelma on edistynyt?

Ohjelma alkaa olla pitkälti paketissa. Täydellinen se ei toki ole, mutta koen oppineeni sen avulla ne asiat, mitä lähdin tavoittelemaan. 

## Mitä opin tällä viikolla / tänään?

Isoin oppi oli, että Javan Swing on kohtuullisen alkeellinen työkalu käyttöliittymäsuunnitteluun. Törmäsin useampaan asiaan mm. tekstien muotoilussa, joita Swing ei tukenut. Tulevissa projekteissa on syytä heti alkuvaiheessa kysyä, mitkä ovat parhaita työkaluja mihinkin tarkoitukseen ja pyrkiä opettelemaan niiden käyttöä. Swing oli kuitenkin perustasolla hyvin yksinkertainen kirjasto käyttää, joten ehkä se oppimismielessä oli ihan ok.

## Mikä jäi epäselväksi tai on tuottanut vaikeuksia?

Törmäsin tänään nopeustestejä tehdessäni ensimmäistä kertaa Dungeon-luokan floodFill() -metodin kanssa pinon ylivuotoon. Pienillä luolakoilla algoritmi toimi moitteettomasti, mutta luolakoon kasvaessa ohjelma aina välillä toimi ja välillä kaatui. "Oikein isoilla" (5000x5000) luolastoilla ylivuoto tapahtui joka kerralla. Tämä johtuu siitä, että floodFill() kutsuu itseään joka alkiossa kahdeksan kertaa ( -> tarkistaa kaikki vieressä olevat alkiot), jolloin pinon koko kasvaa nopeasti erittäin suureksi. En saanut ratkaistua ongelmaa allokoimalla netBeansin kautta ohjelmalle lisää muistia. Googlailun perusteella DFS-algoritmin vaihtaminen BFS-algoritmiin voisi auttaa. En kuitenkaan ehtinyt sitä enää yhdessä illassa toteuttaa, sillä BFS olisi vaatinut jonon toteuttamista omana tietorakenteenaan testailuineen. Yhtä kaikki metodi toimii alun perin suunnittelemillani luolastokooilla moitteettomasti, joten tyydyn tämän kurssin puitteissa alun perin suunnittelemaani ratkaisuun.

## Mitä teen seuraavaksi?

Tarkistan ohjelman toimivuuden vielä kerran, luon siitä julkaisun ja alan valmistautua demotilaisuuteen.

## Tuntikirjanpito

la 22.2.2020
* 5h Käyttöliittymän ongelmien ratkaisua (sivupalkki, tekstimuotoilujen vaihtaminen pois HTML/CSS:stä)

pe 28.2.
* 1h Vertaisarviointi
* 3h Nopeustestaus ja stack overflow -pähkäily
* 1h viikkoraportti + dokumentointia


**VIIKKO YHT. 10 h**

**KOKO PROJEKTI YHT. 82 h**
