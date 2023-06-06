# Agent based traffic simulation
## [PL] Projekt jest symulacją agent-based, która pozwala zasymulować ruch uliczny na skrzyżowaniu.
## [ENG] The project is an agent-based simulation to simulate traffic at an intersection.
##### Table of Contents  
 - [View](#view)  
 - [Docs[PL]](#docspl)
 - [Docs[ENG]](#docseng)  
 - [Class diagram](#class-diagram)
 - [Object diagram](#object-diagram)


## View
![image](https://github.com/MateuszZalobnik/Traffic-Agent-Based-Simulation/assets/77732992/219e3135-2553-45ef-a34e-eef2cac162c5)

## Docs[PL]
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


Światła drogowe:

stan 1 - zielone światło prawo i prosto dla osi X

stan 2 - zielone światło lewo dla osi X

stan 3 - zielone światło prawo i prosto dla osi Y

stan 4 - zielone światło lewo dla osi Y



klasy:
App - Główna klasa, w której startujemy program

CrossRoadModel - klasa odpowiedzialna za cały model symulacji. Obiekt tej klasy posiada wszytkie informacje
dotyczące modelu(położenie samochodów, stan świateł itd).

PixelsPositionObject - abstrakcyjna klasa, która jest odpowiedzialna za położenie obiektów na ekranie

Car - rozszerza PixelsPositionObject - obiekty tej klasy posiadają informacje o początkowym położeniu, kierunku ruchu, czasu reakcji

TrafficLight - rozszerza PixelsPositionObject - obiekt tej klasy posiada informację o stanie świateł

RootDisplay - klasa odpowiedzialna za GUI

Road - obiekt tej klasy obrazuje w sposób graficzny sytuacje na planszy(skrzyżowaniu)

Slider - pomocnicza klasa do GUI 

Label - pomocnicza klasa do GUI 


Do wykonania GUI została użyta biblioteka "Swing"

## Docs[ENG]
For simulation, the user can set the following simulation parameters:

simulation time - simulation display speed

number of cars - the number of cars that will have to pass through the intersection in one wave

number of waves - the number of waves, in each wave there are as many cars as set as "number of cars"

wave time - a new wave of cars appears on the plateau when the numberStep % timeFal == 0 

light time - lights change when numberStep % timeLights == 0

min/max reaction time - the interval from which the reaction time is drawn for each object of class "Car"

In the model used, the initial position of each object of class "Car" is randomized
using the nextInt method from the "Random" class. Each object of class "Car" has a 25% chance to start from each direction.


The direction of movement of an object of class "Car" is randomized using the nextInt method from class "Random".

50% chance to drive straight - blue car

25% chance to drive right - yellow car

25% chance of driving left - pink car


Traffic lights:

state 1 - green light right and straight for the X axis

state 2 - green light left for the X axis

state 3 - green light right and straight for the Y axis

state 4 - green light left for the Y axis



classes:
App - Main class in which we start the program

CrossRoadModel - class responsible for the entire simulation model. An object of this class has all the information
about the model (position of cars, status of lights, etc.).

PixelsPositionObject - an abstract class that is responsible for the position of objects on the screen

Car - extends PixelsPositionObject - objects of this class have information about the initial position, direction of movement, reaction time

TrafficLight - extends PixelsPositionObject - an object of this class has information about the state of the lights

RootDisplay - the class responsible for the GUI

Road - an object of this class graphically illustrates the situation on the board(intersection)

Slider - auxiliary class for GUI 

Label - auxiliary class for the GUI 


For the implementation of the GUI the library "Swing" was used.

## Class diagram
![image](https://github.com/MateuszZalobnik/Traffic-Agent-Based-Simulation/assets/77732992/96c04c2f-4f97-4bd9-b0a9-9d6163eaf4e2)

## Object diagram
![image](https://github.com/MateuszZalobnik/Traffic-Agent-Based-Simulation/assets/77732992/179f2f5c-4aaa-4fe3-ab59-9d331f9f5c86)

