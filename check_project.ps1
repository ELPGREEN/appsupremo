$projectDir = "C:\Users\elpgr\AndroidStudioProjects\Mysupremo"

# Função para verificar arquivos em um diretório
function Check-Files {
    param (
        [string]$Path,
        [string[]]$ExpectedFiles,
        [string]$Section
    )
    Write-Host "`nVerificando $Section em $Path"
    if (-not (Test-Path $Path)) {
        Write-Host "Diretório não encontrado!" -ForegroundColor Red
        return
    }
    $actualFiles = Get-ChildItem -Path $Path -File | Select-Object -ExpandProperty Name
    foreach ($file in $ExpectedFiles) {
        if ($actualFiles -contains $file) {
            Write-Host "OK: $file" -ForegroundColor Green
        } else {
            Write-Host "Faltando: $file" -ForegroundColor Red
        }
    }
}

# Verificar pastas principais
$mainFiles = @("build.gradle.kts", "gradle.properties", "settings.gradle.kts")
Check-Files -Path $projectDir -ExpectedFiles $mainFiles -Section "Diretório Raiz"

# Verificar app/src/main
$manifestFiles = @("AndroidManifest.xml")
Check-Files -Path "$projectDir\app\src\main" -ExpectedFiles $manifestFiles -Section "app/src/main"

# Verificar res/drawable
$drawableFiles = @(
    "balanca_da_justica.jpg",
    "caso_01_malas_dinheiro.jpg",
    "caso_02_protestos.jpg",
    "caso_03_vacina.jpg",
    "caso_04_vazamento.jpg",
    "cyber_security_background.webp",
    "diplomacia_01_fronteira.jpg",
    "diplomacia_02_embargo.jpg",
    "diplomacy_global.jpeg",
    "favicon.png",
    "mesa_negociacoes.jpg",
    "law_pictures.jpg",
    "pf_3.jpg",
    "placeholder.jpeg",
    "placeholder_advanced.png",
    "ponte_ciclopedonale.jpg",
    "projeto_01_ponte.jpg",
    "projeto_02_energia.jpg",
    "tribunal_com_relogio.png",
    "sala_tribunal.jpg",
    "button_background.xml",
    "edit_text_background.xml",
    "ic_home.xml",
    "ic_dashboard_black_24dp.xml",
    "ic_notifications_black_24dp.xml",
    "ic_launcher_background.xml",
    "ic_launcher_foreground.xml"
)
Check-Files -Path "$projectDir\app\src\main\res\drawable" -ExpectedFiles $drawableFiles -Section "res/drawable"

# Verificar res/layout
$layoutFiles = @(
    "activity_main.xml",
    "fragment_home.xml",
    "fragment_dashboard.xml",
    "fragment_notifications.xml"
)
Check-Files -Path "$projectDir\app\src\main\res\layout" -ExpectedFiles $layoutFiles -Section "res/layout"

# Verificar res/menu
$menuFiles = @("bottom_nav_menu.xml")
Check-Files -Path "$projectDir\app\src\main\res\menu" -ExpectedFiles $menuFiles -Section "res/menu"

# Verificar res/navigation
$navFiles = @("mobile_navigation.xml")
Check-Files -Path "$projectDir\app\src\main\res\navigation" -ExpectedFiles $navFiles -Section "res/navigation"

# Verificar res/values
$valuesFiles = @("colors.xml", "strings.xml", "themes.xml", "dimens.xml")
Check-Files -Path "$projectDir\app\src\main\res\values" -ExpectedFiles $valuesFiles -Section "res/values"

# Verificar res/font
$fontFiles = @("roboto_regular.ttf", "cinzel_regular.ttf")
Check-Files -Path "$projectDir\app\src\main\res\font" -ExpectedFiles $fontFiles -Section "res/font"

# Verificar res/anim
$animFiles = @("fade_in.xml", "fade_out.xml")
Check-Files -Path "$projectDir\app\src\main\res\anim" -ExpectedFiles $animFiles -Section "res/anim"

# Verificar java/com/seuapp/tribunalsupremopopular
$javaFiles = @(
    "MainActivity.kt",
    "HomeFragment.kt",
    "DashboardFragment.kt",
    "NotificationsFragment.kt"
)
Check-Files -Path "$projectDir\app\src\main\java\com\seuapp\tribunalsupremopopular" -ExpectedFiles $javaFiles -Section "java/com/seuapp/tribunalsupremopopular"

# Verificar viewmodel
$viewModelFiles = @("GameViewModel.kt")
Check-Files -Path "$projectDir\app\src\main\java\com\seuapp\tribunalsupremopopular\viewmodel" -ExpectedFiles $viewModelFiles -Section "java/com/seuapp/tribunalsupremopopular/viewmodel"

Write-Host "`nVerificação concluída!" -ForegroundColor Cyan