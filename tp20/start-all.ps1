# Script PowerShell pour lancer tous les microservices
Write-Host "=========================================" -ForegroundColor Cyan
Write-Host "Démarrage des microservices Spring Boot" -ForegroundColor Cyan
Write-Host "=========================================" -ForegroundColor Cyan
Write-Host ""

# Fonction pour attendre qu'un service soit prêt
function Wait-ForService {
    param([string]$ServiceName, [int]$Port)
    Write-Host "Attente du démarrage de $ServiceName sur le port $Port..." -ForegroundColor Yellow
    $maxAttempts = 60
    $attempt = 0
    while ($attempt -lt $maxAttempts) {
        try {
            $response = Invoke-WebRequest -Uri "http://localhost:$Port" -TimeoutSec 2 -ErrorAction SilentlyContinue
            if ($response.StatusCode -eq 200 -or $response.StatusCode -eq 404) {
                Write-Host "$ServiceName est prêt!" -ForegroundColor Green
                return $true
            }
        } catch {
            Start-Sleep -Seconds 2
            $attempt++
        }
    }
    Write-Host "$ServiceName n'a pas démarré dans le délai imparti" -ForegroundColor Red
    return $false
}

# Vérifier si Maven est installé
$mvnCmd = Get-Command mvn -ErrorAction SilentlyContinue
if (-not $mvnCmd) {
    Write-Host "Maven n'est pas trouvé dans le PATH. Utilisation de mvnw..." -ForegroundColor Yellow
    $useMvnw = $true
} else {
    $useMvnw = $false
}

# Obtenir le répertoire du script
$scriptDir = Split-Path -Parent $MyInvocation.MyCommand.Path
$originalLocation = Get-Location

# 1. Démarrer Eureka Server
Write-Host "[1/4] Démarrage d'Eureka Server..." -ForegroundColor Cyan
$eurekaPath = Join-Path $scriptDir "eureka-server"
if ($useMvnw) {
    Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd '$eurekaPath'; .\mvnw.cmd spring-boot:run" -WindowStyle Normal
} else {
    Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd '$eurekaPath'; mvn spring-boot:run" -WindowStyle Normal
}
Start-Sleep -Seconds 10

# Attendre qu'Eureka soit prêt
Wait-ForService "Eureka Server" 8761
Start-Sleep -Seconds 5

# 2. Démarrer Client Service
Write-Host "[2/4] Démarrage du Client Service..." -ForegroundColor Cyan
$clientPath = Join-Path $scriptDir "client"
if ($useMvnw) {
    Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd '$clientPath'; .\mvnw.cmd spring-boot:run" -WindowStyle Normal
} else {
    Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd '$clientPath'; mvn spring-boot:run" -WindowStyle Normal
}
Start-Sleep -Seconds 5

# 3. Démarrer Car Service
Write-Host "[3/4] Démarrage du Car Service..." -ForegroundColor Cyan
$carPath = Join-Path $scriptDir "car"
Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd '$carPath'; mvn spring-boot:run" -WindowStyle Normal
Start-Sleep -Seconds 5

# 4. Démarrer Gateway
Write-Host "[4/4] Démarrage de la Gateway..." -ForegroundColor Cyan
$gatewayPath = Join-Path $scriptDir "gateway"
if ($useMvnw) {
    Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd '$gatewayPath'; .\mvnw.cmd spring-boot:run" -WindowStyle Normal
} else {
    Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd '$gatewayPath'; mvn spring-boot:run" -WindowStyle Normal
}

Write-Host ""
Write-Host "=========================================" -ForegroundColor Green
Write-Host "Tous les services sont en cours de démarrage!" -ForegroundColor Green
Write-Host "=========================================" -ForegroundColor Green
Write-Host ""
Write-Host "Services disponibles:" -ForegroundColor Yellow
Write-Host "  - Eureka Server: http://localhost:8761" -ForegroundColor White
Write-Host "  - Client Service: http://localhost:8081" -ForegroundColor White
Write-Host "  - Car Service: http://localhost:8082" -ForegroundColor White
Write-Host "  - Gateway: http://localhost:8888" -ForegroundColor White
Write-Host ""
Write-Host "Appuyez sur Ctrl+C pour arrêter tous les services" -ForegroundColor Yellow

