package fr.istic.taa.jaxrs.domain;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class JpaTest {


	private EntityManager manager;

	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
			EntityManager manager = EntityManagerHelper.getEntityManager();

		JpaTest test = new JpaTest(manager);

		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		try {


                // 1. Création d'un ADMINISTRATEUR (Hérite de Person)
                Admin admin = new Admin();
                admin.setFirstname("Marc");
                admin.setLastname("Admin");
                admin.setEmail("admin@festival.com");
                admin.setPhone("0600000001");
                manager.persist(admin);

                // 2. Création d'un ORGANISATEUR (Hérite de Person)
                Organizer organizer = new Organizer();
                organizer.setFirstname("Julie");
                organizer.setLastname("Events");
                organizer.setEmail("contact@prod.com");
                organizer.setPhone("0600000002");
                manager.persist(organizer);

                // 3. Création d'un ARTISTE (Hérite de Person)
                Artist artist = new Artist();
                artist.setFirstname("Stromae");
                artist.setLastname("Paul"); // Nom de famille fictif pour le test
                artist.setEmail("artiste@music.be");
                manager.persist(artist);

                // 4. Création d'un UTILISATEUR / CLIENT (Hérite de Person)
                User user = new User();
                user.setFirstname("Jean");
                user.setLastname("Dupont");
                user.setEmail("jean.dupont@email.com");
                manager.persist(user);

                // 5. Création d'un CONCERT (Lié à l'Artiste)
                Concert concert = new Concert();
                concert.setName("Tournée Multitude");
                concert.setDate(new java.util.Date());
                concert.setStartTime(20); // 20h
                concert.setEndTime(23);   // 23h
                concert.setPrice(75L);
                concert.setMusicalGenre("Pop");
                concert.setLocation("Bercy, Paris");
                concert.setPlaceNumber(15000);
                concert.setPopularity(1);
                concert.setIsCanceled(false);
                concert.setIsDeleted(false);
                concert.setIsValidated(true);
                // On lie le concert à l'artiste créé plus haut
                // concert.setArtist(artist);
                manager.persist(concert);

                // 6. Création d'un TICKET (Lié au Concert et à l'Utilisateur)
                Ticket ticket = new Ticket();
                ticket.setPrice(75L);
                ticket.setDate(new java.util.Date());
                ticket.setCanceled(false);
                ticket.setRefunded(false);
                // On lie le ticket au concert et à l'utilisateur
                // ticket.setConcert(concert);
                // ticket.setUser(user);
                manager.persist(ticket);

                // 7. Création d'une NOTIFICATION (Généralement liée à une Personne)
                Notification notification = new Notification();
                notification.setContent("Votre commande pour le concert " + concert.getName() + " est validée.");
                notification.setType("INFO_ACHAT");
                // Si votre classe Notification a une relation vers Person :
                // notification.setPerson(user);
                manager.persist(notification);

                System.out.println("Toutes les entités (sauf Dept/Emp) ont été persistées avec succès.");


		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();

			
   	 manager.close();
		EntityManagerHelper.closeEntityManagerFactory();
		System.out.println(".. done");
	}




}
