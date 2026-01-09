# Building and Publishing the THETA Client SDK

This guide explains how to publish the THETA Client SDK for Kotlin Multiplatform.

### Publish to GitHub Packages

#### Local Publishing (This is just if you want to test locally, ignore otherwise)

1. Create a `local.properties` file in the project root (this file is gitignored):

```properties
gpr.user=your-github-username
gpr.key=your-github-personal-access-token
```

2. Generate a GitHub Personal Access Token (PAT) with these scopes:
   - `read:packages`
   - `write:packages`

3. Run the publish command:

```bash
./gradlew :kotlin-multiplatform:publishAllPublicationsToGitHubPackagesRepository
```

#### CI/CD Publishing (GitHub Actions)

The SDK is automatically published to GitHub Packages when changes are pushed to the `main` branch.

The workflow is defined in `.github/workflows/publish-github-packages.yaml`:

- **Trigger**: Push to `main` branch or manual dispatch
- **Runner**: `macos-latest` (required for iOS targets)
- **Authentication**: Uses `GITHUB_TOKEN` automatically provided by GitHub Actions

To manually trigger a publish:
1. Go to Actions tab in GitHub
2. Select "Publish to GitHub Packages" workflow
3. Click "Run workflow"

### Published Artifacts

The following artifacts are published:

| Artifact ID | Description |
|-------------|-------------|
| `theta-client` | Kotlin Multiplatform metadata |
| `theta-client-android` | Android AAR |
| `theta-client-jvm` | JVM JAR |
| `theta-client-iosx64` | iOS x64 klib |
| `theta-client-iosarm64` | iOS ARM64 klib |
| `theta-client-iossimulatorarm64` | iOS Simulator ARM64 klib |

Browse all published artifacts at: https://github.com/danielclipnow/theta-client/packages



## Versioning

The SDK version is defined in `kotlin-multiplatform/build.gradle.kts`:

```kotlin
val thetaClientVersion = "1.13.4"
```

Update this value before publishing a new version.

## Common Errors

### Authentication errors

- **Local**: Verify `local.properties` has correct username and PAT
- **CI/CD**: The `GITHUB_TOKEN` is automatically provided; no configuration needed

### 409 Conflict

If the publish action fails with a **409 Conflict** error, it means an artifact with the same version already exists in GitHub Packages.

**Solution**: Increment the version number in `kotlin-multiplatform/build.gradle.kts`:

```kotlin
val thetaClientVersion = "1.13.5"  // bump the version
```

GitHub Packages does not allow overwriting existing versions. You must publish a new version.

## Using the Published SDK

### From GitHub Packages

Add the GitHub Packages repository to your project:

settings.gradle:
```kotlin 
repositories {
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/danielclipnow/theta-client")
        credentials {
            username = System.getenv("GITHUB_ACTOR") ?: "GITHUB_USERNAME"
            password = System.getenv("GITHUB_TOKEN") ?: "GITHUB_READ_ONLY_TOKEN"
        }
    }
}
```

libs.version.toml
```toml 
theta-client = { module = "com.ricoh360.thetaclient:kotlin-multiplatform", version.ref = "theta-client" }
```


