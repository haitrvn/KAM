import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.bundling.Jar
import org.gradle.kotlin.dsl.`maven-publish`

plugins {
    `maven-publish`
    signing
}

publishing {
    // Configure all publications
    publications.withType<MavenPublication> {
        // Stub javadoc.jar artifact
        artifact(tasks.register("${name}JavadocJar", Jar::class) {
            archiveClassifier.set("javadoc")
            archiveAppendix.set(this@withType.name)
        })

        // Provide artifacts information required by Maven Central
        pom {
            name.set("KAM")
            description.set("Kotlin AdMob for Compsoe Multiplatform")
            url.set("https://github.com/Kotlin/multiplatform-library-template")

            licenses {
                license {
                    name.set("Apache")
                    url.set("https://opensource.org/license/apache-2-0")
                }
            }
            developers {
                developer {
                    id.set("haitrvn")
                    name.set("Hai Tran")
                    organizationUrl.set("https://github.com/haitrvn")
                }
            }
            scm {
                url.set("https://github.com/haitrvn/KAM")
            }
        }
    }
}

signing {
    useGpgCmd()
    sign(publishing.publications)
}
