# Mini-Projet Systèmes Répartis

Ce projet implémente trois services répartis utilisant différentes technologies de communication :

## 1. Service de Messagerie avec gRPC

### Description
Un service de messagerie basé sur gRPC permettant l'échange de messages entre clients.

### Structure
- **Fichier .proto** : Définition des services et messages (configuré pour multi-langage)
- **Serveur** : Implémentation du service gRPC
- **Client** : Client pour interagir avec le service
- **Classe d'implémentation** : Contient la logique métier des méthodes

### Technologies
- gRPC
- Protobuf

## 2. Service de Chat avec Sockets

### Description
Un système de chat en temps réel utilisant la programmation socket.

### Structure
- **Serveur de chat** : Gère les connexions et diffuse les messages
- **Client de chat** : Interface utilisateur pour envoyer/recevoir des messages

### Technologies
- Sockets TCP
- Threads pour gérer les connexions simultanées

## 3. Gestion de Liste de Tâches avec RMI

### Description
Un système distribué pour gérer une liste partagée de tâches.

### Structure
- **Interface RMI** : Définit les méthodes disponibles
- **Serveur RMI** : Implémente les méthodes et gère les données
- **Client RMI** : Interface utilisateur pour interagir avec la liste

### Technologies
- Java RMI (Remote Method Invocation)
- Registry RMI

## Prérequis

- Pour gRPC :
  - Protobuf compiler
  - Bibliothèques gRPC pour votre langage

- Pour Sockets :
  - Java/Python/C++ (selon implémentation)

- Pour RMI :
  - JDK (Java Development Kit)
  - RMI Registry
