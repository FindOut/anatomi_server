# anatomy_server
Fristående server för anatomi-webb-applikation

## Bakgrund

Detta projekt kom till under ett examensarbete på Nackademins yrkeshögskola.


FindOut har arbetat med s k anatomidiagram i många år. En anatomi beskriver ett systems beståndsdelar och deras inbördes relationer.

2014 skapade vi en desktop-applikation, Paipe, som är en fristående anatomieditor, som lagrar anatomi-filer i egna, lokala filer.

Nyligen skrev vi en efterförljare till Piape - Jira-anatomi. Den gjord som en plug-in till Jira (https://www.atlassian.com/software/jira), och den hämtar och lagrar alla data i Jira.

Men alla använder inte Jira, så vi behövde nu en enkel server som gör att man kan arbeta med anatomier utan att vara beroende av Jira.

Uppgiften var att skapa denna server i samarbete med oss som jobbar med anatomi-viewern.

## Implementation

anatomy_server är en webb-server som erbjuder en REST-tjänst för att hantera anatomier. Servern kan köras antingen fristående, eller i en Java EE-webb-app-server som till exempel Tomcat.

Det går i nuläget att lagra data på två olika sätt, i minnet (mestadels tänkt för testning) och i en relationsdatabas. Genom att ändra ett värde i en konfigurationsfil kan man byta lagringssätt.

## Meta-modell

![meta-modell](anatomy_server_model.jpg)

## Rest-gränssnitt

Alla URLer är av typen http://host/anatomy/api/...

Alla REST-anrop returnerar JSON om inte annat anges.

 namn | http-metod | URL | Body | Beskrivning 
 --- | --- | --- | --- | ---
lista modeller | GET | models | | returnerar en lista av alla modeller
en modell | GET | models/*id* | | returnerar modellen med angivet id, med alla dess anatomer, relationer och attribut
ta bort modell | DELETE | models/*id* | | tar bort modellen med angivet id från servern
skapa modell | POST | models | | skapar en ny tom modell med ett unikt id, som returneras
skapa anatom i en modell | POST | models/*id*/anatoms | | skapa en nya anatom i modell *id*, som returneras
hämta alla anatomer från en modell	| GET	|	models/*id*/anatoms | |	returnerar en lista med alla anatomer för modell med angivet id
hämta en anatom	|	GET	|	models/*modelId*/anatoms/*anatomId* | |	returnerar anatomen med angivet id, med alla dess relationer och attribut
ta bort anatom | DELETE | models/*modelId*/anatoms/*anatomId* | | tar bort anatomen med angivet id från servern
skapa relation |	POST |	relations	| {from : [integer], to : [integer]} |	skapar en relation från anatom *anatomId* till anatom *toAnatom*
hämta alla relationer från en anatom |	GET |	models/*modelId*/anatoms/*anatomId*/relations	| | returnerar en lista med alla relationer för anatom med angivet id
hämta alla utgående relationer från en anatom |	GET |	models/*modelId*/anatoms/*anatomId*/relations/outbound	| | returnerar en lista med alla utgående relationer för anatom med angivet id
hämta alla ingående relationer från en anatom |	GET |	models/*modelId*/anatoms/*anatomId*/relations/inbound	| | returnerar en lista med alla ingående relationer för anatom med angivet id
hämta en relation	|	GET |	relations/*relationId* | | returnerar relationen med angivet id
ta bort relation	|	DELETE |	relations/*relationId* | | tar bort relationen med angivet id från servern
sätt anatom-attribut |	POST |	models/*modelId*/anatoms/*anatomId*/attributes |	{value : [integer]}	| skapar attribut, sätter dess värde, och returnerar attributet
ta bort anatom-attribut	|	DELETE |	models/*modelId*/anatoms/*anatomId*/attributes/*attributeId* | |	tar bort attributet med angivet id från servern
sätt relations-attribut	|	POST |	relations/*relationId*/attributes |	{value : [integer]} |	skapar attribut, sätter dess värde, och returnerar attributet
ta bort relations-attribut |	DELETE |	relations/*relationId*/attributes/*attributeId* | |	tar bort attributet med angivet id från servern
hämta ett attribut | GET | attributes/*attributeId* | | returnerar attributet med angivet id
hämta alla attribut för en anatom | GET | model/*modelId*/anatoms/*anatomId*/attributes | | returnerar alla attribut för anatom med angivet id
hämta alla attribut för en relation | GET | relations/*relationId*/attributes | | returnerar alla attribut för relation med angivet id
ändra värde på attribut	|	PUT |	attributes/*attributeId* | {value : [integer]} |	ändrar värdet på attributet med angivet id

## Design

![klass-diagram](anatomy_server_classes.png)

Genom att implementera olika konkreta klasser av interfacet AnatomyService, kan man se till att ingenting behöver ändras i RestService ifall ett till sätt att lagra data på ska implementeras. När en ny konkret service-klass har skapats behövs en till metod i AnatomyServiceConfig, som genom en ändring i extern konfig-fil kan skicka vidare rätt typ av instans till RestService.

## Sätta upp utvecklingsmiljö

Valfri IDE för Java

Maven v3.*

## Bygga och köra appen

### Skapa relationsdatabas

Projektet förutsätter att en MySQL-databas vid namn anatomy_server redan finns.

Importera filen ../anatomy_server/sql/anatomy_server_ddl.sql till MySQL.

Ifall port, namn, användarnamn eller liknande behöver ändras så görs detta i filen ../anatomy_server/src/main/resources/application.properties.

### Lagring i minnet

Ifall man av någon anledning inte vill använda en databas (kanske under utvecklandet) så behöver man ändra några saker i en konfigurationsfil.

Öppna ../anatomy_server/src/main/resources/application.properties och ändra *service.type* till *memory*

Kommentera bort raderna som har med databaskopplingen att göra.

Kommentera in raden med *spring.autoconfigure.exclude* för att Hibernate och JPA inte ska leta efter en databaskoppling.

### Klona från GitHub och köra projektet

git clone git@github.com:FindOut/anatomy_server.git

cd anatomy_server/

cd anatomy_server/

mvn spring-boot:run

#### Stänga av servern

POST-anrop till http://[host]/shutdown

### För att köra tester fristående:

Maven-kommando från ../anatomy_server/

mvn integration-test

### Paketering

Det finns två olika profiler för att bygga JAR-filer respektive WAR.

#### För att bygga en JAR-fil:

mvn package

eller

mvn package -Pjar

#### För att bygga WAR-fil:

mvn package -Pwar

## Avgränsningar

Det finns ingen autenciering eller auktorisering med i denna server.

Datalagring på andra sätt än de ovan nämda är inte implementerade i dagsläget, men man skulle kunna lägga till t.ex molnlagring på DropBox, Google Drive och liknande.

Servern körs separat från webb-applikationen som hanterar anatomi-diagrammen, då denna inte är färdiganpassad för REST-tjänsten servern tillhandahåller. I ett senare skede skulle man kunna ta med även webb-applikationen i detta projekt, för att få en komplett modelleringsapp.
