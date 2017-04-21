# anatomi_server
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

git clone git@github.com:FindOut/anatomi_server.git

cd anatomi_server/

cd anatomi_server/

mvn jetty:run

## Meta-modell

![meta-modell](anatomy_server_model.jpg)

## Rest-gränssnitt

