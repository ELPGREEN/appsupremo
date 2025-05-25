$sourceDir = "C:\Users\elpgr\Tribunal Popular\Tribunalpopular\images"
$destDir = "C:\Users\elpgr\AndroidStudioProjects\Mysupremo\app\src\main\res\drawable"

# Criar o diretório de destino se não existir
if (-not (Test-Path $sourceDir)) {
    Write-Host "Erro: Diretório de origem $sourceDir não existe!" -ForegroundColor Red
    exit
}
if (-not (Test-Path $destDir)) {
    New-Item -ItemType Directory -Path $destDir -Force
}

# Lista de arquivos no diretório de origem
$files = Get-ChildItem -Path $sourceDir -File

if ($files.Count -eq 0) {
    Write-Host "Erro: Nenhum arquivo encontrado em $sourceDir!" -ForegroundColor Red
    exit
}

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

    # Renomeações específicas para simplificar
    $newName = switch ($newName) {
        "imagemdeumamesadenegociações_jpg" { "mesa_negociacoes.jpg" }
        "uma_sala_de_tribunal_com_um_tribunal_e_um_relogio_na_parede_836919_1837_jpg" { "sala_tribunal.jpg" }
        "law_pictures_tna0v1l421r3uglj_jpg" { "law_pictures.jpg" }
        "cyber_security_background_dz8qpz66ryu26ndb_webp" { "cyber_security_background.webp" }
        "ponte_ciclopedonale_e_condotto_idrico_1_jpg" { "ponte_ciclopedonale.jpg" }
        "diplomacia_02_embarg_jpg" { "diplomacia_02_embargo.jpg" }
        "tribunal_com_relogio_png_png" { "tribunal_com_relogio.png" }
        "favicon_png_png" { "favicon.png" }
        default { $newName }
    }

    # Caminho completo do destino
    $destPath = Join-Path $destDir $newName

    # Copiar o arquivo
    try {
        Copy-Item -Path $file.FullName -Destination $destPath -Force
        Write-Host "Copiado: $originalName para $newName" -ForegroundColor Green
    } catch {
        Write-Host "Erro ao copiar $originalName - Erro: $_" -ForegroundColor Red
    }
}

Write-Host "`nTransferência concluída!" -ForegroundColor Cyan