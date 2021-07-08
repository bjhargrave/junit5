import aQute.bnd.gradle.BundleTaskExtension;

plugins {
	`java-library-conventions`
	`junit4-compatibility`
	`testing-conventions`
}

description = "JUnit Jupiter Migration Support"

dependencies {
	api(platform(projects.junitBom))
	api(libs.junit4)
	api(projects.junitJupiterApi)

	compileOnlyApi(libs.apiguardian)

	testImplementation(projects.junitJupiterEngine)
	testImplementation(projects.junitPlatformLauncher)
	testImplementation(projects.junitPlatformRunner)
	testImplementation(projects.junitPlatformTestkit)
}

tasks.jar {
	configure<BundleTaskExtension> {
		bnd("""
			# Import JUnit4 packages with a version
			Import-Package: \
				${extra["importAPIGuardian"]},\
				org.junit;version="[${libs.versions.junit4Min.get()},5)",\
				org.junit.platform.commons.logging;status=INTERNAL,\
				org.junit.rules;version="[${libs.versions.junit4Min.get()},5)",\
				*
		""")
	}
}
