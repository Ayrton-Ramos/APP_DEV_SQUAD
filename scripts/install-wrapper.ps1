# Powershell script to download Gradle distribution (8.4) and extract gradle-wrapper.jar
$version = '8.4'
$distUrl = "https://services.gradle.org/distributions/gradle-$version-bin.zip"
$tempZip = Join-Path $env:TEMP "gradle-$version-bin.zip"
$destDir = Join-Path (Get-Location) 'gradle\wrapper'
if (!(Test-Path $destDir)) { New-Item -ItemType Directory -Path $destDir -Force | Out-Null }
$destFile = Join-Path $destDir 'gradle-wrapper.jar'
Write-Host "Downloading $distUrl to $tempZip ..."
try {
    Invoke-WebRequest -Uri $distUrl -OutFile $tempZip -UseBasicParsing -ErrorAction Stop
    Write-Host "Download complete. Extracting wrapper jar..."
    Add-Type -AssemblyName System.IO.Compression.FileSystem
    $zip = [System.IO.Compression.ZipFile]::OpenRead($tempZip)
    $entry = $zip.Entries | Where-Object { $_.FullName -match 'lib/gradle-wrapper.*\\.jar$' } | Select-Object -First 1
    if ($null -eq $entry) { Write-Error "gradle wrapper jar not found inside zip"; $zip.Dispose(); Remove-Item $tempZip; exit 1 }
    $entry.ExtractToFile($destFile, $true)
    $zip.Dispose()
    Remove-Item $tempZip
    Write-Host "gradle-wrapper.jar extracted to $destFile"
} catch {
    Write-Error "Failed to download or extract gradle wrapper: $_"
    exit 1
}
