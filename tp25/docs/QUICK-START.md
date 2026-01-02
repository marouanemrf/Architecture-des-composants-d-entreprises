# Guide de Démarrage Rapide

## Installation

```bash
# Cloner et installer
cd clientService && mvn clean package
cd ../gatewayService && mvn clean package
cd ../voitureService && mvn clean package
```

## Démarrage

```bash
docker-compose up -d
```

## Vérification

- Gateway: http://localhost:8888
- Client: http://localhost:8088
- Voiture: http://localhost:8082
