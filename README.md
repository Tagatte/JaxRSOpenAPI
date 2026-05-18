## Intro et technologie

Bienvenue sur le dépôt du back-end de Stagely, une application de gestion et d'achat de billets de concerts développée en Java avec JAX-RS (Jersey), JPA (Hibernate) et HSQLDB.


## Installation et Démarrage du Projet

### Prérequis

Java JDK 17 installé.

Apache Maven 3.6 ou supérieur installé.

### Démarrage

Génération et injection des données de test Exécutez la classe de test JpaTest pour initialiser la base de données locale HSQLDB avec les données factices de démonstration :

mvn exec:java -Dexec.mainClass="fr.istic.taa.jaxrs.domain.JpaTest"


Lancement du serveur REST Lancez le serveur d'applications intégré à l'aide du plugin Maven Jetty :

mvn jetty:run


Accès à l'application * API REST : accessible par défaut à l'adresse http://localhost:8080/api

Documentation Swagger (OpenAPI) : disponible à l'adresse http://localhost:8080/swagger ou via le flux JSON http://localhost:8080/api/openapi.json

# Task Open API Integration 

Points d'Accès de l'API REST (Endpoints)

L'API est exposée sous le préfixe /api. Voici une synthèse des routes principales :

## Gestion des Billets (/tickets)

GET /tickets : Liste globale des tickets ou filtrage par utilisateur via paramètre d'e-mail (?email=client@test.com).

GET /tickets/{id} : Récupération des informations d'un billet spécifique.

POST /tickets : Action métier permettant l'achat de places de concert via TicketCreateDto.

PUT /tickets/{id}/transfer : Action métier initiant le transfert sécurisé d'un billet vers un autre e-mail via TicketTransferDto.

PUT /tickets/{id}/cancel : Demande d'annulation d'un billet.

PUT /tickets/{id}/refund : Déclenchement du remboursement d'un billet.

## Gestion des Concerts (/concerts)

GET /concerts : Liste de tous les concerts.

GET /concerts/search?q={query} : Recherche dynamique (nom, artiste, genre musical, lieu).

GET /concerts/{id} : Fiche détaillée d'un concert spécifique.

POST /concerts : Création d'un événement (via ConcertCreateDto).

PUT /concerts/{id} : Mise à jour des informations d'un concert.

DELETE /concerts/{id} : Suppression d'un concert du catalogue.

GET /concerts/sortByPopularity : Tri des événements du plus populaire au moins populaire.

GET /concerts/sortByPrice : Tri des concerts par prix croissant.

GET /concerts/sortByDate : Tri des concerts par ordre chronologique.

## Statistiques Administratives (/stats)

GET /stats : Calcule et retourne un objet consolidé AdminStats contenant les indicateurs clés de performance (KPI) de la plateforme (nombre d'utilisateurs inscrits, total des concerts en ligne, volume de tickets vendus, etc.).

## Profils Utilisateurs (CRUD de base)

/users (Utilisateurs finaux)

/artists (Artistes et groupes)

/organizers (Organisateurs d'événements)

/admins (Gestionnaires de la plateforme)
```xml
		<dependency>
			<groupId>io.swagger.core.v3</groupId>
			<artifactId>swagger-jaxrs2-jakarta</artifactId>
			<version>2.2.15</version>
		</dependency>

		<dependency>
			<groupId>io.swagger.core.v3</groupId>
			<artifactId>swagger-jaxrs2-servlet-initializer-v2</artifactId>
			<version>2.2.15</version>
		</dependency>
```

Next you have to add OpenAPI Resource to your application

Your application could be something like that. 

```java
@ApplicationPath("/")
public class RestApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		final Set<Class<?>> resources = new HashSet<>();


		// SWAGGER endpoints
		resources.add(OpenApiResource.class);

        //Your own resources. 
        resources.add(PersonResource.class);
....
		return resources;
	}
}
```

Next start your server, you must have your api description available at [http://localhost:8080/openapi.json](http://localhost:8080/openapi.json)

### Integrate Swagger UI. 

Next we have to integrate Swagger UI. We will first download it.
https://github.com/swagger-api/swagger-ui

Copy dist folder content in src/main/webapp/swagger in your project. 

Edit index.html file to automatically load your openapi.json file. 

At the end of the index.html, your must have something like that.

```js
   // Build a system
      const ui = SwaggerUIBundle({
        url: "http://localhost:8080/openapi.json",
        dom_id: '#swagger-ui',
        
        ...
```

Next add a new resources to create a simple http server when your try to access to http://localhost:8080/api/.

This new resources can be developped as follows

```java
package app.web.rest;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/api")
public class SwaggerResource {

    private static final Logger logger = Logger.getLogger(SwaggerResource.class.getName());

    @GET
    public byte[] Get1() {
        try {
            return Files.readAllBytes(FileSystems.getDefault().getPath("src/main/webapp/swagger/index.html"));
        } catch (IOException e) {
            return null;
        }
    }

    @GET
    @Path("{path:.*}")
    public byte[] Get(@PathParam("path") String path) {
        try {
            return Files.readAllBytes(FileSystems.getDefault().getPath("src/main/webapp/swagger/"+path));
        } catch (IOException e) {
            return null;
        }
    }

}
```

Add this new resources in your application

```java
@ApplicationPath("/")
public class RestApplication extends Application {


	@Override
	public Set<Class<?>> getClasses() {
		final Set<Class<?>> resources = new HashSet<>();


		// SWAGGER endpoints
		resources.add(OpenApiResource.class);
		resources.add(PersonResource.class);
        //NEW LINE TO ADD
		resources.add(SwaggerResource.class);

		return resources;
	}
}
```

Restart your server and access to http://localhost:8080/api/, you should access to a swagger ui instance that provides documentation on your api. 

You can follow this guide to show how you can specialise the documentation through annotations.

https://github.com/swagger-api/swagger-samples/blob/2.0/java/java-resteasy-appclasses/src/main/java/io/swagger/sample/resource/PetResource.java