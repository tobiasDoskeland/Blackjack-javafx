# 1. Beskrivelse av appen
Appen jeg har laget er en enkel versjon av spillet blackjack. Man skriver inn bet, får to kort, du kan så velge å stå
eller hitte og få nytt kort. Jeg har ikke med split eller side-bets fordi, vel det er mye arbeid. dealer får også
to kort, hvor det ene er skjult – dealer fungerer på en veldig enkel algorytme: hit på alt under 17 og stå på alt over.

Man starter med en satt saldo som endres dynamisk. 

Filbehandlingen logger resultatet av alle spille i en runde som en liste av Strings. denne kan så hentes frem til terminal. filen lagrer bare en "session" av runder. så om man starter appen på ny og lagrer overkjører dette den forrige.

# 2. Diagram
[Diagram](diagram.uxf)

# 3. Spørsmål

## 1. Hvilke deler av pensum i emnet dekkes i prosjektet, og på hvilken måte? (For eksempel bruk av arv, interface, delegering osv.)
    
Jeg har brukt interface ettersom flere av klassene vil ta i bruk de samme metodene og iterator.
interfacene er da tatt i bruk av en superklasse CardContainerImpl som definere arrayliste av kort og metodene definert i interfacet.

Iterator har jeg med og blir ofte tatt i bruk, ettersom det er veldig nyttig å iterere gjennom arrayer gjennom programmet.

Arv blir tatt i brukt aktivt av CardDeck og CardHand, hvor de begge arver fra superklassen CardContainerImpl
dette fordi de begge tar i bruk samme metoder og inneholder en arrayList med kort. På denne måten korter vi ned på kode og generaliserer den.

Delegering er også tatt en del i bruk – for eksempel når kort skal vises til bruker så delegerer BlackJackGame klassen formatering til pent format til kortet i seg selv.

Filhåndtering er også pensum som er tatt i bruk.

## 2. Dersom deler av pensum ikke er dekket i prosjektet deres, hvordan kunne dere brukt disse delene av pensum i appen?

jeg har for eksempel ikke tatt i bruk comparator – jeg ser ikke helt hvorfor jeg burde bruke det i dette tilfellet. Jeg har på en måte ingenting å sortere

## 3. Hvordan forholder koden deres seg til Model-View-Controller-prinsippet? (Merk: det er ikke nødvendig at koden er helt perfekt i forhold til Model-View-Controller standarder. Det er mulig (og bra) å reflektere rundt svakheter i egen kode)

Fungerer ok -, jeg har en god del logikk i Controller objektet. Det er ikke optimalt i henhold til prinsippet. Alikevel var dette en mye letter løsning, fordi jeg har mange knapper og felt i spillet og det er en del logikk angående det visuelle aspektet til spillet. for eksempel det å holde et kort skjult for dealer, vise kort i et fint format og oppdatere dynamisk basert på at man får nye kort etc. Da må oså scoren til spiller og dealer oppdaterers ettersom. Samme med saldo etc. Så det er mye logikk som jeg mener burde stå i Controller av disse årsaker for å gjøre ting enkelt. Alikevel kan man nok flytte over en del av koden til spilllogikk-filen og heller kalle på disse. Da ville nok programmet følge prinsippet bedre, og hvis jeg skulle ha utvidet programmet i fremtiden så ville jeg vurdert å gjøre dette.

## 4. Hvordan har dere gått frem når dere skulle teste appen deres, og hvorfor har dere valgt de testene dere har? Har dere testet alle deler av koden? Hvis ikke, hvordan har dere prioritert hvilke deler som testes og ikke? (Her er tanken at dere skal reflektere rundt egen bruk av tester)

jeg skrev tester til spill-logikken og filhåndteringen ettersom jeg mener dette er de to viktigste aspektene i programmet. Jeg skrev tester til alt som lot seg teste, og fokuserte på den viktigste logikken som er "make-or-break" i koden. Altså om det ville kræsjet programmet eller ført til store feil er det viktig å få testet.

jeg har ikke testet alle aspekter av koden, ettersom det er flere deler som er vanskelige å teste; de gir ikke reelle testbare verdier, eller det er vanskelig å sette opp testkoden slik at man får de villkårene man trenger for å kjøre testen. 

Fks. hvis jeg skal teste et utfall hvor jeg trenger spesifikke kort, fks. ess. Har jeg ingen måte å manuelt sette opp et spill med spesifikke kort, det blir derfor vanskelig å skrive faktiske tester for dette.

Alikvel har jeg gjort tiltak i koden når jeg skrev, selv om jeg ikke får skrevet faktiske tester til dette.



# KI-deklerasjon
### Måtte jeg bruke KI, eller var det bare tidssparende? + Dersom du brukte KI, i hvilken grad?
Jeg MÅTTE nok ikke bruke KI, men jeg valgte å gjøre det, KUN på et punkt og det var troubleshooting.
Grunnen til at jeg gjorde dette var fordi jeg aldri har tatt i bruk JavaFX før, og hadde problemer med at applikasjonen åpnen og lukket seg hele tiden. Derfor brukte jeg AI til å troubleshoote og finne løsningen til dette problemet raskt, slik at jeg kunne fokusere på kodingen.

Jeg valgte å gjøre dette i stedet for å bruke flere timer på å sette meg dypt inn i JavaFx og lære meg inner og uter jeg i praksis ikke vil få bruk for videre.

### Dersom du brukte KI, prøvde du flere modeller?
Nei, jeg brukte kun ChatGPT, gratis versjon hvis du lurte. Den løste problemet jeg hadde så kan egt ikke klage.
prøvde å bruke så lite KI som mulig i prosjektet, for læringsutbyttet sin skyld