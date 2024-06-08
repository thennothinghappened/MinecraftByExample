import net.minecraftforge.gradle.userdev.UserDevExtension
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

plugins {
    id("net.minecraftforge.gradle")
}

dependencies {
    minecraft("net.minecraftforge:forge:1.12.2-14.23.5.2859")
}

version = "1.12.2b"
group = "thegreyghost"

configure<UserDevExtension> {

    mappings("stable",  "39-1.12")

    runs {
        create("client") {
            workingDirectory(project.file("run"))

            // Recommended logging data for a userdev environment
            property("forge.logging.markers", "SCAN,REGISTRIES,REGISTRYDUMP")

            // Recommended logging level for the console
            property("forge.logging.console.level", "debug")
        }

        create("server") {

            // Recommended logging data for a userdev environment
            property("forge.logging.markers", "SCAN,REGISTRIES,REGISTRYDUMP")

            // Recommended logging level for the console
            property("forge.logging.console.level", "debug")
        }
    }
}

tasks.withType<Jar> {

    archiveBaseName.set("minecraftbyexample-$version")

    manifest {
        attributes(
            "Specification-Title" to "minecraftbyexample",
            "Implementation-Title" to project.name,
            "Implementation-Version" to version,
            "Implementation-Timestamp" to LocalDateTime.now()
                .atOffset(ZoneOffset.UTC)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ"))
        )
    }

}

sourceSets.all {
    output.setResourcesDir(output.classesDirs.files.iterator().next())
}

