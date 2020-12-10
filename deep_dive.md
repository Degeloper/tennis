# Clean Object Calesthenics

# 5 Prinzipien

- Messaging
- Living Objects
- 4 Rules of Simple Design
- IOSP
- Software Dimension

## Messaging

In der OO-Programmierung ging es dem Erfinder Alan Kay primär nicht darum, die Welt möglichst realitätsnahe Klassen zu unterteilen.
Viel wichtiger ist, wie Instanzen von Klassen (Objekte) mit den anderen Objekten interagieren sollen.

In der OO-Entwicklung z.B. in Java kommt es häufig vor, dass zwar die Domäne sauber in Klassen ausgelagert wird, 
diese aber in Getter Datentöpfe ausufern. Dies ist oft ein Zeichen für schlechte Encapsulation. Die Objekte 
sind dann seelenlose Datenträger oft mit Beschreibungen wie DTOs oder POJOs gerechtfertigt.
Sie enthalten kein Behaviour und tragen die innere Struktur der Klasse nach Aussen, indem sie diese mit Getter Klassen
beleuchten (siehe auch "Living Objects").

[TODO: Beispiel]

Ein hilfreiches Konzept um solche Klassen zu vermeiden ist "Tell don't ask".
Hier get es darum sich vorzustellen, wie die Objekte miteinander interagieren sollen.
Die öffentlichen Methoden einer Klasse sollen anderen Objekten aufzeigen, wie damit interagiert werden kann.
Wenn z.B. ObjektA eine Methode von ObjektB aufruft schickt er im eine Nachricht (Message). 
Die Nachricht soll dem Empfänger möglichst konkret sagen, was zu tun ist (Tell). 
Die Nachricht, die an den den Aufrufer zurückgeschickt wird, soll im nicht zum Nachfragen bewegen (Don't Ask).
Oftmals ist dies schwierig umzusetzen. Jedoch kann ein sich Häuffen von solchen "getDataA()" und "isReady()" Methoden als 
CodeSmell und ungünstiges Klassendesign verstanden werden.

## Living Objects

Wie bereits bei Messaging angesprochen ist es schlechtes Objektorientiertes Design, wenn die Objekte 
kein Behaviour und somit kein "Leben" haben. Das Prinzip Living Objects bemüht sich darum, den seelenlosen Klassen
leben einzuhauchen und den Sprung von strukturierter hin zum objektorientierter Entwicklung zu machen.
Es gibt sicherlich Parallelen zum "Messaging" Prinzip aber was bringt einem gutes Messaging, wenn 
alle Klassenvariablen und Methoden statisch sind?

Für OO Neulinge sind statische Methoden und Variablen allzu oft verwendete OOD Killer, 
weil sie in diesem Fall keine Attribute nutzen.
Um einem Objekt mithilfe von OOD leben einzuhauchen braucht es Daten um einen Zustand haben zu können. 
Diese Daten sollen intern in Form von Instanzvariablen gespeichert werden. Dadurch entsteht eine interne Struktur, 
welche es gegen Aussen so gut wie möglich zu verbergen gilt (Information Hiding). 
Die bereits erwähnten Getter-Methoden machen dabei das pure Gegenteil. Sie verhindern zwar den direkt Zugriff der Daten, 
jedoch geht es bei Information Hiding weniger um das Verstecken von Daten als vielmehr um das Verstecken von 
Design Entscheide (Strukturen) innerhalb einer Klasse.

## 4 Rules of Simple Design
Das Design einer Software kann dann als Simple verstanden werden, wenn der Source-Code folgende 4 Regeln einhält:

1. Tests sind grün
2. Duplikationen sind minimiert
3. Absichten werden offenbart
4. Anzahl Klassen/Module/Packages sind minimiert

Von diesen 4 Regeln gibt es aber 2 Regeln, welchen besondere Beachtung geschenkt werden sollte. Gemeint sind die Regeln 2. und 3.
In der Theorie führt nämlich die Erfüllung von "Duplikationen sind minimiert" und "Absichten werden offenbart" automatisch
zu grünen Tests und minimierte Anzahl Klassen. 
Theoretisch könnte simples Design auch ohne das Schreiben eines einzelnen Tests erreicht werden.
Durch Methoden wie Test Driven Design (TDD) kommt es ohnehin automatisch zu UnitTests. 
Die Tests können auch als Beweis dafür verstanden werden, dass der Code gut strukturiert, einfach Testbar und somit
simple designt ist. 

## IOSP

Die Abkürzung IOSP steht für Integration Operation Segregation Principle.
Wie der Name schon vermuten lässt, ist das Ziel einzelne Units in 2 Kategorien zu separieren:

- Operational Units
  - Enthält Business Logic
  - Ist idempotent
  - darf keine keine Integration Units aufrufen
  - Ist immer der Leaf Node
  - Bekommt die Daten Injected
- Integration Units
  - Enthält Daten
  - Gibt die Daten an Operational Units weiter
  - Operational Units auf
  - kann andere Integration Units aufrufen

[TODO: Beispiel]


## Software Dimension

Dimensionen werden horizontal und vertikal gemessen.
Horizontal wird die Einrückungstiefe (Konzept von Adam Thornhill) betrachtet und vertikal die Anzahl der Zeilen. 

# Design-Mindset

## Software Aesthetics
Das Konzept der Symmetrie wird beginnend von kleinsten Einheiten genutzt

- Anzahl der Tests ausgleichend zur Anzahl der produktiven Methoden?
- Hält sich im Umfeld der Funktionen, die vertikale mit der horizontalen Dimension? 
- Ist die Größe der vorhandenen Klassen ähnlich oder gibt es Gott-Klassen?
- Wann muss ich dieses Design etwas aufweichen (Asymmetrie) um ein besseres Ergebnis zu erreichen? 
- Wie ist die Balance der Struktur Organisation Informations-Flow?
