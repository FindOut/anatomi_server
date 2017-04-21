# anatomy_server
Fristående server för anatomi-web-applikation

## Bakgrund

FindOut har arbetat med s k anatomidiagram i många år. En anatomi beskriver ett systems beståndsdelar och deras inbördes relationer.

2014 skapade vi en desktop-applikation, Paipe, som är en fristående anatomieditor, som lagrar anatomi-filer i egna, lokala filer.

Nyligen skrev vi en efterförljare till Piape - Jira-anatomi. Den gjord som en plug-in till Jira (https://www.atlassian.com/software/jira), och den hämtar och lagrar alla data i Jira.

Men alla använder inte Jira, så vi behöver nu en enkel server som gör att man kan arbeta med anatomier utan att vara beroende av Jira.

Uppgiften är att skapa denna server i samarbete med oss som jobbar med anatomi-viewern. Vi har en prioriterad lista på olika funktioner som kan vara användbara för oss. Så ambitionsnivån kan anpassas till tidsramen i exjobbet.

Servern kan skrivas i Java enligt JavaEE-standarden.

## Sätta upp utvecklingsmiljö

Valfri IDE för Java

Maven

## Bygga och köra appen

git clone git@github.com:FindOut/anatomy_server.git

cd anatomy_server/

cd anatomy_server/

mvn jetty:run

## Meta-modell

![meta-modell](anatomy_server_model.jpg)

## Rest-gränssnitt

Alla URLer är av typen http://host/anatomy/api/..... 
Alla REST-anrop returnerar JSON om inte annat anges.

 namn | http-metod | URL | Beskrivning 
 --- | --- | --- 
lista modeller | GET | models | returnerar en lista av alla modeller
en modell | GET | models/*id* | returnerar modellen med angivet id, med all dess anatomer, relationer och attribut
ta bort modell | DELETE | models/*id* | tar bort modellen med angivet id från servern
skapa modell | POST | models | skapar en ny tom modell med ett unikt id, som returneras
skapa anatom i en modell | POST | models/*id*/anatoms | skapa en nya anatom i modell *id*, och returnerar ett unikt auomatgenererat id.
ta bort anatom | DELETE | models/*modelId*/anatoms/*anatomId*
skapa relation
ta bort relation
sätt anatom-attribut | .. | skapa attribut om det ej finns, och sätt dess värde
ta bort anatom-attribut
sätt relations-attribut
ta bort relations-attribut

### lista modeller

[
  {id: 123},
  {id: 234},
  ...
]

### skapa ny modell

Skapar tom modell och returnerar ett unikt automatgenerat id som respons.

