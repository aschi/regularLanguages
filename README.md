Semesterarbeit: Evaluation von verschiedenen Ansätzen für evolutionäre Algorithmen
==================================================================================
Diese Semesterarbeit beschäftigt sich mit Grundlagen der Automatentheorie, Grundlagen von evolutionären Algorithmen und schliesslich einem konkreten praktischen Anwendungsfall dieser theoretischen Gebiete. Es geht um das finden von endlichen Automaten zu gegebenen regulären Ausdrücken mithilfe von evolutionären Algorithmen. Dabei wird das Verhalten von Algorithmen mit unterschiedlichen relativen Fitnessfunktionen betrachtet. Die Qualität der Resultate und der dazu benötigte Rechenaufwand wird verglichen um so die Stärken und Schwächen der verschiedenen Herangehensweisen zu aufzuzeigen.

##Installation:
1. Dateien herunterladen: git clone git@github.com:aschi/regularLanguages.git
2. Installieren mvn install
3. Gegebenenfalls Eclipse Projekt erstellen: mvn eclipse:eclipse

##Verzeichnisstruktur
- *doc*: Dokumentation. Enthält unter anderem Thesis (semesterarbeit.pdf) und Klassendiagramm (ClassDiagram.pdf)
- *doc/content*: Latex Quelltext des Inhalts der Thesis
- *doc/images*: Verwendete Bilder in der Thesis
- *doc/statistics*: Logfiles der Algorithmen, Scripts zur Auswertung und grafische Auswertungen
- *src/main*: Sourcecode der Anwendung
- *src/test*: Unit Tests