#!/usr/bin/env bash
set -euo pipefail

# Configuration des paramètres
BOOK_ID="${1:-1}"
REQUESTS="${2:-50}"

# Configuration des ports des instances
PORTS=(8081 8083 8084)

echo "== Test de charge =="
echo "Ressource=$BOOK_ID Requêtes=$REQUESTS"
echo "Ports=${PORTS[*]}"
echo

# Création du répertoire temporaire
tmpdir="$(mktemp -d)"
success_file="$tmpdir/success.txt"
conflict_file="$tmpdir/conflict.txt"
other_file="$tmpdir/other.txt"

touch "$success_file" "$conflict_file" "$other_file"

# Fonction d'exécution d'une requête
run_one() {
  local i="$1"
  local port="${PORTS[$((i % 3))]}"
  local url="http://localhost:${port}/api/books/${BOOK_ID}/borrow"

  local body_file="$tmpdir/body_$i.json"
  local status
  status="$(curl -s -o "$body_file" -w "%{http_code}" -X POST "$url" || true)"

  if [[ "$status" == "200" ]]; then
    echo "$port $status $(cat "$body_file")" >> "$success_file"
  elif [[ "$status" == "409" ]]; then
    echo "$port $status $(cat "$body_file")" >> "$conflict_file"
  else
    echo "$port $status $(cat "$body_file" 2>/dev/null || echo '')" >> "$other_file"
  fi
}

# Exécution parallèle des requêtes
pids=()
for i in $(seq 1 "$REQUESTS"); do
  run_one "$i" &
  pids+=($!)
done

# Attente de la fin de toutes les requêtes
for p in "${pids[@]}"; do
  wait "$p"
done

# Affichage des résultats
echo "== Résultats =="
echo "Succès (200):   $(wc -l < "$success_file")"
echo "Conflit (409):  $(wc -l < "$conflict_file")"
echo "Autres:         $(wc -l < "$other_file")"
echo
echo "Répertoire des résultats: $tmpdir"
