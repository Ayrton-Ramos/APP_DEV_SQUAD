$z = Join-Path $env:TEMP 'gradle-8.4-bin.zip'
if (Test-Path $z) {
  Add-Type -AssemblyName System.IO.Compression.FileSystem
  $zip=[System.IO.Compression.ZipFile]::OpenRead($z)
  $zip.Entries | ForEach-Object { $_.FullName } | Where-Object { $_ -like '*gradle-wrapper*' -or $_ -like 'lib/*' } | Select-Object -First 200
  $zip.Dispose()
} else { Write-Output 'ZIP_NOT_FOUND' }
