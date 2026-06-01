param (
    [string]$Action = "build"
)

$SrcDir = Join-Path $PSScriptRoot "src"
$BinDir = Join-Path $PSScriptRoot "bin"
$SourcesFile = Join-Path $PSScriptRoot "build_sources.txt"

switch ($Action) {
    "build" {
        if (-not (Test-Path $BinDir)) {
            New-Item -ItemType Directory -Path $BinDir -Force | Out-Null
        }
        Write-Host "Compiling to bin/..." -ForegroundColor Cyan
        javac -d $BinDir "@$SourcesFile"
        if ($LASTEXITCODE -eq 0) {
            Write-Host "Build successful! Binaries in bin/" -ForegroundColor Green
        }
    }
    "run" {
        $MainClass = "main.Main"
        if (-not (Test-Path (Join-Path $BinDir "main\Main.class"))) {
            & $PSScriptRoot\build.ps1 -Action build
        }
        Write-Host "Running..." -ForegroundColor Cyan
        java -cp $BinDir $MainClass
    }
    "clean" {
        if (Test-Path $BinDir) {
            Remove-Item -Recurse -Force $BinDir
            Write-Host "Cleaned bin/" -ForegroundColor Yellow
        }
    }
    default {
        Write-Host "Usage: .\build.ps1 [-Action build|run|clean]" -ForegroundColor White
    }
}
