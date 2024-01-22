Aplikacja sprawdza, czy przesyłany plik nie jest pusty, nie przekracza dopuszczalnego limitu 5 MB oraz czy posiada wszystkie wymagane atrybuty i czy nie są one puste. 
Dodatkowo, czy plik zawiera poprawny format, akceptując jedynie pliki o rozszerzeniach '.json' i '.xml'.
Po dodaniu listy, wyświetlane jest podsumowanie wraz z guzikiem umożliwiającym bezpośrednie przejście do strony wyświetlającej użytkowników.

Strona wyświetlająca użytkowników umożliwia sortowanie po polach 'name', 'surname' oraz 'login'. 
Posiada również jedno pole do wyszukiwania użytkowników po 'name', 'surname' oraz 'login'. 
Do nazwiska dodawany jest kod MD5 z imienia, dane w bazie danych pozostają niezmienione.

Strony są ostylowane za pomocą CSS.

Technologie używane w projekcie:
Spring
Java 17
HTML5
JDBC
MySQL
JavaScript
Tomcat
Bootstrap
Hibernate
