# Tennis Game
Im Rahmen des Foundation Level Zertifikats der CSS Software Academy wurde eine selbst gewählte Programmieraufgabe 
umgesetzt.
Bei der Aufgabe handelt es sich um ein Tennis Game, welches folgende Coding Kata als Vorlage nimmt:
https://codingdojo.org/kata/Tennis/

## Features
- Browser Applikation
- Berechnung des Balles in Echtzeit
- Steuern des Rackets via Pfeiltasten
- 2 Spieler Multiplayer
- Lobbyfunktion in welcher 2 Spieler joinen können
- Anzeigen von eigenem und gegnerischem Racket (Spielfigur) in Echtzeit
- Vertikale Steuerung des Rackets via Pfeiltasten

## Spielregeln
- Jeder Spieler kann folgender Score haben: **0, 15, 30, 40**
- Der Spieler, welcher nach dem **Score** 40 ein Punkt macht, gewinnt
- Wenn beide Spieler den Score 40 erreichen, wechselt das Spiel in den Status **Deuce**
  - Im Status Deuce, erreicht der Gewinner des Balls den Score **40A** und hat **Matchball**
  - Gewinnt der Spieler mit Score 40A, gewinnt er das Spiel
  - Gewinnt der Gegenspieler (mit Score 40), wechselt das Spiel wieder in Status **Deuce**

## Steuerung
- Pfeiltaste ↑: Racket nach oben verschieben
- Pfeiltaste ↓: Racket nach unten verschieben

## Technologien
- Java SDK 15.0.1
- Helidon 2.0.2 (Webserver/Websockets)
- JavaScript/CSS/HTML (Frontend)

## Aktueller Stand

### Message Data Flow


### Mit TDD entwickelte Packages
Package: ch.css.coaching.game.score

### Offene Features
- Automatisches Wiederverbinden nach Verbindungsunterbruch
- Erweiterung von Scoring mit Spielen/Sätzen
- Variation des Schwierigkeitsgrades (dynamische Beschleunigung des Balles)- 
- Vergrösserung des Spielfelds
- Responsive GUI (Handy/Tablet)


## Screenshots
### Warten auf Spieler 2
![](images/Waiting.png)

### Spielen nach Eintritt von Spieler 2
![](images/Playing.png)

### Bildschirm von Gewinner (Spieler 2)
![](images/Winning.png)

### Bildschirm von Verlierer (Spieler 1) 
![](images/Losing.png)

## Mockup für Design
![](images/Mockup.jpg)





