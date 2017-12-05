# Cities REST Backend

Backend REST fourni pour la réalisation d'une application cliente JavaScript

## Configuration pour le lancement de Tomcat

- Avec le plugin Maven Apache Tomcat : mettre à jour la propriété système *referentiel.db.jdbc.url* dans la configuration du plugin tomcat8-maven-plugin (*pom.xml*) pour pointer sur l'emplacement dans lequel se trouve la base fichier (adapter surtout le répertoire parent de *db/data/referentiel*)
- Dans Eclipse : dans la configuration d'exécution (Run as ...), onglet *Arguments*, ajouter *-Dreferentiel.db.jdbc.url=jdbc:h2:file:~/work/pe-promo2/cities-rest-backend/db/data/referentiel* en adaptant l'emplacement de la base fichier (adapter surtout le répertoire parent de *db/data/referentiel*)

## Prérequis au lancement en ligne de commande

La variable d'environnement *JAVA_HOME* doit être définie et pointer vers le répertoire d'un JDK 7+ (ex. JAVA\_HOME=C:\Outils\JDK1.7.0)

## Lancement en ligne de commande

- Avec Maven si installé : *mvn clean tomcat8:run*
- Avec le Wrapper Maven compris dans le projet : *./mvnw clean tomcat8:run* (Unix) ou *./mvnw.cmd clean tomcat8:run* (Windows)

## Accès direct à la base de données 

Il suffit de lancer lz script *h2.sh* ou *h2.bat* situé dans le répertoire*db*, lequel va engendrer l'ouverture d'une console H2 dans un navigateur.
Il restera à adapter l'URL (JBDC) de connexion définissant l'emplacement de la base, puis de valider ppur accéder à l'explorateur H2 et jouer des requêtes SQL.

## Exemples d'URLs en GET

- [Liste des 10 premières villes](http://localhost:8080/cities-rest-backend/rest/villes?from=0&limit=10)
- [Détails d'une ville](http://localhost:8080/cities-rest-backend/rest/villes/21578)
- [Liste des 10 premiers monuments](http://localhost:8080/cities-rest-backend/rest/monuments?from=0&limit=10)
- [Liste des monuments d'une ville](http://localhost:8080/cities-rest-backend/rest/villes/21578/monuments)
- [Détails d'un monument](http://localhost:8080/cities-rest-backend/rest/monuments/1)
