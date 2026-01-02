# Guide de Déploiement

## Prérequis

- Docker et Docker Compose
- Java 17+
- Node.js 18+ (pour le frontend)

## Étapes de déploiement

1. Construction des images Docker
```bash
docker-compose build
```

2. Démarrage des services
```bash
docker-compose up -d
```

3. Vérification
```bash
docker-compose ps
```
