# Kalenteri
Sovellus tapahtumien ja muistiinpanojen lisäykseen eri päivämäärille

Huom: Jos sovellukseen kuuluvat .java-tiedostot ovat kansiossa "kalenteri", tapahtuma-tiedostot tallennetaan  alakansioon "tapahtumat". Ohjelma ei osaa vielä(!) luoda tätä kansiota automaattisesti, joten se täytyy käydä tekemässä itse ennen ohjelman suoritusta.

Ohjelman luokat lyhyesti:

- Luokka Kalenteri
  - Sisältää ohjelman käytettävyyteen liittyviä metodeja, esimerkiksi tulostaAlkuvalikko().
  - Metodit tarkistavat käyttäjän syötteen ja kutsuvat vastaavaa metodia toisessa luokassa, esimerkiksi luokan Tietokanta lisääTapahtuma()-metodia. Metodeista saatu tieto tulostetaan käyttäjälle.
- Luokka Tapahtuma
  - Sisältää vain konstruktorin sekä havainnointi- ja asetusmetodeja Tapahtuma-olioille, esimerkiksi annaMuistiinpanot().
- Luokka Tietokanta
  - Hoitaa tiedostojen käsittelyn ja sisältää listan kaikista tapahtumista
  - Tärkeimmät metodit:
   - lisääTapahtuma()
     - Luo uuden tekstitiedoston annetun tapahtuman nimellä ja lisää tiedostoon annetut muistiinpanot.
   - etsiTapahtumia()
     - Palauttaa Tapahtuma-olioita sisältävän listan
   - poistaTapahtumia()
     - Poistaa päiväyksellä haetun Tapahtuma-olion listasta. Poistaa myös oliosta luodun tiedoston.
- Luokka Testaa
  - Sisältää main-metodin. Tulostaa ohjelman valikon ja mahdollistaa sovelluksen eri ominaisuuksien valitsemisen.
