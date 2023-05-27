# Agent based traffic simulation
## Projekt jest symulacją agent-based, która pozwala zasymulować ruch uliczny na skrzyżowaniu.

W celu przeprowadzenia symulacji użytkownik może ustawić następujące parametry symulacji:
czas symulacji - szybkość wyświetlania symulacji

liczba samochodów - liczba samochodów, które będą musiały przejechać przez skrzyżowanie w jednej fali

liczba fal - liczba fal, w każdej fali jest tyle samochodów ile zostało ustawionych jak "liczba samochodów"

czas fal - nowa fala samochodów pojawia się na plaszy kiedy numerKroku % czasFali == 0 

czas świateł - światła zmieniają się kiedy numerKroku % czasSwiatel == 0

min/max czas reakcji - przedział, z którego losowany jest czas reakcji dla każdego obiektu klasy "Car"

W użytym modelu początkowa pozycja każdego obiektu klasy "Car" jest losowana
przy pomocy metody nextInt z klasy "Random". Każdy obiekt klasy "Car" ma 25% szans na rozpoczęcie z każdego z kierunków.

Kierunek ruchu obiektu klasy "Car" jest losowany przy pomocy metody nextInt z klasy "Random".

50% szans na jazdę prosto - samochód niebieski

25% szans na jazdę prawo - samochód żółty

25% szans na jazdę lewo - samochód różowy

klasy:
App - Główna klasa, w której startujemy program

CrossRoadModel - klasa odpowiedzialna za cały model symulacji. Obiekt tej klasy posiada wszytkie informacje
dotyczące modelu(położenie samochodów, stan świateł itd).

Car - obiekty tej klasy posiadają informacje o położeniu, kierunku ruchu, czasu reakcji

Car oraz CrossRoadModel są w kompozycji ???


RootDisplay - klasa odpowiedzialna za GUI

Road - obiekt tej klasy obrazuje w sposób graficzny sytuacje na planszy(skrzyżowaniu)

Slider - pomocnicza klasa do GUI 

Label - pomocnicza klasa do GUI 


Do wykonania GUI została użyta biblioteka "Swing"
