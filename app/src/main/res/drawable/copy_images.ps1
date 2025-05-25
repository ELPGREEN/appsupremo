$sourceDir = "C:\Users\elpgr\Tribunal Popular\Tribunalpopular\images"
$destDir = "C:\Users\elpgr\Tribunal Popular\Tribunalpopular\app\src\main\res\drawable"

# Criar o diretório de destino se não existir
if (-not (Test-Path $destDir)) {
    New-Item -ItemType Directory -Path $destDir -Force
}

# Lista de arquivos no diretório de origem
$files = Get-ChildItem -Path $sourceDir -File

foreach ($file in $files) {
    # Obter o nome do arquivo
    $originalName = $file.Name
    
    # Converter para letras minúsculas, substituir espaços e caracteres especiais
    $newName = $originalName.ToLower()
    $newName = $newName -replace '[^a-z0-9._]', '_'  # Substitui caracteres inválidos por '_'
    $newName = $newName -replace '_+', '_'            # Remove múltiplos '_' consecutivos
    $newName = $newName -replace '\.png\.png$', '.png' # Corrige extensões duplicadas
    $newName = $newName -replace '\.jpg\.jpg$', '.jpg'
    $newName = $newName -replace '\.jpeg\.jpeg$', '.jpeg'
    $newName = $newName -replace '\.webp\.webp$', '.webp'

    # Caminho completo do destino
    $destPath = Join-Path $destDir $newName

    # Copiar o arquivo
    try {
        Copy-Item -Path $file.FullName -Destination $destPath -Force
        Write-Host "Copiado: $originalName para $newName"
    } catch {
        Write-Host "Erro ao copiar $originalName - Erro: $_"
    }
}

Write-Host "Transferência concluída!"