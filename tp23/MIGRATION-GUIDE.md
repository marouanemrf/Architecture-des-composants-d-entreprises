# Guide de Migration Eureka vers Consul

## Checklist de Migration

### Phase 1: Préparation

- [ ] Cloner le projet existant
- [ ] Vérifier le bon fonctionnement avec Eureka
- [ ] Installer Consul localement
- [ ] Tester Consul en mode développement

### Phase 2: Migration du Code

Pour chaque service (Client, Gateway, Voiture):

#### Dépendances (pom.xml)

- [ ] Supprimer `spring-cloud-starter-netflix-eureka-client`
- [ ] Ajouter `spring-cloud-starter-consul-discovery`
- [ ] Compiler: `mvn clean package -DskipTests`

#### Configuration (application.yml)

- [ ] Supprimer les propriétés `eureka.*`
- [ ] Configurer `spring.cloud.consul.*`
- [ ] Définir le nom du service
- [ ] Configurer les health checks

#### Code Java

- [ ] Vérifier `@EnableDiscoveryClient`
- [ ] Supprimer `@EnableEurekaClient`
- [ ] Valider les appels inter-services

### Phase 3: Tests

- [ ] Démarrer Consul
- [ ] Lancer les services
- [ ] Vérifier l'enregistrement dans Consul UI
- [ ] Tester la communication entre services

### Phase 4: Conteneurisation

- [ ] Créer les Dockerfile
- [ ] Configurer docker-compose.yml
- [ ] Construire les images
- [ ] Déployer et tester

## Commandes Utiles

### Consul

```bash
consul agent -dev
consul catalog services
```

### Docker

```bash
docker-compose build
docker-compose up -d
docker-compose logs -f
docker-compose down
```

### Maven

```bash
mvn clean package -DskipTests
mvn spring-boot:run
```

## Dépannage

### Service non visible dans Consul

1. Vérifier que Consul est actif
2. Contrôler la configuration host/port
3. Vérifier la dépendance consul-discovery

### Health Check en échec

1. Exposer les endpoints actuator
2. Ajuster l'intervalle de vérification
3. Consulter les logs

### Erreur de connexion entre services

1. Vérifier le réseau Docker
2. Utiliser les noms de services
3. Configurer `prefer-ip-address: true`

## Comparaison Eureka vs Consul

| Aspect | Eureka | Consul |
|--------|--------|--------|
| Dépendance | eureka-client | consul-discovery |
| Port UI | 8761 | 8500 |
| Health Check | HTTP | HTTP, TCP, TTL |
