# Viikkoraportti 3
perjantai 31.1.2020


## Mitä olen tehnyt tällä viikolla?

Tämän viikon isoin urakka oli käyttöliittymän tekeminen ohjelmalle. Käyttöliittymä on rakennettu yhteensopivaksi suunnittelemani laajennuksen kanssa, so. toisen luolatyypin generaattori. Toinen selkeä kokonaisuus tällä viikolla oli jacocon ja checkstylen käyttöönotto. Vaikka kyseessä ovat oman alkeellisen käsitykseni perusteella erittäinkin toimivat ja helppokäyttöiseksi suunnitellut koodin laatu- ja testiraporttienlaadintatyökalut, oli niiden toimimaan saamisessa omat haasteensa. En ollut aikaisemmin käyttänyt niitä, joten aika paljon piti lueskella ennen kuin homma lähti toimimaan.


## Miten ohjelma on edistynyt?

Projekti on mielestäni edennyt mukavasti aikataulussa. Ohjelma alkaa jo "näyttää joltain" ja sitä on nyt motivoivaa lähteä täydentämään uusilla palasilla. Käyttöliittymä on muutamia korjauksia lukuun ottamatta riittävällä tasolla valmis (aina voi ulkoasua hioa) ja ensimmäinen luolageneraattori toimii. 

Saamani palautteen mukaan työn laajuutta on tässä kohtaa vielä vaikea arvioida. Teen nyt niin, että jatkan työtä suunnitelmani mukaisena ja otan seuraavilla viikoilla taas mielelläni palautetta vastaan, mikäli työ näyttää liian suppealta. 

## Mitä opin tällä viikolla / tänään?

Tällä viikolla rakensin ensimmäisen käyttöliittymäni! Jotain pientä tuli tehtyä ohjelmoinnin jatkokurssilla, mutta mitään selkeää kokonaisuutta sillä kurssilla ei toteutettu. Käyttöliittymäasioiden ohella checkstyle- ja jacoco-raportteihin tuli tutustuttua. Niihin perehtymistä pitää kuitenkin harrastaa jatkossakin.

## Mikä jäi epäselväksi tai on tuottanut vaikeuksia?

Käyttöliittymän kanssa on yksi ongelma. Käytän Javan swing-kirjastoa sen toteuttamiseen ja esitän luodun luolageneraattorin omassa ruudussaan (JFrame) HTML-muotoisena syötteenä. Onnistuin tyylitiedostoa käyttämällä muokkaamaan fontin oikeanlaiseksi (monospace ja sopivan pieni fonttikoko), mutta samalla logiikalla toteuttamani line-height -muutos ei toiminut. En osaa sanoa tähän syytä, ja se saa tulostukset näyttämään yhä suorakulmioilta neliöiden sijaan. Tähän pitää löytää ratkaisu. Alla vielä ongelmakohdan koodi tiedostosta DungeonGUI.java.

```java
                // create new frame (window) for the dungeon
                JFrame printFrame = new JFrame();
                printFrame.setTitle("Random dungeon");
                printFrame.setSize(1500, 1000);
                
                // create a new editor frame for the print output
                JEditorPane dungeonPane = new JEditorPane();
                dungeonPane.setContentType("text/html"); //HTML OR PLAIN?
                dungeonPane.setEditable(false);
                dungeonPane.setBounds(0, 0, 1500, 850);
                
                // create CSS style sheet for dungeonPane in order to change line spacing and font
                HTMLEditorKit kit = new HTMLEditorKit();
                dungeonPane.setEditorKit(kit);
                JScrollPane scrollPane = new JScrollPane(dungeonPane);
                
                StyleSheet styleSheet = kit.getStyleSheet();
                styleSheet.addRule("body { font: 8px courier, sans-serif; line-height: 0.7;}"); // ****** LINE-HEIGHT NOT WORKING ******
               //styleSheet.addRule("body { line-height:80%; }"); // NOT WORKING EITHER
                
                Document doc = kit.createDefaultDocument();
                dungeonPane.setDocument(doc);
```


## Mitä teen seuraavaksi?

Ensi viikon agendalla on toisen luolageneraattorin laatiminen. Kun nyt olen saanut checkstylen toimimaan, sain ison kasan ilmoituksia koodin laadusta, joita en vielä ole korjannut. Myös nämä korjaukset ovat ensi viikon työlistalla. Jos aikaa jää (ja jos saan uusia ideoita ongelman ratkaisemiseksi), niin yritän saada käyttöliittymän yllä kuvatun luolastojen esitysongelman kuntoon. Ensi viikolla pitää lisäksi täydentää dokumentaatiota testauksen osalta ja toteuttaa se. Yksikkötestauksessa on vielä pieniä puutteita muutaman metodin osalta (ks. [koodikattavuus](https://github.com/lauriap/random-dungeons/blob/master/documentation/test-coverage/test-coverage-w3.png)), koska ne saattavat vielä muuttaa aavistuksen muotoaan GUI-ongelman ratkaisemiseksi.

Koska Random-luokka pitää toteuttaa tässä työssä itse, sen aloitus on listalla, jos aikaa jää. 


## Tuntikirjanpito

la 25.1.2020
* 8h käyttöliittymän kirjoitus ja suunnittelu

pe 31.1.2020
* 5h jacoco + checkstyle + GUI-pähkäilyä + viikkoraportti 3

**VIIKKO YHT. 13 h**

**KOKO PROJEKTI YHT. 34 h**
